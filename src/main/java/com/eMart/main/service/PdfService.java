package com.eMart.main.service;

import com.eMart.main.entity.Invoice;
import com.eMart.main.entity.InvoiceSummary;
import com.eMart.main.entity.Product;
import com.eMart.main.entity.ProductDetails;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.FontSelector;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

@Service
public class PdfService {

    public void orginalInvoice(Invoice invoice)
    {
        try {
            OutputStream file = new FileOutputStream(new File("HelloWorld.pdf"));
            Document document = new Document();
            PdfWriter.getInstance(document, file);
            PdfPTable fileHeader = getPageheader("PURCHASE INVOICE");

            PdfPTable leftTable = new PdfPTable(2);
            leftTable.addCell(getNotBoderCell("Invoice No:",PdfPCell.ALIGN_LEFT));
            leftTable.addCell(getNotBoderCell(invoice.getId().toString(),PdfPCell.ALIGN_LEFT));
            leftTable.addCell(getNotBoderCell("Invoice Date:",PdfPCell.ALIGN_LEFT)); // pass invoice number
            leftTable.addCell(getNotBoderCell(invoice.getTimeStamp().toString(),PdfPCell.ALIGN_LEFT)); // pass invoice date
            leftTable.addCell(getNotBoderCell("supplier:",PdfPCell.ALIGN_LEFT));
            leftTable.addCell(getNotBoderCell("Flipkart",PdfPCell.ALIGN_LEFT));

            PdfPTable rightTable = new PdfPTable(2);
            rightTable.addCell(getNotBoderCell("Updated Date:",PdfPCell.ALIGN_RIGHT));
            rightTable.addCell(getNotBoderCell("13-Mar-2016",PdfPCell.ALIGN_RIGHT));
            rightTable.addCell(getNotBoderCell("No of Products",PdfPCell.ALIGN_RIGHT)); // pass invoice number
            rightTable.addCell(getNotBoderCell(invoice.getNumberOfProduct().toString(),PdfPCell.ALIGN_RIGHT));

            PdfPTable detailsTable = new PdfPTable(3);
            detailsTable.setWidthPercentage(100);
            PdfPCell invoiceTable = new PdfPCell (leftTable);
            invoiceTable.setBorder(0);
            detailsTable.addCell(invoiceTable);
            detailsTable.addCell(getNotBoderCell("", PdfPCell.ALIGN_LEFT));
            PdfPCell invoiceTable2 = new PdfPCell (rightTable);
            invoiceTable2.setBorder(0);
            detailsTable.addCell(invoiceTable2);

            PdfPTable productTable = new PdfPTable(8); //one page contains 15 records
            productTable.setWidthPercentage(100);
            productTable.setWidths(new float[] { 2,5,5,5,5,3, 3,4});
            productTable.setSpacingBefore(30.0f);

            productTable.addCell(getTableHeaderCell("sno"));
            productTable.addCell(getTableHeaderCell("Product Description"));
            productTable.addCell(getTableHeaderCell("Product Category"));
            productTable.addCell(getTableHeaderCell("Expiry Date"));
            productTable.addCell(getTableHeaderCell("Vendor code"));
            productTable.addCell(getTableHeaderCell("Qty"));
            productTable.addCell(getTableHeaderCell("cost"));
            productTable.addCell(getTableHeaderCell("currency"));

            for (InvoiceSummary invoiceSummary:invoice.getInvoiceSummaries()
            ) {
                productTable.addCell(getTableRowCell(String.valueOf(invoiceSummary.getId())));
                productTable.addCell(getTableRowCell(invoiceSummary.getProductDescription()));
                productTable.addCell(getTableRowCell(invoiceSummary.getProductCategory()));
                productTable.addCell(getTableRowCell(String.valueOf(invoiceSummary.getExpiryDate())));
                productTable.addCell(getTableRowCell(String.valueOf(invoiceSummary.getVendorCode())));
                productTable.addCell(getTableRowCell(String.valueOf(invoiceSummary.getCount())));
                productTable.addCell(getTableRowCell(String.valueOf(invoiceSummary.getCost())));
                productTable.addCell(getTableRowCell(invoiceSummary.getCurrency()));

            }

            PdfPCell summaryLeft = new PdfPCell (getTableRowCell(" "));
            summaryLeft.setBorderWidth(0);
            summaryLeft.setColspan (6);
            productTable.addCell(summaryLeft);
            PdfPTable accounts = new PdfPTable(2);
            accounts.setWidthPercentage(100);
            accounts.addCell(getAccountsCell("Total"));
            accounts.addCell(getAccountsCellR(String.valueOf(invoice.getTotalAmount())));
            PdfPCell summaryRight = new PdfPCell (accounts);
            summaryRight.setColspan (3);
            productTable.addCell(summaryRight);

            PdfPTable describer = new PdfPTable(1);
            describer.setWidthPercentage(100);
            describer.addCell(getdescriptionCell(" "));
            describer.addCell(getdescriptionCell("This invoice is generated by system //" +
                    "if any queries contact the company"));

            document.open();//PDF document opened........


            document.add(fileHeader);
            addEmptyLine(document,3);
            document.add(detailsTable);
            document.add(productTable);
            addEmptyLine(document,3);
            document.add(describer);

            document.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }


    }




//-------------------------------------------------------------------------------------------
public ByteArrayOutputStream DamageReport(List<Product> productList)
{
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    try {
        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);
        PdfPTable fileHeader = getPageheader("Damage Report");

        PdfPTable leftTable = new PdfPTable(2);
        leftTable.addCell(getNotBoderCell("Date:",PdfPCell.ALIGN_LEFT));
        leftTable.addCell(getNotBoderCell("02/05/2001",PdfPCell.ALIGN_LEFT));

        PdfPTable detailsTable = new PdfPTable(3);
        detailsTable.setWidthPercentage(100);

        PdfPCell leftCell = new PdfPCell (leftTable);
        leftCell.setBorder(0);
        detailsTable.addCell(leftCell);
        detailsTable.addCell(getNotBoderCell("", PdfPCell.ALIGN_LEFT));
        detailsTable.addCell(getNotBoderCell("", PdfPCell.ALIGN_LEFT));


        PdfPTable productTable = new PdfPTable(11); //one page contains 15 records
        productTable.setWidthPercentage(100);
        productTable.setWidths(new float[] { 2,5,5,5,5,3, 3,3,3,5,5});
        productTable.setSpacingBefore(30.0f);

        productTable.addCell(getTableHeaderCell("sno"));
        productTable.addCell(getTableHeaderCell("Product Description"));
        productTable.addCell(getTableHeaderCell("Product Category"));
        productTable.addCell(getTableHeaderCell("Expiry Date"));
        productTable.addCell(getTableHeaderCell("Vendor code"));
        productTable.addCell(getTableHeaderCell("Qty"));
        productTable.addCell(getTableHeaderCell("cost"));
        productTable.addCell(getTableHeaderCell("currency"));
        productTable.addCell(getTableHeaderCell("location"));
        productTable.addCell(getTableHeaderCell("lot number"));
        productTable.addCell(getTableHeaderCell("Reason"));


        for (Product product:productList
        ) {
            for (ProductDetails prductDetails:product.getProductDetails()
                 ) {
                productTable.addCell(getTableRowCell(String.valueOf(product.getProductId())));
                productTable.addCell(getTableRowCell(product.getDescription()));
                productTable.addCell(getTableRowCell(product.getCategory().getCategoryName()));
                productTable.addCell(getTableRowCell(String.valueOf(prductDetails.getExpiryDate())));
                productTable.addCell(getTableRowCell(String.valueOf(prductDetails.getSupplierCode())));
                productTable.addCell(getTableRowCell(String.valueOf(prductDetails.getCount())));
                productTable.addCell(getTableRowCell(String.valueOf(prductDetails.getCost())));
                productTable.addCell(getTableRowCell(prductDetails.getCurrency()));
                productTable.addCell(getTableRowCell(prductDetails.getLocation()));
                productTable.addCell(getTableRowCell(prductDetails.getLotNumber().toString()));
                productTable.addCell(getTableRowCell("Product Expires in 2 days"));
            }

        }

        document.open();//PDF document opened........


        document.add(fileHeader);
        addEmptyLine(document,3);
        document.add(detailsTable);
        document.add(productTable);
        addEmptyLine(document,3);
       // document.add(describer);

        document.close();


    } catch (DocumentException e) {
        e.printStackTrace();
    } catch (IOException exception) {
        exception.printStackTrace();
    }
    return  outputStream;
}

    public void createPDF (Invoice invoice){

        try {

            OutputStream file = new FileOutputStream(new File("HelloWorld.pdf"));
            Document document = new Document();
            PdfWriter.getInstance(document, file);


            PdfPTable header = getPageheader("RETURN INVOICE");


            PdfPTable leftTable = new PdfPTable(2);
            leftTable.addCell(getNotBoderCell("Invoice No:",PdfPCell.ALIGN_LEFT));
            leftTable.addCell(getNotBoderCell(invoice.getId().toString(),PdfPCell.ALIGN_LEFT));
            leftTable.addCell(getNotBoderCell("Invoice Date:",PdfPCell.ALIGN_LEFT)); // pass invoice number
            leftTable.addCell(getNotBoderCell(invoice.getTimeStamp().toString(),PdfPCell.ALIGN_LEFT)); // pass invoice date
            leftTable.addCell(getNotBoderCell("supplier:",PdfPCell.ALIGN_LEFT));
            leftTable.addCell(getNotBoderCell("Flipkart",PdfPCell.ALIGN_LEFT));

            PdfPTable rightTable = new PdfPTable(2);
            rightTable.addCell(getNotBoderCell("Updated Date:",PdfPCell.ALIGN_RIGHT));
            rightTable.addCell(getNotBoderCell("13-Mar-2016",PdfPCell.ALIGN_RIGHT));
            rightTable.addCell(getNotBoderCell("No of Products",PdfPCell.ALIGN_RIGHT)); // pass invoice number
            rightTable.addCell(getNotBoderCell(invoice.getNumberOfProduct().toString(),PdfPCell.ALIGN_RIGHT));

            PdfPTable detailsTable = new PdfPTable(3);
           detailsTable.setWidthPercentage(100);

            PdfPCell invoiceTable = new PdfPCell (leftTable);
            invoiceTable.setBorder(0);
            detailsTable.addCell(invoiceTable);
            detailsTable.addCell(getNotBoderCell("", PdfPCell.ALIGN_LEFT));
            PdfPCell invoiceTable2 = new PdfPCell (rightTable);
            invoiceTable2.setBorder(0);
            detailsTable.addCell(invoiceTable2);




           /* FontSelector fs = new FontSelector();
            Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 13, Font.BOLD);
            fs.addFont(font);
            Phrase bill = fs.process("Bill To"); // customer information
            Paragraph name = new Paragraph("Mr.Venkateswara Rao");
            name.setIndentationLeft(20);
            Paragraph contact = new Paragraph("9652886877");
            contact.setIndentationLeft(20);
            Paragraph address = new Paragraph("Kuchipudi,Movva");
            address.setIndentationLeft(20);*/

            PdfPTable productTable = new PdfPTable(9); //one page contains 15 records
            productTable.setWidthPercentage(100);
            productTable.setWidths(new float[] { 2, 5,5,5,5,3 ,2, 3,5});
            productTable.setSpacingBefore(30.0f);

            productTable.addCell(getTableHeaderCell("sno"));
            productTable.addCell(getTableHeaderCell("Product Description"));
            productTable.addCell(getTableHeaderCell("Product Category"));
            productTable.addCell(getTableHeaderCell("Expiry Date"));
            productTable.addCell(getTableHeaderCell("Reason"));
            productTable.addCell(getTableHeaderCell("Vendor code"));
            productTable.addCell(getTableHeaderCell("Qty"));
            productTable.addCell(getTableHeaderCell("cost"));
            productTable.addCell(getTableHeaderCell("currency"));

            for (InvoiceSummary invoiceSummary:invoice.getInvoiceSummaries()
            ) {
                productTable.addCell(getTableRowCell(String.valueOf(invoiceSummary.getId())));
                productTable.addCell(getTableRowCell(invoiceSummary.getProductDescription()));
                productTable.addCell(getTableRowCell(String.valueOf(invoiceSummary.getExpiryDate())));
                productTable.addCell(getTableRowCell(invoiceSummary.getReturnReason()));
                productTable.addCell(getTableRowCell(String.valueOf(invoiceSummary.getVendorCode())));
                productTable.addCell(getTableRowCell(invoiceSummary.getProductCategory()));
                productTable.addCell(getTableRowCell(String.valueOf(invoiceSummary.getCount())));
                productTable.addCell(getTableRowCell(String.valueOf(invoiceSummary.getCost())));
                productTable.addCell(getTableRowCell(invoiceSummary.getCurrency()));
            }


           /* PdfPTable validity = new PdfPTable(1);
            validity.setWidthPercentage(100);
            validity.addCell(getValidityCell(" "));
            validity.addCell(getValidityCell("Warranty"));
            validity.addCell(getValidityCell(" * Products purchased comes with 1 year national warranty \n   (if applicable)"));
            validity.addCell(getValidityCell(" * Warranty should be claimed only from the respective manufactures"));
            */
            PdfPCell summaryLeft = new PdfPCell (getTableRowCell(" "));
            summaryLeft.setBorderWidth(0);
            //summaryL.setBorderWidthBottom(0);
           // summaryL.setBorderWidthLeft(0);
            summaryLeft.setColspan (7);
           // summaryL.setPadding (1.0f);
            productTable.addCell(summaryLeft);

            PdfPTable accounts = new PdfPTable(2);
            accounts.setWidthPercentage(100);
            /*accounts.addCell(getAccountsCell("Subtotal"));
            accounts.addCell(getAccountsCellR("12620.00"));
            accounts.addCell(getAccountsCell("Discount (10%)"));
            accounts.addCell(getAccountsCellR("1262.00"));
            accounts.addCell(getAccountsCell("Tax(2.5%)"));
            accounts.addCell(getAccountsCellR("315.55"));*/
            accounts.addCell(getAccountsCell("Total"));
            accounts.addCell(getAccountsCellR("11673.55"));
            PdfPCell summaryRight = new PdfPCell (accounts);
            summaryRight.setColspan (3);
            productTable.addCell(summaryRight);

            PdfPTable describer = new PdfPTable(1);
            describer.setWidthPercentage(100);
            describer.addCell(getdescriptionCell(" "));
            describer.addCell(getdescriptionCell("This invoice is generated by system //" +
                    "if any queries contact the company"));

            document.open();//PDF document opened........

            //document.add(image);
            document.add(header);
            addEmptyLine(document,3);
            //document.add(new Paragraph("  "));
            document.add(detailsTable);
            //document.add(bill);
           // document.add(name);
           // document.add(contact);
           // document.add(address);
            document.add(productTable);
            addEmptyLine(document,3);
            document.add(describer);

            document.close();

            file.close();

            System.out.println("Pdf created successfully..");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    public static PdfPTable getPageheader(String title) throws IOException, BadElementException {

        Image image = Image.getInstance ("src/main/resources/Emart-02.png");//Header Image
        image.scaleAbsolute(100f, 50f);//image width,height
        FontSelector fs = new FontSelector();
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 15,Font.BOLD,BaseColor.ORANGE);
        PdfPTable headerTable = new PdfPTable(3);
        headerTable.setWidthPercentage(100);
        PdfPCell logo = new PdfPCell(image);
        logo.setPadding(0);
        logo.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        logo.setBorder(PdfPCell.NO_BORDER);
        headerTable.addCell(logo);
        //adding title
        fs.addFont(font);
        Phrase phrase = fs.process(title);
        PdfPCell reportTitle = new PdfPCell(phrase);
        reportTitle.setPadding(5);
        reportTitle.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        reportTitle.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
        reportTitle.setBorder(PdfPCell.NO_BORDER);
        headerTable.addCell(reportTitle);

        phrase = fs.process("");
        PdfPCell emtycell = new PdfPCell(phrase);
        emtycell.setPadding(5);
        emtycell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        emtycell.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
        emtycell.setBorder(PdfPCell.NO_BORDER);
        headerTable.addCell(emtycell);
        headerTable.addCell("");
        //imageTable.addCell(getNoBroderCell("", PdfPCell.ALIGN_RIGHT));
        return headerTable;
    }


    public static PdfPCell getImageCell(Image image, int alignment)
    {
        PdfPCell cell = new PdfPCell(image);
        cell.setPadding(0);
        cell.setHorizontalAlignment(alignment);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    public static PdfPCell getNotBoderCell(String text, int alignment) {
        FontSelector fs = new FontSelector();
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fs.addFont(font);
        Phrase phrase = fs.process(text);
        PdfPCell cell = new PdfPCell(phrase);
        cell.setPadding(5);
        cell.setHorizontalAlignment(alignment);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    public static PdfPCell getBoderedCell(String text) {
        PdfPCell cell = new PdfPCell (new Paragraph (text));
        cell.setHorizontalAlignment (Element.ALIGN_CENTER);
        cell.setPadding (5.0f);
        cell.setBorderColor(BaseColor.LIGHT_GRAY);
        return cell;
    }

    public static PdfPCell getTableHeaderCell(String text) {
        FontSelector fs = new FontSelector();
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10,Font.BOLD);
        fs.addFont(font);
        Phrase phrase = fs.process(text);
        PdfPCell cell = new PdfPCell (phrase);
        cell.setHorizontalAlignment (Element.ALIGN_CENTER);
        cell.setPadding (5.0f);
        return cell;
    }

    public static PdfPCell getTableRowCell(String text) {
        FontSelector fs = new FontSelector();
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10);
        fs.addFont(font);
        Phrase phrase = fs.process(text);
        PdfPCell cell = new PdfPCell (phrase);
        cell.setHorizontalAlignment (Element.ALIGN_CENTER);
        cell.setPadding (5.0f);
        return cell;
    }

    public static PdfPCell getBillFooterCell(String text) {
        PdfPCell cell = new PdfPCell (new Paragraph (text));
        cell.setHorizontalAlignment (Element.ALIGN_CENTER);
        cell.setPadding (5.0f);
        cell.setBorderWidthBottom(0);
        cell.setBorderWidthTop(0);
        return cell;
    }

    public static PdfPCell getValidityCell(String text) {
        FontSelector fs = new FontSelector();
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10);
        font.setColor(BaseColor.GRAY);
        fs.addFont(font);
        Phrase phrase = fs.process(text);
        PdfPCell cell = new PdfPCell (phrase);
        cell.setBorder(0);
        return cell;
    }

    public static PdfPCell getAccountsCell(String text) {
        FontSelector fs = new FontSelector();
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10);
        fs.addFont(font);
        Phrase phrase = fs.process(text);
        PdfPCell cell = new PdfPCell (phrase);
        cell.setBorderWidthRight(0);
        cell.setBorderWidthTop(0);
        cell.setPadding (5.0f);
        return cell;
    }
    public static PdfPCell getAccountsCellR(String text) {
        FontSelector fs = new FontSelector();
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10);
        fs.addFont(font);
        Phrase phrase = fs.process(text);
        PdfPCell cell = new PdfPCell (phrase);
        cell.setBorderWidthLeft(0);
        cell.setBorderWidthTop(0);
        cell.setHorizontalAlignment (Element.ALIGN_RIGHT);
        cell.setPadding (5.0f);
        cell.setPaddingRight(20.0f);
        return cell;
    }

    public static PdfPCell getdescriptionCell(String text) {
        FontSelector fs = new FontSelector();
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10);
        font.setColor(BaseColor.RED);
        fs.addFont(font);
        Phrase phrase = fs.process(text);
        PdfPCell cell = new PdfPCell (phrase);
        cell.setHorizontalAlignment (Element.ALIGN_CENTER);
        cell.setBorder(0);
        return cell;
    }

    private static void addEmptyLine(Document document, int number) throws DocumentException {
        for (int i = 0; i < number; i++) {
            document.add(new Paragraph(" "));
        }
    }

}