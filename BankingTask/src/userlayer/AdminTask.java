package userlayer;


import java.util.List;



import java.util.Map;

import banking.BankInterface;
import banking.BankLogical;
import check.UserDefinedException;
import pojoclasses.Account;
import pojoclasses.AccountRequest;
import pojoclasses.Customer;
import pojoclasses.Login;
import pojoclasses.Transaction;
import pojoclasses.User;
import mysql.ConnectionDB;

public class AdminTask {
ConnectionDB con=new ConnectionDB();	
BankInterface bInterface=new BankLogical();
	// show customer info   EX 2 customer and admin
	public User getUserInfo(int customerId)throws UserDefinedException{
		return bInterface.getUserInfo(customerId);
	}
	// get Userinfo activity CASE 1 Customer
		public List<User> getAllUserActivity()throws UserDefinedException{
			return bInterface.getAllUserActivity();
		}
	// get all show User info ADMIN CASE 13
	public List<User> getAllUserInfo(int...customerId)throws UserDefinedException{
		return bInterface.getAllUserInfo(customerId);
	}
	// get Customer info CASE 10 customer and admin
	public Customer getCustomerInfo(int customerId)throws UserDefinedException{
		return bInterface.getCustomerInfo(customerId);
	}
	// get all customer info   CASE 10 ADMIN
	public List<Customer> getAllCustomerInfo(int...customerId)throws UserDefinedException{
		return bInterface.getAllCustomerInfo(customerId);
	}
	// show Account info CASE 3 customer and admin
	public Map<Integer,Map> getAccountInfo(int customerId,long accountNumber)throws UserDefinedException{
		return bInterface.getAccountInfo(customerId,accountNumber);
	}
	// get all account info admin CASE 11
	public List<Account> getAllAccountInfo()throws UserDefinedException{
		return bInterface.getAllAccountInfo();
	}
	// show customer id to get all account info  CASE 9 customer and admin
	public List<Account> getCustomerIdToGetAllAccountInfo(int customerId)throws UserDefinedException{
	      return bInterface.getCustomerIdToGetAllAccountInfo(customerId);
	}
	// show transaction info CASE 4 customer and admin
	public Map<Integer,Transaction> getTransactionInfo(int customerId,long accountNumber)throws UserDefinedException{
		return bInterface.getTransactionInfo(customerId,accountNumber);
	}
	// Account status  ADMIN case 14 admin
	public void deactiveAccount(int customerId,long accountNumber,boolean flag)throws UserDefinedException{
		bInterface.deactiveAccount(customerId, accountNumber, flag);
	}
	// Block Accontinfo account status
	public boolean blockedAccount(int customerId,long accountNumber)throws UserDefinedException{
		return bInterface.blockAccount(customerId, accountNumber);
	}
	// check Accountinfo account status
	public boolean checkCustomerInfoStatus(int customerId)throws UserDefinedException{
		return bInterface.checkCustomerInfoStatus(customerId);
	}
	// update Customerinfo customer status
	public boolean updateCustomerInfoStatus(int customerId)throws UserDefinedException{
		return bInterface.updateCustomerInfoStatus(customerId);
	}
	// get Account status request info
	public List<AccountRequest> getRequestForAccountStatus()throws UserDefinedException{
		return bInterface.getRequestForAccountStatus();
	}
	// block customer
	public boolean blockedCustomer(int customerId)throws UserDefinedException{
		return bInterface.blockCustomer(customerId);
	}
	// get all login info
	public List<Login> getAllLoginInfo(int...customerId)throws UserDefinedException{
		return bInterface.getAllLoginInfo(customerId);
	}
	// get login info by customerId
//	public Login getLoginInfoByUserId(int customerId)throws UserDefinedException{
//		return bInterface.getLoginInfoByUserId(customerId);
//	}
	// update Account statusresult in Accountstatusinfl
	public boolean updateAccountstatusApprove(int customerId,long accountNumber,boolean flag)throws UserDefinedException{
		return bInterface.updateAccountStatus(customerId,accountNumber, flag);
	}
	// insert into login by login pojo
	public boolean insertValuesInLogin(Login lPojo)throws UserDefinedException{
		return bInterface.insertValuesInLogin(lPojo);
	}
	// insert into user info by user pojo
	public boolean insertValuesInUserInfo(User uPojo)throws UserDefinedException{
		return bInterface.insertValuesInUserInfo(uPojo);
	}
	// insert into customer info by customer pojo
	public boolean insertValuesInCustomerInfo(Customer cPojo)throws UserDefinedException{
		return bInterface.insertValuesInCustomerInfo(cPojo);
	}
	// insert into account info by account pojo
	public boolean inserValuesInAccountInfo(Account aPojo)throws UserDefinedException{
		return bInterface.inserValuesInAccountInfo(aPojo);
	}
}
