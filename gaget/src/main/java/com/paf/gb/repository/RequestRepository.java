package com.paf.gb.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;
import com.paf.gb.models.Request;
import com.paf.gb.util.DBConnectionUtil;

public class RequestRepository {

	Connection connection = null;

	/*
	 * 1) get all requests 2) get specific request 3) create request 4) update
	 * request 5) delete request
	 * 
	 */

	// get all Requests
	public List<Request> getAllRequests() {

		List<Request> requestList = new ArrayList<>();

		String sql = "select * from Requests";

		try {

			connection = DBConnectionUtil.getDBconnection();
			Statement st = (Statement) connection.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				Request request = new Request();

				request.setFundraiserId(rs.getInt(1));
				request.setProductId(rs.getInt(2));
				request.setRequestDescription(rs.getString(3));

				requestList.add(request);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return requestList;
	}

	// get all Requests belongs to specific fundraiser
	public List<Request> getAllRequestsBelongsFundraiser(int fundId) {

		List<Request> requestList = new ArrayList<>();
		
		String sql = "SELECT Requests.fundraiserId, requests.productId , products.productName, products.productPrice, "
				+ "products.description, requests.reqDescription "
				+ "FROM Requests RIGHT JOIN products ON requests.productId = products.productId "
				+ "WHERE Requests.fundraiserId = ?;";
		try {
			connection = DBConnectionUtil.getDBconnection();
			
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, fundId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				//fId, pId, pName, pPrice, pDes, rDes
				Request req = new Request(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));

				requestList.add(req);				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("requestList "+requestList);
		return requestList;
	}

	// get all Requests belongs to specific fundraiser and product
	public List<Request> getAllRequestsBelongsFundraiserAndProduct(int fundId, int proId) {

		List<Request> requestList = new ArrayList<>();
		
		String sql = "SELECT Requests.fundraiserId, requests.productId , requests.reqDescription "
				+ "FROM Requests RIGHT JOIN products ON requests.productId = products.productId "
				+ "WHERE Requests.fundraiserId = ? AND Requests.productId = ?;";
		try {
			connection = DBConnectionUtil.getDBconnection();
			
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, fundId);
			ps.setInt(2, proId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				//fundraiserId, productId, requestDescription
				Request req = new Request(rs.getInt(1), rs.getInt(2), rs.getString(3));
				
				requestList.add(req);				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("requestList BelongsFundraiserAndProduct >> "+requestList);
		return requestList;
	}

	/*
	 * //get specific request public Request getrequest(int id) {
	 * 
	 * String sql = "select * from requests where requestId=?"; Request request =
	 * new Request();
	 * 
	 * 
	 * try { connection = DBConnectionUtil.getDBconnection();
	 * 
	 * PreparedStatement ps = connection.prepareStatement(sql); ps.setInt(1, id);
	 * ResultSet rs = ps.executeQuery();
	 * 
	 * if(rs.next()) { request.setRequestId(rs.getInt("requestId"));
	 * request.setCompanyName(rs.getString("companyName"));
	 * request.setContact(rs.getString("contact"));
	 * request.setRequestname(rs.getString("Requestname"));
	 * request.setPassword(rs.getString("password")); }
	 * 
	 * } catch (Exception e) { System.out.println("1"); e.printStackTrace(); }
	 * System.out.println(request); return request; }
	 */

	// create request
	public void createRequest(Request request) {

		String sql = "insert into requests(fundraiserId, productId, reqDescription) values (?,?,?)";

		try {
			PreparedStatement ps = DBConnectionUtil.getDBconnection().prepareStatement(sql);

			ps.setInt(1, request.getFundraiserId());
			ps.setInt(2, request.getProductId());
			ps.setString(3, request.getRequestDescription());

			int rowsInserted = ps.executeUpdate();

			if (rowsInserted > 0) {
				System.out.println("A new request was inserted successfully!");
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	// Update request
	public boolean updateRequests(Request request) {

		boolean updateReq = false;
		try {
			// System.out.println("1");
			connection = DBConnectionUtil.getDBconnection();
			String sql = "UPDATE requests SET reqDescription=? WHERE fundraiserId=? and productId=?";

			PreparedStatement ps = connection.prepareStatement(sql);

			ps.setString(1, request.getRequestDescription());
			ps.setInt(2, request.getFundraiserId());
			ps.setInt(3, request.getProductId());

			ps.executeUpdate();

			updateReq = true;

			System.out.println("Succesfully Updated..!");
			// connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("updaterequest -" + updateReq);
		return updateReq;
	}

	// Delete request
	public boolean deleteRequest(int fundId, int proId) {
		System.out.println("req del call");
		boolean delrequest = false;
		try {
			connection = DBConnectionUtil.getDBconnection();
			String sql = "DELETE FROM requests WHERE fundraiserId=? and productId=?";

			PreparedStatement ps = connection.prepareStatement(sql);

			ps.setInt(1, fundId);
			ps.setInt(2, proId);
			ps.executeUpdate();
			System.out.println("proId " + proId + " of fundId " + fundId + "was deleted successfully..!");
			delrequest = true;
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return delrequest;
	}

}
