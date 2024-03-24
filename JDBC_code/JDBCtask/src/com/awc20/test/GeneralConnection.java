package com.awc20.test;

import java.sql.*;

public class GeneralConnection {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/test";
        String name="root";
        String password="23321212";
        Connection connection = DriverManager.getConnection(url, name, password);
/*        String sql="insert into student value(0,'李四','13332041234','乌鲁木齐','1991-07-24')";
        int i = statement.executeUpdate(sql);*/

        PreparedStatement pst = connection.prepareStatement("select * from student where id=?");

        pst.setString(1,"2");
        ResultSet resultSet = pst.executeQuery();
        while (resultSet.next()){
            Object ob1 = resultSet.getObject(1);
            Object ob2 = resultSet.getObject(2);
            Object ob3 = resultSet.getObject(3);
            Object ob4 = resultSet.getObject(4);
            Object ob5 = resultSet.getObject(5);
            System.out.println("id="+ob1+" name="+ob2+" telephone="+ob3+" addr="+ob4+" birthday="+ob5);
        }


        resultSet.close();
        pst.close();
        connection.close();
    }
}
