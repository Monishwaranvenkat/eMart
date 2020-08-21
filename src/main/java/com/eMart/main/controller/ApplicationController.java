package com.eMart.main.controller;

import com.eMart.main.Exception.ExceptionHandler;
import com.eMart.main.entity.*;
import com.eMart.main.model.AuthenticationRequest;
import com.eMart.main.model.AuthenticationResponse;
import com.eMart.main.model.SupplierList;
import com.eMart.main.repository.*;
import com.eMart.main.service.*;
import com.eMart.main.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.Join;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.springframework.http.HttpStatus.*;


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
	SupplierRepositry supplierRepositry;
	@Autowired
	BarcodeService barcodeService;
	@Autowired
	ProductRepositry productRepositry;

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
	public List<AccountDetails> addEmployee(@RequestBody AccountDetails accountDetails) throws ExceptionHandler {


		try{
			accountDetailsRepositry.save(accountDetails);
		}catch (Exception exception){
			throw new ExceptionHandler("user already exist");
		}

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


	@PostMapping("/addsupplier")
	public Boolean addSupplier(@RequestBody Supplier supplier) throws ExceptionHandler {
		try{
			supplierRepositry.save(supplier);
		}catch (Exception exception){
			throw new ExceptionHandler("supplier already exist");
		}
		return true;
	}

	@GetMapping("/getallsupplier")
	public List<Supplier> getAllSuppliers(){
		return supplierRepositry.findAll();
	}

	@GetMapping("/getsupplier")
	public Object getsupplier(@RequestParam("id")Integer id)
	{
		try{
			return supplierRepositry.findBysupplierid(id);
		}catch (Exception exception){
			return new ResponseEntity<>("User Not Found", NOT_FOUND);
		}
	}
	@GetMapping("/getsuppliersbyname")
	public List<SupplierList> getSupplierByName()
	{
		List<SupplierList> supplierList=new ArrayList<SupplierList>();
		for (Object[] object:supplierRepositry.findAllCompanyname()) {

			supplierList.add(new SupplierList((Integer)object[0],(String)object[1]));
		}
		return supplierList;
	}
	@Autowired
	CategoryRepositry categoryRepositry;
	@Autowired
	PdfService pdfService;
	@Autowired
	InvoiceRepositry invoiceRepositry;
	@Autowired
	ProductService productService;
	@Autowired
	EmailService emailService;
	@Autowired
	MessageService messageService;
	@Autowired
	ProductDetailsRepositry productDetailsRepositry;
	@PostMapping(value = "/test")
	public List<Product> test() throws Exception {
		//emailService.sendMail();
		//messageService.sendSMS();
	//productService.getCategory("Drink");
	//	productService.verifyProducts(invoice.getInvoiceSummaries());

	//productRepositry.save(product);
		//throw new ExceptionHandler("my exception");
		//System.out.println(categoryRepositry.findAll());
		return productRepositry.findAll();

	}

	/*@PostMapping("/addproducts")
	public String addproducts(@RequestBody Invoice products)
	{
		for (InvoiceSummary product:products.getInvoiceSummaries()
			 ) {

			if(! isFound(product.getProductCategory()))
			{
				//complete the code below
				//send cofirmation request
				//if ok
				//addCategory(product.getProductCategory())
				//else return "products not added
			}
			addProduct(product);
		}
		return "products added";
	}*/


}
