package com.paf.gb.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//import com.paf.gb.models.Login;
import com.paf.gb.util.DBConnectionUtil;

public class LoginRepository {

	Connection connection = null;
	
	public int getUserId(String username, String password, String userType){  
		
		
		String sql;
		int userId;
		
		if (userType == "seller") {
			sql = "SELECT sellerId, username FROM sellers WHERE username=? and password=?";
			
		}else  if(userType == "customer") {
			sql = "SELECT customerId, username FROM customers WHERE username=? and password=?";
			
		}else { //userType = fundraiser
			sql = "SELECT fundraiserId, username FROM fundraisers WHERE username=? and password=?";
		}
		
          
        try{
        	connection = DBConnectionUtil.getDBconnection();
			
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			
            if(rs.next()){  
            	//Login login = new Login();
            	userId = rs.getInt(1); 
            	return userId;
                 
            }  
            connection.close();  
        }catch(Exception ex){
        	
        	ex.printStackTrace();
        }  
          
        return 0;  
    }  
}
