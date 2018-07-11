package main.java.mysql;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class mysql {

    public String user;
    public String password;
    public String server;
    public String request;

    public mysql(String user, String password, String server, String request){
        this.user = user;
        this.password = password;
        this.server = server;
        this.request = request;
    }

    public void mysqlConnector(String user, String password, String server, String request){
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(password);
        dataSource.setServerName(server);
    }

}
