package com.eMart.main.controller;
import com.eMart.main.models.Invoice;
import com.eMart.main.models.InvoiceBody;
import com.eMart.main.repository.InvoiceBodyRepositry;
import com.eMart.main.repository.InvoiceRepositry;
import com.eMart.main.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;


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
}
