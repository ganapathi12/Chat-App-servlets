package com.chat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DbConfig {

    public static Connection connect() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        final String URL = "jdbc:mysql://localhost/social_db";
        final String USER = "root";
        final String PWD = "7730098120Gana!";
        Connection con = DriverManager.getConnection(URL, USER, PWD);
        return con;
    }

    public static void updateStatus(int id,String status) throws Exception {
        Connection con = connect();
        con.createStatement().executeUpdate("update users set status='"+status+"' where id="+id);
        con.close();
    }
    
    public static Map<String, String> findSingle(String table, String condition) throws Exception {
        Connection con = connect();
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM " + table + " WHERE " + condition);
        ResultSetMetaData rsm = rs.getMetaData();
        int cols = rsm.getColumnCount();
        HashMap<String, String> map = new HashMap<String, String>();
        rs.next();
        for (int i = 1; i <= cols; i++) {
            map.put(rsm.getColumnName(i), rs.getString(i));
        }
        con.close();
        return map;
    }
    
    public static String findCount(String table) throws Exception {
        Connection con = connect();
        ResultSet rs = con.createStatement().executeQuery("SELECT count(*) FROM " + table);      
        rs.next();
        String result=rs.getString(1);
        con.close();
        return result;
    }

    public static List<Map<String, String>> allRecords(String table) throws Exception {
        Connection con = connect();
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM " + table);
        ResultSetMetaData rsm = rs.getMetaData();
        int cols = rsm.getColumnCount();
        List<Map<String, String>> list = new ArrayList<>();
        while (rs.next()) {
            Map<String,String> map=new HashMap<>();
            for (int i = 1; i <= cols; i++) {
                map.put(rsm.getColumnName(i), rs.getString(i));
            }
            list.add(map);
        }
        con.close();
        return list;
    }
    
    public static List<Map<String, String>> findall(String table,String condition) throws Exception {
        Connection con = connect();
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM " + table+" WHERE "+condition);
        ResultSetMetaData rsm = rs.getMetaData();
        int cols = rsm.getColumnCount();
        List<Map<String, String>> list = new ArrayList<>();
        while (rs.next()) {
            Map<String,String> map=new HashMap<>();
            for (int i = 1; i <= cols; i++) {
                map.put(rsm.getColumnName(i), rs.getString(i));
            }
            list.add(map);
        }
        con.close();
        return list;
    }
    
    public static Optional<String> getExtension(String filename) {
        return Optional.ofNullable(filename)
          .filter(f -> f.contains("."))
          .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }
}
