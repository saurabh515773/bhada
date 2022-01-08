package com.bhada.security;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bhada.entity.Owner;
import com.bhada.repository.OwnerRepository;

@Service
public class MyUserDetailService implements UserDetailsService{
	
	@Autowired
	private OwnerRepository ownerRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return getOwnerUserDetails(username);
	}
	
	private UserDetails getOwnerUserDetails(String id) {
		Optional<Owner> ownerUser = ownerRepo.findById(Integer.valueOf(id));
		if (ownerUser.isPresent()) {
			Owner ownerObj = ownerUser.get();
			List<GrantedAuthority> authorities = getUserAuthority();
			Boolean userEnabled = false;
			Boolean accountNotExpired = false;
			Boolean credentialNotExpired = false;
			Boolean accountNotLocked = false;
			
			User newUser = new User(String.valueOf(ownerObj.getOwnerId()), ownerObj.getPassword()
					, userEnabled
					, accountNotExpired
					, credentialNotExpired
					, accountNotLocked
					, authorities);
			return newUser;
		} else {
			throw new UsernameNotFoundException("username not found");
		}
	}

	private List<GrantedAuthority> getUserAuthority() {
		Set<GrantedAuthority> authorities = new HashSet<>();
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>(authorities);
		return grantedAuthorities;
	}

}
