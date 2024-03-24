package com.awc20.test;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class BaseDaoTest {
    public static void main(String[] args) throws SQLException, NoSuchFieldException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        BaseDAO baseDAO = new BaseDAO();
        /*String sql="insert into student value(0,?,?,?,?)";
        boolean insert = baseDAO.update(sql,"赵六","14487896767","福建","2001-05-24");
        System.out.println(insert);*/

        /*String sql="delete from student where id=?";
        boolean success = baseDAO.update(sql, 4);
        System.out.println(success);*/

        /*String sql="select * from student where id>=?";
        List<Map<String, Object>> list = baseDAO.queryToListMap(sql, 0);
        System.out.println(list);*/

        String sql="select * from student where id=?";
        Class<Student> studentClass = Student.class;
        List<Student> list = baseDAO.queryToListBean(sql, studentClass, 2);
        System.out.println(list);



    }
}
