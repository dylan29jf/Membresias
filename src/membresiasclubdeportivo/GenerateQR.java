/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package membresiasclubdeportivo;

import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.image.BufferedImage;
import java.util.Hashtable;
import com.google.zxing.BarcodeFormat;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author dylan
 */
public class GenerateQR {

    public BufferedImage generateQRCode(String text, int size) throws WriterException {
        // Configuración del código QR

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Hashtable<EncodeHintType, String> hints = new Hashtable<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, size, size, hints);

        int matrixWidth = bitMatrix.getWidth();
        BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < matrixWidth; x++) {
            for (int y = 0; y < matrixWidth; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }

        return image;
    }

    public List<String> getPrivilegios(String membresia) {

        String premium[] = {"Asiento en palco", "Liguilla completa", "Alimento y Bebidas", "Acceso a foto oficial", "Acceso a Videollamada exclusivas", "Foro de opinión", 
                            "Visitas a entrenamientos", "Firma de autografos","Descuentos en tiendas oficiales","Concursos promocionales"};
        String plus[] = {"Alimentos y bebidas","Visitas a entrenamientos","Firma de autografos","Descuento en tiendas oficiales","Concursos promocionales"};
        
        String regular[] = {"Alimentos y bebidas","Visitas a entrenamientos","Firmas de autografos"};
        
        String basica[] = {"Visitas a entrenamientos","Firmas de autografos"};
        
        List<String> privilegios = new ArrayList<>();
        
        switch(membresia){
            case "premium":
                privilegios = Arrays.asList(premium);
                break;
            case "plus":
                privilegios = Arrays.asList(plus);
                break;
            case "regular":
                privilegios = Arrays.asList(regular);
                break;
            default:
                privilegios = Arrays.asList(basica);
                break;
        }

        return privilegios;
    }
    
    public String generateColor(String membresia) {

        switch (membresia) {
            case "premium":
                return "Black";
            case "plus":
                return "Gold";
            case "regular":
                return "Silver";
            default:
                return "Bonze";
        }

        
    }

}
