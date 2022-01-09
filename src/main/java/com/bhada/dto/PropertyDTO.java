package com.bhada.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.bhada.entity.UniversalAddress;
import com.bhada.utils.AmenitiesEnum;
import com.bhada.utils.PropertyType;
import com.bhada.utils.RatingEnum;
import com.bhada.utils.RulesEnum;

import lombok.Data;

@Data
public class PropertyDTO {
	

	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer propertyId;
	
	@NotNull
	private String propertyFullAddress;
	
	@OneToOne(targetEntity = UniversalAddress.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "property_universalAddress_fk" , referencedColumnName = "addressId")
	private UniversalAddress universalAddress;
	
	@NotNull
	private String propertyName;
	
	@NotNull
	private Integer propertyType;
	
	public PropertyType getPropertyType() {
		return PropertyType.getType(this.propertyType);
	}
	
	public void setPropertyType(PropertyType propertyType) {
		if(propertyType == null) {
			this.propertyType = null;
		}else {
			this.propertyType = propertyType.getId();
		}
	}
	
	
	@NotNull
	private Integer noOfPeopleToAccomodate;
	@NotNull
	private Integer noOfBedrooms;
	@NotNull
	private Integer noOfBathrooms;
	
	@Lob 
	private List<Integer> amenities;
	
	public List<AmenitiesEnum> getAmenities() {
		List<AmenitiesEnum> list = new ArrayList<>();
		for(Integer i : amenities) {
			list.add(AmenitiesEnum.getType(i));
		}
		return list;
	}
	
	public void setAmenities(List<AmenitiesEnum> amenities) {
		if(amenities == null) {
			this.amenities = null;
		}else {
			this.amenities.clear();
			for(AmenitiesEnum amenity : amenities) {
				this.amenities.add(amenity.getId());
			}
		}
	}
	
	@NotNull
	@Lob
	private List<Integer> rules;
	
	public List<RulesEnum> getRules() {
		List<RulesEnum> list = new ArrayList<>();
		for(Integer i : rules) {
			list.add(RulesEnum.getRule(i));
		}
		return list;
	}
	
	public void setRules(List<RulesEnum> rules) {
		if(rules == null) {
			this.rules = null;
		}else {
			this.rules.clear();
			for(RulesEnum rule : rules) {
				this.rules.add(rule.getId());
			}
		}
	}
	
	private Integer totalSpamsCount;
	
	@Lob
	private List<String> comments;
	
	@Lob
	private List<String> reviews;
	
	@Lob
	private Map<Integer, Integer> ratings;
	
	public Map<RatingEnum, Integer> getRatings() {
		Map<RatingEnum, Integer> map = new HashMap<>();
		for(Entry<Integer, Integer> rating : ratings.entrySet()) {
			map.put(RatingEnum.getRating(rating.getKey()), rating.getValue());
		}
		return map;
	}
	
	public void setRatings(Map<RatingEnum, Integer> ratings) {
		if(ratings == null) {
			this.ratings = null;
		}else {
			this.ratings.clear();
			for(Entry<RatingEnum, Integer> rating : ratings.entrySet()) {
				if(rating!=null && rating.getKey()!=null) {
					this.ratings.put(rating.getKey().getId(), rating.getValue());
				}
			}
		}
	}
	
	private Double overAllRating;
	
	@NotNull
	private Double propertyPrice;
	
	@NotNull
	private boolean isActive;
	
	@Lob
	private List<String> propertyImages;
	
	@NotNull
	private LocalDateTime propertyRegisteredDateTime;
	
	private LocalDateTime propertyDeRegisteredDateTime;
	
	@NotNull
	private LocalDateTime propertyExpiryDateTime;


}
