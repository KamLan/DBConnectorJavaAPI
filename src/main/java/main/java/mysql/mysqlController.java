package main.java.mysql;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import java.sql.*;

import static java.lang.System.out;

@RestController
public class mysqlController {

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/connect/{user}/{password}/{server}/{request}", method = RequestMethod.POST)
    public JSONArray mysqlUser(@PathVariable(value="user") String user,
                                @PathVariable(value="password") String password,
                                @PathVariable(value="server") String server,
                                @PathVariable(value="request") String request){
        out.print("Post triggered");
        request = request.replace("%20", " ");
        JSONArray JsonArray = mysqlConnector(user, password, server, request);
        return JsonArray;
    }

    @RequestMapping(value = "/connect/{user}/{password}/{server}", method = RequestMethod.GET)
    public mysql mysql(@PathVariable(value="user") String user,
                           @PathVariable(value="password") String password,
                           @PathVariable(value="server") String server,
                           @PathVariable(value="request") String request){
        return new mysql(user, password, server, request);
    }

    @RequestMapping(value = "/request", method = RequestMethod.GET)
    public JSONArray mysqlDisplayJSONArray(){
        String request= "select * from employees order by emp_no asc limit 10";
        JSONArray JsonArray = mysqlConnector("root", "secret", "172.17.0.2", request);
        return JsonArray;
    }

    public static JSONArray convertToJSON(ResultSet resultSet)
    {
        JSONArray jsonArray = new JSONArray();
        try{
            while (resultSet.next()) {
                int total_rows = resultSet.getMetaData().getColumnCount();
                JSONObject obj = new JSONObject();
                for (int i = 0; i < total_rows; i++) {
                    int j = 0;
                    obj.put(resultSet.getMetaData().getColumnLabel(i + 1)
                            .toLowerCase(), resultSet.getObject(i + 1));
                    j++;
                }
                jsonArray.add(obj);
            }
        }
        catch(Exception e){
        }
        return jsonArray;
    }

    public static void displaySQLrequest(ResultSet rs){
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = rs.getString(i);
                    System.out.print(columnValue + " " + rsmd.getColumnName(i));
                }
                System.out.println("");
            }
        }
        catch (Exception e){
            out.print("Error !");
        }
    }

    public JSONArray mysqlConnector(String user, String password, String server, String request){
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(password);
        dataSource.setServerName(server);
        JSONArray JsonArray = null;
        try {
            Statement st;
            ResultSet rs;
            Connection connection = (Connection) dataSource.getConnection();
            st = connection.createStatement();
            //st.executeUpdate("CREATE DATABASE coucou;");
            st.executeQuery("USE employees;");
            //rs = st.executeQuery("select * from employees order by emp_no asc limit 10;");
            rs = st.executeQuery(request);
            //displaySQLrequest(rs);
            //out.print(convertToJSON(rs));
            JsonArray = convertToJSON(rs);
            out.print("connected");
            connection.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return JsonArray;
    }

}
