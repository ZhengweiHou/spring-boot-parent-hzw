package com.hzw.learn.springboot.springbase.Transaction.txbase;

import org.junit.Test;
import org.springframework.stereotype.Component;

import java.sql.*;

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
        _query(con);
        _delete(1, con);
        con.commit();
        con.close();
    }


    @Test
    // 未提交读模式 可以脏读
    public void ReadUncommittedTest() throws SQLException, InterruptedException {

        // Thread 1
        new Thread(() -> {
            try {
                Connection con = openConnection();
                _insert(1,"1",con);
                Thread.sleep(1000);
                con.rollback();
//                con.commit();
                con.close();
            } catch (Exception e) { e.printStackTrace(); }
        }).start();

        // Thread 2
        new Thread(() -> {
            try {
                Connection con = openConnection();
                con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED); // 未提交读模式
                Thread.sleep(10);
                _query(con);    // 此处可以读取到上面线程未提交的事务
                con.close();
            } catch (Exception e) { e.printStackTrace(); }
        }).start();

        Thread.sleep(1000);
    }





    public static void _insert(int id, String name, Connection con) {
        try {
            Statement stat = con.createStatement();
            stat.execute("INSERT INTO test (id, name) VALUES (" + id + ", '" + name + "');");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void _query(Connection con) {
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
            con.setAutoCommit(false);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}
