package immunehistory.User;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ConnectMSSQL {
    
    public Connection connection;
    ConnectMSSQL()
    {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://192.168.0.10:1433;databaseName=ImmuneHistory;selectMethod=cursor", "sa", "123456");

            System.out.println("DATABASE NAME IS:" + connection.getMetaData().getDatabaseProductName());

         
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
            
    public void connectDB() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://192.168.0.10:1433;databaseName=ImmuneHistory;selectMethod=cursor", "sa", "123456");

            System.out.println("DATABASE NAME IS:" + connection.getMetaData().getDatabaseProductName());

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT user_name FROM user_table");
            
            
            while (resultSet.next()) {
                
                System.out.println("Customer NAME:" + 
                        resultSet.getString("user_name"));
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    int  rowList(String sql)
    {
       int rowNum=0;
         try {
            

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery(sql);
            
              while (resultSet.next()) {
                
              
                rowNum=Integer.parseInt(resultSet.getString("row"));
                
            }
                
            }
            
            
            catch (Exception e) {
            e.printStackTrace();
        }
        return rowNum;
    }
    public Object[] showAppointment(String sql) {
         ArrayList<String> SLno = new ArrayList<String>();
   
     ArrayList<String> ConsultId = new ArrayList<String>();
   
     ArrayList<String> DoctorName = new ArrayList<String>();
     
     ArrayList<String>  AppointmentDate= new ArrayList<String>();
     
     ArrayList<String> ConsultDate = new ArrayList<String>();
     
     ArrayList<String> Status = new ArrayList<String>();
   
    
        try {
            
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery(sql);
            
              while (resultSet.next()) {
                SLno.add(resultSet.getString("ap_sl_no"));
              DoctorName.add(resultSet.getString("doctor_name"));
              ConsultId.add(resultSet.getString("consult_id"));
              AppointmentDate.add(resultSet.getString("date_of_appointment_taken"));
              ConsultDate.add(resultSet.getString("date_of_consult"));
             Status.add(resultSet.getString("appoint_active")) ;  
            }
                
            }
            
            
            catch (Exception e) {
            e.printStackTrace();
        }
        
        return new Object[]{SLno,ConsultId,DoctorName,AppointmentDate,ConsultDate,Status};
    }
    public static void main(String[] args) {
        ConnectMSSQL cnObj = new ConnectMSSQL();
        //cnObj.connectDB();
        //cnObj.rowList();
        Object[] obj ;
      obj= cnObj.showAppointment("SELECT ap_sl_no,appointment.consult_id,doctor_name,FORMAT (date_of_appointment_taken, 'dd-MM-yy')AS date_of_appointment_taken,FORMAT (date_of_consult, 'dd-MM-yy')AS date_of_consult,appoint_active FROM appointment INNER JOIN consult ON consult.consult_id=appointment.consult_id INNER JOIN doctor ON doctor.doctor_id=consult.doctor_id");
    ArrayList<String> res = new ArrayList<String>();
   res=(ArrayList)obj[1];
        System.out.println(res);
       
    }
    
    boolean loginValidation(String UserName,String Phone)
    {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://192.168.0.10:1433;databaseName=ImmuneHistory;selectMethod=cursor", "sa", "123456");

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT user_name,phone FROM user_table");
            
            
            while (resultSet.next()) {
                 
                        if(resultSet.getString("user_name").toString().equals(UserName)&& resultSet.getString("phone").toString().equals(Phone))
                        {
                            return true;
                        }
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    ArrayList fetchData(String condition)
    {
        ArrayList<String> data = new ArrayList<String>();
          try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://192.168.0.10:1433;databaseName=ImmuneHistory;selectMethod=cursor", "sa", "123456");

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT * FROM user_table where phone="+condition);
            
            
            while (resultSet.next()) {
                 data.add(resultSet.getString("user_id"));
                data.add(resultSet.getString("user_name"));
                data.add(resultSet.getString("email"));
                data.add(resultSet.getString("phone"));
                data.add(resultSet.getString("date_of_birth"));
                data.add(resultSet.getString("gender"));
                data.add(resultSet.getString("address"));
                data.add(resultSet.getString("zip_code"));
                data.add(resultSet.getString("image_url"));
                
                        
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
          return data;
    }
    void insertData(String Name,String Phone,String Email,String DOB) throws SQLException, ClassNotFoundException
    {
        int Age=22;
         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://192.168.0.10:1433;databaseName=ImmuneHistory;selectMethod=cursor", "sa", "123456");
        System.out.println("INSERT INTO user_table(user_name, age,date_of_birth,phone,email)VALUES ('"+Name+"','"+Age+"','"+DOB+"','"+Phone+"','"+Email+"')");
            Statement statement = connection.createStatement();
           
            statement
                   .executeUpdate("INSERT INTO user_table(user_name, age,date_of_birth,phone,email)VALUES ('"+Name+"','"+Age+"','"+DOB+"','"+Phone+"','"+Email+"')");
        

    }
   void updateData(String Name,String Email,String Phone,String DateOfBirth,String Gender,String Address,String Zipcode,String imageURL,String Condition) throws SQLException, ClassNotFoundException
   {
       System.out.println("UPDATE user_table SET user_name="+Name+",email="+Email+",phone="+Phone+",date_of_birth="+DateOfBirth+",gender="+Gender+",address="+Address+",zip_code="+Zipcode+",image_url="+imageURL);
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://192.168.0.10:1433;databaseName=ImmuneHistory;selectMethod=cursor", "sa", "123456");

            Statement statement = connection.createStatement();
           
            statement
                   .executeUpdate("UPDATE user_table SET user_name='"+Name+"',email='"+Email+"',phone='"+Phone+"',date_of_birth='"+DateOfBirth+"',gender='"+Gender+"',address='"+Address+"',zip_code="+Zipcode+",image_url='"+imageURL+"'WHERE phone='"+Condition+"'");
   }
   
   Object[] fetchDoctorList()
   {
       ArrayList<String> doctorName = new ArrayList<String>();
       ArrayList<String> degree = new ArrayList<String>();
          try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://192.168.0.10:1433;databaseName=ImmuneHistory;selectMethod=cursor", "sa", "123456");

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT doctor_name,degree FROM doctor");
            
            
           while (resultSet.next())
           {
               
                 doctorName.add(resultSet.getString("doctor_name"));
                 degree.add(resultSet.getString("degree"));
              
           }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
          return new Object[]{doctorName,degree};
   }
}

