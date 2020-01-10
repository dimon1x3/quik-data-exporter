package basicTypes;
import java.sql.*;
public class OraConnection {
    private String userName;
    private String userPass;
    private String hostName;
    private String hostPort;
    private String sidName;
    private String connectUrl;
    private Connection oraConnection;
    public OraConnection(String userName, String userPass, String hostName, String hostPort, String sidName){
        this.userName=userName;
        this.userPass=userPass;
        this.hostName=hostName;
        this.hostPort=hostPort;
        this.sidName=sidName;
        formConnectUrl();
        formConnection();
    }
    private void formConnectUrl(){
        connectUrl=new String("jdbc:oracle:thin:@"+hostName+":"+hostPort+":"+sidName);
    }
    private void formConnection(){
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            oraConnection=DriverManager.getConnection(connectUrl,userName,userPass);
        }
        catch (SQLException e) {
            System.err.println(e.getMessage( ));
            e.printStackTrace( );
        }
    }
    public Connection getConnection(){
        return oraConnection;
    }
    public void Close() throws SQLException {
        oraConnection.close();
    }
}

