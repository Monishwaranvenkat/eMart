package com.eMart.main.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.EAN13Writer;
import com.google.zxing.oned.UPCAWriter;
import com.google.zxing.qrcode.QRCodeWriter;
import org.krysalis.barcode4j.impl.code128.Code128;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.impl.upcean.EAN13Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class BarcodeService {

    public BufferedImage generateBarcode() throws Exception{
        String text = "20080200001";

        Code128Bean barcodeGenerator=new Code128Bean();
        //EAN13Bean barcodeGenerator = new EAN13Bean();
        BitmapCanvasProvider canvas =
                new BitmapCanvasProvider(160, BufferedImage.TYPE_BYTE_BINARY, false, 0);

        barcodeGenerator.generateBarcode(canvas, text);
        return canvas.getBufferedImage();

       /*
        int width = 300;
        int height = 100; // change the height and width as per your requirement
        String imageFormat = "png"; // could be "gif", "tiff", "jpeg"
        BitMatrix bitMatrix = null;
            bitMatrix = new EAN13Writer().encode(text, BarcodeFormat.EAN_13, width, height);
            return MatrixToImageWriter.toBufferedImage(bitMatrix);
           // MatrixToImageWriter.writeToStream(bitMatrix, imageFormat, new FileOutputStream(new File("qrcode_97802017507991.png")));
        */

    }
}
