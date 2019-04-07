package com.URLshortener.shortener;

import java.util.List;

import javax.ws.rs.Consumes;
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
     * We do NOT need that
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
	
	/**
     * Method handling HTTP GET requests by giving an Id. 
     * The returned value is the LongURL of the given Id.
     *
     * @return String that will be returned as a application/json response.
     */
	@GET
	@Path("shorten/{Id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getShortenById(@PathParam("Id") int Id) {
		System.out.println("shortens...");
		
		try{
			Shortener value = repo.getShortenById(Id);	
			String msg = String.format("The value is: %s ", value.getLongURL());       
	        return Response.status(301).entity(msg).build();       
			}
			catch(Exception e) {
				return Response.status(404).entity("NOT FOUND").build();
			}	
	}
	
	@POST
	@Path("shorten")
	@Consumes(MediaType.APPLICATION_XML)
	public Shortener createShorten(Shortener a1) {
		System.out.println(a1);
		repo.create(a1);
		
		return a1;
	}
	
	@PUT
	@Path("shorten/{Id}")
	@Consumes(MediaType.APPLICATION_XML)
	public Response updateShortenById(@PathParam("Id") int Id, Shortener a1) {		
		try {
			Shortener a = repo.update(Id, a1);
			return Response.status(301).entity(a).build();
		} catch(Exception e) {
			return Response.status(404).entity("NOT FOUND").build();
		}
	}
}


// curl http://localhost:8080/shortener/webapi/shortener/shorten/1
//POST:  curl -H "Content-Type: application/xml" -d "<shortener><longURL>www.google.com</longURL><shortURL>www.short.com</shortURL><id>1222</id></shortener>" -X POST http://localhost:8080/shortener/webapi/shortener/shorten
// PUT:  curl -H "Content-Type: application/xml" -d "<shortener><longURL>www.paok.com</longURL><shortURL>www.short.com</shortURL><id>1222</id></shortener>" -X PUT http://localhost:8080/shortener/webapi/shortener/shorten/1
	