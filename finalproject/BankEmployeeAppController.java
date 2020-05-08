package finalproject;

import java.awt.Color;
import java.awt.Font;

// task: set monthly fee and transaction limit parameters 
//lvl 1: fee is charged for every deposit and withdrawal
//lvl 2: free transactions (let's say 6, accumulation of deposits/withdrawals). fee charged once 6 transactions are exceeded, 
//but only at the end of the month 
//use new method deductMonthlyCharge to the BankAccount class. --> deducts fee, and RESETS transaction count 
// use Math.max(actual transaction count, free transaction count) 

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Using the BankCustomerAppController as an example, 
 * build the employee part of the application  here.
 * 
 * 
 * @author <Hset Hset Naing>
 *
 */
public class BankEmployeeAppController implements ActionListener{

	private JTextField userTextInput;
	private JButton SetFeeButton;
	private JButton LimitsButton;
	private JButton InfoButton; //fee and limit that employee sets 
	private JButton overdraftButton; 
	BankAccount account; //allows Employee window to access BankAccount 


	public BankEmployeeAppController(JFrame customerWindow, BankAccount newbankAccount) {

		this.account = newbankAccount; 
		JPanel panel = new JPanel(); 
		panel.setLayout(new GridLayout(6,0)); 
		customerWindow.add(panel);
		//this.account.setFee(); // initialization. You HAVE to give them a value! 
		//this.account.setLimit();  

		userTextInput = new JTextField();
		userTextInput.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		Color red = new Color(255,0,0);
		SetFeeButton = new JButton("Set Withdrawal Penalty Fee");
		SetFeeButton.setFont(new Font("Segoe UI", Font.PLAIN, 40));
		SetFeeButton.setBackground(red);
		LimitsButton = new JButton("Set Transaction Limit");
		Color purple = new Color(255,0,255);
		LimitsButton.setFont(new Font("Segoe UI", Font.PLAIN, 40));
		LimitsButton.setBackground(purple);
		InfoButton = new JButton("Withdrawal Penalty fee:    "+this.account.getFee()+"     Free withdrawals:    "+this.account.getLimit());
		InfoButton.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		InfoButton.setBackground(Color.WHITE);

		Color cadetblue = new Color(95,158,160);
		overdraftButton.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		overdraftButton.setBackground(cadetblue);



		panel.add(userTextInput);
		panel.add(SetFeeButton);
		SetFeeButton.addActionListener(this);
		panel.add(LimitsButton);
		LimitsButton.addActionListener(this);
		panel.add(InfoButton);

		panel.add(overdraftButton);
		LimitsButton.addActionListener(this);

	}


	//Other methods and fields as needed go here.

	public void actionPerformed(ActionEvent e) {

		if(e.getSource().equals(SetFeeButton)) {
			String userInput = userTextInput.getText(); //getText pulls text out of text box and puts it into variable user input 
			try {
				//this means if someone types something in text field, try and turn it into double. if successful, it becomes a double.
				double userFee = Double.parseDouble(userInput); 
				//Process the user fee.
				//you want the class variable ("fee") to reflect the new number employee has entered
				this.account.setFee(userFee); //set fee in bank account 

				String response = "Withdrawal Penalty fee:    "+this.account.getFee()+"     Free withdrawals:    "+this.account.getLimit();
				InfoButton.setText(response); //I need to create string response first! above line!


			} catch (NumberFormatException e1) {
				//The user has input something other than a number. 
				//Do something....
				String response = "Sorry, " + userInput + " is not a valid number. Please enter numbers only.";
				userTextInput.setText(response);
			}
		}
		//process other button clicks here

		if(e.getSource().equals(LimitsButton)) {
			String userInput = userTextInput.getText();
			try {
				int userLimit = Integer.parseInt(userInput); //try and make it an int!
				//Process the userWithdrawal
				this.account.setLimit(userLimit);
				String response = "Withdrawal Penalty fee:    "+this.account.getFee()+"     Free withdrawals:    "+this.account.getLimit();
				InfoButton.setText(response); //I need to create string response first! above line!


			} catch (NumberFormatException e2) {
				//The user has input something other than a number. 
				//Do something....
				String response = "Sorry, " + userInput + " is not a valid number. Please enter numbers only.";
				userTextInput.setText(response);
			}
		}
        
		/*
		if(e.getSource().equals(overdraftButton)) {
			String userInput = userTextInput.getText();
			try {
				int userLimit = Integer.parseInt(userInput); //try and make it an int!
				//Process the userWithdrawal
				this.account.setLimit(userLimit);
				String response = "Withdrawal Penalty fee:    "+this.account.getFee()+"     Free withdrawals:    "+this.account.getLimit();
				InfoButton.setText(response); //I need to create string response first! above line!


			} catch (NumberFormatException e2) {
				//The user has input something other than a number. 
				//Do something....
				String response = "Sorry, " + userInput + " is not a valid number. Please enter numbers only.";
				userTextInput.setText(response);

			}

		}
		*/ 
		
	} //actionPeformed
	
} //class 


/**logic: 
 * fee in both Customer and Employee window must be equal to each other
 * create fee and limit variable in Customer (bc it is already in Employee)
 * create a method that gets the value of fee from employee (cuz this is where you set value of fee! IMPT!)
 * 
 */

