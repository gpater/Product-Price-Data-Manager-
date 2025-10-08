package domain;

import java.util.HashMap;

public class Product {
	private String name; 
	private String alias;
	private String category;
	private HashMap<Year, Integer> years;
	
	public Product(String theName, String theAlias, String theCategory) {
		name = theName;
		alias = theAlias;
		category = theCategory;
		years = new HashMap<>();
	}
	
	public void addYear(Year y, Integer price) {
		years.put(y, price);
	}

	public String getName() {
		return name;
	}

	public String getAlias() {
		return alias;
	}

	public String getCategory() {
		return category;
	}

	public HashMap<Year, Integer> getYears() {
		return years;
	}
	
	
}
