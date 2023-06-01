package Group7.EksamenMai;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AllOffices extends JFrame implements ActionListener {

    JComboBox comboBox;

    AllOffices() {

        this.setLayout(new FlowLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(500, 500);



        String[] offices = {"office1", "office2", "office3"};

        JComboBox comboBox = new JComboBox(offices);
        comboBox.addActionListener(this);

        setTitle("List of all offices");

        // comboBox.setEditable(true); //search and select
        // System.out.println(comboBox.getItemCount());
        // comboBox.addItem("office4"); // adds product

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
