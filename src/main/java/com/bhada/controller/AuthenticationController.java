package com.bhada.controller;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bhada.entity.Owner;
import com.bhada.entity.TempStorage;
import com.bhada.repository.OwnerRepository;
import com.bhada.repository.TempStorageRepository;
import com.bhada.security.JwtUtils;
import com.bhada.security.MyUserDetailService;
import com.bhada.valueObject.AuthBodyVO;
import com.bhada.valueObject.RsaCryptography;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
	
	@Autowired
	TempStorageRepository tsq;

	@Autowired
	OwnerRepository ownerRepository;

	@Autowired
	MyUserDetailService myUserDetailsService;

	@Autowired
	JwtUtils jwtUtils;

	@SuppressWarnings("rawtypes")
	@GetMapping("/prelogin")
	public ResponseEntity preLogin() {

		KeyPair pair;
		try {
			pair = new RsaCryptography().generateKeyPair();
			String pub_algo = pair.getPublic().getAlgorithm();
			String pub_format = pair.getPublic().getFormat();

			String pri_algo = pair.getPrivate().getAlgorithm();
			String pri_format = pair.getPrivate().getFormat();

			// Store private Key into datstore for login method to utilize
			Map<String, String> persist = new HashMap<>();
			persist.put("pri_algo", pri_algo);
			persist.put("pri_format", pri_format);
			persist.put("pub_algo", pub_algo);
			persist.put("pub_format", pub_format);
			String salt = getAlphaNumericString(8);
			persist.put("salt", salt);

			ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		    ObjectOutputStream out = new ObjectOutputStream(byteOut);
		    out.writeObject(persist);
			
			TempStorage ts = new TempStorage();
			ts.setKvObj(byteOut.toByteArray());
			ts = tsq.save(ts);

			// send public Key to caller with index to encrypt sensitive data and pass
			Map<String, Object> model = new HashMap<>();
			model.put("pub_algo", pub_algo);
			model.put("pub_format", pub_format);
			model.put("key_index", ts.getId());
			model.put("otp_req", false);
			model.put("otp_isalpha", false);
			model.put("salt", salt);

			return ResponseEntity.ok(model);

		} catch (NoSuchAlgorithmException | NoSuchProviderException e) {
			e.printStackTrace();
			throw new BadCredentialsException("Invalid email/password supplied");
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new BadCredentialsException("Invalid email/password supplied");
		}
	}


	@SuppressWarnings("rawtypes")
	@PostMapping("/login")
	public ResponseEntity login(@RequestBody AuthBodyVO data) throws Exception {

		Optional<Owner> ownerObj = ownerRepository.findById(Integer.valueOf(data.getId()));

		if (ownerObj.isPresent()) {
			Owner owner = ownerObj.get();

			if (this.autentication(owner, data)) {
				return new ResponseEntity<>(createUserAuthenticatedResp(owner, data), HttpStatus.ACCEPTED);
			}
			Map<String, Object> model = new HashMap<>();
			model.put("message", "Invalid username/password supplied");
			return new ResponseEntity<>(model, HttpStatus.UNAUTHORIZED);

		} else {
			Map<String, Object> model = new HashMap<>();
			model.put("message", "Invalid username/password supplied");
			return new ResponseEntity<>(model, HttpStatus.UNAUTHORIZED);
		}
	}

	public Boolean autentication(Owner owner, AuthBodyVO data) {
		if (data.getKeyIndex() == null) {
			return false;
		}
		if(!tsq.existsById(data.getKeyIndex())) {
			return false;
		}
		if (data.getPassword().equalsIgnoreCase(owner.getPassword())) {
			return true;
		} else {
			return false;
		}
	}

	private Map<String, Object> createUserAuthenticatedResp(Owner owner, AuthBodyVO data) {

		final UserDetails userDetails = myUserDetailsService.loadUserByUsername(data.getId());

		final String token = jwtUtils.generateToken(userDetails);

		Map<String, Object> model = new HashMap<>();
		model.put("token", token);
		model.put("FullName", owner.getFullName());
		model.put("Email", owner.getEmail());
		model.put("PhoneNumber", owner.getMobileNumber());
		model.put("username", owner.getOwnerId());
		model.put("success", true);
		try {
			tsq.deleteById(data.getKeyIndex());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	public String getAlphaNumericString(int n) {

		// chose a Character random from this String
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

		// create StringBuffer size of AlphaNumericString
		StringBuilder sb = new StringBuilder(n);

		for (int i = 0; i < n; i++) {

			// generate a random number between
			// 0 to AlphaNumericString variable length
			int index = (int) (AlphaNumericString.length() * Math.random());

			// add Character one by one in end of sb
			sb.append(AlphaNumericString.charAt(index));
		}

		return sb.toString();
	}
}