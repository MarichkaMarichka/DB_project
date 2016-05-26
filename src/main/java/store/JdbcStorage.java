package store;

import service.Settings;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcStorage {


    private final Connection connection;
    JdbcStorage(){
        //System.out.println("CLASSPATH = " + System.getProperty("java.class.path"));
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        final Settings settings = Settings.getInstanse();
        try{
            this.connection = DriverManager.getConnection(settings.value("jdbc.url"),settings.value("jdbc.username"),settings.value("jdbc.password"));
        }catch (SQLException e){
            throw new IllegalStateException(e);
        }
    }

}
