package com.eMart.main.service;

import com.eMart.main.models.Invoice;
import com.eMart.main.models.InvoiceBody;
import com.eMart.main.repository.InvoiceBodyRepositry;
import com.eMart.main.repository.InvoiceRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

@Service
public class FileService {

    @Autowired
    InvoiceRepositry invoiceRepositry;
    @Autowired
    InvoiceBodyRepositry invoiceBodyRepositry;
    public List<Invoice> readFile(MultipartFile file)
    {
        if(file.isEmpty())
        {
            //return "upload the file with content";
        }
        if(!isCsv(Objects.requireNonNull(file.getOriginalFilename())))
        {
            //return "Only CSV file accepted";
        }
        Invoice invoice =new Invoice();
        List<String[]> data=extractData(file);
        String[] line=data.get(0);
        invoice.setNumberOfProduct(Integer.parseInt(line[0]));
        invoice.setTotalAmount(Integer.parseInt(line[1]));
        invoice.setTimeStamp(Timestamp.valueOf(line[2]));
        //invoice.setHashCode(line[3]);

        Set<InvoiceBody> invoiceBodies=new HashSet<>();
        for (int i=1;i<data.size();i++)
        {
            InvoiceBody invoiceBody=new InvoiceBody();
             line=data.get(i);
             invoiceBody.setId(Integer.parseInt(line[0]));
             invoiceBody.setVendorCode(Integer.parseInt(line[1]));
             invoiceBody.setProductCategory(line[2]);
             invoiceBody.setProductDescription(line[3]);
             invoiceBody.setCount(Integer.parseInt(line[4]));
             invoiceBody.setCost(Double.parseDouble(line[5]));
             invoiceBody.setCurrency(Double.parseDouble(line[6]));
             invoiceBody.setExpiryDate(new Date(line[7]));
             invoiceBody.setInvoice(invoice);
            invoiceBodies.add(invoiceBody);
        }
        invoice.setInvoiceBodies(invoiceBodies);
        invoiceRepositry.save(invoice);
        return invoiceRepositry.findAll();
    }

    private Boolean isCsv(String filename)
    {
        int index = filename.lastIndexOf(".");
        return filename.substring(index + 1).toLowerCase().equals("csv");
    }

    private List<String[]> extractData(MultipartFile file)
    {
        BufferedReader br;
        List<String[]> result = new ArrayList<>();
        try {
            String line;
            InputStream inputStream = file.getInputStream();
            br = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = br.readLine()) != null) {
                result.add(line.replaceAll("\"","").split(","));
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return result;
    }

}
