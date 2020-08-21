package com.eMart.main.schedules;

import com.eMart.main.entity.Product;
import com.eMart.main.entity.ProductDetails;
import com.eMart.main.repository.ProductRepositry;
import com.eMart.main.service.EmailService;
import com.eMart.main.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class TestSchedule {
    @Autowired
    EmailService emailService;
    @Autowired
    ProductRepositry productRepositry;
    @Autowired
    PdfService pdfService;
    @Scheduled(fixedRate = 20000)
    public void fixedDelaySch() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 3);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date newDate=null;
        try {
            newDate=df.parse(df.format(cal.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<Product> product=productRepositry.getExpiredProducts(newDate);
        if(product.size()!=0)
        {

                emailService.sendMail(pdfService.DamageReport(product));

        }

    }
}
