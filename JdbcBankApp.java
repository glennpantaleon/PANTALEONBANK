package com.jdbcbank.JDBCBank;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import com.revature.bankexception.UserDoesntExistException;
import com.revature.jdbcbankconnect.JdbcBankConnect;
import com.revature.jdbcbankmodel.RegisteredAccount;
import com.revature.jdbcbankmodel.RegisteredUser;
import com.revature.jdbcbankservice.JdbcBankService;

/**
 * @author glenn
 *
 */

public class JdbcBankApp 
{
	static Scanner scan = new Scanner(System.in); 
	static Connection conn = JdbcBankConnect.getConnection();
	static JdbcBankService bs = new JdbcBankService();
	
    public static void main( String[] args ) throws UserDoesntExistException, SQLException
    {
    	createUser();
    }
    
    //Creates New User for Bank
    /**
     * @throws UserDoesntExistException
     * @throws SQLException
     */
    public static void createUser() throws UserDoesntExistException, SQLException {
    	System.out.println("Please Enter Your Full Name");
    	String fullname = scan.nextLine();
    	System.out.println("Please Create A Username");
    	String username = scan.nextLine();
    	System.out.println("Please Create A Password");
    	String userpassword = scan.nextLine();
    	
    	RegisteredUser newUser = new RegisteredUser(0,fullname, username,userpassword,false);
    	RegisteredAccount newAccnt = new RegisteredAccount(0,0,newUser.getUserid());
    	
    	
//    	bs.insertRegiUser(newUser);
//    	bs.insertRegiAccnt(newAccnt);
//    	JdbcBankService.updateAccnt(newAccnt);
//    	JdbcBankService.allUsers();
//    	
//    	loginSessionUser(newUser,newAccnt);
    	
    	System.out.println(bs.insertRegiUser(newUser));
    	//System.out.println(JdbcBankService.updateUser(newUser));
    	System.out.println(bs.insertRegiAccnt(newAccnt));
    	System.out.println(JdbcBankService.updateAccnt(newAccnt));
    	System.out.println(JdbcBankService.allUsers());
    	System.out.println(JdbcBankService.allAccounts());
    	
    	loginSessionUser(newUser,newAccnt);
    }
    
    public static boolean isSuperUser() {
    	System.out.println("Are You the Admin? Answer 'Yes'");
    	String response = scan.nextLine();
    	System.out.println("Please Enter Your Full Name");
    	String fullname = scan.nextLine();
    	System.out.println("Please Create A Username");
    	String username = scan.nextLine();
    	System.out.println("Please Create A Password");
    	String userpassword = scan.nextLine();
    	
    	if(response == "Yes") {
    	RegisteredUser superUser = new RegisteredUser(0,fullname,username,userpassword,true);
    	superUser.isSuperuser();
		bs.insertRegiUser(superUser);
    	}
		return true;
    }
    
    
    public static void validateRegUser() throws UserDoesntExistException{
    	//To do
    	System.out.println("Please Enter Your Username");
    	String  username = scan.nextLine();
    	System.out.println("Please Enter Your Bank Account Id");
    	int bankaccntid = scan.nextInt();
    	
    	RegisteredUser currentUser = null;
    	RegisteredAccount currentAccnt = null;
    	currentUser = JdbcBankService.getUser(username);
    	currentAccnt = JdbcBankService.getAccntId(bankaccntid);
    	System.out.println("Please Enter Your Password");
    	String userpassword = scan.nextLine();
    	
    	if(!userpassword.equals(currentUser.getPassword())) {
    		System.out.println(currentUser.getPassword());
    		System.out.println("You have typed in the Incorrect Password.");
    		validateRegUser();
    	}
    	else {
    		System.out.println("Welcome Back " + currentUser.getFullname());
    		if(currentUser.isSuperuser()) {
    			try {
					loginSessionSuper(currentUser);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}else {
    			loginSessionUser(currentUser,currentAccnt);
    		}
    	}
    }
    public static void makeDeposit(RegisteredAccount ra) {
    	System.out.println("How much would you like to deposit?");
    	float amntdeposit = (float) scan.nextDouble();
    	ra.depositBalan(amntdeposit);
    	System.out.println("Would You Like to Make Another Deposit:"
    			+ " 1.) Continue Deposit"
    			+ " 2.) Make a Withdraw"
    			+ " 3.) Check Balance"
    			+ " 4.) Exit Out");
    	int option = scan.nextInt();
    	switch (option) {
    	
    	case 1: option = 1;
    			makeDeposit(ra);
    			break;
    	case 2: option = 2;
    			makeWithdraw(ra);
    			break;
    	case 3: option = 3;
    			checkBalance(ra);
    			break;
    	default: System.out.println("Thank You For Using JDBC BANK");
    			break;
    	}
    }
    public static void makeWithdraw(RegisteredAccount ra) {
    	System.out.println("How much would you like to withdraw?");
    	float amntwithdraw = (float) scan.nextDouble();
    	ra.withdrawBalan(amntwithdraw);
    	System.out.println("Would You Like to Make Another Withdraw:"
    			+ " 1.) Continue Withdraw"
    			+ " 2.) Make a Deposit"
    			+ " 3.) Check Balance"
    			+ " 4.) Exit Out");
    	int option = scan.nextInt();
    	switch (option) {
    	
    	case 1: option = 1;
    			makeWithdraw(ra);
    			break;
    	case 2: option = 2;
    			makeDeposit(ra);
    			break;
    	case 3: option = 3;
    			checkBalance(ra);
    			break;
    	default: System.out.println("Thank You For Using JDBC Bank");
    			break;
    	}

    }
    
    public static void checkBalance(RegisteredAccount ra) {
    	System.out.println("Your Current Balance: "+ ra.getAccountbalan());
    	System.out.println("Welcome, Please Make a Selection:"
    			+ " 1.) Deposit"
    			+ " 2.) Withdraw"
    			+ " 3.) Exit Out");
    	int option = scan.nextInt();
    	switch (option) {
    	
    	case 1: option = 1;
    			makeDeposit(ra);
    			break;
    	case 2: option = 2;
    			makeWithdraw(ra);
    			break;
    	default: System.out.println("Thank You For Using JDBC Bank");
    			break;
    	}
    }
    
    public static void loginSessionUser(RegisteredUser ru,RegisteredAccount ra) {
    	System.out.println("Welcome, Please Make a Selection:"
    			+ " 1.) Deposit"
    			+ " 2.) Withdraw"
    			+ " 3.) Check Balance");
    	int selectopt = scan.nextInt();
    	switch (selectopt) {
    	
    	case 1: selectopt = 1;
    			makeDeposit(ra);
    			break;
    	case 2: selectopt = 2;
    			makeWithdraw(ra);
    			break;
    	case 3: selectopt = 3;
    			checkBalance(ra);
    			break;
    	default: System.out.println("This is an Invalid Option");
    			loginSessionUser(ru,ra);
    			break;
    	}
    	
    }
    
    public static void loginSessionSuper(RegisteredUser ru) throws UserDoesntExistException, SQLException {
    	//To do
    	System.out.println("Welcome Admin, What would you like to do? "
    			+ " 1.) View User"
    			+ " 2.) Create User"
    			+ " 3.) Update User"
    			+ " 4.) Delete User");
    	int superopt = scan.nextInt();
    	if (superopt == 1) {
    			JdbcBankService.allUsers();
    	}else if(superopt == 2) {
    			createUser();
    	}else if(superopt == 3) {
    			JdbcBankService.updateUser(ru);
    	}else if(superopt == 4) {
    			JdbcBankService.deleteRegiUser(ru);
    		}else {
    			System.out.println("That is an Invalid Option");
    		}
    	}
    
   
    }

