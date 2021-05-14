package com.paf.gb.resources;

import java.util.List;






import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import com.paf.gb.models.User;
import com.paf.gb.repository.UserRepository;

@Path("users")
public class UserResource {

	UserRepository userRepo = new UserRepository();
	
	/*----------------------------- resources of sellers -------------------------------*/
	/*----------------------------- resources of sellers -------------------------------*/
	
	
	@GET
	@Path("sellers")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getAllSellers(){
		
		System.out.println("getAllSeller called...");
		return userRepo.getAllSellers();
	}
	
	@GET
	@Path("seller/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getSeller(@PathParam("id") int id) {
		System.out.println(" * getSeller called...");
		return userRepo.getSeller(id);
	}
	
	@POST
	@Path("seller")
	@Consumes(MediaType.APPLICATION_JSON)
	public User createSeller(User seller) {
		System.out.println("seller create data, came from Client side..."+seller);
		
		userRepo.createSeller(seller);
		return seller;
	}
	
	@PUT
	@Path("seller")
	@Consumes(MediaType.APPLICATION_JSON)
	public User updateSeller(User seller) {
		System.out.println("seller update data, came from Client side..."+seller);
		
		userRepo.updateSellers(seller);
		return seller;
	}
	
	@DELETE
    @Path("seller/{id}")
    public boolean deleteOrderById(@PathParam("id") int id) {
		System.out.println("seller delete data, came from Client side..."+id);
        return userRepo.deleteSeller(id);
    }
	
	/*------------------------ resources of customer -----------------------------*/	
	/*------------------------ resources of customer -----------------------------*/	
	
	@GET
	@Path("customers")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getAllCustomers(){
		
		System.out.println("getAllCustomer called...");
		return userRepo.getAllCustomers();
	}
	
	@GET
	@Path("customer/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getCustomer(@PathParam("id") int id) {
		System.out.println("getCustomer called...");
		return userRepo.getCustomer(id);
	}
	
	@POST
	@Path("customer")
	@Consumes(MediaType.APPLICATION_JSON)
	public User createCustomer(User customer) {
		System.out.println("Customer create data, came from Client side..."+customer);
		
		userRepo.createCustomer(customer);
		return customer;
	}
	
	@PUT
	@Path("customer")
	@Consumes(MediaType.APPLICATION_JSON)
	public User updateCustomer(User customer) {
		System.out.println("Customer update data, came from Client side..."+customer);
		
		userRepo.updateCustomers(customer);
		return customer;
	}
	
	@DELETE
    @Path("customer/{id}")
    public boolean deleteCustomerById(@PathParam("id") int id) {
		System.out.println("Customer deleted data, came from Client side..."+id);
        return userRepo.deleteCustomer(id);
    }	
	
	/*------------------------ resources of fundraisers ----------------------------*/
	/*------------------------ resources of fundraisers ----------------------------*/
	
	@GET
	@Path("fundraisers")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getAllFundraisers(){
		
		System.out.println("getAllfundraisers called...");
		return userRepo.getAllFundraisers();
	}
	
	@GET
	@Path("fundraiser/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getFundraiser(@PathParam("id") int id) {
		System.out.println("getFundraiser called...");
		return userRepo.getFundraiser(id);
	}
	
	@POST
	@Path("fundraiser")
	@Consumes(MediaType.APPLICATION_JSON)
	public User createFundraiser(User fundraiser) {
		System.out.println("Fundraiser create data, came from Client side..."+fundraiser);
		
		userRepo.createFundraiser(fundraiser);
		return fundraiser;
	}
	
	@PUT
	@Path("fundraiser")
	@Consumes(MediaType.APPLICATION_JSON)
	public User updateFundraiser(User fundraiser) {
		System.out.println("Fundraiser update data, came from Client side..."+fundraiser);
		
		userRepo.updateFundraisers(fundraiser);
		return fundraiser;
	}
	
	@DELETE
    @Path("fundraiser/{id}")
    public boolean deleteFundraiserById(@PathParam("id") int id) {
		System.out.println("Fundraiser delete id, came from Client side..."+id);
        return userRepo.deleteFundraiser(id);
    }	
	
}
