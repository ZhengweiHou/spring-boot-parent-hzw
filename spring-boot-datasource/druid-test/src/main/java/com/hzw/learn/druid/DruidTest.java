package com.hzw.learn.druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.google.gson.Gson;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Properties;

/**
 * @ClassName DruidTest
 * @Description TODO
 * @Author houzw
 * @Date 2023/2/15
 **/
public class DruidTest {
    public static DruidDataSource getDataSource(){
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/test");
        ds.setUsername("root");
        ds.setPassword("root");

        ds.setKeepAlive(true);
        ds.setPhyTimeoutMillis(25200000);
        return ds;
    }

    public static Connection openConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public static void main(String[] args) throws SQLException, InterruptedException {
//        DruidDataSource ds = getDataSource();
//        Connection con = ds.getConnection();
        Connection con = openConnection();
        con.setAutoCommit(false);
        System.out.println("update");
        _update(1,"hhh",con);

        int sleep = 60 * 31;
//        int sleep = 40;
        for (int i=0; i< sleep; i++){
            if (i%10 == 0) {
                System.out.println("" + i + "/" + sleep);
//                System.out.println("" + i + "/" + sleep + ":" + new Gson().toJson(ds.getStatData()));
            }
            Thread.sleep(1000l);
        }

        System.out.println("query");
        _query(1,con);
        con.commit();
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

    public static void _update(int id, String name, Connection con) {
        try {
            Statement stat = con.createStatement();
            stat.execute("update test set name='"+name+"' where id = " + id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
