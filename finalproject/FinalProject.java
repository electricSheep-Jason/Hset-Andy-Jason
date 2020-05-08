package finalproject;

import javax.swing.JFrame;

/**
 * This class has the  main method required to run the BankAccount application
 * 
 * @author <Hset Hset Naing >
 * Honor Code Upheld <Hset Hset Naing>
 * 
 *
 */

public class FinalProject {
	
	private FinalProject(){
			
	}
	/**
	 * Run the application by opening two JFrame windows and 
	 * instantiate the various classes that make up the application.
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame customerWindow = new JFrame("CP122 Honors Assignment 1 : Bank Customer Window");
		customerWindow.setSize(1000,700);
		customerWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		customerWindow.setLocation(300, 200);
		
//		JFrame employeeWindow = new JFrame("CP122 Honors Assignment 1 : Bank Employee Window");
//		employeeWindow.setSize(800,500);
//		employeeWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		employeeWindow.setLocation(1000, 300);
		
		BankAccount newbankAccount = new BankAccount(); 
		LoginAppController login1 = new LoginAppController(customerWindow,newbankAccount);
		
		//CreateNewAccAppController login = new CreateNewAccAppController(customerWindow,newbankAccount);

//		new BankEmployeeAppController(employeeWindow,newbankAccount);
//	    new BankCustomerAppController(customerWindow,newbankAccount);
	    
	     
		customerWindow.setVisible(true);
//		employeeWindow.setVisible(true);
	}
}
