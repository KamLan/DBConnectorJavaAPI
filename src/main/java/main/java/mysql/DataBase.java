package main.java.mysql;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import net.minidev.json.JSONArray;

import java.sql.*;
import com.mysql.jdbc.Connection;

import static java.lang.System.out;
import static main.java.mysql.Data.convertToJSON;

public class DataBase {

    public static JSONArray SQLConnector(String user, String password,
                                         String server, String port,
                                         String db, String table, String type,
                                         String request){

        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(password);
        dataSource.setServerName(server);
        JSONArray JsonArray = null;
        try {
            Statement st;
            ResultSet rs = null;
            PreparedStatement ps;
            String sqlUseDb = "USE "+db+";";
            Connection connection = (Connection) dataSource.getConnection();
            st = connection.createStatement();
            st.executeQuery(sqlUseDb);
            if (type.equals("UPDATE") || type.equals("INSERT")){
                ps = connection.prepareStatement(request);
                ps.executeUpdate();
            }
            if(type.equals("DELETE")){
                mysqlDelete(user,password,server,db,table);
            }
            else{
                rs = st.executeQuery(request);
                JsonArray = convertToJSON(rs);
            }
            connection.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return JsonArray;
    }

    public static JSONArray SQLInfo(String user, String password, String server, String port, String db, String table){

        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(password);
        dataSource.setServerName(server);
        JSONArray JsonArray = null;
        Statement st;
        ResultSet rs = null;
        PreparedStatement ps;
        String sqlUseDb = "USE "+db+";";
        String request = "describe "+table+";";
        try {
            Connection connection = (Connection) dataSource.getConnection();
            st = connection.createStatement();
            st.executeQuery(sqlUseDb);
            rs = st.executeQuery(request);
            JsonArray = convertToJSON(rs);
            System.out.println(JsonArray);
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return JsonArray;
    }

    public static String mysqlDelete(String user, String password, String server, String db, String request){
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(password);
        dataSource.setServerName(server);
        Statement st;
        String result = "";
        ResultSet rs = null;
        PreparedStatement ps;
        String sqlUseDb = "USE "+db+";";
        try {
            Connection connection = (Connection) dataSource.getConnection();
            st = connection.createStatement();
            st.executeQuery(sqlUseDb);
            ps = connection.prepareStatement(request);
            ps.executeUpdate();
            result = "delete is a success";
        } catch (SQLException e) {

            e.printStackTrace();
            result = "delete has failed";
        }
        return result;
    }

    public static JSONArray mysqlJson(String user, String password, String server, String db, String type, String request){
        JSONArray JsonArray = new JSONArray();
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(password);
        dataSource.setServerName(server);
        PreparedStatement ps;
        try{
            Connection connection = (Connection) dataSource.getConnection();
            ps = connection.prepareStatement(request);
            ps.executeUpdate();
            out.print(ps);
        }catch(Exception e){

        }
        return JsonArray;
    }

}
