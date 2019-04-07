package com.URLshortener.shortener;

import java.util.ArrayList;
import java.util.List;

public final class ShortenerRepository {
	
		List<Shortener> shortens;
		public static final ShortenerRepository repo = new ShortenerRepository();
		
		private ShortenerRepository()
		{
			shortens = new ArrayList<>();
		}
		
		public List<Shortener> getShortens(){
			return shortens;
		}
		
		public List<Integer> getKeys(){
			List<Integer> Keys = new ArrayList<>();
			for (Shortener a : shortens) {
				Keys.add(a.getId());
			}
			return Keys;
		}
		
		public Shortener getShortenById(int id) {
			
			for (Shortener a : shortens) {
				if(a.getId()==id)
					return a;
			}
			return null;
		}
		
		public void create(String url) {
			Shortener a1 = new Shortener();
			a1.setLongURL(url);
			if (shortens.size() == 0) {
				a1.setId(0);
			}
			else {
				a1.setId(shortens.size()+1);
			}
			shortens.add(a1);

			System.out.println(shortens);
		}
		
		public Shortener update(int Id, Shortener a1) {
			int i = 0;
			for (Shortener a : shortens) {
				if(a.getId()==Id) {
					a = a1;
					shortens.set(i, a);
					System.out.println(shortens);
					return a;
				}		
				i++;
			}
			return null;
		}
		
		public void delete(int Id) {
			int i = 0;
			for (Shortener a : shortens) {
				if(a.getId()==Id) {
					shortens.remove(i);
					System.out.println(shortens);
				}		
				i++;
			}
		}
		
		public void delete() {
			shortens.clear();
		}

	}
