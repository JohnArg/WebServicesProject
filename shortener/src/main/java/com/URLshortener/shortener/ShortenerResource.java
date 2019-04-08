package com.URLshortener.shortener;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Root resource (exposed at "shortener" path)
 */
@Path("shortener")
public class ShortenerResource {
    
    ShortenerRepository repo = ShortenerRepository.repo;
    
    /** 
     * For Testing purposes
     * Method handling HTTP GET requests. 
     * It returns all the URLs and IDs
     *
     * @return String that will be returned as a application/xml response.
     */
	@GET
	@Path("shortens")
	@Produces(MediaType.APPLICATION_XML)
	public List <Shortener> getShortens() {
		System.out.println("shortens...");
		
		return repo.getShortens();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getKeys() {
		
		String msg = String.format("The Keys (IDs) are: %s ", repo.getKeys());
		return Response.status(200).entity(msg).build();
	}
	
	/**
     * Method handling HTTP GET requests by giving an Id. 
     * The returned value is the LongURL of the given Id.
     *
     * @return String that will be returned as a application/json response.
     */
	@GET
	@Path("{Id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getShortenById(@PathParam("Id") int Id) {
		System.out.println("shortens...");
		
		Shortener value = repo.getShortenById(Id);	    
		if (value != null) {
			String msg = String.format("The value is: %s ", value.getUrl());
			return Response.status(301).entity(msg).build();
		}
		return Response.status(404).entity("NOT FOUND").build();	
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response createShorten(String url) {
		
		try {
			URL url1 = new URL(url);
			int id = repo.create(url);
			return Response.status(301).entity(id).build();
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return Response.status(400).entity("Error...URL is invalid").build();
		}	
	}
	
	@PUT
	@Path("{Id}")
	@Consumes(MediaType.APPLICATION_XML)
	public Response updateShortenById(@PathParam("Id") int Id, String url) {		
		Shortener a = null;
		try {
			URL url1 = new URL(url);
			a = repo.update(Id, url);
			if (a != null) {
				return Response.status(301).entity(a).build();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return Response.status(400).entity("Error...URL is invalid").build();
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return Response.status(404).entity("NOT FOUND").build();
	}
	
	@DELETE
	@Path("{Id}")
	public Response deleteShortenById(@PathParam("Id") int Id) {		
		int del = repo.delete(Id);
		if (del == 1){
			return Response.status(204).entity("DELETED").build();
		}
		return Response.status(404).entity("NOT FOUND").build();	
	}
	
	@DELETE
	public Response deleteShorten() {		
		repo.delete();
		return Response.status(204).entity("DELETED").build();
	}	
}	