package finalproject;

//CREATE NEW ACCOUNT PAGE

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font; 
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

//for actually writing data to file 
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.io.*;
import java.util.*; 

public class CreateNewAccAppController implements ActionListener{

	private JButton nextButton;
	private BankAccount account;
	private JFrame viewer; 
	
	private JTextField userID; 
	private JLabel userIDLabel;
	
	private JTextField PIN; 
	private JLabel PINLabel;
	
	private BankCustomerAppController customer; 
	
	private String actualuserID; //created because i need to call it from my storingData() method, tho I created it first in actionPerform method
	private String actualPIN; 
	
	Scanner sensitiveData; //for UserID and PIN 
	File sensitiveDataFile; 
	PrintWriter sensitiveDataWriter; 
	
	int clickCount;
	
	//public static final int PASSWORD_LENGTH = 8;
	
	public CreateNewAccAppController(JFrame viewer, BankAccount account){
		
		
		this.viewer = viewer;
		this.account = account;
		
		JPanel panel = new JPanel(new BorderLayout());
		panel.setLayout(new GridLayout(6,0)); //x is for rows, y is for column 
		//panel.setLayout(null);
	
		

		userIDLabel = new JLabel();
		userIDLabel.setFont(new Font("Segoe UI", Font.BOLD, 40));
		userIDLabel.setText("User ID: ");
		//userIDLabel.setSize(150,150);
		//userIDLabel.setLocation(50,300);
		//userIDLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		userID = new JTextField();
		userID.setFont(new Font("Segoe UI", Font.PLAIN, 40));
		//userID.setSize(150,150);
		//userID.setLocation(0,300);
		
		PINLabel = new JLabel();
		PINLabel.setFont(new Font("Segoe UI", Font.BOLD, 40));
		PINLabel.setText("PIN: ");
	    //PINLabel.setSize(150,150);
	    //PINLabel.setLocation(0,600);
		
		
		PIN = new JTextField();
		PIN.setFont(new Font("Segoe UI", Font.PLAIN, 40));
		//PIN.setSize(150,150);
		//PIN.setLocation(0,900);
		
		
		
		
		Color green = new Color(1, 168, 32);
		nextButton = new JButton("Next");
		nextButton.setFont(new Font("Segoe UI", Font.BOLD, 40));
		nextButton.setBackground(green);
		
        
		
		panel.add(userIDLabel);
		panel.add(userID);
	
		
		panel.add(PINLabel);
		panel.add(PIN);
		
		
		panel.add(nextButton);

		nextButton.addActionListener(this);
		viewer.add(panel);
		viewer.setVisible(true);
		
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(nextButton)) {
			
			System.out.println("first");
			//first, get the user input
			actualuserID = userID.getText(); //beware: userID is variable for JTextField. actualuserID is the actual user input
			actualPIN = PIN.getText();
			
			System.out.println("second");
			
			//then, place restrictions on the stuff that's been retrieved 
			boolean login_restric = loginRestrict();  // boollean can be values true or false 
			//loginRestrict(actualuserID, actualPIN);  
			
			System.out.println("third");
			
			/*
			if(loginRestrict(actualuserID, actualPIN) == false) { //if user doesn't follow restrictions, it's "false". they can't click "next", error message will pop up
				//print error message in JTextField 
				String response = "Error! Incorrect User ID/PIN. Please try again.";
				userID.setText(response);
				PIN.setText(response);
			}
			*/ 
			
			
			//finally, write data to file
			storingData(); 
			
			
			
			if (!login_restric) {
				removeContent(); 
				customer = new BankCustomerAppController(viewer,account);	
			}
			
		}

	}
	
	public void removeContent() {
		userIDLabel.setVisible(false);
		nextButton.setVisible(false);
		userID.setVisible(false);
		PINLabel.setVisible(false);
		PIN.setVisible(false);
	}
	
	public void storingData() {
		
		//sensitiveDataFile is a file. the following line of code creates new files with the name actual...txt 
		sensitiveDataFile = new File(actualuserID+".txt"); //this converts it into a .txt file
		clickCount = 0;
		
		while(sensitiveDataFile.exists()) {
			clickCount++; //increase by 1 each time 
			sensitiveDataFile = new File(actualuserID+clickCount+".txt"); //rename file if duplicate exists
		}
		/*logic: 
		 * use a while loop 
		 * check if new user has inputted the same username (d_filmore) as previous user
		 * in order to do that, read through the file to see
		 * if yes, then add a number to end of file (d_filmore1)
		 * if someone else is also named d_filmore, then the third file should be named (d_filmore2)
		 * last digit increases by 1 each time 
		 */
		try {
			sensitiveDataWriter = new PrintWriter(sensitiveDataFile); //making the writer here 
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		//this is supposed to write the data to the file 
		try { 
			FileWriter writer = new FileWriter(sensitiveDataFile);
			BufferedWriter bwr = new BufferedWriter(writer); 
			bwr.write(actualuserID);
			bwr.write("\r\n"); // \n means line 
			bwr.write(actualPIN); 
			
			bwr.close();

		} 
		catch (IOException ioe) { 
			ioe.printStackTrace();
		}

		
		
	} //storingData() method
	
	/*
	public boolean loginRestrict(String actualuserID, String actualPIN) {  //for now, PIN is also a string. change to ints only, limit of 6 ints later
        
		clickCount = 0; 

		this.actualuserID = actualuserID;
		this.actualPIN = actualPIN; 

		for (int i = 0; i < actualPIN.length(); i++) {
			clickCount++;
			if (actualPIN.length() < 6 || actualPIN.length() > 6) {
				String response = "Error: PIN cannot be more or less than 6 characters";
				PIN.setText(response);

			}
			
			}
		


		for (int i = 0; i < actualuserID.length(); i++) {
			clickCount++;

			if (actualuserID.length() > 15) {
				String response2 = "Error: User ID cannot be more than 15 characters";
				userID.setText(response2);

			}
			
			}
			
			return false; 
		}
		*/ 
	
	//realised it might be better to do while loop as I don't know num. of chars user has inputted.
	
	public boolean loginRestrict() { //you want this method to return something so it must be public BOOLEAN not public void anymore!!!
		//TRUE if they did it wrong 
		//FALSE if done right 
		
		//boolean rightPW = true; 
		//int i = 0;
		//while (rightPW) {
			//CORRECT: i don't think actualPIN.length() actually retrieves num of chars in string
			//find syntax that actually counts num of chars in string 
			//System.out.println(i);
			//i++;
			String strPIN = actualPIN;
			strPIN.length( ); 
			
			String strID = actualuserID;
			strID.length();
			
			// strPIN.length() == 6
			if(strPIN.length() != 6 || strID.length() > 15) { //!=6 means not equal to 6
				//rightPW = false; 
				String response = "Error: PIN can only be 6 characters";
				PIN.setText(response);
				String response2 = "Error: User ID cannot be more than 15 characters";
				userID.setText(response2);
				
				return true; 
			} 
			else {
				return false;
			}

			
			
			
		//}
	}
//	
//	//getter 
//	public static File getSensitiveDataFile() {
//		//search for the file 
//		//OPEN THE FILE
//		//READ THE FILE
//		//P.S. cannot return "sensitiveDataFile" - you need the specific name of the file
//		System.out.println(arg0);
//		return actualuserID+"".txt"; 
//	}
	
	public void findFile(String name,File file)
    {
        File[] list = file.listFiles();
        if(list!=null)
        for (File fil : list)
        {
            if (fil.isDirectory())
            {
                findFile(name,fil);
            }
            else if (name.equalsIgnoreCase(fil.getName()))
            {
                System.out.println(fil.getParentFile());
            }
        }
    }
		
} //class 





		
			