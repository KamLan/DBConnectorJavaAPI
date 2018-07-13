package main.java.mysql;

import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.util.JSON;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.sun.xml.internal.fastinfoset.util.CharArray;
import net.minidev.json.JSONArray;

import java.io.CharArrayReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static java.lang.System.out;
import static main.java.mysql.Data.convertToJSON;

public class DataBase {

    public static JSONArray SQLConnector(String user, String password, String server, String db, String type, String request){

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
            if (type.equals("UPDATE")){
                ps = connection.prepareStatement(request);
                ps.executeUpdate();
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

    public static JSONArray SQLInfo(String user, String password, String server, String db, String table){

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
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return JsonArray;
    }

    public static JSONArray mysqlDelete(String user, String password, String server, String db, String request){
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(password);
        dataSource.setServerName(server);
        JSONArray JsonArray = null;
        Statement st;
        ResultSet rs = null;
        PreparedStatement ps;
        String sqlUseDb = "USE "+db+";";
        try {
            Connection connection = (Connection) dataSource.getConnection();
            st = connection.createStatement();
            st.executeQuery(sqlUseDb);
            ps = connection.prepareStatement(request);
            ps.executeUpdate();
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return JsonArray;
    }

    public static JSONArray MongoConnector(String user, String password, String server, String db, String type, String request){
        JSONArray JsonArray = new JSONArray();
        char[] pw = password.toCharArray();

        //MongoClient mongoClient = new MongoClient(new MongoClientURI(server));
        //MongoCredential credential = MongoCredential.createCredential(user, db, pw);
        //MongoDatabase database = mongoClient.getDatabase("db");


        return JsonArray;
    }

}
