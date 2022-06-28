package com.hzw.learn.springboot.springbase.Transaction.isolation;

import org.junit.Test;

import java.sql.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * @ClassName ACIDTest2
 * @Description TODO
 * @Author houzw
 * @Date 2022/4/24
 **/

public class ACIDTest2 {
    static String jdbcUrl = "jdbc:mysql://localhost:3306/test";


    @Test
    public void test1() {
        Connection con = openConnection();
        _query(1, con);
        _update(1, con);
        _query(1, con);
    }


    @Test
    public void test2() throws Exception {
        CountDownLatch endcd = new CountDownLatch(2);
        CountDownLatch cd1 = new CountDownLatch(1);
        CountDownLatch cd2 = new CountDownLatch(1);
        CountDownLatch cd3 = new CountDownLatch(1);
        CountDownLatch cd4 = new CountDownLatch(1);
        CountDownLatch cd5 = new CountDownLatch(1);

        CyclicBarrier cb = new CyclicBarrier(2); // 循环障碍
        new Thread(() -> {
            try {
                cd1.await();
                Connection con = openConnection();
                con.setAutoCommit(false); //关闭自动提交，使下面的查询都处于同一个事务中
                con.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);   // 可重复读

                cd2.await();
                _query(1, con);
                cb.await();
                cd3.await();
                _query(1, con); // t2 update 后查
                _update(1, con);
                cb.await();
                _query(1, con);
                cb.await();
                con.commit();
            } catch (Exception e) {
            } finally {
                endcd.countDown();
            }
        },"t1").start();

        new Thread(() -> {
            try {
                cd1.await();
                Connection con = openConnection();
                con.setAutoCommit(false); //关闭自动提交，使下面的查询都处于同一个事务中
                con.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);   // 可重复读

                cd2.await();
                _query(1, con);
                cb.await();
                _update(1, con);
                cd3.countDown();
                cb.await();
                _query(1, con);
                cb.await();
                con.commit();
            } catch (Exception e) {
            } finally {
                endcd.countDown();
            }
        },"t2").start();

        Thread.sleep(500);
        cd1.countDown();

        Thread.sleep(500);
        cd2.countDown();
        endcd.await();


    }


    @Test // 幻读测试
    public void IsolationLevelTest2() throws Exception {
        CountDownLatch cd1 = new CountDownLatch(1);
        CountDownLatch cd2 = new CountDownLatch(1);
        CountDownLatch cd3 = new CountDownLatch(1);
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                try {
                    cd1.await();
                    Connection con = openConnection();
                    con.setAutoCommit(false); //关闭自动提交，使下面的查询都处于同一个事务中
                    con.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);   // 可重复读

                    cd2.await();
                    _query(1, con);

                    System.out.println(Thread.currentThread().getName() + "===============");
                    _update(1, con);

                    System.out.println(Thread.currentThread().getName() + "----------------");
                    _query(1, con);
//                    cd3.await();

                    con.commit();
                } catch (Exception e) {
                    System.out.println(Thread.currentThread().getName() + "error");
                    e.printStackTrace();
                }
            }).start();
        }

        Thread.sleep(1000);
        cd1.countDown();

        Thread.sleep(1000);
        cd2.countDown();

        Thread.sleep(1000);
        cd3.countDown();

    }


    public static void _update(int id, Connection con) {
        try {
            Statement stat = con.createStatement();
            stat.execute("update test set age=age + 5 where id =" + id);
            System.out.println(Thread.currentThread().getName() + " update");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public static int _query(int id, Connection con) {
        int size = 0;
        try {
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery("select * from test where id = " + id);

            while (rs.next()) {
                // 获取每列的数据,使用的是ResultSet接口的方法getXXX
                int sid = rs.getInt("id");
                int age = rs.getInt("age");
                System.out.print(Thread.currentThread().getName() + "[id:" + id + ",age:" + age + "]," );
                size++;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("");
        return size;
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
