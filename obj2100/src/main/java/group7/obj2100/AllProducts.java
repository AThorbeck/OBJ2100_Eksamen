package group7.obj2100;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AllProducts extends JFrame implements ActionListener {

    JComboBox comboBox;

    AllProducts() {

        this.setLayout(new FlowLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(500, 500);



        String[] products = {"product1", "product2", "product3"};

        JComboBox comboBox = new JComboBox(products);
        comboBox.addActionListener(this);

        setTitle("List of all products");

        // comboBox.setEditable(true); //search and select
        // System.out.println(comboBox.getItemCount());
        // comboBox.addItem("product4"); // adds product

        this.add(comboBox);
        this.pack();
        this.setVisible(true);





    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== comboBox) {
           // System.out.println(comboBox.getSelectedItem());
            System.out.println(comboBox.getSelectedIndex());

        }
}
}
