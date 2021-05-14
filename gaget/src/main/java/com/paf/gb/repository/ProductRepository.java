package com.paf.gb.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;
import com.paf.gb.models.Product;
import com.paf.gb.util.DBConnectionUtil;

public class ProductRepository {

	Connection connection = null;
	
	public ArrayList<Product> getAllProducts() {
		ArrayList<Product> productList = new ArrayList<Product>();
		
		String sql = "SELECT * FROM products";

		try {
			connection = DBConnectionUtil.getDBconnection();
			Statement st = (Statement) connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			

			while (rs.next()) {
				Product pro = new Product();
				
				pro.setProductId(rs.getInt("productId"));
				pro.setProductName(rs.getString("productName"));
				pro.setProductType(rs.getString("productType"));
				pro.setProductPrice(rs.getString("productPrice"));
				pro.setImage(addPath(rs.getString("image")));// image
				pro.setDescription(rs.getString("description"));
				pro.setSellerId(rs.getInt("sellerId"));
				
				//System.out.println("2222222222");
				productList.add(pro);
			}

		} catch (Exception ex) {
			System.out.println("[Error][ProductDao][getAllProducts] - " + ex.getMessage());
		}
		return productList;
	}
	
	public ArrayList<Product> getAllProductsBelogsToSellerId(int sId) {
		ArrayList<Product> productList = new ArrayList<Product>();
		
		String sql = "SELECT * FROM products WHERE sellerId=? and status=1";

		try {
			connection = DBConnectionUtil.getDBconnection();
			
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, sId);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Product pro = new Product();
				
				pro.setProductId(rs.getInt("productId"));
				pro.setProductName(rs.getString("productName"));
				pro.setProductType(rs.getString("productType"));
				pro.setProductPrice(rs.getString("productPrice"));
				pro.setImage(addPath(rs.getString("image")));// image
				pro.setDescription(rs.getString("description"));
				pro.setSellerId(rs.getInt("sellerId"));
				
				System.out.println("33333");
				productList.add(pro);
			}

		} catch (Exception ex) {
			System.out.println("[Error][ProductDao][getAllProductsBelogsToSeller] - " + ex.getMessage());
		}
		return productList;
	}
	
	public boolean addProduct(Product pro) {
		System.out.println("********");
		System.out.println("******** pro >>"+pro);
		boolean isSuccess;
		
		try {

			String sql;
			sql = "INSERT INTO products (productName, productType, productPrice, image, relatedField, description, sellerId) VALUES (?,?,?,?,?,?,?)";

			PreparedStatement stmt = DBConnectionUtil.getDBconnection().prepareStatement(sql);
			//PreparedStatement stmt = this._dbConnection.prepareStatement(sql);

			stmt.setString(1, pro.getProductName());
			stmt.setString(2, pro.getProductType());
			stmt.setString(3, pro.getProductPrice());
			//stmt.setBlob(4, new ByteArrayInputStream(pro.getImage().getBytes()));
			stmt.setString(4, removePath(pro.getImage()));
			stmt.setString(5, pro.getRelatedField());
			stmt.setString(6, pro.getDescription());
			stmt.setInt(7, pro.getSellerId());

			isSuccess = stmt.executeUpdate() > 0;

		} catch (Exception ex) {
			System.out.println("[Error][ProductDao][addProduct] - " + ex.toString());
			isSuccess = false;
		}
		System.out.println("return ");
		return isSuccess;
	}
	
	// Update product
	public boolean updateProducts(Product product) {

		boolean updatePro = false;
		try {
			// System.out.println("1");
			connection = DBConnectionUtil.getDBconnection();
			String sql = "UPDATE products SET productName=?, productPrice=?, description=? WHERE sellerId=? and productId=?";

			PreparedStatement ps = connection.prepareStatement(sql);

			ps.setString(1, product.getProductName());
			ps.setString(2, product.getProductPrice());
			ps.setString(3, product.getDescription());
			ps.setInt(4, product.getSellerId());
			ps.setInt(5, product.getProductId());

			ps.executeUpdate();

			updatePro = true;

			System.out.println("Succesfully Updated (Product)..!");
			// connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("updateproduct -" + updatePro);
		return updatePro;
	}
	
	// Update product
	public boolean deleteProduct(int proId) {

		boolean deletePro = false;
		try {
			// System.out.println("1");
			connection = DBConnectionUtil.getDBconnection();
			String sql = "UPDATE products SET status=0 WHERE productId=?";

			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setInt(1, proId);

			ps.executeUpdate();

			deletePro = true;

			System.out.println("Succesfully Deleted (Product)..!");
			// connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("deleteproduct -" + deletePro);
		return deletePro;
	}
	
/*
	// Delete product
	public boolean deleteProduct(int proId) {
		System.out.println("pro del call proId>>> "+proId);
		boolean delproduct = false;
		try {
			connection = DBConnectionUtil.getDBconnection();
			String sql = "DELETE FROM products WHERE productId=?";

			PreparedStatement ps = connection.prepareStatement(sql);

			ps.setInt(1, proId);
			ps.executeUpdate();
			System.out.println("proId " + proId + "was deleted successfully..!");
			delproduct = true;
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return delproduct;
	}
	*/
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
