package main.java.mysql;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Data {
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
}
