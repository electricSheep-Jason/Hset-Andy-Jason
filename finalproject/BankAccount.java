package finalproject;


/**
 * Put your bank account code in this class.
 * 
 * @author <Hset Hset Naing>
 *
 */
public class BankAccount { //class declaration 
	
	//this is where I declare my class variables 
	private double balance; 
	private double fee; //this fee is not related to the one in Employee at all 
	private int limit;  //this limit is not related to the one in Employee at all
	static int clickCount; 
	private double userWithdrawal; 
	private double overdraftFee; 

	//constructor is required in order to MAKE the objects. This is where I have to initialize my class variables 
	public BankAccount() { 
		//when I run the program, these will be the default values 
		//if I want to be able to change the values later, I need to create a method in the same class (this class) to allow me to do so 
		balance = 0; 
		fee = 0; 
		limit = 0;		
		clickCount = 0; 
		overdraftFee = 50;
	}

	//makes the class variable and local variable the same. allows other classes to access fee while leaving it private
	public void setFee(double fee) { 
		this.fee = fee;
	}
	
	public void setLimit(int userLimit) { 
		this.limit = userLimit;
	}
	
	//important to create getters for Fee, Limit and Balance so that I can access them from other classes!
	public double getFee() { 
		return fee; 
	}
	
	public double getLimit() {
		return limit; 
	}

	public double getBalance() {
		return balance;
	}
	
	public static int getClickCount() {
		return clickCount;
	}

	public void makeDeposit(double userDeposit) { 
		this.balance += userDeposit; //this makes a deposit. how do you make deposit? Add deposit to balance 
	    //balance = balance + userDeposit
	}
	
	public void makeWithdrawal(double userWithdrawal) {
		this.userWithdrawal = userWithdrawal;
		this.balance -= userWithdrawal;
		//balance = balance - userWithdrawal 
	}
	
	public void deductFee(double userDeduct) {
		userDeduct = userDeduct + fee; 
		this.balance -= userDeduct;
		//userDeduct = userWithdrawal + fee		
		//balance = balance - userWithdrawal - fee
	}
	
	/*
	public void overDraft(double userOverdraft) {
		userOverdraft = userOverdraft + overdraftFee; 
		this.balance -= userOverdraft;
		//balance = balance - userWithdrawal - overdraftFee 	
	}
	*/ 
	
	/*
	 * logic: 
	 * else if conditions (for doing the math and counting down free withdrawals)
	 * start charging fee when withdrawal button is clicked on more than the variable... (e.g. 10 times/setLimit) 
	 * balance = balance - withdrawal - fee (getFee)
	 */
	
}


//need a method in here to bring stuff from Employee and place into BankCustomer 

//whatever changes in Employee must be communicated to this class BankAccount, which then tells the changes to Customer 

/**
logic for bank customer (must be in BankAcccount class) 

[firstly, 0 money in bank account] ---> check cash register/bank account code
- enter number to make deposit 
[accumulate ]
- enter number to make withdrawal
[subtract] 
 - click on button to show account balance (maybe have a PIN number?) [getter to return result]

logic for bank employee 

//task: set monthly fee ($1 per transaction) and transaction limit parameters (max deposit: 1000, max. withdrawal 200) 
//lvl 1: fee is charged for every deposit and withdrawal
//lvl 2: free transactions (let's say 6, accumulation of deposits/withdrawals). fee charged once 6 transactions are exceeded, 
//but only at the end of the month 
//use new method deductMonthlyCharge to the BankAccount class. --> deducts fee, and RESETS transaction count 
//use Math.max(actual transaction count, free transaction count) 
 **/
