package gui;


import javax.swing.*;
import java.awt.*;

public class AllOffices {

    JFrame frame = new JFrame();
    JLabel label = new JLabel("Here is a list of all offices:");


    AllOffices() {


        label.setFont(new Font(null, Font.PLAIN,20));
        label.setBounds(70,30,2000,140);

        frame.add(label);


        frame.setTitle("List of all offices");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setLayout(null);
        frame.setVisible(true);


    }
}
