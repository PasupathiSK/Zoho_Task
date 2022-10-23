package userlayer;

import java.util.List;
import java.util.Map;


import banking.BankInterface;
import banking.BankLogical;
import check.UserDefinedException;
import pojoclasses.Account;
import pojoclasses.Customer;
import pojoclasses.Login;
import pojoclasses.Transaction;
import pojoclasses.User;
import mysql.ConnectionDB;

public class CustomerTask {
	ConnectionDB con=new ConnectionDB();
	BankInterface bInterface=new BankLogical();
	// get user activity status  CASE 1  Customer
	public void userActivityStatus(int customerId,boolean flag)throws UserDefinedException {
		bInterface.userActivityStatus(customerId, flag);
	}
	// show customer info   EX 2 customer and admin
	public User getUserInfo(int customerId)throws UserDefinedException{
		return bInterface.getUserInfo(customerId);
	}
	public List<User> getAllUserInfo(int...customerId)throws UserDefinedException{
		return bInterface.getAllUserInfo(customerId);
	}
	// get Customer info CASE 10 customer and admin
	public Customer getCustomerInfo(int customerId)throws UserDefinedException{
		return bInterface.getCustomerInfo(customerId);
	}
	public List<Customer> getAllCustomerInfo(int...customerId)throws UserDefinedException{
		return bInterface.getAllCustomerInfo(customerId);
	}
	// show Account info CASE 3 customer and admin
	public Map<Integer,Map> getAccountInfo(int customerId,long accountNumber)throws UserDefinedException{
		return bInterface.getAccountInfo(customerId,accountNumber);
	}
	// show customer id to get all account info  CASE 9 customer and admin
	public List<Account> getCustomerIdToGetAllAccountInfo(int customerId)throws UserDefinedException{
		return bInterface.getCustomerIdToGetAllAccountInfo(customerId);
	}
	// show transaction info CASE 4 customer and admin
	public Map<Integer,Transaction> getTransactionInfo(int customerId,long accountNumber)throws UserDefinedException{
		return bInterface.getTransactionInfo(customerId,accountNumber);
	}
	// set request for account status
	public boolean setRequestForAccountStatus(int customerId,long accountNumber,String status)throws UserDefinedException{
		return bInterface.setRequestForAccountStatus(customerId, accountNumber, status);
	}
	// get Accountinfo account status
	public boolean getAccountInfoAccountStatus(long accountNumber)throws UserDefinedException{
		return bInterface.getAccountInfoAccountStatus(accountNumber);
	}
	// get Transaction by last N
	public List<Transaction> getLastNTransactionInfo(long accountNumber,int length)throws UserDefinedException{
		return bInterface.getLastNTransactionInfo(accountNumber, length);
	}
	// update passcode in login by lPojo customerId
	public boolean updatePasscodeInLogin(Login lPojo)throws UserDefinedException{
		return bInterface.updatePasscodeInLogin(lPojo);
	}
	// get login info by customerId using lPojo
	public List<Login> getAllLoginInfo(int customerId)throws UserDefinedException{
		return bInterface.getAllLoginInfo(customerId);
	}
	// update userinfo
	public boolean updateUserInfo(User user)throws UserDefinedException{
		return bInterface.updateUserInfo(user);
	}
	// update customerinfo
	public boolean updateCustomrInfo(Customer customer)throws UserDefinedException{
		return bInterface.updateCustomrInfo(customer);
	}
	// encrypted passCode
	public String getEncryptedPasscode(String passCode)throws UserDefinedException{
		return bInterface.getEncryptedPasscode(passCode);
	}
	}
