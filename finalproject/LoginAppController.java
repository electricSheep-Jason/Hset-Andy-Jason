package finalproject;

//LOGIN PAGE - data should already be stored and ready to be read here 

import java.awt.Color;
import java.awt.Font; 
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class LoginAppController implements ActionListener{

	private JButton createNewButton;
	private JButton loginButton; 
	private JTextField userID;
	private JPasswordField PIN; 
	
	//bold black words 
	private JLabel userIDLabel;
	private JLabel PINLabel;
	private JLabel newUser; 
	
	Scanner sensitiveData; //for UserID and PIN 
	File sensitiveDataFile; 
	PrintWriter sensitiveDataWriter; 
	
	
	private BankAccount account;
	private BankCustomerAppController customer; 
	private JFrame viewer; 
	private CreateNewAccAppController createNewAcc; 
	

	public LoginAppController(JFrame viewer, BankAccount account){
		this.viewer = viewer;
		this.account = account;
		
		JPanel panel = new JPanel(); 
		panel.setLayout(new GridLayout(7,0)); //x is for rows, y is for column 
		viewer.add(panel);
		
		
		//LABEL for userID
		userIDLabel = new JLabel();
		userIDLabel.setFont(new Font("Segoe UI", Font.BOLD, 40));
		userIDLabel.setText("User ID: ");
		
		//TEXTFIELD for userID
		userID = new JTextField();
		userID.setFont(new Font("Segoe UI", Font.PLAIN, 40));
		
		//LABEL for PIN
		PINLabel = new JLabel();
		PINLabel.setFont(new Font("Segoe UI", Font.BOLD, 40));
		PINLabel.setText("PIN: ");
		
		//TEXTFIELD for PIN
		PIN = new JPasswordField();
		PIN.setFont(new Font("Segoe UI", Font.PLAIN, 40));
		

		//LOGIN BUTTON 
	    Color pink = new Color(254,127,156);
		loginButton = new JButton("Login");
		loginButton.setFont(new Font("Segoe UI", Font.BOLD, 40));
		loginButton.setBackground(pink);
		
		
		//newUser LABEL 
		newUser = new JLabel();
		newUser.setFont(new Font("Segoe UI", Font.BOLD, 40));
		newUser.setText("New User?");
		
		
		
		//NEW ACCOUNT BUTTON
		Color purple = new Color(102, 51, 153);
		createNewButton = new JButton("Create new account");
		createNewButton.setFont(new Font("Segoe UI", Font.BOLD, 40));
		createNewButton.setForeground(Color.WHITE);
		createNewButton.setBackground(purple);

		panel.add(userIDLabel);
		panel.add(userID);
		
		panel.add(PINLabel);
		panel.add(PIN);
		
		panel.add(loginButton);
		
		panel.add(newUser);
		panel.add(createNewButton);
		
		createNewButton.addActionListener(this);
		loginButton.addActionListener(this);
		
		viewer.add(panel);
		
	}

	public void actionPerformed(ActionEvent e) {
		//if user presses on Create New button, go to that page
		if(e.getSource().equals(createNewButton)) {
			createNewButton.setVisible(false);
			removeContent();
			createNewAcc = new CreateNewAccAppController(viewer, account);
		}

		//if user presses Login button, 
		else if (e.getSource().equals(loginButton)) {
			boolean is_valid_login = true;

			try {
				/*logic:
				 * Java should know which file to open 
				 * while file? the one user has typed into the User ID textfield
				 * if cannot be found, then show error message
				 * Otherwise, open the file in the directory with that name
				 * Read the info and compare 
				 */

				//read file to check userID and PIN
                
				BufferedReader reader = new BufferedReader(new FileReader(userID.getText() + ".txt")); //reading the file with its name = user's input into field  
				//System.out.println(reader.readLine());
				
				//assign new boolean variable, then 
				//compare the text in the field with the password saved in file 
				
				String read_user_ID = reader.readLine(); //(first line of text is assigned to String read_user_ID
    			boolean is_valid_id = read_user_ID.equals(userID.getText()); 
    			System.out.println(userID.getText());
				//true/false?

				String read_PIN = reader.readLine(); 
				boolean is_valid_pin = read_PIN.equals(String.valueOf(PIN.getPassword()));
				
				System.out.println(is_valid_pin);

				//make it such that login is valid only if id and pin are valid as well
				is_valid_login = is_valid_pin && is_valid_id;
  
				reader.close();
				
				if (is_valid_login) {
					loginButton.setVisible(false);
					removeContent();
					customer = new BankCustomerAppController(viewer,account);
				}

			} catch (Exception event) { 
				//prevent from going forward - display user id & pin is incorrect 
				System.out.println(event);
				String response2 = "Incorrect User ID/PIN!";
				userID.setText(response2);
				
			}

		} //bracket for else if 


		/*if password is correct, go to BankCustomerAppController class
		 * else show error message 
		 * String response = "Error! Wrong UserID / PIN. Please try again.";
		 * loginButton.setText(response);
		 * 
		 */

		
		
		//only perform function below if userID and PIN are correct 
		//loginButton.setVisible(false);
		//removeContent();
		//customer = new BankCustomerAppController(viewer,account);

	}
		
	
		
	 
		
	
	
	
	
		public void removeContent() {
			createNewButton.setVisible(false);
			loginButton.setVisible(false);
			userID.setVisible(false);
			PIN.setVisible(false);
			newUser.setVisible(false);
			userIDLabel.setVisible(false);
			PINLabel.setVisible(false);
		}


}
			
			
