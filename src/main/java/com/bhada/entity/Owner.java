package com.bhada.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
@Entity
@Table(name = "owner")
public class Owner{
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer ownerId;
	
	@NotNull
    private String fullName;
	@Email
    private String email;
    @NotNull
    @Size(max=10)
    private String mobileNumber;
    @NotNull
    private String countryCode;
    @NotNull
    private boolean isActive;
    @NotNull
    private boolean isOwner;
    @Size(min = 8, max = 25)
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;
	
	private String ownerAddress;
	
	@OneToOne(targetEntity = UniversalAddress.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "owner_address_fk" , referencedColumnName = "addressId")
	private UniversalAddress universalAddress;
	
	@OneToMany(mappedBy="owner")	
//	@OneToMany(targetEntity = Property.class, cascade = CascadeType.ALL)	
//	@JoinColumn(name = "owner_property_fk" , referencedColumnName = "propertyId")
	private Set<Property> properties;
	
	@NotNull
	private boolean userEnabled = true;
	
	@NotNull
	private boolean accountNotExpired = true;
	
	@NotNull
	private boolean credentialNotExpired = true;
	
	@NotNull
	private boolean accountNotLocked = true;
}
