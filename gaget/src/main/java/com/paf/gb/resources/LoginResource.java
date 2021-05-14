package com.paf.gb.resources;

import javax.ws.rs.core.MediaType;


//import javax.ws.rs.Consumes;



//import javax.ws.rs.POST;


//import com.paf.gb.models.Login;
//import com.paf.gb.models.User;
import com.paf.gb.repository.LoginRepository;




import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;



@Path("login")
public class LoginResource {
	
	LoginRepository loginRepo = new LoginRepository();
	
	@GET
	@Path("/{uname}/{pwd}/{utype}")
	@Produces(MediaType.APPLICATION_JSON)
	public int getLoginUserId(@PathParam("uname") String uname, @PathParam("pwd") String pwd, @PathParam("utype") String utype) {
		System.out.println(" * getLogin called...");
		return loginRepo.getUserId(uname, pwd, utype);
	}
	/*
	@POST
	@Path("")
	@Consumes(MediaType.APPLICATION_JSON)
	public User createCustomer(Login login) {
		System.out.println("Customer create data, came from Client side..."+login);
		
		//loginRepo.createCustomer(customer);
		//return customer;
	}*/
}
