package com.bhada.utils;

public enum AmenitiesEnum {
	WIFI(1), 
	WATER_FILTER(2), 
	GEYSER(3), 
	SWIMMING_POOL(4), 
	EXTRA_BEDDING(5), 
	INDOOR_GAMES(6), 
	OUTDOOR_GAMES(7), 
	BALCONY(8), 
	CARETAKER(9), 
	BBQ(10),
	PARKING(11), 
	ELEVATOR(12), 
	BONFIRE(13), 
	POWERBACKUP(14), 
	BAR(15), 
	CCTV(16), 
	FACILITY_FOR_SPECIALLYABLEDGUEST(17),
	BREAKFAST(18),
	STUDY_TABLE(19);

	private int id;

	private AmenitiesEnum(int id) {
		this.id = id;
	}

	public static AmenitiesEnum getType(Integer id) {
		if(id == null) {
			return null;
		}
		for(AmenitiesEnum amenity : AmenitiesEnum.values()) {
			if(id.equals(amenity.getId())) {
				return amenity;
			}
		}
		throw new IllegalArgumentException("No matching AmenitiesEnum type for id "+id);
	}

	public int getId() {
		return id;
	}
}
