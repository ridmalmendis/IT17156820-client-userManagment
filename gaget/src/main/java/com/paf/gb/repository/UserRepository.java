package com.paf.gb.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;
import com.paf.gb.models.User;
import com.paf.gb.util.DBConnectionUtil;

public class UserRepository {

	Connection connection = null;
	
	/*----------------------------- sellers -------------------------------*/
	/*----------------------------- sellers -------------------------------*/
	/*----------------------------- sellers -------------------------------*/
	/*----------------------------- sellers -------------------------------*/
	/*----------------------------- sellers -------------------------------*/
	
	/*
	 * 1) get all sellers
	 * 2) get specific seller
	 * 3) create seller
	 * 4) update seller
	 * 5) delete seller
	 * 
	 * */
	
	//get all sellers
	public List<User> getAllSellers(){
		
		List<User> sellersList = new ArrayList<>();
		
		String sql = "select * from Sellers";
		System.out.println("1");
		try {
			System.out.println("2");
			connection = DBConnectionUtil.getDBconnection();
			Statement st = (Statement) connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()) {
				User seller = new User();
				
				seller.setUserId(rs.getInt(1));
				seller.setCompanyName(rs.getString(2));
				seller.setContact(rs.getString(3));
				seller.setUsername(rs.getString(4));
				seller.setPassword(rs.getString(5));
				
				sellersList.add(seller);
				System.out.println(seller);
			}	
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		System.out.println(sellersList);
		return sellersList;
	}
	
	//get specific seller
	public User getSeller(int id) {
		
		String sql = "select * from Sellers where sellerId=?";
		User seller = new User();
		
		
		try {
			connection = DBConnectionUtil.getDBconnection();
			
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				seller.setUserId(rs.getInt("sellerId"));
				seller.setCompanyName(rs.getString("companyName"));
				seller.setContact(rs.getString("contact"));
				seller.setUsername(rs.getString("username"));
				seller.setPassword(rs.getString("password"));
			}
			
		} catch (Exception e) {
			System.out.println("1");
			e.printStackTrace();
		}
		System.out.println(seller);
		return seller;
	}
	
	//create seller
	public void createSeller(User seller) {
		
		String sql = "insert into Sellers(companyName, contact, username, password) values (?,?,?,?)";
		
		try {
			PreparedStatement ps = DBConnectionUtil.getDBconnection().prepareStatement(sql);
			
			ps.setString(1, seller.getCompanyName());
			ps.setString(2, seller.getContact());
			ps.setString(3, seller.getUsername());
			ps.setString(4, seller.getPassword());			
			
			int rowsInserted = ps.executeUpdate();
			
			if (rowsInserted > 0) {
			    System.out.println("A new seller was inserted successfully!");
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	//Update seller
    public boolean updateSellers(User seller) {
        
        boolean updateSell = false;
        try {
            //System.out.println("1");
            connection = DBConnectionUtil.getDBconnection();
            String sql = "UPDATE Sellers SET companyName=?,contact=?,username=? WHERE sellerId=?";

            PreparedStatement ps = connection.prepareStatement(sql);
            
            ps.setString(1, seller.getCompanyName());
            ps.setString(2, seller.getContact());
            ps.setString(3, seller.getUsername());
            //ps.setString(4, seller.getPassword());
            ps.setInt(4, seller.getUserId());
            
            ps.executeUpdate();

            updateSell = true;
            
            System.out.println("Succesfully Updated..!");
            //connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("updateSeller -"+updateSell);
        return updateSell;
    }
	
    //Delete seller
    public boolean deleteSeller(int sellerId) {
        
        boolean delSeller = false;
        try {
            connection = DBConnectionUtil.getDBconnection();
            String sql = "DELETE FROM Sellers WHERE sellerId=?";
            
            PreparedStatement ps = connection.prepareStatement(sql);
            
            ps.setInt(1, sellerId);
            ps.executeUpdate();
            System.out.println("user " + sellerId + " delete successfully..!");
            delSeller = true;
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return delSeller;
    }
	
	/*------------------------ customer -----------------------------*/
	/*------------------------ customer -----------------------------*/	
	/*------------------------ customer -----------------------------*/	
	/*------------------------ customer -----------------------------*/	
	/*------------------------ customer -----------------------------*/	
	
	// get all customers
	public List<User> getAllCustomers(){
		
		List<User> customersList = new ArrayList<>();
		
		String sql = "select * from Customers";
		
		try {
			PreparedStatement ps = DBConnectionUtil.getDBconnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				User customer = new User();
				
				customer.setUserId(rs.getInt(1));
				customer.setFname(rs.getString(2));
				customer.setLname(rs.getString(3));
				customer.setContact(rs.getString(4));
				customer.setUsername(rs.getString(5));
				customer.setPassword(rs.getString(6));
				
				customersList.add(customer);
			}			
		} catch (Exception e) {			
			e.printStackTrace();
		}		
		return customersList;
	}
	
	//get specific customer
	public User getCustomer(int id) {
		
		String sql = "select * from customers where customerId=?";
		User customer = new User();
		
		
		try {
			connection = DBConnectionUtil.getDBconnection();
			
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				customer.setUserId(rs.getInt("customerId"));
				customer.setFname(rs.getString("fname"));
				customer.setLname(rs.getString("lname"));
				customer.setContact(rs.getString("contact"));
				customer.setUsername(rs.getString("username"));
				customer.setPassword(rs.getString("password"));
			}
			
		} catch (Exception e) {
			//System.out.println("1");
			e.printStackTrace();
		}
		System.out.println(customer);
		return customer;
	}
	
	//create customer
	public void createCustomer(User customer) {
		
		String sql = "insert into customers(fname, lname, contact, username, password) values (?,?,?,?,?)";
		
		try {
			PreparedStatement ps = DBConnectionUtil.getDBconnection().prepareStatement(sql);
			
			ps.setString(1, customer.getFname());
			ps.setString(2, customer.getLname());
			ps.setString(3, customer.getContact());
			ps.setString(4, customer.getUsername());
			ps.setString(5, customer.getPassword());			
			
			int rowsInserted = ps.executeUpdate();
			
			if (rowsInserted > 0) {
			    System.out.println("A new customer was inserted successfully!");
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	//Update customer
    public boolean updateCustomers(User customer) {
        
        boolean updatCus = false;
        try {
            //System.out.println("1");
            connection = DBConnectionUtil.getDBconnection();
            String sql = "UPDATE Customers SET fname=?, lname=?, contact=?,username=? WHERE customerId=?";

            PreparedStatement ps = connection.prepareStatement(sql);
            
            ps.setString(1, customer.getFname());
            ps.setString(2, customer.getLname());
            ps.setString(3, customer.getContact());
            ps.setString(4, customer.getUsername());
            ps.setInt(5, customer.getUserId());
            
            ps.executeUpdate();

            updatCus = true;
            
            System.out.println("Succesfully Updated..!");
            //connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("updateSeller -"+updatCus);
        return updatCus;
    }
	
    //Delete customer
    public boolean deleteCustomer(int customerId) {
        
        boolean delCustomer = false;
        try {
            connection = DBConnectionUtil.getDBconnection();
            String sql = "DELETE FROM customers WHERE customerId=?";
            
            PreparedStatement ps = connection.prepareStatement(sql);
            
            ps.setInt(1, customerId);
            ps.executeUpdate();
            System.out.println("user " + customerId + " delete successfully..!");
            delCustomer = true;
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return delCustomer;
    }	

	/*------------------------- fundraisers ----------------------------*/
	/*------------------------- fundraisers ----------------------------*/
	/*------------------------- fundraisers ----------------------------*/
	/*------------------------- fundraisers ----------------------------*/
	/*------------------------- fundraisers ----------------------------*/
	
	
	// get all Fundraisers
		public List<User> getAllFundraisers(){
			
			List<User> fundraisersList = new ArrayList<>();
			
			String sql = "select * from Fundraisers";
			
			try {
				PreparedStatement ps = DBConnectionUtil.getDBconnection().prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					User fundraiser = new User();
					
					fundraiser.setUserId(rs.getInt(1));
					fundraiser.setFname(rs.getString(2));
					fundraiser.setLname(rs.getString(3));
					fundraiser.setContact(rs.getString(4));
					fundraiser.setUsername(rs.getString(5));
					fundraiser.setPassword(rs.getString(6));
					
					fundraisersList.add(fundraiser);
				}			
			} catch (Exception e) {			
				e.printStackTrace();
			}		
			return fundraisersList;
		}
		
		//get specific fundraiser
		public User getFundraiser(int id) {
			
			String sql = "select * from fundraisers where fundraiserId=?";
			User fundraiser = new User();
			
			
			try {
				connection = DBConnectionUtil.getDBconnection();
				
				PreparedStatement ps = connection.prepareStatement(sql);
				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();
				
				if(rs.next()) {
					fundraiser.setUserId(rs.getInt("fundraiserId"));
					fundraiser.setFname(rs.getString("fname"));
					fundraiser.setLname(rs.getString("lname"));
					fundraiser.setContact(rs.getString("contact"));
					fundraiser.setUsername(rs.getString("username"));
					fundraiser.setPassword(rs.getString("password"));
				}
				
			} catch (Exception e) {
				//System.out.println("1");
				e.printStackTrace();
			}
			System.out.println(fundraiser);
			return fundraiser;
		}
		
		//create fundraiser
		public void createFundraiser(User fundraiser) {
			
			String sql = "insert into fundraisers(fname, lname, contact, username, password) values (?,?,?,?,?)";
			
			try {
				PreparedStatement ps = DBConnectionUtil.getDBconnection().prepareStatement(sql);
				
				ps.setString(1, fundraiser.getFname());
				ps.setString(2, fundraiser.getLname());
				ps.setString(3, fundraiser.getContact());
				ps.setString(4, fundraiser.getUsername());
				ps.setString(5, fundraiser.getPassword());			
				
				int rowsInserted = ps.executeUpdate();
				
				if (rowsInserted > 0) {
				    System.out.println("A new fundraiser was inserted successfully!");
				}
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}
		
		//Update fundraiser
	    public boolean updateFundraisers(User fundraiser) {
	        
	        boolean updateFund = false;
	        try {
	            //System.out.println("1");
	            connection = DBConnectionUtil.getDBconnection();
	            String sql = "UPDATE fundraisers SET fname=?, lname=?, contact=?,username=? WHERE fundraiserId=?";

	            PreparedStatement ps = connection.prepareStatement(sql);
	            
	            ps.setString(1, fundraiser.getFname());
	            ps.setString(2, fundraiser.getLname());
	            ps.setString(3, fundraiser.getContact());
	            ps.setString(4, fundraiser.getUsername());
	            ps.setInt(5, fundraiser.getUserId());
	            
	            ps.executeUpdate();

	            updateFund = true;
	            
	            System.out.println("Succesfully Updated..!");
	            //connection.close();

	        } catch (SQLException e) {
	            e.printStackTrace();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        System.out.println("updateSeller -"+updateFund);
	        return updateFund;
	    }
		
	    //Delete fundraiser
	    public boolean deleteFundraiser(int fundraiserId) {
	        
	        boolean delFundraiser = false;
	        try {
	            connection = DBConnectionUtil.getDBconnection();
	            String sql = "DELETE FROM fundraisers WHERE fundraiserId=?";
	            
	            PreparedStatement ps = connection.prepareStatement(sql);
	            
	            ps.setInt(1, fundraiserId);
	            ps.executeUpdate();
	            System.out.println("user " + fundraiserId + " delete successfully..!");
	            delFundraiser = true;
	            connection.close();

	        } catch (SQLException e) {
	            e.printStackTrace();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	        return delFundraiser;
	    }
}
