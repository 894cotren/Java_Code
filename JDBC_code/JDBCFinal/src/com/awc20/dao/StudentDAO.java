package com.awc20.dao;

import com.awc20.bean.Student;

import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

public interface StudentDAO {
    BigInteger addStudent(Student s) throws SQLException;

    boolean updateStudent(Student s);

    boolean deleteStudentById(int id) throws SQLException;

    List<Student> getStudents() throws SQLException, NoSuchFieldException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException;

    Student getStudentById(int id) throws SQLException, NoSuchFieldException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException;
}
