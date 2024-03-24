package com.awc20.test;

import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class EasyBaseDaoTest {
    public static void main(String[] args) throws SQLException, NoSuchFieldException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        EasyBaseDao easyBaseDao = new EasyBaseDao();

        /*String sql="insert into student value(0,?,?,?,?)";
        BigInteger insert = easyBaseDao.insert(sql, "赵六", "15678789898", "杭州", "2002-07-09");
        System.out.println(insert);*/


        /*String sql="update student set addr=? where id=?";
        boolean ret = easyBaseDao.update(sql, "南京", 2);
        System.out.println(ret);*/

       /* String sql="select * from student where id>=?";
        List<Map<String, Object>> maps = easyBaseDao.queryToListMap(sql, 1);
        System.out.println(maps);*/

        String sql="select * from student where id>=?";
        Class<Student> studentClass = Student.class;
        List<Student> students = easyBaseDao.queryToListBean(sql, studentClass, 1);
        System.out.println(students);

    }
}
