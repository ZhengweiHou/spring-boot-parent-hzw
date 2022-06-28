package com.hzw.learn.springboot.springbase.Transaction.isolation;

import org.junit.Test;

import java.sql.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * @ClassName ACIDTest
 * @Description TODO
 * @Author houzw
 * @Date 2022/4/24
 **/

public class ACIDTest {
    static String jdbcUrl = "jdbc:mysql://localhost:3306/test";


    @Test
    public void simpleTest() throws SQLException {
        Connection con = openConnection();
        con.setAutoCommit(false);
        con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        _insert(1, "1", con);
        con.commit();
        _list(con);
        _delete(1, con);
        con.commit();
        con.close();
    }


    @Test   // 脏读、不可重复读测试
    public void IsolationLevelTest1() throws Exception {
        Connection coninit = openConnection();
        coninit.setAutoCommit(false);
        _insert(99,"99",coninit);
        coninit.commit();
        Thread.sleep(10);

        CyclicBarrier cb = new CyclicBarrier(2); // 循环障碍

        new Thread(()->{
            try {
                Connection con = openConnection();
                con.setAutoCommit(false); //关闭自动提交，使下面的查询都处于同一个事务中
                con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);   // 未提交读模式（可能脏读）
//                con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);   // 已提交读模式（不可重复读）
//                con.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);    // 可重复读模式（可能幻读）
                _list(con);    // 第一次查询
                cb.await();
                cb.await();
                _list(con);    // (检查脏读）第二次查询，更新语句执行但事务没提交
                cb.await();
                _list(con);    // (检查不可重复读）第三次查询，更新事务已提交
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                cb.await();
                Connection con = openConnection();
                con.setAutoCommit(false);
                _update(99,"999",con);
                cb.await();
                con.commit();
                cb.await();
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }
        }).start();

        Thread.sleep(1000);

        _delete(99,coninit);
        coninit.commit();
    }


    @Test // 幻读测试
    public void IsolationLevelTest2() throws Exception {
        CountDownLatch cd = new CountDownLatch(1);
        for (int i=0;i<2;i++) {
            new Thread(() -> {
                try {
                    Connection con = openConnection();
                    con.setAutoCommit(false); //关闭自动提交，使下面的查询都处于同一个事务中
//                    con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);   // 未提交读模式（可能脏读）
                    con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);   // 串行化可以防止幻读，其他模式都可能幻读。此时就没法并发了，下面insert的并发会有一个是异常的
                    cd.await();
                    // 查询，不存在时插入逻辑（验证幻读)
                    int id = _query(999, con);
                    Thread.sleep(100);
                    if (id == 0) {
                        _insert(999, "thread-"+ Thread.currentThread().getId(), con);
                    }
                    con.commit();
                } catch (Exception throwables) {
                    throwables.printStackTrace();
                }
            }).start();
        }
        Thread.sleep(100);
        cd.countDown();
        Thread.sleep(500);

        _query(999,openConnection()); // 验证
        _delete(999,openConnection()); // 清理

    }




    public static void _insert(int id, String name, Connection con) {
        try {
            Statement stat = con.createStatement();
            stat.execute("INSERT INTO test (id, name) VALUES (" + id + ", '" + name + "');");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void _update(int id, String name, Connection con) {
        try {
            Statement stat = con.createStatement();
            stat.execute("update test set name='"+name+"' where id = " + id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void _list(Connection con) {
        try {
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery("select * from test");

            while (rs.next()) {
                // 获取每列的数据,使用的是ResultSet接口的方法getXXX
                int id = rs.getInt("id");
                String name = rs.getString("name");
                System.out.println(id + "\t" + name);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public static int _query(int id, Connection con) {
        int sid=0;
        try {
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery("select * from test where id = " + id);
            int size = 0;
            while (rs.next()) {
                // 获取每列的数据,使用的是ResultSet接口的方法getXXX
                sid = rs.getInt("id");
                String name = rs.getString("name");
                System.out.print("[id:" + id + ",name:" + name +"],");
                size ++;
            }
            System.out.println("总数量：" + size);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return sid;
    }

    public static void _delete(int id, Connection con) {
        try {
            con.createStatement().execute("delete from test where id='" + id + "'");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static Connection openConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            con = DriverManager.getConnection(jdbcUrl, "root", "root");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}
