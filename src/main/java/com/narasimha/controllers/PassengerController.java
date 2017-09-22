package com.narasimha.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.narasimha.beans.PassengerProfile;
import com.narasimha.service.CreditCardService;
import com.narasimha.service.PassengerService;

@RestController
@RequestMapping("/passenger")
public class PassengerController {

	@Autowired
	CreditCardService cservice;

	@Autowired
	PassengerService pservice;

	@RequestMapping(value = "/{profile_id}", method = RequestMethod.DELETE, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> deletePassenger(@PathVariable int profile_id) {
		try {
			pservice.deletePassengerProfile(profile_id);
			return new ResponseEntity<>(profile_id, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/{profile_id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })

	public ResponseEntity<?> getPassenger(@PathVariable int profile_id) {

		PassengerProfile passenger = pservice.getPassengerProfile(profile_id);
		if (passenger == null) {
			return new ResponseEntity<>(passenger, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(passenger, HttpStatus.ACCEPTED);
		}
	}

	@RequestMapping(value = "/", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> addPassenger(@RequestBody PassengerProfile pass) {

		PassengerProfile passenger = pservice.addPassengerProfile(pass);

		if (passenger.getProfile_id() < 1) {
			return ResponseEntity.noContent().build();
		} else {

			return new ResponseEntity<>(passenger, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> updateEmployee(@RequestBody PassengerProfile pass) {
		try {
			PassengerProfile passenger = pservice.updatePassengerProfile(pass);
			return new ResponseEntity<>(passenger, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
