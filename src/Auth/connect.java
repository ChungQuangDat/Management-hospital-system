package Auth;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class connect {
    public Connection connection;
    public Statement statement;


    public connect(){
       try {
         connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management_system","root","quangdat2005@#+");
         statement = connection.createStatement();
       } catch (Exception e){
           e.printStackTrace();
       }
    }
}
