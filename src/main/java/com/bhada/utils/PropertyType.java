package com.bhada.utils;

public enum PropertyType {
	ONE_BHK(1), 
	TWO_BHK(2), 
	THREE_BHK(3), 
	VILLA(4), 
	ENTIRE_HOUSE(5), 
	COTTAGES(6);
	
	private int id;
	
	private PropertyType(int id) {
		this.id = id;
	}
	
	public static PropertyType getType(Integer id) {
		if(id == null) {
			return null;
		}
		for(PropertyType proptype : PropertyType.values()) {
			if(id.equals(proptype.getId())) {
				return proptype;
			}
		}
		throw new IllegalArgumentException("No matching type for id "+id);
	}
	
	public int getId() {
		return id;
	}
}
