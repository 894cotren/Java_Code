package com.awc20.test;

import java.sql.Connection;
import java.sql.SQLException;

public class ToolTest {
    public static void main(String[] args) throws SQLException {
        Connection c1 = ConnectionTool.getConnection();
        Connection c2 = ConnectionTool.getConnection();
        System.out.println(c1==c2);
    }
}
