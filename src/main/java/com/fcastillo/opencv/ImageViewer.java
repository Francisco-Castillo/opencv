/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.opencv;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import org.opencv.core.Mat;

/**
 *
 * @author fcastillo
 */
public class ImageViewer {

  private JLabel jlabel;

  public void show(Mat img) {

  }

  public void show(Mat img, String name) throws UnsupportedLookAndFeelException {
    setSystemLookAndFeel();
    JFrame frame = createJFrame(name);
    Image loadedImage = toBufferedImage(img);
    jlabel.setIcon(new ImageIcon(loadedImage));
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }

  private JFrame createJFrame(String windowName) {
    JFrame frame = new JFrame(windowName);
    jlabel = new JLabel();
    final JScrollPane imageScrollPane = new JScrollPane(jlabel);
    imageScrollPane.setPreferredSize(new Dimension(640, 480));
    frame.add(imageScrollPane, BorderLayout.CENTER);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    return frame;
  }

  private void setSystemLookAndFeel() throws UnsupportedLookAndFeelException {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (UnsupportedLookAndFeelException e) {
      e.printStackTrace();
    }
  }

  public Image toBufferedImage(Mat matrix) {
    int type = BufferedImage.TYPE_BYTE_GRAY;
    if (matrix.channels() > 1) {
      type = BufferedImage.TYPE_3BYTE_BGR;
    }
    int bufferSize = matrix.channels() * matrix.cols() * matrix.rows();
    byte[] buffer = new byte[bufferSize];
    matrix.get(0, 0, buffer); // get all the pixels
    BufferedImage image = new BufferedImage(matrix.cols(), matrix.
            rows(), type);
    final byte[] targetPixels = ((DataBufferByte) image.getRaster().
            getDataBuffer()).getData();
    System.arraycopy(buffer, 0, targetPixels, 0, buffer.length);
    return image;
  }

}
