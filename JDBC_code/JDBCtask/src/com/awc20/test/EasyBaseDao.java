package com.awc20.test;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EasyBaseDao {

    private Connection connection=null;
    private QueryRunner runner =new QueryRunner();
    public EasyBaseDao() throws SQLException {
        connection = ConnectionTool.getConnection();
    }
    //通过可变参数跟PreparedStatement可以设置占位符来创建一个通用的sql语句.
    public BigInteger insert(String sql, Object...objects) throws SQLException {
        BigInteger id = runner.insert(connection,sql, new ScalarHandler<BigInteger>(), objects);
        return id;
    }

    public boolean update(String sql, Object...objects) throws SQLException {
        int update = runner.update(connection, sql, objects);
        return update!=0;
    }

    public List<Map<String,Object>>queryToListMap(String sql, Object...objects) throws SQLException {

        return runner.query(connection,sql,new MapListHandler(),objects);

    }

    public <T>List<T> queryToListBean(String sql,Class<T> clz,Object...objects) throws SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        return runner.query(connection,sql,new BeanListHandler<>(clz),objects);
    }


    public <T>T queryToBean(String sql,Class<T> clz,Object...objects) throws SQLException, NoSuchFieldException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        List<T> list = queryToListBean(sql, clz, objects);
        if(list.size()>0){
            return list.get(0);
        }

        return null;
    }
}
