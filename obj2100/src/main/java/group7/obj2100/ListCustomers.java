import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ListCustomers extends JPanel
{
	static String cityString = "City";
	static String stateString = "State";	
	
	public static void main(String[] args)
	{	
		JFrame frame = new JFrame("List Customers");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JRadioButton cityRadio = new JRadioButton("By City");
		cityRadio.setMnemonic(KeyEvent.VK_B);
		cityRadio.setActionCommand(cityString);
		cityRadio.setSelected(true);
		
		cityRadio.addActionListener(new ActionListener("City Radio")
				{
					public void actionPerformed(ActionEvent e) 
					{
						if (cityRadio.isSelected()) 
						{
							String[] data = {"Norway", "Sweden", "Denmark"};
							JList<String> cityList = new JList<String>(data);
						}
					}
				}
		
		JRadioButton stateRadio = new JRadioButton("By State");
		stateRadio.setMnemonic(KeyEvent.VK_B);
		stateRadio.setActionCommand(stateString);
		
		stateRadio.addActionListener(new ActionListener("State Radio")
		{
			public void actionPerformed(ActionEvent e) 
			{
				if (stateRadio.isSelected()) 
				{
					String[] data = {"California", "Florida", "New York"};
					JList<String> stateList = new JList<String>(data);
				}
			}
		}
		
		JButton write = new JButton("Write customer list based on selected criteria");
		
		
	}	
}
