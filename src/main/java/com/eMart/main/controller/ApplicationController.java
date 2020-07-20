package com.eMart.main.controller;

import com.eMart.main.entity.AccountDetails;
import com.eMart.main.model.AuthenticationRequest;
import com.eMart.main.model.AuthenticationResponse;
import com.eMart.main.repository.AccountDetailsRepositry;
import com.eMart.main.repository.EmployeeDetailsRepositry;
import com.eMart.main.service.MyUserDetailsService;
import com.eMart.main.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@CrossOrigin
public class ApplicationController {
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

	@RequestMapping("/")
	public String home()
	{
		return "<h1 style='color:red'>welcome to home<h1>";
	}


	@PostMapping("/authenticate")
	public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest authenticationRequest) throws Exception
	{
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							authenticationRequest.getUsername(),authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			//System.out.println(e);
			throw new RuntimeException("Bad credentials my monish",e);
		}
		final UserDetails userDetails=userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		return ResponseEntity.ok(new AuthenticationResponse(jwtbUtil.generateToken(userDetails)));
	}

	@RequestMapping("/user")
	public String user()
	{
		return "hi user";
	}

	@RequestMapping("/getemployees")
	public List<AccountDetails> getEmployees()
	{
		return accountDetailsRepositry.findAll();
	}


	@PostMapping(path = "/addemployee")
	public List<AccountDetails> addEmployee(@RequestBody AccountDetails accountDetails)
	{
		accountDetailsRepositry.save(accountDetails);
		return accountDetailsRepositry.findAll();
	}

	@DeleteMapping("/deleteemployee")
	public List<AccountDetails> deleteEmployee(@RequestParam("id") int id)
	{
		accountDetailsRepositry.deleteById(id);
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
