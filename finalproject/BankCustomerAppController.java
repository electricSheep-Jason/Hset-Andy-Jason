package finalproject;

import java.awt.Color;
import java.awt.Font; 
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BankCustomerAppController  implements ActionListener {

	private JTextField userTextInput;
	private JButton depositButton;
	private JButton withdrawalButton; 
	private JButton ShowAccBalance;
	private JButton CustomerInfo; //info about limits/fee that customer can see 

	private JButton logoutButton; 
	private LoginAppController loginApp; 
	private JFrame viewer; 
	
	
	private BankAccount account;
	int clickCount; 

	/**
	 * Construct your customer bank account window here in the controller constructor.
	 * @param viewer the JFrame window that displays the application
	 * With no access modifier this constructor is visible at the package level.
	 */
	public BankCustomerAppController(JFrame viewer, BankAccount account){
		this.account = account; //make them the same, bc prev same name but not same thing 
        clickCount = 0;
        this.viewer = viewer;
		/*
		 * This code creates a panel to layout the various components of the 
		 * bank account application
		 * 
		 * Try various settings on the grid layout
		 * or perhaps other layout managers to make your application look nice.
		 */
		JPanel panel = new JPanel(); 
		panel.setLayout(new GridLayout(5,0)); //x is for rows, y is for column 

		/*
		 * Create the objects that make up the GUI
		 */

		//blank field for typing 
		userTextInput = new JTextField();
		userTextInput.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		//deposit button 
		Color green = new Color(159, 218, 64);
		depositButton = new JButton("Make a deposit");
		depositButton.setFont(new Font("Segoe UI", Font.PLAIN, 40));
		depositButton.setBackground(green);

		//withdrawal button
		Color blue = new Color(100,149,237);
		withdrawalButton = new JButton("Make a withdrawal");
		withdrawalButton.setFont(new Font("Segoe UI", Font.PLAIN, 40));
		withdrawalButton.setBackground(blue);

		//yellow acc balance button
		ShowAccBalance = new JButton("Show Account Balance");
		Color yellow = new Color(255,255,0);
		ShowAccBalance.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		ShowAccBalance.setBackground(yellow);

		//customer info button 
		CustomerInfo = new JButton("Fee:    "+this.account.getFee()+"     Free withdrawals:    "+this.account.getFee()+"   (Click to refresh)");
		CustomerInfo.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		CustomerInfo.setBackground(Color.WHITE);

		
		//logout button
		Color indianred = new Color(205,92,92);
		logoutButton = new JButton("Logout");
		logoutButton.setFont(new Font("Segoe UI", Font.PLAIN, 40));
		logoutButton.setBackground(indianred);



		/*
		 * Wire everything together
		 */
		panel.add(userTextInput);
		panel.add(depositButton);
		depositButton.addActionListener(this);
		panel.add(withdrawalButton);
		withdrawalButton.addActionListener(this);
		panel.add(ShowAccBalance);
		ShowAccBalance.addActionListener(this);
		panel.add(CustomerInfo);
		CustomerInfo.addActionListener(this);
		panel.add(logoutButton);
		logoutButton.addActionListener(this);
		viewer.add(panel);
		viewer.setVisible(true);



	}

	/**
	 * This method processes the user button clicks
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource().equals(depositButton)) {
			String userInput = userTextInput.getText();
			try {
				double userDeposit = Double.parseDouble(userInput);
				//Process the userDeposit
				this.account.makeDeposit(userDeposit);
				//following line of code: action has to occur immediately after deposit
				String response = "Current balance $"+this.account.getBalance(); //automatic update happens here, so put here instead of under ShowAccBalance
				ShowAccBalance.setText(response); //ShowAccBalance because you want the string response to happen in that field, not blank space at top


			} catch (NumberFormatException e1) {
				//The user has input something other than a number. 
				//Do something....
				String response = "Sorry, " + userInput + " is not a valid number. Please enter numbers only.";
				userTextInput.setText(response);
			}
		}
		//process other button clicks here

		if(e.getSource().equals(withdrawalButton)) {
			
			//assign name userInput to the user's input in the place which is JTextField called userTextinput.
			//GET that text. 
			String userInput = userTextInput.getText(); 
		
			//clickCount = clickCount + 1;//this should mean: add 1 to clickCount every time withdrawal button is clicked 
			clickCount++; 
			
			//if clickcount is more than set limit, start deducting fee. else process user withdrawal as per normal. 
			try {
				double userWithdrawal = Double.parseDouble(userInput); //userWithdrawal is text input. try forcing it into a double
				if(clickCount > this.account.getLimit()) {
					this.account.deductFee(userWithdrawal); 
					//what does this line above "this.account.deductFee(userWithdrawal);" mean? 
					//call on method deductFee, use that method on the user in put 
					String response = "Current balance $"+this.account.getBalance(); //automatic update happens here, so put here instead of under ShowAccBalance
					ShowAccBalance.setText(response);
				}
				if(userWithdrawal > this.account.getBalance()){
					
					//aesthetics 
					withdrawalButton.setBackground(Color.RED);
					String response = "Overdraft";
					withdrawalButton.setText(response);
					
					//action of deducting overdraft fee
					//this.account.overDraft(userWithdrawal);
					//String response2 = "Current balance $"+this.account.getBalance();
					//ShowAccBalance.setText(response2);
					
					overdraft();	//disables button after 5 overdrafts 
				}
				
					
				
				else {
					//Process the userWithdrawal
					this.account.makeWithdrawal(userWithdrawal);
					String response = "Current balance $"+this.account.getBalance(); //automatic update happens here, so put here instead of under ShowAccBalance
					ShowAccBalance.setText(response);
				} 


			} catch (NumberFormatException e2) {
				//The user has input something other than a number. 
				//Do something....
				String response = "Sorry, " + userInput + " is not a valid number. Please enter numbers only.";
				userTextInput.setText(response);
			}
		}
		// button for show account balance 
		if(e.getSource().equals(ShowAccBalance)) {
			String response = "Current balance: $"+this.account.getBalance(); //response is a local variable. this line is making a new string 
			ShowAccBalance.setText(response); //setText argument is a string 

		} 
		if(e.getSource().equals(CustomerInfo)) {
			//code that gets the value 
			this.account.getFee(); //this java statement is also called initialization 
			this.account.getLimit();
			String response = "Withdrawal Penalty Fee:    "+this.account.getFee()+"     Free withdrawals:    "+this.account.getLimit()+"   (Click to refresh)";
			CustomerInfo.setText(response); //I need to create string response first! above line!
		}
		if(e.getSource().equals(logoutButton)) {
			logoutButton.setVisible(false);
			removeContent();
			loginApp = new LoginAppController(viewer, account);
		}



	}
	
	public void removeContent() {
		userTextInput.setVisible(false);
		depositButton.setVisible(false);
		withdrawalButton.setVisible(false);
	    ShowAccBalance.setVisible(false);
		CustomerInfo.setVisible(false);
		logoutButton.setVisible(false);
	}
	 
	public void overdraft() {
		if (clickCount >= 5) {
			withdrawalButton.setEnabled(false);	
		}
		
		//this doesn't re-enable the button 
		/*if(this.account.getBalance() >= 0) {
			withdrawalButton.setEnabled(true);
		}
		*/ 
	}
	
}


/*class declarations:
 *private JButton loginButton;
 *private JTextField passcode; 
 *private JTextField userName;
 *
 *in constructor:
 *loginButton = new JButton("Login");
 *passcode = new JTextField("Enter Password");
 *
 *panel.add(UserName);
 *panel.add(passcode)
 *panel.add(loginButton);
 *loginButton.addActionListener(this);
 */




