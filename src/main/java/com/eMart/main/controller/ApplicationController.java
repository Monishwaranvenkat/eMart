package com.eMart.main.controller;

import com.eMart.main.models.AccountDetails;
import com.eMart.main.repository.AccountDetailsRepositry;
import com.eMart.main.repository.EmployeeDetailsRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ApplicationController {


	@Autowired
    EmployeeDetailsRepositry employeeDetailsRepositry;
	@Autowired
    AccountDetailsRepositry accountDetailsRepositry;


	@RequestMapping("/")
	public String home()
	{
		return "<h1 style='color:red'>welcome to home<h1>";
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
