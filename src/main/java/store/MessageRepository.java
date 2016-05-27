package store;

import service.Settings;

import javax.xml.soap.Text;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MessageRepository {
    private final Connection connection;
    public MessageRepository(){
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        final Settings settings = Settings.getInstanse();
        try{
            this.connection = DriverManager.getConnection(settings.value("jdbc.url"), settings.value("jdbc.username"), settings.value("jdbc.password"));
        }catch (SQLException e){
            throw new IllegalStateException(e);
        }
    }

    //-------------------------------------------------------------
    public void outputMessage(String address, String text) {
        try {
            final Statement statement = this.connection.createStatement();
            final ResultSet rs = statement.executeQuery("select * from address where address = '"+address+"' ");
            rs.next();
            int id = rs.getInt(3);
            final PreparedStatement pStatement = this.connection.prepareStatement("insert into mail (to_user_id,text) values ('" + id + "','"+text+"')");
            pStatement.executeUpdate();
            pStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //-------------------------------------------------------------


    public List selectAllMessages(){
        List list = new ArrayList();
        try{
            final Statement statement = this.connection.createStatement();
            final ResultSet rs = statement.executeQuery("select * from mail ");
            while (rs.next())
            {
                int i = rs.getInt("to_user_id");
                list.add(i);
                String s = rs.getString("text");
                list.add(s);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
    //----------------------------------------------
    public List getMessages(String text){
        List list = new ArrayList();
        try{
            final Statement statement = this.connection.createStatement();
            final ResultSet rs = statement.executeQuery("select * from mail where text ='"+text+"'  ");
            if (rs.next())
            {
                int i = rs.getInt("to_user_id");
                list.add(i);
                String s = rs.getString("text");
                list.add(s);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
    //----------------------------------------------
    public static void main(String []args) {
        MessageRepository storage = new MessageRepository();
        storage.outputMessage("12","example");
        List list = storage.selectAllMessages();
        System.out.println(list.get(0));

    }
}
