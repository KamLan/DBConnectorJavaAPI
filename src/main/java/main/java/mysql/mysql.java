package main.java.mysql;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class mysql {

    public String user;
    public String password;
    public String server;
    public String db;
    public String request;

    public mysql(String user, String password, String server, String db, String request){
        this.user = user;
        this.password = password;
        this.server = server;
        this.db = db;
        this.request = request;
    }

}
