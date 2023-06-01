package group7.obj2100;

import javax.swing.*;
import java.awt.*;

public class ImportOrders {




   public static void load() {

       JFrame frame = new JFrame();
       JLabel label = new JLabel("Here is bulk import of orders:"); // title of page

        label.setFont(new Font(null, Font.PLAIN,20));
        label.setBounds(70,30,2000,140);

        frame.add(label);


        frame.setTitle("Bulk import of orders");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setLayout(null);
        frame.setVisible(true);


    }
}
