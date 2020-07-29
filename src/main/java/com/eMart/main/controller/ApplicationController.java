package com.eMart.main.controller;

import com.eMart.main.Exception.ExceptionHandler;
import com.eMart.main.entity.AccountDetails;
import com.eMart.main.model.AuthenticationRequest;
import com.eMart.main.model.AuthenticationResponse;
import com.eMart.main.repository.AccountDetailsRepositry;
import com.eMart.main.repository.EmployeeDetailsRepositry;
import com.eMart.main.service.MyUserDetailsService;
import com.eMart.main.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin
public class ApplicationController  {
	@Autowired
	private AuthenticationManager  authenticationManager;
	@Autowired
	MyUserDetailsService userDetailsService;
	@Autowired
    EmployeeDetailsRepositry employeeDetailsRepositry;
	@Autowired
    AccountDetailsRepositry accountDetailsRepositry;

	@Autowired
	JwtUtil jwtbUtil;




	@PostMapping("/authenticate")
	public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest authenticationRequest) throws Exception
	{
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							authenticationRequest.getUsername(),authenticationRequest.getPassword()));

		} catch (BadCredentialsException e) {
			throw new Exception(e);
		}catch (AccountStatusException e1)
		{
			throw new Exception(e1);

		}
		final UserDetails userDetails=userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String role=userDetails.getAuthorities().stream().findFirst().get().toString();
		return ResponseEntity.ok(new AuthenticationResponse(jwtbUtil.generateToken(userDetails),role,""));
	}

	/*
	@GetMapping("/user/{id}")
	public List<AccountDetails> user(@PathVariable("id") String id) throws ExceptionHandler {
		try {
			accountDetailsRepositry.deleteById(Integer.parseInt(id));
		}catch (Exception exception)
		{
			throw new ExceptionHandler("user not found");
		}
		return accountDetailsRepositry.findAll();
	}*/

	@RequestMapping("/getemployees")
	public List<AccountDetails> getEmployees()
	{
		return accountDetailsRepositry.findAll();
	}

	@GetMapping("/getemployees/{username}")
	public Optional<AccountDetails> getEmployee(@RequestParam("username")String username)
	{
		return accountDetailsRepositry.findByEmployeeDetailsEmail(username);
	}


	@PostMapping(path = "/addemployee")
	public List<AccountDetails> addEmployee(@RequestBody AccountDetails accountDetails)
	{
		accountDetailsRepositry.save(accountDetails);
		return accountDetailsRepositry.findAll();
	}

	@DeleteMapping("/deleteemployee")
	public List<AccountDetails> deleteEmployee(@RequestParam("id") int id) throws ExceptionHandler {
		try {
			accountDetailsRepositry.deleteById(id);
		}catch (Exception exception)
		{
			throw new ExceptionHandler("user not found");
		}
		return accountDetailsRepositry.findAll();
	}

	@PutMapping("/updateemployee/{id}")
	public List<AccountDetails> updateEmployee(@PathVariable int id, @RequestBody AccountDetails accountDetails)
	{
		AccountDetails userObject=accountDetailsRepositry.findById(id).orElseThrow();
		userObject.setRole(accountDetails.getRole());
		userObject.setIsactive(accountDetails.getIsactive());
		userObject.setIslocked(accountDetails.getIslocked());
		userObject.getEmployeeDetails();
		accountDetailsRepositry.save(userObject);
		return accountDetailsRepositry.findAll();
	}



}
