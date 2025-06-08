package com.example.bank.operation;

import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ATM extends JFrame {

    public ATM() {
        setTitle(" State Bank Of India - ATM ");
        setSize(800, 480);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // center on screen
        setLayout(null);

        // Load the image from src/main/resources/icon/bank_image.jpeg
        URL imageUrl = getClass().getClassLoader().getResource("icon/bank_image.jpeg");

        if (imageUrl != null) {
            ImageIcon icon = new ImageIcon(imageUrl);
            JLabel label = new JLabel(icon);
            label.setBounds(0, 0, 800, 480);
            add(label);
        } else {
            System.out.println("❌ Image not found! Check location or name.");
        }

        setVisible(true);
    }

    public static void main(String[] args) {
        new ATM();
    }
}
