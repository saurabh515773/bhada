package com.bhada.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.bhada.utils.PropertyType;

import lombok.Data;

@Data
@Entity
@Table(name = "property")
public class Property {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer propertyId;
	@NotNull
	private String propertyName;
	@NotNull
	private String propertyFullAddress;
	@NotNull
	private Integer propertyType;
	@NotNull
	private Integer noOfPeopleToAccomodate;
	@NotNull
	private Integer noOfBedrooms;
	@NotNull
	private Integer noOfBathrooms;
	
	private Double overAllRating;
	@NotNull
	private Double propertyPrice;
	@NotNull
	private boolean isActive;
	@NotNull
	private LocalDateTime propertyRegisteredDateTime;
	
	private LocalDateTime propertyDeRegisteredDateTime;
	@NotNull
	private LocalDateTime propertyExpiryDateTime;
	
	private Integer totalSpamsCount;
	@Lob
	private byte[] comments;
	@Lob
	private byte[] reviews;
	@Lob
	private byte[] ratings;
	@Lob
	private byte[] propertyImages;
	@Lob
	private byte[] amenities;
	@NotNull
	@Lob
	private byte[] rules;

	@OneToOne(targetEntity = UniversalAddress.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "property_universalAddress_fk", referencedColumnName = "addressId")
	private UniversalAddress universalAddress;

	@ManyToOne
	@JoinColumn(name = "ownerId")
	private Owner owner;

	
	
	
	
	public PropertyType getPropertyType() {
		return PropertyType.getType(this.propertyType);
	}

	public void setPropertyType(PropertyType propertyType) {
		if (propertyType == null) {
			this.propertyType = null;
		} else {
			this.propertyType = propertyType.getId();
		}
	}




//	public List<AmenitiesEnum> getAmenities() {
//		List<AmenitiesEnum> list = new ArrayList<>();
//		for(Integer i : amenities) {
//			list.add(AmenitiesEnum.getType(i));
//		}
//		return list;
//	}

//	public void setAmenities(List<AmenitiesEnum> amenities) {
//		if(amenities == null) {
//			this.amenities = null;
//		}else {
//			this.amenities.clear();
//			for(AmenitiesEnum amenity : amenities) {
//				this.amenities.add(amenity.getId());
//			}
//		}
//	}



//	public List<RulesEnum> getRules() {
//		List<RulesEnum> list = new ArrayList<>();
//		for(Integer i : rules) {
//			list.add(RulesEnum.getRule(i));
//		}
//		return list;
//	}
//	
//	public void setRules(List<RulesEnum> rules) {
//		if(rules == null) {
//			this.rules = null;
//		}else {
//			this.rules.clear();
//			for(RulesEnum rule : rules) {
//				this.rules.add(rule.getId());
//			}
//		}
//	}



	

//	public Map<RatingEnum, Integer> getRatings() {
//		Map<RatingEnum, Integer> map = new HashMap<>();
//		for(Entry<Integer, Integer> rating : ratings.entrySet()) {
//			map.put(RatingEnum.getRating(rating.getKey()), rating.getValue());
//		}
//		return map;
//	}
//	
//	public void setRatings(Map<RatingEnum, Integer> ratings) {
//		if(ratings == null) {
//			this.ratings = null;
//		}else {
//			this.ratings.clear();
//			for(Entry<RatingEnum, Integer> rating : ratings.entrySet()) {
//				if(rating!=null && rating.getKey()!=null) {
//					this.ratings.put(rating.getKey().getId(), rating.getValue());
//				}
//			}
//		}
//	}

	
}
