package com.URLshortener.shortener;

import java.util.ArrayList;
import java.util.List;

public class ShortenerRepository {
	
		List<Shortener> shortens;
		
		public ShortenerRepository()
		{
			shortens = new ArrayList<>();
			
			Shortener a1 = new Shortener();
			a1.setLongURL("www.amazon.com");
			a1.setShortURL("www.short.com");
			a1.setId(1);
			
			Shortener a2 = new Shortener();
			a2.setLongURL("www.yahoo.com");
			a2.setShortURL("www.short.com");
			a2.setId(2);
			
			shortens.add(a1);
			shortens.add(a2);
		}
		
		public List<Shortener> getShortens(){
			return shortens;
		}
		
		public Shortener getShortenById(int id) {
			
			for (Shortener a : shortens) {
				if(a.getId()==id)
					return a;
			}
			return null;
		}
		
		public void create(Shortener a1) {
			shortens.add(a1);
		}
		
		public Shortener update(int Id, Shortener a1) {
			for (Shortener a : shortens) {
				if(a.getId()==Id)
					a = a1;
					return a;
			}
			return null;
		}

	}
