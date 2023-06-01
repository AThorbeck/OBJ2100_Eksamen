/*
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;

public class ListCustomers 
{
	static String cityString = "City";
	static String stateString = "State";
	
	static String url = "jdbc:mysql://localhost:3306/classicmodels";
	static String user = "root";
	static String pass = "";
	
	public static void main(String[] args) 
	{		
		List cityData = new ArrayList();
		List stateData = new ArrayList();

		try 
		{
	    Connection con = null;
		con = DriverManager.getConnection(url, user, pass);
		
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("Select * FROM customers");
		}
		
		while (rs.next()) 
		{
			String dbCity = rs.getString("city");
			String dbState = rs.getString("state");
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
	}
		
		private static void jframe()
		{
		JFrame frame = new JFrame("List Customers");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JRadioButton cityRadio = new JRadioButton("By City");
		cityRadio.setMnemonic(KeyEvent.VK_B);
		cityRadio.setActionCommand(cityString);
		cityRadio.setSelected(true);
		
		cityRadio.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				if (cityRadio.isSelected()) 
				{
					String[] cityData = {dbCity};
					JList<String> cityList = new JList<String>(cityData);
				}
			}
		});
		
		JRadioButton stateRadio = new JRadioButton("By State");
		stateRadio.setMnemonic(KeyEvent.VK_B);
		stateRadio.setActionCommand(stateString);
		
		stateRadio.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				if (stateRadio.isSelected()) 
				{
					String[] stateData = {dbState};
					JList<String> stateList = new JList<String>(stateData);
				}
			}
		});
		
		JButton writeToFile = new JButton("Write to file");
		writeToFile.setBounds(50,200,95,30);
		writeToFile.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (cityRadio.isSelected())
				{
					try
					{
						Statement stCity = con.createStatement();
						ResultSet rsCity = myst.executeQuery("Select * FROM customers WHERE city = cityData");
						
						FileWriter myWriter = new FileWriter(Filename);			// endre når du vet hva filnavnet er 
						myWriter.write(rsCity);				// Skal skrive de kundene fra den selecta byen
						myWriter.close();
						System.out.println("Successfully wrote to the file");
					}
				}
				if (stateRadio.isSelected())
				{
					try
					{
						Statement stState = con.createStatement();
						ResultSet rsState = myst.executeQuery("Select * FROM customers WHERE state = stateData");
						
						FileWriter myWriter = new FileWriter(Filename);
						myWriter.write(rsState);				// Skal skrive de kundene fra den selecta staten
						myWriter.close();
						System.out.println("Successfully wrote to the file");
					}
				}
				catch (IOException e) 
				{
					System.out.println("An error has occurred");
					e.printStackTrace();
				}
			}
		});
	}
}
*/
