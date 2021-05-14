package com.paf.gb.resources;

import java.util.List;


import javax.ws.rs.core.MediaType;

import com.paf.gb.models.Cart;
import com.paf.gb.repository.CartRepository;


import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;


@Path("cart")
public class CartResource {

	CartRepository cartRepo = new CartRepository();
	
	/*----------------------------- resources of carts -------------------------------*/
	/*----------------------------- resources of carts -------------------------------*/
	
	
	@GET
	@Path("cart-items/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Cart> getAllCarts(@PathParam("id") int id){
		System.out.println("id>> "+id);
		System.out.println("getAllCart called...");
		return cartRepo.getAllCartItemsBelongsToCustomer(id);
	}
	/*
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getSeller(@PathParam("id") int id) {
		System.out.println("getSeller called...");
		return userRepo.getSeller(id);
	}*/
	
	@POST
	@Path("")
	@Consumes(MediaType.APPLICATION_JSON)
	public Cart createCart(Cart cartItem) {
		System.out.println("Cart create data, came from Client side..."+cartItem);
		
		cartRepo.createCart(cartItem);
		return cartItem;
	}
	
	@PUT
	@Path("")
	@Consumes(MediaType.APPLICATION_JSON)
	public Cart updateCart(Cart cartItem) {
		System.out.println("Cart update data, came from Client side..."+cartItem);
		
		cartRepo.updateCarts(cartItem);
		return cartItem;
	}
	
	@DELETE
    @Path("/{id}")
    public boolean deleteCartById(@PathParam("id") int id) {
		System.out.println("Cart delete data, came from Client side...id="+id);
        return cartRepo.deleteCart(id);
    }

}
