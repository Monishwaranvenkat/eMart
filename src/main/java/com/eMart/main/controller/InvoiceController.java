package com.eMart.main.controller;

import com.eMart.main.entity.Invoice;
import com.eMart.main.entity.Product;
import com.eMart.main.repository.InvoiceSummaryRepositry;
import com.eMart.main.repository.InvoiceRepositry;
import com.eMart.main.service.FileService;
import com.eMart.main.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin
public class InvoiceController {
    @Autowired
    FileService fileService;
    @Autowired
    InvoiceRepositry invoiceRepositry;
    @Autowired
    InvoiceSummaryRepositry invoiceSummaryRepositry;
    @Autowired
    ProductService productService;

    //to upload the csv file to database
    @PostMapping("/csvupload")
    public Boolean csvupload(@RequestParam("file") MultipartFile file,@RequestParam("supplierid") Integer supplierid){
            fileService.readFile(file,supplierid);
            return true;
    }

    //returns the header of the invoice
    @GetMapping("/getinvoice")
    public List<Invoice> getInvoice()
    {
        List<Invoice> invoices=new ArrayList<Invoice>();
        for (Object[] objects:invoiceRepositry.getMyInvoice()
             ) {
            invoices.add(new Invoice((Integer)objects[0]
                    ,(Integer)objects[1],(Integer)objects[2]
                    ,(Timestamp) objects[3],(Integer)objects[4],(Boolean) objects[5]));
        }

        return invoices;
    }

    //returns invoice by id
    @GetMapping("/getinvoicebyid")
    Optional<Invoice> getInvoiceById(@RequestParam("id") int id)
    {
        return invoiceRepositry.findById(id);
    }

    //return invoice that are not verified
    @GetMapping("/nonverifeidinvoice")
    Optional<Invoice[]> getNonverifeidInvoice()
    {
        return invoiceRepositry.getNonVerifiedInvoice();
    }

    //update the invoice details
    @PutMapping("/updateinvoice")
   String updateInvoice(@RequestBody Invoice invoice)
    {
        if(productService.verifyProducts(invoice.getInvoiceSummaries()))

        invoiceRepositry.save(invoice);
        return "true";
    }

}
