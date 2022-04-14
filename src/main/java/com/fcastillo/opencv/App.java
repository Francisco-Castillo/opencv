/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.opencv;

import java.io.File;
import javax.swing.UnsupportedLookAndFeelException;
import net.sourceforge.tess4j.Tesseract;

/**
 *
 * @author fcastillo
 */
public class App {

  public static void main(String[] args) throws UnsupportedLookAndFeelException {
    String filePath = "/ubicacion/directorio/imagen.png";
    Tesseract image = new Tesseract();
    try {
      image.setDatapath("/ubicacion/directorio/Descargas/Tess4J/tessdata");
      String imageToString = image.doOCR(new File(filePath));
      System.out.println("Texto encontrado: " + imageToString);
    } catch (Exception e) {
      System.out.println("Error: " + e.getLocalizedMessage());
    }

  }

}
