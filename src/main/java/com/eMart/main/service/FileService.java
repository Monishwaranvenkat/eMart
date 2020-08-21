package com.eMart.main.service;

import com.eMart.main.Exception.ExceptionHandler;
import com.eMart.main.entity.Invoice;
import com.eMart.main.entity.InvoiceSummary;
import com.eMart.main.repository.InvoiceRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class FileService {

    @Autowired
    InvoiceRepositry invoiceRepositry;
    private String content="";
    public Boolean readFile(MultipartFile file,Integer supplierid) throws ExceptionHandler
    {
        //check the file format
        if(!isCsv(Objects.requireNonNull(file.getOriginalFilename())))
        {
            //System.out.println(generateHash("hello world"));
            throw new ExceptionHandler("Only CSV file accepted");
        }
        //check is the file is empty
        if(file.isEmpty())
        {
            throw new ExceptionHandler("File is empty");
        }

        //invoice object
        Invoice invoice=new Invoice();
        List<String[]> data=extractData(file);

        //Throws exception if the data was unable to extract
        if(data==null)
        {
            throw new ExceptionHandler("Unable to extract data");
        }

        //Inserting data in database
        try {
            String[] line=data.get(0);
            invoice.setNumberOfProduct(Integer.parseInt(line[0]));
            invoice.setTotalAmount(Integer.parseInt(line[1]));
            invoice.setTimeStamp(Timestamp.valueOf(line[2]));
            invoice.setHashCode(line[3]);
            invoice.setSupplierid(supplierid);
            System.out.println(generateHash(content));

            if(!line[3].equals(generateHash(content)))
            {
                content="";
                throw new ExceptionHandler("Hash does'nt match");
            }
            content="";
            if(invoiceRepositry.isInvoiceExist(line[3],line[2],supplierid)>0){
                    throw new ExceptionHandler("Invoice already exist");
            }

         //   System.out.println(invoiceRepositry.isTimeStampExist(line[2]));
            Set<InvoiceSummary> invoiceSummaries=new HashSet<>();
            for (int i=1;i<data.size();i++)
            {
                InvoiceSummary invoiceSummary =new InvoiceSummary();
                line=data.get(i);
                // invoiceBody.setId(Integer.parseInt(line[0]));

                invoiceSummary.setVendorCode(Integer.parseInt(line[1]));
                invoiceSummary.setProductCategory(line[2]);
                invoiceSummary.setProductDescription(line[3]);
                invoiceSummary.setCount(Integer.parseInt(line[4]));
                invoiceSummary.setCost(Double.parseDouble(line[5]));
                invoiceSummary.setCurrency(line[6]);
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                Date cDate=null;
                try {
                     cDate = df.parse(line[7]);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                invoiceSummary.setExpiryDate(cDate);
                invoiceSummary.setInvoice(invoice);
                invoiceSummaries.add(invoiceSummary);
            }
            //System.out.println(invoice);
            invoice.setInvoiceSummaries(invoiceSummaries);
            invoiceRepositry.save(invoice);
        }catch (Exception exception){
            throw new ExceptionHandler(exception.getMessage());
        }
        return true;
    }

    //function to check the format is csv
    private Boolean isCsv(String filename)
    {
        int index = filename.lastIndexOf(".");
        return filename.substring(index + 1).toLowerCase().equals("csv");
    }

    //function to extract data from csv file
    private List<String[]> extractData(MultipartFile file)
    {
        BufferedReader br;
        List<String[]> result = new ArrayList<>();
        try {
            String line;
            InputStream inputStream = file.getInputStream();
            br = new BufferedReader(new InputStreamReader(inputStream));
            boolean isfirst=false;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                if(isfirst)
                content+=line;
                isfirst=true;
                result.add(line.replaceAll("'","").split(","));
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return null;
        }
        return result;
    }


    //Function to generate hash value

    public static String generateHash(String input)
    {
        try {
            // getInstance() method is called with algorithm SHA-224
            MessageDigest md = MessageDigest.getInstance("SHA-224");


            // to calculate message digest of the input string
            // returned as array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);

            // Add preceding 0s to make it 32 bit
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            // return the HashText
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            throw new RuntimeException(noSuchAlgorithmException);
        }
    }

}
