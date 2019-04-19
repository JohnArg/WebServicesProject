/* This is the resource class. It defines the paths and the accepted methods */
package com.URLshortener.shortener;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
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
     * Method handling HTTP GET requests in path "/shortens". 
     * It returns all the URLs and IDs
     *
     * @return String that will be returned as a application/json response.
     */
	@GET
	@Path("shortens")
	@Produces(MediaType.APPLICATION_JSON)
	public List <Shortener> getShortens() {
		System.out.println("shortens...");
		
		return repo.getShortens();
	}
	
	/** 
     * Method handling HTTP GET requests. 
     * It returns all the Keys (IDs) of all the stored URLs
     *
     * @return String that will be returned as a application/json response.
     */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getKeys() {
		
		String msg = String.format("The Keys (IDs) are: %s ", repo.getKeys());
		return Response.status(200).entity(msg).build();
	}
	
	/**
     * Method handling HTTP GET requests by giving an Id. 
     * The returned value is the long URL of the given Id.
     * It returns 301 status code, and client redirects in the url
     * It return 404 status code, in case the id is not found
     */
	@GET
	@Path("{Id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getShortenById(@PathParam("Id") int Id) {
		System.out.println("shortens...");
		
		Shortener value = repo.getShortenById(Id);
		if (value == null) {
			return Response.status(404).entity("NOT FOUND").build();
		}
		
		URI new_url = null;
		try {
			new_url = new URI(value.getUrl());
		} catch (URISyntaxException e) {
			e.printStackTrace();
			
		}
		return Response.status(301).location(new_url).build();		
	}
	
	/**
     * Method handling HTTP POST requests. 
     * Accepts application/json with a new url.
     * After the URL is stored, it returns the id assigned to it, with status code 201
     * or 400 in case of invalid URL
     */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createShorten(final Shortener shortener) {
		
		try {
			URL url1 = new URL(shortener.getUrl());
			int id = repo.create(shortener.getUrl());
			return Response.status(201).entity("id = "+id).build();
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return Response.status(400).entity("Error...URL is invalid").build();
		}	
	}
	
	/**
     * Method handling HTTP PUT requests with a path parameter, the id. 
     * Accepts application/json body with a new url.
     * After the URL is updated, it returns status code 200 (If id exists),
     * 400 in case of invalid URL or 404 in case the id is not found
     */
	@PUT
	@Path("{Id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateShortenById(@PathParam("Id") int Id, final Shortener shortener) {		
		Shortener a = null;
		try {
			URL url1 = new URL(shortener.getUrl());
			a = repo.update(Id, shortener.getUrl());
			if (a != null) {
				return Response.status(200).entity("OK").build();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return Response.status(400).entity("Error...URL is invalid").build();
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return Response.status(404).entity("NOT FOUND").build();
	}
	
	/**
     * Method handling HTTP DELETE requests with a path parameter, the id. 
     * After the URL is deleted, it returns status code 204,
     * or 404 in case the id is not found
     */
	@DELETE
	@Path("{Id}")
	public Response deleteShortenById(@PathParam("Id") int Id) {		
		int del = repo.delete(Id);
		if (del == 1){
			return Response.status(204).entity("DELETED").build();
		}
		return Response.status(404).entity("NOT FOUND").build();	
	}
	
	/**
     * Method handling HTTP DELETE requests. 
     * After all the entries in the arraylist are deleted, 
     * it returns status code 204
     */
	@DELETE
	public Response deleteShorten() {		
		repo.delete();
		return Response.status(204).entity("DELETED").build();
	}	
}	