package com.awc20.test;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//执行通用的sql语句
public class BaseDAO {
    /**
     *
     * @param sql 需要执行的sql语句
     * @param objects   sql语句里面的参数
     * @return  表示修改插入是否成功
     * @throws SQLException
     */
    private Connection connection=null;
    public BaseDAO() throws SQLException {
        connection = ConnectionTool.getConnection();
    }
    //通过可变参数跟PreparedStatement可以设置占位符来创建一个通用的sql语句.
    public boolean update(String sql,Object...objects) throws SQLException {

        PreparedStatement pst= connection.prepareStatement(sql);
        for (int i = 0; i < objects.length; i++) {
            pst.setObject(i+1,objects[i]);
        }
        int i = pst.executeUpdate();
        return i!=0;
    }

    /**
     *将查询的结果封装成一个map对象放进list集合里
     * @param sql sql查询语句
     * @param objects sql语句参数
     * @return 返回一个查询结果集合
     * @throws SQLException
     */
    public List<Map<String,Object>>queryToListMap(String sql,Object...objects) throws SQLException {
        PreparedStatement pst = connection.prepareStatement(sql);
        for (int i = 0; i < objects.length; i++) {
            pst.setObject(i+1,objects[i]);
        }
        ResultSet resultSet = pst.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        List<Map<String,Object>> list= new ArrayList<>();
        while (resultSet.next()){
            Map<String,Object> map=new HashMap<>();
            for (int i = 0; i < columnCount; i++) {
                String columnLabel = metaData.getColumnLabel(i+1);
                Object object = resultSet.getObject(i+1);
                map.put(columnLabel,object);
            }
            list.add(map);
        }

        return  list;

    }


    /**
     * 将查询到的结果转换为JavaBean类型的list集合
     * 这里有个问题是泛型的对象实例怎么创建,  这里用的是clz参数传入类对象,调用类对象的方法创建泛型实例.
     * @param sql 查询的sql语句
     * @param clz 类对象
     * @param objects sql参数
     * @param <T> 对象类型
     * @return 返回list集合
     * @throws SQLException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws NoSuchFieldException
     */
    public <T>List<T> queryToListBean(String sql,Class<T> clz,Object...objects) throws SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        PreparedStatement pst = connection.prepareStatement(sql);
        for (int i = 0; i < objects.length; i++) {
            pst.setObject(i+1,objects[i]);
        }
        ResultSet resultSet = pst.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        List<T> list =new ArrayList<>();
        while (resultSet.next()){
            T t = clz.newInstance();
            for (int i = 0; i < columnCount; i++) {

                String columnLabel = metaData.getColumnLabel(i + 1);
                Object value = resultSet.getObject(i + 1);
                Field field = clz.getDeclaredField(columnLabel);
                field.setAccessible(true);
                field.set(t,value);
            }

            list.add(t);
        }

        return  list;
    }
}
