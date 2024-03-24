package com.awc20.dao;

import com.awc20.tools.ConnectionTool;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class BaseDAO {

    private Connection connection=null;
    private QueryRunner runner =new QueryRunner();
    public BaseDAO() throws SQLException {
        connection = ConnectionTool.getConnection();
    }
    //通过可变参数跟PreparedStatement可以设置占位符来创建一个通用的sql语句.
   protected BigInteger insert(String sql, Object...objects) throws SQLException {
        BigInteger id = runner.insert(connection,sql, new ScalarHandler<BigInteger>(), objects);
        return id;
    }

    protected boolean update(String sql, Object...objects) throws SQLException {
        int update = runner.update(connection, sql, objects);
        return update!=0;
    }

    protected List<Map<String,Object>>queryToListMap(String sql, Object...objects) throws SQLException {

        return runner.query(connection,sql,new MapListHandler(),objects);

    }

    protected <T>List<T> queryToListBean(String sql,Class<T> clz,Object...objects) throws SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        return runner.query(connection,sql,new BeanListHandler<>(clz),objects);
    }


    protected <T>T queryToBean(String sql,Class<T> clz,Object...objects) throws SQLException, NoSuchFieldException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        List<T> list = queryToListBean(sql, clz, objects);
        if(list.size()>0){
            return list.get(0);
        }

        return null;
    }
}
