package com.bhada.utils;

public enum RatingEnum {
	ONE(1),
	TWO(2),
	THREE(3),
	FOUR(4),
	FIVE(5);
	
	private int id;

	private RatingEnum(int id) {
		this.id = id;
	}

	public static RatingEnum getRating(Integer id) {
		if(id == null) {
			return null;
		}
		for(RatingEnum rating : RatingEnum.values()) {
			if(id.equals(rating.getId())) {
				return rating;
			}
		}
		throw new IllegalArgumentException("No matching RatingEnum type for id "+id);
	}

	public int getId() {
		return id;
	}
}
