package com.awc20.test;

import com.awc20.bean.Student;
import com.awc20.dao.impl.StudentDAOImpl;

import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Testdao {
    public static void main(String[] args) throws SQLException, ParseException, NoSuchFieldException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        StudentDAOImpl studentDAO = new StudentDAOImpl();
        /*SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse("1995-02-28");
        Student student = new Student("托尼","17780848392","甘肃",date);
        BigInteger bigInteger = studentDAO.addStudent(student);
        System.out.println(bigInteger);*/

        /*List<Student> students = studentDAO.getStudents();
        System.out.println(students);*/

        Student student = studentDAO.getStudentById(2);
        System.out.println(student);

    }
}
