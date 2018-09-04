package main.java.mysql;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import net.minidev.json.JSONArray;

import java.sql.*;

import static main.java.mysql.Data.convertToJSON;

public class SQLServer {

    public static JSONArray SQLServerConnector(String user, String password,
                                               String server, String port, String db,
                                               String table, String type,
                                               String request){
        JSONArray JsonArray = null;

        System.out.println("SQL Server method triggered");

        String connectionUrl = "jdbc:sqlserver://"+server+":"+port+";databaseName="+db
                +";user="+user+";password="+password;

        //String connectionUrl = "jdbc:sqlserver://0.0.0.0:1433;databaseName=TestDB;user=SA;password=secretPw!1";

        try (Connection con = DriverManager.getConnection(connectionUrl);
             Statement stmt = con.createStatement();) {
            String SQL = request;
            ResultSet rs = stmt.executeQuery(SQL);

            JsonArray = convertToJSON(rs);

            if(type == "DELETE"){
                stmt.executeUpdate(SQL);
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return JsonArray;
    }

    public static JSONArray SQLServerInfo(String user, String password, String server, String port, String db, String table){

        JSONArray JsonArray = null;

        System.out.println("SQL Server info method triggered");

        String connectionUrl = "jdbc:sqlserver://"+server+":"+port+";databaseName="+db
        +";user="+user+";password="+password;

        //String connectionUrl = "jdbc:sqlserver://0.0.0.0:1433;databaseName=TestDB;user=SA;password=secretPw!1";

        String SQL  = "SELECT \n" +
                "    c.name 'Column Name',\n" +
                "    t.Name 'Data type',\n" +
                "    c.max_length 'Max Length',\n" +
                "    c.precision ,\n" +
                "    c.scale ,\n" +
                "    c.is_nullable,\n" +
                "    ISNULL(i.is_primary_key, 0) 'Primary Key'\n" +
                "FROM    \n" +
                "    sys.columns c\n" +
                "INNER JOIN \n" +
                "    sys.types t ON c.user_type_id = t.user_type_id\n" +
                "LEFT OUTER JOIN \n" +
                "    sys.index_columns ic ON ic.object_id = c.object_id AND ic.column_id = c.column_id\n" +
                "LEFT OUTER JOIN \n" +
                "    sys.indexes i ON ic.object_id = i.object_id AND ic.index_id = i.index_id\n" +
                "WHERE\n" +
                "    c.object_id = OBJECT_ID('"+table+"')";

        try (Connection con = DriverManager.getConnection(connectionUrl);
             Statement stmt = con.createStatement();) {
            //String SQL = "exec sp_help "+table;
            ResultSet rs = stmt.executeQuery(SQL);

            JsonArray = convertToJSON(rs);
            System.out.println(JsonArray);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return JsonArray;
    }
}
