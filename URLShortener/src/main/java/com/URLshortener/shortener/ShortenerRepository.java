/* This class serves in-memory objects for Rest API's */
package com.URLshortener.shortener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class ShortenerRepository {
	
		List<Shortener> shortens;
		public static final ShortenerRepository repo = new ShortenerRepository();
		
		private ShortenerRepository()
		{
			shortens = new ArrayList<>();
		}
		
		/**
		 * Return the list with URLs and ids
		 */
		public List<Shortener> getShortens(){
			return shortens;
		}
		
		/**
		 * Return all the stored Keys (ids)
		 */
		public List<Integer> getKeys(){
			List<Integer> Keys = new ArrayList<>();
			for (Shortener a : shortens) {
				Keys.add(a.getId());
			}
			return Keys;
		}
		
		/**
		 * Return the object Shortener with the given id
		 */
		public Shortener getShortenById(int id) {
			
			for (Shortener a : shortens) {
				if(a.getId()==id)
					return a;
			}
			return null;
		}
		
		/**
		 * Store the URL and assign an id to it
		 * Return the assigned id
		 */
		public int create(String url) {
			Shortener a1 = new Shortener();
			a1.setUrl(url);
			if (shortens.size() == 0) {
				a1.setId(0);
			}
			else {
				int[] idsArray = new int[shortens.size()];
				
				//retrieve all Ids and store them in an array
				int i=0;
				for (Shortener a : shortens) {
					idsArray[i] = a.getId();
					i++;
				}
				
				//sort the array
				Arrays.sort(idsArray);
				
				//find the smallest missing id
				a1.setId(shortens.size());
				for (int i1=0 ; i1<shortens.size() ; i1++) {
					if (idsArray[i1] != i1) {
						a1.setId(i1);
						break;
					}
				}
			}
			shortens.add(a1);
			return a1.getId();
		}
		
		/**
		 * Accepts an id and a url as a parameters.
		 * If the id does not exist in the Arraylist it returns null
		 * If exists, the function updates the URL assigned in the specific id
		 * with the new given url.
		 * Return the object Shortener with the given id
		 */
		public Shortener update(int Id, String url) throws Exception {
			int i = 0;
			for (Shortener a : shortens) {
				if(a.getId()==Id) {
					a.setUrl(url);
					shortens.set(i, a);
					System.out.println(shortens);
					return a;
				}		
				i++;
			}
			return null;
		}
		
		/**
		 * Delete the object Shortener with the given id (if exists)
		 * Return 1 if the id exists (and deleted), or 0 if not found
		 */
		//delete a url
		//Returns 1 if deleted
		public int delete(int Id) {
			int i = 0;
			for (Shortener a : shortens) {
				if(a.getId()==Id) {
					shortens.remove(i);
					System.out.println(shortens);
					return 1;
				}		
				i++;
			}
			return 0;
		}
		
		/**
		 * Delete everything in the Arraylist
		 */
		public void delete() {
			shortens.clear();
		}

	}
