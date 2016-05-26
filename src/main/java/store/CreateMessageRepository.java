package store;

import org.postgresql.util.PGTimestamp;
import service.Settings;

import java.sql.*;


public class CreateMessageRepository {
    private final Connection connection;
    public CreateMessageRepository(){
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
    public void addMessage(String name) {
        try {
            final PreparedStatement statement = this.connection.prepareStatement("insert into  () values ('" + name + "')");
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //-------------------------------------------------------------
    public void update(int vatin,double duration, PGTimestamp start_time){
        try{
            final PreparedStatement statement = this.connection.prepareStatement("update sleep_record " +
                    "set vatin = " + vatin +
                    " where duration = " + duration

            );
            statement.executeUpdate();
            statement.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    //------------------------------------------------------------
    public boolean isExist(int dur){
        boolean exist = false;
        try{
            final Statement statement = this.connection.createStatement();
            final ResultSet rs = statement.executeQuery("select * from sleep_record where duration = "+dur);
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
        CreateMessageRepository storage = new CreateMessageRepository();
        long curTime = System.currentTimeMillis();
        PGTimestamp curDate = new PGTimestamp(curTime);
        // System.out.println(curDate);
        storage.addMessage("Bob");

    }
}
