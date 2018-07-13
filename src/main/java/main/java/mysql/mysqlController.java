package main.java.mysql;

import net.minidev.json.JSONArray;
import org.springframework.web.bind.annotation.*;
import static java.lang.System.out;

@RestController
public class mysqlController {

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/connect/{user}/{password}/{server}/{db}/{type}/{request}", method = RequestMethod.POST)
    public JSONArray mysqlUser(@PathVariable(value="user") String user,
                               @PathVariable(value="password") String password,
                               @PathVariable(value="server") String server,
                               @PathVariable(value="db") String db,
                               @PathVariable(value="type") String type,
                               @PathVariable(value="request") String request){
        out.print("Post triggered");
        request = request.replace("%20", " ");
        JSONArray JsonArray = DataBase.SQLConnector(user, password, server, db, type, request);
        if (type.equals("UPDATE")){
            return JsonArray;
        }
        else{
            return JsonArray;
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/getId/{table}/{db}/{user}/{password}/{server}", method = RequestMethod.POST)
    public JSONArray mysqlInfo(@PathVariable(value="user") String user,
                               @PathVariable(value="password") String password,
                               @PathVariable(value="server") String server,
                               @PathVariable(value="table") String table,
                               @PathVariable(value="db") String db){
        out.print("Post triggered");
        JSONArray JsonArray = DataBase.SQLInfo(user, password, server, db, table);
        return JsonArray;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/delete/{db}/{user}/{password}/{server}/{request}", method = RequestMethod.POST)
    public JSONArray mysqlDelete(@PathVariable(value="user") String user,
                                 @PathVariable(value="password") String password,
                                 @PathVariable(value="server") String server,
                                 @PathVariable(value="request") String request, 
                                 @PathVariable(value="db") String db){
        out.print("Post triggered");
        JSONArray JsonArray = DataBase.mysqlDelete(user, password, server, db, request);
        return JsonArray;
    }

    @RequestMapping(value = "/connect/{user}/{password}/{server}/{request}", method = RequestMethod.GET)
    public mysql mysql(@PathVariable(value="user") String user,
                           @PathVariable(value="password") String password,
                           @PathVariable(value="server") String server,
                           @PathVariable(value="db") String db,
                           @PathVariable(value="request") String request){
        return new mysql(user, password, server, db, request);
    }

    @RequestMapping(value = "/request", method = RequestMethod.GET)
    public JSONArray mysqlDisplayJSONArray(){
        String request= "select * from employees order by emp_no asc limit 10";
        JSONArray JsonArray = DataBase.SQLConnector("root", "secret", "172.17.0.2", "employees", "select", request);
        return JsonArray;
    }



}
