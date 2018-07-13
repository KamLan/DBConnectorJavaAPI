package main.java.mysql;

public class postgreSQL {

    public String user;
    public String password;
    public String server;
    public String request;

    public postgreSQL(String user, String password, String server, String request){
        this.user = user;
        this.password = password;
        this.server = server;
        this.request = request;
    }
}
