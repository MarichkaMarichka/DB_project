package store;

import service.Settings;

import java.sql.*;



public class UsersRepository {
    private final Connection connection;
    public UsersRepository(){
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
    public void addUser(String name,String password) {
        try {
            final PreparedStatement statement = this.connection.prepareStatement("insert into users (name,password,address) values ('" + name + "','"+password+"')");
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //-------------------------------------------------------------
    public void updateUser(String name , int id){
        try{
            final PreparedStatement statement = this.connection.prepareStatement("update users " +
                    "set name = '" + name + "' where user_id = " + id

            );
            statement.executeUpdate();
            statement.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public boolean searchUser(String name, String password){
        boolean exist = false;
        try{
            final Statement statement = this.connection.createStatement();
            final ResultSet rs = statement.executeQuery("select * from users where name = '"+name+"' and password = '"+password+"'");
            if (rs.next()){
                exist = true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return exist;
    }
    //----------------------------------------------
    public static void main(String []args) {
        UsersRepository storage = new UsersRepository();
        storage.addUser("1","1");
        System.out.println(storage.searchUser("m","m"));
    }
}
