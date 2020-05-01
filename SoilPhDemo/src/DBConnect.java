import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBConnect {
	
private Connection con;
private ResultSet rs;
private Statement st;
	public DBConnect() {
		// TODO Auto-generated constructor stub
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost/test");
			st = con.createStatement();

			if(con==null)
				System.out.println("Connection is not established");
			else
				System.out.println("Connection is established");
			
			 
		}catch(Exception ex){
			}
		}
	public float DBData(float a) throws SQLException{
		float result = 0.0f;
		System.out.println(a);
			rs=st.executeQuery("Select ph_value from soilph where ph_index<="+a+" order by ph_index desc");
			rs.next();
			result=rs.getFloat("ph_value");
			con.close();
		return result;
		
	}

}
