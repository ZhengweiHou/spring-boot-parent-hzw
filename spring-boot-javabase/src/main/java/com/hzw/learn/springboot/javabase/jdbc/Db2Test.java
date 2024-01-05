package com.hzw.learn.springboot.javabase.jdbc;

import org.junit.Test;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.*;

/**
 * @ClassName Db2Test
 * @Description TODO
 * @Author houzw
 * @Date 2023/10/16
 **/

public class Db2Test {
//    static String jdbcUrl = "jdbc:mysql://localhost:3306/test";
//    static String jdbcUrl = "jdbc:db2://localhost:50003/testdb:currentSchema=TEST;";
    static String jdbcUrl = "jdbc:db2://localhost:50003/testdb:clientEncoding=GB18030;";
    static String user = "db2inst1";
    static String password = "db2inst1";

    public static void main(String[] args) {
//        test1();
    }


    @Test
    public void test1(){
        try {
            Class.forName("com.ibm.db2.jcc.DB2Driver");
            Connection connection = DriverManager.getConnection(jdbcUrl, user, password);

            String insertQuery = "INSERT INTO test.tt2 (id, name) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

            // 设置要插入的值
            int id = 3;
            String name = "㐀";
            System.out.println("defaultCharset:" +Charset.defaultCharset());
            System.out.println("gb18030:" + bytes2HexStr(name.getBytes("GB18030")));
            System.out.println("utf8:" + bytes2HexStr(name.getBytes("utf8")));
            System.out.println(bytes2HexStr(name.getBytes()));

            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Insert successful. Rows affected: " + rowsAffected);
            } else {
                System.out.println("Insert failed.");
            }

            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() throws Exception {
//        String jdbcUrl = "jdbc:db2://localhost:50003/testdb:currentSchema=TEST";

        try {
            Class.forName("com.ibm.db2.jcc.DB2Driver");
            Connection connection = DriverManager.getConnection(jdbcUrl, user, password);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM test.tt2");

            System.out.println("defaultCharset:" +Charset.defaultCharset());
            while (resultSet.next()) {
                int id      = resultSet.getInt("id");
                String name = resultSet.getString("name");

                System.out.println("gb18030:" + bytes2HexStr(name.getBytes("GB18030")));
                System.out.println("utf8:" + bytes2HexStr(name.getBytes("utf8")));
                System.out.println("gbk:" + bytes2HexStr(name.getBytes("gbk")));
//                System.out.println(bytes2HexStr(name.getBytes()));

                System.out.println("ID: " + id + ", Name: " + name);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String bytes2HexStr(byte[] bytes) {
        StringBuilder hexString = new StringBuilder(2 * bytes.length);

        for (byte b : bytes) {
            String hex = Integer.toHexString(0xFF & b);
            if (hex.length() == 1) {
                // Ensure each byte is represented by two characters in the hexadecimal string
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }


}
