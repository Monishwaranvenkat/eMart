package com.eMart.main.controller;

import com.eMart.main.Exception.ErrorResponse;
import com.eMart.main.Exception.ExceptionHandler;
import com.eMart.main.entity.Invoice;
import com.eMart.main.entity.InvoiceBody;
import com.eMart.main.model.InvoiceModel;
import com.eMart.main.repository.InvoiceBodyRepositry;
import com.eMart.main.repository.InvoiceRepositry;
import com.eMart.main.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;


@RestController
@CrossOrigin
public class MakerController {
    @Autowired
    FileService fileService;
    @Autowired
    InvoiceRepositry invoiceRepositry;
    @Autowired
    InvoiceBodyRepositry invoiceBodyRepositry;


    @PostMapping("/csvupload")
    public ResponseEntity<Object> csvupload(@RequestParam("file") MultipartFile file){

        try
        {
            return new ResponseEntity<>(fileService.readFile(file), HttpStatus.OK);
        } catch (ExceptionHandler exceptionHandler)
        {
            ErrorResponse errorResponse=new ErrorResponse(exceptionHandler.getMessage(), NOT_ACCEPTABLE, LocalDateTime.now());
            return new ResponseEntity<>(errorResponse, BAD_REQUEST);
        }


    }

    @GetMapping("/getdetails")
    public List<Invoice> getinvoice()
    {
        return invoiceRepositry.findAll();
    }

    @GetMapping("/getbody")
    public Iterable<InvoiceBody> getbody()
    {
        return invoiceBodyRepositry.findAllByInvoiceId(1);
    }
    @GetMapping("/getinvoice")
    public List<InvoiceModel> getInvoice()
    {
        List<InvoiceModel> ls=null;
        for (Object[] i:invoiceRepositry.getMyInvoice()
             ) {
            System.out.println(i[1]);
        }
        //invoiceRepositry.getMyInvoice()
        return ls;
    }
    @GetMapping("/getinvoice/{id}")
    Optional<Invoice> getInvoiceById(@PathVariable("id") int id)
    {
        return invoiceRepositry.findById(id);
    }
}
