package com.eMart.main.controller;
import com.eMart.main.entity.Invoice;
import com.eMart.main.entity.InvoiceBody;
import com.eMart.main.model.InvoiceModel;
import com.eMart.main.repository.InvoiceBodyRepositry;
import com.eMart.main.repository.InvoiceRepositry;
import com.eMart.main.service.FileService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Optional;


@RestController
public class MakerController {
    @Autowired
    FileService fileService;
    @Autowired
    InvoiceRepositry invoiceRepositry;
    @Autowired
    InvoiceBodyRepositry invoiceBodyRepositry;

    @PostMapping("/csvupload")
    public List<Invoice> csvupload(@RequestParam("file") MultipartFile file)
    {
        //System.out.println(fileService.readFile(file));
        return fileService.readFile(file);

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
            System.out.println(i[0]);
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
