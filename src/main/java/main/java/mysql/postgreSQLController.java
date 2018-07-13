package main.java.mysql;

import net.minidev.json.JSONArray;
import org.springframework.web.bind.annotation.*;

import static java.lang.System.out;

@RestController
public class postgreSQLController {

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/connectPostgres/{user}/{password}/{server}/{type}/{request}", method = RequestMethod.POST)
    public JSONArray PostgreSQL(@PathVariable(value="user") String user,
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

}
