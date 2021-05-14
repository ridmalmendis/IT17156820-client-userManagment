package com.paf.gb.repository;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import com.mysql.jdbc.Statement;
import com.paf.gb.models.Cart;
import com.paf.gb.util.DBConnectionUtil;

public class CartRepository {

	Connection connection = null;
	
	/*
	 * 1) get all carts
	 * 2) get specific cart
	 * 3) create cart
	 * 4) update cart
	 * 5) delete cart
	 * 
	 * */
	
	//get all Cart item belongs to customer
	public List<Cart> getAllCartItemsBelongsToCustomer(int cusId){
		System.out.println("cusId >> "+cusId);
		List<Cart> cartList = new ArrayList<>();
		
		/*
		 * SELECT cart.cartId, product.productId, product.image, cart.numOfItems, cart.totalPrice FROM cart FULL OUTER JOIN products ON cart.productId=products.productId WHERE cart.status=1;
		*/
		String sql = "SELECT cart.cartId, cart.productId, products.image, products.productName, "
				+ "products.productPrice, cart.numOfItems, cart.totalPrice "
				+ "FROM cart RIGHT JOIN products ON cart.productId=products.productId "
				+ "WHERE cart.status=1 and cart.customerId=?;";
		
		try {
			
			connection = DBConnectionUtil.getDBconnection();
			
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, cusId);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Cart cart = new Cart();
				
				cart.setCartId(rs.getInt("cartId"));
				cart.setProductId(rs.getInt("productId"));
				cart.setProductImage(addPath(rs.getString("image")).trim());
				cart.setProductName(rs.getString("productName"));
				cart.setProductPrice(rs.getString("productPrice"));
				cart.setNumOfItems(rs.getInt("numOfItems"));
				cart.setTotalPrice(rs.getString("totalPrice"));
				
				cartList.add(cart);
			}			
		} catch (Exception e) {			
			e.printStackTrace();
		}		
		
		System.out.println("cartList >>>"+cartList);
		return cartList;
	}
	
	/*
	//get specific cart
	public Cart getcart(int id) {
		
		String sql = "select * from carts where cartId=?";
		Cart cart = new Cart();
		
		
		try {
			connection = DBConnectionUtil.getDBconnection();
			
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				cart.setCartId(rs.getInt("cartId"));
				cart.setCompanyName(rs.getString("companyName"));
				cart.setContact(rs.getString("contact"));
				cart.setCartname(rs.getString("Cartname"));
				cart.setPassword(rs.getString("password"));
			}
			
		} catch (Exception e) {
			System.out.println("1");
			e.printStackTrace();
		}
		System.out.println(cart);
		return cart;
	}*/
	
	//create cart
	public void createCart(Cart cart) {
		
		String sql = "insert into cart(numOfItems, totalPrice, customerId, productId) values (?,?,?,?)";
		
		try {
			PreparedStatement ps = DBConnectionUtil.getDBconnection().prepareStatement(sql);
			
			ps.setInt(1, cart.getNumOfItems());
			ps.setString(2, cart.getTotalPrice());	
			ps.setInt(3, cart.getCustomerId());
			ps.setInt(4, cart.getProductId());
					
			
			int rowsInserted = ps.executeUpdate();
			
			if (rowsInserted > 0) {
			    System.out.println("A new cart item was inserted successfully!");
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	//Update cart
    public boolean updateCarts(Cart cart) {
    	
    	int numOfItems = cart.getNumOfItems();
    	int cartId = cart.getCartId();
    	Double proPrice = Double.parseDouble(cart.getProductPrice());
    	double totalPrice = 0;
    	
    	
    	if (cart.isIncreaseNumOfItems() == true) { //this is increment of number of products
    		
    		numOfItems++;
    		totalPrice = proPrice * numOfItems;
    		
    		
    	}else {//this is decrement of number of products
    		if(numOfItems == 1) {// cannot decrease items
    			numOfItems = 1;
    			totalPrice = proPrice * numOfItems;
    		}else {// decrease item by 1
    			numOfItems--;
    			totalPrice = proPrice * numOfItems;
    		}
    	}
    	
        //String convertedTotalPrice = Double.toString(totalPrice);
        
        boolean updateCart = false;
        try {
            //System.out.println("1");
            connection = DBConnectionUtil.getDBconnection();
            String sql = "UPDATE cart SET numOfItems=?, totalPrice=? WHERE cartId=?";

            PreparedStatement ps = connection.prepareStatement(sql);
            
            
            ps.setInt(1, numOfItems);
            ps.setString(2, Double.toString(totalPrice));
            ps.setInt(3, cartId);
            
            ps.executeUpdate();

            updateCart = true;
            
            System.out.println("number of products Succesfully Updated..!");
            //connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("updatecart -"+updateCart);
        return updateCart;
    }
	
    //Delete cart
    public boolean deleteCart(int cId) {
        System.out.println("req del call");
        boolean delcart = false;
        try {
            connection = DBConnectionUtil.getDBconnection();
            String sql = "DELETE FROM cart WHERE cartId=?";
            
            PreparedStatement ps = connection.prepareStatement(sql);
            
            ps.setInt(1, cId);
            
            ps.executeUpdate();
            System.out.println("cId " + cId + " delete successfully..!");
            delcart = true;
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return delcart;
    }
    
    
	public String removePath(String path) {
		
		String newPath = path.replaceFirst("C:", "").replace("fakepath", "").replace("\\", "");
		return newPath;
	}
	
	public String addPath(String imageName) {
		String str = "../../gb-images/";
		String newPath = str.concat(imageName);
		return newPath;
	}
}
