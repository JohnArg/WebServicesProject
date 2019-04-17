package rest.client;

import java.util.InputMismatchException;
import java.util.Scanner;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class ShortenerClient {
	public static void main(String[] args) {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://localhost:8080/shortener/webapi");
		
		//post(webTarget, "http://ajax.nl");
		//put(webTarget, 1, "http://paok.gr");
		//delete(webTarget, 0);
		//delete(webTarget);
		//getKeys(webTarget);
		//getAllShortens(webTarget);
		
		clientOperations(webTarget);
		
	}
	
	public static void getUrlById(WebTarget webTarget, int Id) {
		StringBuilder sb1 = new StringBuilder("shortener/"); 
		sb1.append(Id);
		String shortUrl = sb1.toString();
		WebTarget getShortensWebTarget = webTarget.path(shortUrl);
		
		Response response = getShortensWebTarget.request(MediaType.APPLICATION_JSON)
                .get();
        System.out.println("status : " + response.getStatus());
        String result = response.readEntity(String.class);
        System.out.println(result);
	}
	
	public static void getKeys(WebTarget webTarget) {
		WebTarget getKeysWebTarget = webTarget.path("shortener");
		
		Response response = getKeysWebTarget.request(MediaType.APPLICATION_JSON)
                .get();
        System.out.println("status : " + response.getStatus());
        String result = response.readEntity(String.class);
        System.out.println(result);
	}
	
	public static void getAllShortens(WebTarget webTarget) {
		WebTarget getKeysWebTarget = webTarget.path("shortener/shortens");
		
		Response response = getKeysWebTarget.request(MediaType.APPLICATION_JSON)
                .get();
        System.out.println("status : " + response.getStatus());
        String result = response.readEntity(String.class);
        System.out.println(result);
	}
	
	
	public static void post(WebTarget webTarget, String url) {
		WebTarget postWebTarget = webTarget.path("shortener");
		StringBuilder sb1 = new StringBuilder ("{\"url\":\"");
		sb1.append(url);
		sb1.append("\"}");
		String inputData = sb1.toString();
		System.out.println(inputData);
		
		Response response = postWebTarget.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(inputData, MediaType.APPLICATION_JSON));
        System.out.println("status : " + response.getStatus());
        String result = response.readEntity(String.class);
        System.out.println(result);
	}
	
	public static void put(WebTarget webTarget, int Id, String url) {
		// Path parameter
		StringBuilder sb1 = new StringBuilder("shortener/"); 
		sb1.append(Id);
		String shortUrl = sb1.toString();
		WebTarget putWebTarget = webTarget.path(shortUrl);
		
		// Body parameter
		StringBuilder sbBody = new StringBuilder ("{\"url\":\"");
		sbBody.append(url);
		sbBody.append("\"}");
		String inputData = sbBody.toString();
		System.out.println(inputData);
		
		Response response = putWebTarget.request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(inputData, MediaType.APPLICATION_JSON));
        System.out.println("status : " + response.getStatus());
        String result = response.readEntity(String.class);
        System.out.println(result);
	}
	
	public static void delete(WebTarget webTarget, int Id) {
		// Path parameter
		StringBuilder sb1 = new StringBuilder("shortener/"); 
		sb1.append(Id);
		String shortUrl = sb1.toString();
		WebTarget deleteWebTarget = webTarget.path(shortUrl);
		
		Response response = deleteWebTarget.request(MediaType.APPLICATION_JSON)
                .delete();
        System.out.println("status : " + response.getStatus());
        String result = response.readEntity(String.class);
        System.out.println(result);
	}
	
	public static void delete(WebTarget webTarget) {
		WebTarget deleteWebTarget = webTarget.path("shortener");
		
		Response response = deleteWebTarget.request(MediaType.APPLICATION_JSON)
                .delete();
        System.out.println("status : " + response.getStatus());
        String result = response.readEntity(String.class);
        System.out.println(result);
	}
	
	
	public static void clientOperations(WebTarget webTarget) {
		Scanner input = new Scanner(System.in);
		//Action loop
		StringBuilder message = new StringBuilder("Select one of the following options : \n");
		message.append("a) Type 1 to Add new URL (POST)\n");
		message.append("b) Type 2 to update a URL (PUT)\n");
		message.append("c) Type 3 to retrieve all Keys (GET)\n");
		message.append("d) Type 4 to retrieve all shortens (GET)\n");
		message.append("d) DO NOT Type 5 to retrieve a specific URL - Redirect (GET)\n");
		message.append("d) Type 6 to delete a URL (DELETE)\n");
		message.append("d) Type 7 to delete all the URLs (DELETE)\n");
		message.append("e) Type 0 to exit\n");
		int action = 1;
		String url;
		int id;
		try {
			do {
				System.out.println(message);
				action = input.nextInt();
				if(action > 0 && action <8) {
					switch(action) {
					case 1 :
						System.out.println("------- POST --------");
						input.nextLine(); // It consumes the \n character
						System.out.println("Give the URL : ");
						url = input.nextLine();
						post(webTarget, url);
						break;
					case 2 :
						System.out.println("------- PUT --------");
						System.out.println("Give the id : ");
						id = input.nextInt();
						input.nextLine(); // It consumes the \n character
						System.out.println("Give the URL : ");
						url = input.nextLine();				
						put(webTarget,id, url);
						break;
					case 3 :
						System.out.println("------- GET keys --------");
						getKeys(webTarget);
						break;
					case 4 :
						System.out.println("------- GET all --------");
						getAllShortens(webTarget);
						break;
					case 5 :
						System.out.println("------- GET by id --------");
						System.out.println("Give the id : ");
						id = input.nextInt();
						getUrlById(webTarget, id);
						break;
					case 6 :
						System.out.println("------- DELETE by id --------");
						System.out.println("Give the id : ");
						id = input.nextInt();
						delete(webTarget, id);
						break;
					case 7 :
						System.out.println("------- DELETE --------");
						delete(webTarget);
						break;
					default:
						break;
					}
					
				}
			}while(action != 0);
		}catch(InputMismatchException e) {
			System.out.println("Invalid input. Make sure you type numbers and not letters");
		}finally {
			input.close();
			System.out.println("Client terminated.");
		}
	}
}

