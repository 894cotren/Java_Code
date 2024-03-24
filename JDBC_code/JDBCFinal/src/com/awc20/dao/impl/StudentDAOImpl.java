package com.awc20.dao.impl;

import com.awc20.bean.Student;
import com.awc20.dao.BaseDAO;
import com.awc20.dao.StudentDAO;

import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

public class StudentDAOImpl extends BaseDAO implements StudentDAO {
     public StudentDAOImpl() throws SQLException {
    }

    @Override
    public BigInteger addStudent(Student s) throws SQLException {
        return  insert("insert into student values(0,?,?,?,?)",s.getName(),s.getTelephone(),s.getAddr(),s.getDate());

    }

    @Override
    public boolean updateStudent(Student s) {
        return false;
    }

    @Override
    public boolean deleteStudentById(int id) throws SQLException {
        boolean update = update("delete from student where id=?", id);
        return update;
    }

    @Override
    public List<Student> getStudents() throws SQLException, NoSuchFieldException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
         return queryToListBean("select * from student",Student.class);
    }

    @Override
    public Student getStudentById(int id) throws SQLException, NoSuchFieldException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        return queryToBean("select * from student where id=?",Student.class,id);
    }

}
