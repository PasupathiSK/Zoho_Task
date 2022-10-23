package userlayer;


import java.util.List;

import banking.BankInterface;
import banking.BankLogical;
import check.UserDefinedException;
import mysql.ConnectionDB;
import pojoclasses.Request;
import pojoclasses.Transaction;

public class AmountTransaction {
	ConnectionDB con=new ConnectionDB();
	BankInterface bInterface=new BankLogical();
	// Get Balance  CASE 8 customer and admin
	public long getBalance(long aNumber)throws UserDefinedException{
		return bInterface.getBalance(aNumber);
	}
	// Withdraw Money    CASE 6 customer only
//	public boolean withdrawMoney(long amount,long aNumber)throws UserDefinedException{
//		return bInterface.withdrawMoney(amount, aNumber);
//	}
//	// deposit money  CASE 5 customer only
//	public boolean depositMoney(long amount,long aNumber)throws UserDefinedException{
//		return bInterface.depositMoney(amount, aNumber);
//	}
//	// transfer money  CASE 7 customer only
//	public boolean transferMoney(long sAccount,long rAccount,long amount)throws UserDefinedException{
//		return bInterface.transferMoney(sAccount, rAccount, amount);
//	}
	// make a transaction by transaction pojo
		public boolean makeTransaction(Transaction tPojo)throws UserDefinedException{
			return bInterface.makeTransaction(tPojo);
		}
	// set withdraw request
	public boolean setWithdrawRequest(int customerId,long accountNumber,long amount)throws UserDefinedException{
		return bInterface.setWithdrawRequest(customerId, accountNumber,amount);
	}
	// get withdraq request
		public List<Request> getWithdrawRequest()throws UserDefinedException{
			return bInterface.getWithdrawRequest();
		}
	// update approve withdraw request
	public boolean updateWithdrawApprove(Request rPojo,boolean flag)throws UserDefinedException{
		return bInterface.updateWithdrawApprove(rPojo,flag);
	}
}
