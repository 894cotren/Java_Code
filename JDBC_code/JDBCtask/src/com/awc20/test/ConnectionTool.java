package com.awc20.test;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.lang.invoke.VarHandle;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionTool {
    private static DataSource dataSource;  //数据库连接池
    private static ThreadLocal<Connection> local = new ThreadLocal<>();

    //静态代码块创建仅一个数据库连接池
    static {
        try {
            Properties prop = new Properties();
            InputStream in = ConnectionTool.class.getClassLoader().getResourceAsStream("druid.properties");
            prop.load(in);
            dataSource = DruidDataSourceFactory.createDataSource(prop);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ConnectionTool() {

    }

    //通过数据库连接池返回一个链接对象.
    public static Connection getConnection() throws SQLException {
        Connection connection = local.get();
        if (connection == null) {
            connection = dataSource.getConnection();
            local.set(connection);
        }
        return connection;
    }

    public static void close() {
        Connection connection = local.get();
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                local.remove();
            }
        }
    }

}
