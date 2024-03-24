package com.awc20.test;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResultSetListBean {
    public static void main(String[] args) throws SQLException, NoSuchFieldException, IllegalAccessException {
        Connection connection = ConnectionTool.getConnection();
        String sql="select * from student";
        PreparedStatement pst = connection.prepareStatement(sql);
        ResultSet resultSet = pst.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();//获取到元数据
        int columnCount = metaData.getColumnCount(); //从元数据中获取到字段数量

        List<Student> list = new ArrayList<>();
        while (resultSet.next()){
            Student student = new Student();
            for (int i = 1; i <= columnCount; i++) {
                // String columnName = metaData.getColumnName(i); //从元数据中获取到字段名

                /*metaData.getColumnLabel(i)
                从元数据中获取到字段别名(sql语句里面自定义的别名),如果没有指定别名就使用默认别名
                这样就能解决因为mysql里命名规则跟java里不一致导致的 属性名跟字段名不一样的问题.
                * */
                String columnLabel = metaData.getColumnLabel(i);
                //通过利用字段名和反射给对象设置值,这样方便,不然只有一个个手打设置
                Field field = Student.class.getDeclaredField(columnLabel);
                field.setAccessible(true);
                Object value = resultSet.getObject(i);
                field.set(student,value);
            }
            list.add(student);
        }

        System.out.println(list);

    }
}
