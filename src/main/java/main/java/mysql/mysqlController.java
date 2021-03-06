package main.java.mysql;

import net.minidev.json.JSONArray;
import org.springframework.web.bind.annotation.*;
import static java.lang.System.out;

@RestController
public class mysqlController {

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/connect/mysql/{user}/{password}/{server}/{port}/{db}/{table}/{type}/{request}"
            , method = RequestMethod.POST)
    public JSONArray mysqlUser(@PathVariable(value="user") String user,
                               @PathVariable(value="password") String password,
                               @PathVariable(value="server") String server,
                               @PathVariable(value="port") String port,
                               @PathVariable(value="db") String db,
                               @PathVariable(value="table") String table,
                               @PathVariable(value="type") String type,
                               @PathVariable(value="request") String request){
        out.print("Post triggered select/update");
        request = request.replace("%20", " ");
        JSONArray JsonArray = DataBase.SQLConnector(user, password, server, port, db, table, type, request);
       return JsonArray;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/connect/sqlserver/{user}/{password}/{server}/{port}/{db}/{table}/{type}/{request}"
            , method = RequestMethod.POST)
    public JSONArray sqlServer(@PathVariable(value="user") String user,
                               @PathVariable(value="password") String password,
                               @PathVariable(value="server") String server,
                               @PathVariable(value="port") String port,
                               @PathVariable(value="db") String db,
                               @PathVariable(value="table") String table,
                               @PathVariable(value="type") String type,
                               @PathVariable(value="request") String request){
        request = request.replace("%20", " ");
        JSONArray JsonArray = SQLServer.SQLServerConnector(user, password, server, port, db, table, type, request);
        return JsonArray;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/json/{user}/{password}/{server}/{port}/{db}/{table}/{type}/{request}", method = RequestMethod.POST)
    public JSONArray mysqlJson(@PathVariable(value="user") String user,
                               @PathVariable(value="password") String password,
                               @PathVariable(value="server") String server,
                               @PathVariable(value="port") String port,
                               @PathVariable(value="db") String db,
                               @PathVariable(value="table") String table,
                               @PathVariable(value="type") String type,
                               @PathVariable(value="request") String request){
        out.print("Post triggered select/update");
        request = request.replace("%20", " ");
        JSONArray JsonArray = DataBase.SQLConnector(user, password, server, port, db, table, type, request);
        return JsonArray;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/getId/mysql/{table}/{db}/{user}/{password}/{server}/{port}", method = RequestMethod.POST)
    public JSONArray mysqlInfo(@PathVariable(value="user") String user,
                               @PathVariable(value="password") String password,
                               @PathVariable(value="server") String server,
                               @PathVariable(value="port") String port,
                               @PathVariable(value="table") String table,
                               @PathVariable(value="db") String db){
        out.print("Post triggered get id");
        JSONArray JsonArray = DataBase.SQLInfo(user, password, server, port, db, table);
        return JsonArray;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/getId/sqlserver/{table}/{db}/{user}/{password}/{server}/{port}", method = RequestMethod.POST)
    public JSONArray sqlServerInfo(@PathVariable(value="user") String user,
                               @PathVariable(value="password") String password,
                               @PathVariable(value="server") String server,
                               @PathVariable(value="port") String port,
                               @PathVariable(value="table") String table,
                               @PathVariable(value="db") String db){
        out.print("Post triggered get id");
        JSONArray JsonArray = SQLServer.SQLServerInfo(user, password, server, port, db, table);
        return JsonArray;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/delete/{db}/{user}/{password}/{server}/{request}", method = RequestMethod.POST)
    public String mysqlDelete(@PathVariable(value="user") String user,
                                 @PathVariable(value="password") String password,
                                 @PathVariable(value="server") String server,
                                 @PathVariable(value="request") String request,
                                 @PathVariable(value="db") String db){
        out.print("Post triggered delete");
        request = request.replace("%20", " ");
        String result = DataBase.mysqlDelete(user, password, server, db, request);
        return result;
    }

    @RequestMapping(value = "/connect/{user}/{password}/{server}/{request}", method = RequestMethod.GET)
    public mysql mysql(@PathVariable(value="user") String user,
                           @PathVariable(value="password") String password,
                           @PathVariable(value="server") String server,
                           @PathVariable(value="db") String db,
                           @PathVariable(value="request") String request){
        return new mysql(user, password, server, db, request);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/request", method = RequestMethod.GET)
    public JSONArray mysqlDisplayJSONArray(){
        String request= "select * from employees order by emp_no asc limit 10";
        JSONArray JsonArray = DataBase.SQLConnector("root2", "secret", "localhost", "3306", "employees", "select", "SELECT",request);
        return JsonArray;
    }



}
