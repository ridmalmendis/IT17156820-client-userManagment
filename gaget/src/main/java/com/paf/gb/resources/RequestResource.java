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
import jakarta.ws.rs.core.MediaType;


import com.paf.gb.models.Request;
import com.paf.gb.repository.RequestRepository;

@Path("request")
public class RequestResource {

	RequestRepository requestRepo = new RequestRepository();
	
	/*----------------------------- resources of requests -------------------------------*/
	/*----------------------------- resources of requests -------------------------------*/
	
	
	@GET
	@Path("requests")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Request> getAllRequests(){
		
		System.out.println("getAllRequest called...");
		return requestRepo.getAllRequests();
	}
	
	@GET
	@Path("/{fid}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Request> getRequestByFid(@PathParam("fid") int fid) {
		System.out.println("getAllRequestsBelongsFundraiser called...");
		return requestRepo.getAllRequestsBelongsFundraiser(fid);
	}
	
	@GET
	@Path("/{fid}/{pid}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Request> getRequestByFidAndPid(@PathParam("fid") int fid, @PathParam("pid") int pid) {
		System.out.println("getAllRequestsBelongsFundraiserAndProduct called...");
		return requestRepo.getAllRequestsBelongsFundraiserAndProduct(fid, pid);
	}
	
	@POST
	@Path("")
	@Consumes(MediaType.APPLICATION_JSON)
	public Request createRequest(Request request) {
		System.out.println("Request create data, came from Client side..."+request);
		
		requestRepo.createRequest(request);
		return request;
	}
	
	@PUT
	@Path("")
	@Consumes(MediaType.APPLICATION_JSON)
	public Request updateRequest(Request request) {
		System.out.println("Request update data, came from Client side..."+request);
		
		requestRepo.updateRequests(request);
		return request;
	}
	
	@DELETE
    @Path("/{fid}/{pid}")
    public boolean deleteRequestById(@PathParam("fid") int fid, @PathParam("pid") int pid) {
		System.out.println("Request delete data, came from Client side...fid="+fid+" pid="+pid);
        return requestRepo.deleteRequest(fid, pid);
    }
	

}
