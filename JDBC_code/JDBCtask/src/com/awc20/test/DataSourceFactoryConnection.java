package com.awc20.test;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class DataSourceFactoryConnection {
    public static void main(String[] args) throws Exception {
        Properties prop=new Properties();
        InputStream in = DataSourceFactoryConnection.class.getClassLoader().getResourceAsStream("druid.properties");
        prop.load(in);
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }
}
