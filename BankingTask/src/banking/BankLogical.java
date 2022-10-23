package banking;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import check.UserDefinedException;
import mysql.ConnectionDB;
import pojoclasses.Account;
import pojoclasses.AccountRequest;
import pojoclasses.Customer;
import pojoclasses.Login;
import pojoclasses.Request;
import pojoclasses.Transaction;
import pojoclasses.User;


public class BankLogical implements BankInterface{
	ConnectionDB con=new ConnectionDB();
// EX 1
	// get user activity status  CASE 1  Customer
	public void userActivityStatus(int customerId,boolean flag)throws UserDefinedException {
		String sQuery="";
		if(flag) {
		      sQuery="update Userinfo set status='Login' where customerId="+customerId;
		}
		else {
			sQuery="update Userinfo set status='Logout' where customerId="+customerId;
		}
		try(PreparedStatement pStatement=con.getPreparedStatement(sQuery)){
			pStatement.executeUpdate();
		}
		catch(SQLException ex) {
			throw new UserDefinedException("Error Occur : ",ex);
		}
	}
	
// EX 2
	// get Userinfo activity CASE 9 admin
	public List<User> getAllUserActivity()throws UserDefinedException{
		String sQuery="select * from Userinfo";
		List<User>list=new ArrayList<User>();
		try(PreparedStatement pStatement=con.getPreparedStatement(sQuery);ResultSet resultSet=pStatement.executeQuery()){
			while(resultSet.next()) {
				User user=new User();
				user.setCustomerId(resultSet.getInt("customerId"));
				user.setName(resultSet.getString("cName"));
				user.setMobileNumber(resultSet.getString("mNumber"));
				user.setEmail(resultSet.getString("email"));
				user.setDateOfBirth(resultSet.getString("DOB"));
				list.add(user);
			}
		}
		catch(SQLException ex) {
			throw new UserDefinedException("Error Occur : ",ex);
		}
		return list;
	}
	
// EX 3
	// show customer info   CASE 2 customer and admin
	public User getUserInfo(int customerId)throws UserDefinedException{
		String cQuery="select * from Userinfo where customerId=?";
		Map<Object,Object>map=new HashMap<Object,Object>();
		try(PreparedStatement pStatement=con.getPreparedStatement(cQuery)){
			pStatement.setInt(1, customerId);
			try(ResultSet resultSet=pStatement.executeQuery()){
				User user=new User();
				while(resultSet.next()) {
				        user.setName(resultSet.getString("cName"));
				        user.setMobileNumber(resultSet.getString("mNumber"));
				        user.setEmail(resultSet.getString("email"));
				        user.setDateOfBirth(resultSet.getString("DOB"));
				        user.setStatus(resultSet.getString("status"));
					}
				return user;
				}
			}
		catch(SQLException ex) {
			throw new UserDefinedException("Error occur : ",ex);
		}
	}
	
// EX 4
	// get all show User info ADMIN CASE 13 and Customer CASE 2
//		public List<User> getAllUserInfo()throws UserDefinedException{
//			String aQuery="select * from Userinfo";
//			List<User>list=new ArrayList<User>();
//			try(PreparedStatement pStatement=con.getPreparedStatement(aQuery);ResultSet resultSet=pStatement.executeQuery()){
//				while(resultSet.next()) {
//				    User user=new User();
//				    user.setCustomerId(resultSet.getInt("customerId"));
//				    user.setName(resultSet.getString("cName"));
//			        user.setMobileNumber(resultSet.getString("mNumber"));
//			        user.setEmail(resultSet.getString("email"));
//			        user.setDateOfBirth(resultSet.getString("DOB"));
//			        user.setStatus(resultSet.getString("status"));
//					list.add(user);
//				}
//			}
//			catch(SQLException ex) {
//				throw new UserDefinedException("Error Occur : ",ex);
//			}
//			return list;
//		}
	public List<User> getAllUserInfo(int... customerId)throws UserDefinedException{
		int length=customerId.length;
		String aQuery="";
		List<User>list=new ArrayList<User>();
		int i=0;
		do {
				if(length==0) {
					aQuery="select * from Userinfo";
				}
				else{
		        aQuery="select * from Userinfo where customerId="+customerId[i];
				}
		try(PreparedStatement pStatement=con.getPreparedStatement(aQuery);ResultSet resultSet=pStatement.executeQuery()){
			while(resultSet.next()) {
			    User user=new User();
			    user.setCustomerId(resultSet.getInt("customerId"));
			    user.setName(resultSet.getString("cName"));
		        user.setMobileNumber(resultSet.getString("mNumber"));
		        user.setEmail(resultSet.getString("email"));
		        user.setDateOfBirth(resultSet.getString("DOB"));
		        user.setStatus(resultSet.getString("status"));
				list.add(user);
				i++;
			}
		}
		catch(SQLException ex) {
			throw new UserDefinedException("Error Occur : ",ex);
		}
			}while(i<length);
		return list;
	}
		
// EX 5
		// get Customer info CASE 10 customer and admin
		public Customer getCustomerInfo(int customerId)throws UserDefinedException{
			String cQuery="select * from Customerinfo where customerId="+customerId;
			try(PreparedStatement pStatement=con.getPreparedStatement(cQuery);ResultSet resultSet=pStatement.executeQuery()){
				Customer customer=new Customer();
				while(resultSet.next()) {
					customer.setAadhar(resultSet.getLong("Aadhar"));
					customer.setCustomerId(resultSet.getInt("customerId"));
					customer.setPanNumber(resultSet.getString("panNumber"));
					customer.setZipcode(resultSet.getInt("zipcode"));
//					customer.setStatus(resultSet.getString("accountStatus"));
				}
				return customer;
			}
			catch(SQLException ex) {
				throw new UserDefinedException("Error Occur : ",ex);
			}
		}
		
// EX 6
		// get all customer info   CASE 7 ADMIN
//		public List<Customer> getAllCustomerInfo()throws UserDefinedException{
//			String aQuery="select * from Customerinfo";
//			List<Customer>list=new ArrayList<Customer>();
//			try(PreparedStatement pStatement=con.getPreparedStatement(aQuery);ResultSet resultSet=pStatement.executeQuery()){
//				while(resultSet.next()) {
//				    Customer customer=new Customer();
//					customer.setAadhar(resultSet.getLong("Aadhar"));
//					customer.setCustomerId(resultSet.getInt("customerId"));
//					customer.setPanNumber(resultSet.getString("panNumber"));
//					customer.setZipcode(resultSet.getInt("zipcode"));
//					customer.setStatus(resultSet.getString("CustomerStatus"));
//					list.add(customer);
//				}
//			}
//			catch(SQLException ex) {
//				throw new UserDefinedException("Error Occur : ",ex);
//			}
//			return list;
//		}
		public List<Customer> getAllCustomerInfo(int...customerId)throws UserDefinedException{
			int length=customerId.length;
			String aQuery="";
			List<Customer>list=new ArrayList<Customer>();
			int i=0;
			do {
				if(length==0) {
					aQuery="select * from Customerinfo";
				}
				else {
					aQuery="select * from Customerinfo where customerId="+customerId[i];
				}
			try(PreparedStatement pStatement=con.getPreparedStatement(aQuery);ResultSet resultSet=pStatement.executeQuery()){
				while(resultSet.next()) {
				    Customer customer=new Customer();
					customer.setAadhar(resultSet.getLong("Aadhar"));
					customer.setCustomerId(resultSet.getInt("customerId"));
					customer.setPanNumber(resultSet.getString("panNumber"));
					customer.setZipcode(resultSet.getInt("zipcode"));
					customer.setStatus(resultSet.getString("CustomerStatus"));
					list.add(customer);
					i++;
				}
			}
			catch(SQLException ex) {
				throw new UserDefinedException("Error Occur : ",ex);
			}
			}while(i<length);
			return list;
		}
		
// EX 7
		// show Account info CASE 3 customer and admin
		public Map<Integer,Map> getAccountInfo(int customerId,long accountNumber)throws UserDefinedException{
			String aQuery="select * from Accountinfo where (AccountNumber=?)";
			Map<Integer,Map>map=new HashMap<Integer,Map>();
			Map<Long,Account>nMap=new HashMap<Long,Account>();
			try(PreparedStatement pStatement=con.getPreparedStatement(aQuery)){
				pStatement.setLong(1,accountNumber);
				try(ResultSet resultSet=pStatement.executeQuery()){
					     while(resultSet.next()) {
					    	 Account account=new Account();
					    	 account.setAccountType(resultSet.getString("Accounttype"));
					    	 account.setBalance(resultSet.getDouble("Balance"));
					    	 account.setBranch(resultSet.getString("Branch"));
					    	 account.setAccountStatus(resultSet.getString("accountstatus"));
					    	 account.setIfsc(resultSet.getString("ifsc"));
					    	 nMap.put(account.getAccountNumber(), account);
					}
					map.put(customerId, nMap);
				}
			}
			catch(SQLException ex) {
				throw new UserDefinedException("Error Occur : ",ex);
			}
			return map;
		}
		
// EX 8
		// get all account info admin CASE 8
		public List<Account> getAllAccountInfo()throws UserDefinedException{
			String aQuery="select * from Accountinfo";
			List<Account>list=new ArrayList<Account>();
			try(PreparedStatement pStatement=con.getPreparedStatement(aQuery);ResultSet resultSet=pStatement.executeQuery()){
				while(resultSet.next()) {
					Account account=new Account();
					account.setCustomerId(resultSet.getInt("customerId"));
					account.setAccountNumber(resultSet.getLong("AccountNumber"));
					account.setBranch(resultSet.getString("Branch"));
					account.setAccountType(resultSet.getString("Accounttype"));
					account.setBalance(resultSet.getDouble("Balance"));
					 account.setAccountStatus(resultSet.getString("accountstatus"));
			    	 account.setIfsc(resultSet.getString("ifsc"));
					list.add(account);
				}
			}
			catch(SQLException ex) {
				throw new UserDefinedException("Error Occur : ",ex);
			}
			return list;
		}
		
// EX 9
		// show customer id to get all account info  CASE 9 customer and admin CASE 6
		public List<Account> getCustomerIdToGetAllAccountInfo(int customerId)throws UserDefinedException{
			String aQuery="select * from Accountinfo where (customerId=?)";
			List<Account>list=new ArrayList<Account>();
			try(PreparedStatement pStatement=con.getPreparedStatement(aQuery)){
				pStatement.setLong(1, customerId);
				try(ResultSet resultSet=pStatement.executeQuery()){
					while(resultSet.next()) {
					Account account=new Account();
					account.setAccountNumber(resultSet.getLong("AccountNumber"));
					account.setAccountType(resultSet.getString("Accounttype"));
					account.setBalance(resultSet.getDouble("Balance"));
					account.setBranch(resultSet.getString("Branch"));
					 account.setAccountStatus(resultSet.getString("accountstatus"));
			    	 account.setIfsc(resultSet.getString("ifsc"));
					list.add(account);
				}
				}
				return list;
			}
			catch(SQLException ex) {
				throw new UserDefinedException("Error Occur : ",ex);
			}
		}
		
// EX 10
		// show transaction info CASE 4 customer and admin
		public Map<Integer,Transaction> getTransactionInfo(int customerId,long accountNumber)throws UserDefinedException{
			String tQuery="select * from Transaction where (customerId=?) and (AccountNumber=?)";
			Map<Integer,Transaction>map=new HashMap<Integer,Transaction>();
			try(PreparedStatement pStatement=con.getPreparedStatement(tQuery)){
				pStatement.setInt(1,customerId);
				pStatement.setLong(2, accountNumber);
				try(ResultSet resultSet=pStatement.executeQuery()){
					while(resultSet.next()) {
						Transaction transaction=new Transaction();
						transaction.setAccountNumber(resultSet.getLong("AccountNumber"));
						transaction.setBranch(resultSet.getString("Branch"));
						transaction.setAmount(resultSet.getLong("Amount"));
						transaction.setCustomerId(resultSet.getInt("customerId"));
						transaction.setDateOfTransaction(resultSet.getLong("DateOfTransaction"));
						transaction.setTransferAccountFrom(resultSet.getLong("TransferAccountFrom"));
						transaction.setReceivedAccountFrom(resultSet.getLong("ReceivedAccountFrom"));
						transaction.setCurrentBalance(resultSet.getDouble("currentBalance"));
						transaction.setTransactionType(resultSet.getString("Transactiontype"));
						transaction.setTransactionNumber(resultSet.getInt("TransactionNumber"));
						transaction.setModeOfTransaction(resultSet.getString("ModeOfTransaction"));
						map.put(resultSet.getInt("TransactionNumber"), transaction);
					}
					return map;
				}
			}
			catch(SQLException ex) {
				throw new UserDefinedException ("Error Occur : ",ex);
			}
		}
		
// EX 11
		// Get Balance  CASE 8 customer and admin CASE 5
		public long getBalance(long aNumber)throws UserDefinedException{
			String bQuery="select Balance from Accountinfo where AccountNumber="+aNumber;
			long balance=0;
			try(PreparedStatement pStatement=con.getPreparedStatement(bQuery)){
				ResultSet resultSet=pStatement.executeQuery();
				while(resultSet.next()) {
					balance=resultSet.getLong("Balance");
				}
				return balance;
			}
			catch(SQLException ex) {
				throw new UserDefinedException("Error Occur : ",ex);
			}
		}
		
		public boolean insertInTransaction(long aNumber,long rAccount,long sAccount,long amount,String modeOfTransaction,boolean flag)throws UserDefinedException{
			int cId=0;
			long accountNumber=0,balance=0;
			String branch="",tType="Credited";
			String iQuery="select * from Accountinfo where AccountNumber="+aNumber;
			String tQuery="insert into Transaction (AccountNumber,Branch,Amount,customerId,DateOfTransaction,TransferAccountFrom,ReceivedAccountFrom,currentBalance,Transactiontype,ModeOfTransaction) values (?,?,?,?,?,?,?,?,?,?)";
			if(!flag) {
				amount=-amount;
				tType="Debited";
			}
			try(PreparedStatement pStatement=con.getPreparedStatement(iQuery);ResultSet resultSet=pStatement.executeQuery()){
				while(resultSet.next()) {
					accountNumber=resultSet.getLong("AccountNumber");
					branch=resultSet.getString("Branch");
					cId=resultSet.getInt("customerId");
					balance=resultSet.getLong("Balance");
				}
				try(PreparedStatement NPStatement=con.getPreparedStatement(tQuery)){
					NPStatement.setLong(1, accountNumber);
					NPStatement.setString(2, branch);
					NPStatement.setLong(3, amount);
					NPStatement.setInt(4, cId);
					NPStatement.setLong(5, System.currentTimeMillis());
					NPStatement.setLong(6, sAccount);
					NPStatement.setLong(7,rAccount);
					NPStatement.setLong(8, balance);
					NPStatement.setString(9, tType);
					NPStatement.setString(10, modeOfTransaction);
					NPStatement.executeUpdate();
					return true;
				}
			}
			catch(SQLException ex) {
				throw new UserDefinedException("Error Occur : ",ex);
			}
		}
		
		
// EX 12
//		 Withdraw Money    CASE 6 customer only  and CASE 12 admin
		public boolean withdrawMoney(long amount,long aNumber)throws UserDefinedException{
			long balance=getBalance(aNumber);
			String wQuery="update Accountinfo set Balance="+(balance-amount)+" where AccountNumber="+aNumber;
//			String aQuery="select * from Accountinfo where AccountNumber="+aNumber;
//			String tQuery="insert into Transaction (AccountNumber,Branch,Amount,customerId,DateOfTransaction,TransferAccountFrom,ReceivedAccountFrom,currentBalance) values (?,?,?,?,?,?,?,?)";
			try(PreparedStatement pStatement=con.getPreparedStatement(wQuery)){
				if(pStatement.executeUpdate()==1) {
					if(insertInTransaction(aNumber,aNumber,aNumber,amount,"withdraw",false)) {
					return true;
					}
				}
			}
			catch(SQLException ex) {
				throw new UserDefinedException("Error Occur : ",ex);
			}
			return false;
		}


// EX 13
// deposit money  CASE 5 customer only
		public boolean depositMoney(long amount,long aNumber)throws UserDefinedException{
			long balance=getBalance(aNumber);
			String dQuery="update Accountinfo set Balance="+(balance+amount)+" where AccountNumber="+aNumber;
			try(PreparedStatement pStatement=con.getPreparedStatement(dQuery)){
				if(pStatement.executeUpdate()==1) {
					if(insertInTransaction(aNumber,aNumber,aNumber,amount,"deposit",true)) {
						return true;
					}
				}
			}
			catch(SQLException ex) {
				throw new UserDefinedException("Error Occur : ",ex);
			}
			return false;
		}
		
// EX 14
//		// transfer money  CASE 7 customer only
//		public boolean transferMoney(long sAccount,long rAccount,long amount)throws UserDefinedException{
//			long sBalance=getBalance(sAccount);
//			long rBalance=getBalance(rAccount);
//			String tQuery="select * from Accountinfo where (AccountNumber=?)";
////			String dQuery="update Accountinfo set Balance="+(sBalance-amount)+" where AccountNumber="+sAccount;
////			String rQuery="update Accountinfo set Balance="+(rBalance+amount)+" where AccountNumber="+rAccount;
//			Connection getCon=con.connectionDB();
//			try {
//				getCon.setAutoCommit(false);
////			try (PreparedStatement pStatement=getCon.prepareStatement(tQuery)){
////				pStatement.setLong(1, sAccount);
////			try(ResultSet resultSet=pStatement.executeQuery()){
//				// debit
//				
//				getCon.setSavepoint();
//				withdrawMoney(amount,sAccount);
////				pStatement.executeUpdate(dQuery);
//				// credit
////				pStatement.executeUpdate(rQuery);
//				depositMoney(amount,rAccount);
//						getCon.commit();
//						if(insertInTransaction(rAccount,rAccount,sAccount,amount,true)) {
//						        return true;
//						}
////				}
////			}
//			}
//			catch(SQLException ex) {
//				try {
//					getCon.rollback();
//				}
//				catch(SQLException e) {
//					throw new UserDefinedException("Error Occur : ",e);
//				}
//				throw new UserDefinedException("Error Occur : ",ex);
//			 }
//			return false;
//		}
		public boolean makeTransaction(Transaction transaction)throws UserDefinedException{
			if(transaction.getModeOfTransaction()=="withdraw") {
				return withdrawMoney(transaction.getAmount(),transaction.getAccountNumber());
			}
			if(transaction.getModeOfTransaction()=="deposit") {
				return depositMoney(transaction.getAmount(),transaction.getAccountNumber());
			}
			else {
				long sBalance=getBalance(transaction.getTransferAccountFrom());
				long rBalance=getBalance(transaction.getReceivedAccountFrom());
				String tQuery="select * from Accountinfo where (AccountNumber=?)";
			    String dQuery="update Accountinfo set Balance="+(sBalance-transaction.getAmount())+" where AccountNumber="+transaction.getTransferAccountFrom();
				String rQuery="update Accountinfo set Balance="+(rBalance+transaction.getAmount())+" where AccountNumber="+transaction.getReceivedAccountFrom();
				Connection getCon=con.connectionDB();
				try {
					getCon.setAutoCommit(false);
				try (PreparedStatement pStatement=getCon.prepareStatement(tQuery)){
					pStatement.setLong(1, transaction.getTransferAccountFrom());
				try(ResultSet resultSet=pStatement.executeQuery()){
					// debit
					
					getCon.setSavepoint();
//					withdrawMoney(amount,sAccount);
					pStatement.executeUpdate(dQuery);
					// credit
					pStatement.executeUpdate(rQuery);
//					depositMoney(amount,rAccount);
							getCon.commit();
							if((insertInTransaction(transaction.getReceivedAccountFrom(),transaction.getReceivedAccountFrom(),transaction.getTransferAccountFrom(),transaction.getAmount(),"deposit",true))&&
									(insertInTransaction(transaction.getTransferAccountFrom(),transaction.getReceivedAccountFrom(),transaction.getTransferAccountFrom(),transaction.getAmount(),"withdraw",false)))
                      {
								return true;
							}
					}
				}
				}
				catch(SQLException ex) {
					try {
						getCon.rollback();
					}
					catch(SQLException e) {
						throw new UserDefinedException("Error Occur : ",e);
					}
					throw new UserDefinedException("Error Occur : ",ex);
				 }
			}
			return false;
		}
		
//  EX 15
		// Account status  ADMIN case 10 admin
		public void deactiveAccount(int customerId,long accountNumber,boolean flag)throws UserDefinedException{
			String sQuery="";
			if(flag) {
			      sQuery="update Accountinfo set accountstatus='Active' where (AccountNumber="+accountNumber+") and (customerId="+customerId+")";
			}
			else {
				sQuery="update Accountinfo set accountstatus='InActive' where (AccountNumber="+accountNumber+") and (customerId="+customerId+")";
			}
			try(PreparedStatement pStatement=con.getPreparedStatement(sQuery)){
				pStatement.executeUpdate();
			}
			catch(SQLException ex) {
				throw new UserDefinedException("Error Occur : ",ex);
			}
		}
		
// EX 16
		// set request for withdraw customer CASE 11
		public boolean setWithdrawRequest(int customerId,long accountNumber,long amount)throws UserDefinedException{
			String rQuery="insert into Requestinfo values(?,?,?,?)";
			try(PreparedStatement pStatement=con.getPreparedStatement(rQuery)){
				pStatement.setInt(1, customerId);
				pStatement.setLong(2, accountNumber);
				pStatement.setString(3,"pending");
				pStatement.setLong(4, amount);
				if(pStatement.executeUpdate()==1) {
				        return true;
				}
				}
		catch(SQLException ex) {
			throw new UserDefinedException("Error Occur : ",ex);
		     }
			return false;
		}
		
// EX 17
		// get request for withdraw CASE 11 admin
		public List<Request> getWithdrawRequest()throws UserDefinedException{
			String gQuery="select * from Requestinfo where status='pending'";
			List<Request>list=new ArrayList<Request>();
			try(PreparedStatement pStatement=con.getPreparedStatement(gQuery);ResultSet resultSet=pStatement.executeQuery()){
				while(resultSet.next()) {
					Request request=new Request();
					request.setAccountNumber(resultSet.getLong("AccountNumber"));
					request.setCustomerId(resultSet.getInt("customerId"));
					request.setAmount(resultSet.getLong("Amount"));
					request.setStatus(resultSet.getString("status"));
					list.add(request);
				}
			}
			catch(SQLException ex) {
				throw new UserDefinedException("Error Occur : ",ex);
			}
			return list;
		}

// EX 18
		// update request approve   CASE 12 admin
//		public boolean updateWithdrawApprove(int customerId,long accountNumber,long amount,String status,boolean flag)throws UserDefinedException{
		public boolean updateWithdrawApprove(Request request,boolean flag)throws UserDefinedException{

			String uQuery="";
			if(flag) {
			        uQuery="update Requestinfo set status='approved' where (customerId=?) and (AccountNumber=?) and (Amount=?) and (status=?)";
			}
			else {
				uQuery="update Requestinfo set status='rejected' where (customerId=?) and (AccountNumber=?) and (Amount=?) and (status=?)";
			}
			try(PreparedStatement pStatement=con.getPreparedStatement(uQuery)){
				pStatement.setInt(1, request.getCustomerId());
				pStatement.setLong(2,request.getAccountNumber());
				pStatement.setLong(3,request.getAmount());
				pStatement.setString(4, request.getStatus());
				if(pStatement.executeUpdate()==1) {
					return true;
				}
			}
			catch(SQLException ex) {
				throw new UserDefinedException("Error Occur : ",ex);
			}
			return false;
		}
		
// EX 19
		// update accountinfo status blocked   CASE 13 admin
		public boolean blockAccount(int customerId,long accountNumber)throws UserDefinedException{
			String bQuery="update Accountinfo set accountstatus='Blocked' where (customerId=?) and (AccountNumber=?)";
			try(PreparedStatement pStatement=con.getPreparedStatement(bQuery)){
				pStatement.setInt(1, customerId);
				pStatement.setLong(2, accountNumber);
				if(pStatement.executeUpdate()==1) {
					return true;
				}
			}
			catch(SQLException ex) {
				throw new UserDefinedException("Error Occur : ",ex);
			}
			return false;
		}
// EX 20
		// check customer info CASE 10 admin
		public boolean checkCustomerInfoStatus(int customerId)throws UserDefinedException{
			String uQuery="select accountstatus from Accountinfo where customerId="+customerId;
			String status="";
			boolean flag=false;
			try(PreparedStatement pStatement=con.getPreparedStatement(uQuery);ResultSet resultSet=pStatement.executeQuery()){
				while(resultSet.next()) {
					status=resultSet.getString("accountstatus");
					if(status=="active") {
						flag=true;
					}
				}
			}
			catch(SQLException ex) {
				throw new UserDefinedException("Error Occur : ",ex);
			}
			return flag;
		}
		
// EX 21
		// update customer info CASE 10 admin
		public boolean updateCustomerInfoStatus(int customerId)throws UserDefinedException{
			String uQuery="update Customerinfo set customerStatus='inactive' where customerId="+customerId;
			try(PreparedStatement pStatement=con.getPreparedStatement(uQuery)){
				if(pStatement.executeUpdate()==1) {
				    return true;
				}
			}
			catch(SQLException ex) {
				throw new UserDefinedException("Error Occur : ",ex);
			}
			return false;
		}
		
// EX 22
		// set request for active (or) deactive account
		// customer CASE 12
		public boolean setRequestForAccountStatus(int customerId,long accountNumber,String status)throws UserDefinedException{
			String sQuery="insert into Accountstatusinfo values(?,?,?,?)";
			try(PreparedStatement pStatement=con.getPreparedStatement(sQuery)){
				pStatement.setInt(1, customerId);
				pStatement.setLong(2, accountNumber);
				pStatement.setString(3,status);
				pStatement.setString(4, "pending");
				if(pStatement.executeUpdate()==1) {
					return true;
				}
			}
			catch(SQLException ex) {
				throw new UserDefinedException("Error Occur : ",ex);
			}
			return false;
		}
		
// EX 23
		// get request for active (or) inactive account  CASE 14
		public List<AccountRequest> getRequestForAccountStatus()throws UserDefinedException{
			String gQuery="select * from Accountstatusinfo where statusresult='pending'";
			List<AccountRequest>list=new ArrayList<AccountRequest>();
			try(PreparedStatement pStatement=con.getPreparedStatement(gQuery);ResultSet resultSet=pStatement.executeQuery()){
				while(resultSet.next()) {
					AccountRequest account=new AccountRequest();
					account.setAccountNumber(resultSet.getLong("AccountNumber"));
					account.setCustomerId(resultSet.getInt("customerId"));
					account.setStatus(resultSet.getString("Requeststatus"));
					account.setStatusResult(resultSet.getString("statusresult"));
					list.add(account);
				}
			}
			catch(SQLException ex) {
				throw new UserDefinedException("Error Occur : ",ex);
			}
			return list;
		}
		
// EX 24
		// get Accountinfo account status
		// Permission check for user userLongin customer CASE 11
		public boolean getAccountInfoAccountStatus(long accountNumber)throws UserDefinedException{
			String sQuery="select * from Accountinfo where AccountNumber="+accountNumber;
			String status="";
			try(PreparedStatement pStatement=con.getPreparedStatement(sQuery);ResultSet resultSet=pStatement.executeQuery()){
				while(resultSet.next()) {
				status=resultSet.getString("accountstatus");
				}
				if(status.equals("active")) {
					return true;
				}
			}
			catch(SQLException ex) {
				throw new UserDefinedException("Error Occur : ",ex);
			}
			return false;
		}
		
// EX 25
		// update customer status blocked   CASE 15 admin
				public boolean blockCustomer(int customerId)throws UserDefinedException{
					String bQuery="update Customerinfo set CustomerStatus='Blocked' where customerId=?";
					try(PreparedStatement pStatement=con.getPreparedStatement(bQuery)){
						pStatement.setInt(1, customerId);
						if(pStatement.executeUpdate()==1) {
							return true;
						}
					}
					catch(SQLException ex) {
						throw new UserDefinedException("Error Occur : ",ex);
					}
					return false;
				}
				
// EX 26
		// get all login info admin CASE 16
//		public List<Login> getAllLoginInfo()throws UserDefinedException{
//			String lQuery="select * from login";
//			List<Login>list=new ArrayList<Login>();
//			try(PreparedStatement pStatement=con.getPreparedStatement(lQuery);ResultSet resultSet=pStatement.executeQuery()){
//				while(resultSet.next()) {
//					Login login=new Login();
//					login.setCustomerId(resultSet.getInt("customerId"));
//					login.setName(resultSet.getString("cName"));
//					login.setPassCode(resultSet.getString("passCode"));
//					login.setRole(resultSet.getString("role"));
//					list.add(login);
//				}
//			}
//			catch(SQLException ex) {
//				throw new UserDefinedException("Error Occur : ",ex);
//			}
//			return list;
//		}
				public List<Login> getAllLoginInfo(int...customerId)throws UserDefinedException{
					int length=customerId.length;
					String lQuery="";
					List<Login>list=new ArrayList<Login>();
					int i=0;
					do {
						if(length==0) {
							lQuery="select * from login";
						}
						else {
							lQuery="select * from login where customerId="+customerId[i];
						}
					try(PreparedStatement pStatement=con.getPreparedStatement(lQuery);ResultSet resultSet=pStatement.executeQuery()){
						while(resultSet.next()) {
							Login login=new Login();
							login.setCustomerId(resultSet.getInt("customerId"));
							login.setName(resultSet.getString("cName"));
							login.setPassCode(resultSet.getString("passCode"));
							login.setRole(resultSet.getString("role"));
							list.add(login);
						}
					}
					catch(SQLException ex) {
						throw new UserDefinedException("Error Occur : ",ex);
					}
					}while(i<length);
					return list;
				}
		
// EX 27
		// get login info by userid CASE 17 admin and Customer CASE 15
		public Login getLoginInfoByUserId(int customerId)throws UserDefinedException{
			String lQuery="select * from login where customerId="+customerId;
			Login login=new Login();
			try(PreparedStatement pStatement=con.getPreparedStatement(lQuery);ResultSet resultSet=pStatement.executeQuery()){
				while(resultSet.next()) {
					login.setCustomerId(resultSet.getInt("customerId"));
					login.setName(resultSet.getString("cName"));
					login.setPassCode(resultSet.getString("passCode"));
					login.setRole(resultSet.getString("role"));
				}
			}
			catch(SQLException ex) {
				throw new UserDefinedException("Error Occur : ",ex);
			}
			return login;
		}
		
// EX 28
		// update Account statusresult in Accountstatusinfo
		public boolean updateAccountStatus(int customerId,long accountNumber,boolean flag)throws UserDefinedException{
			String uQuery="";
			if(flag) {
			        uQuery="update Accountstatusinfo set statusresult='approved' where (customerId=?) and (AccountNumber=?)";
			}
			else {
				uQuery="update Accountstatusinfo set statusresult='rejected' where (customerId=?) and (AccountNumber=?)";
			}
			try(PreparedStatement pStatement=con.getPreparedStatement(uQuery)){
				pStatement.setInt(1, customerId);
				pStatement.setLong(2, accountNumber);
				if(pStatement.executeUpdate()==1) {
					return true;
				}
			}
			catch(SQLException ex) {
				throw new UserDefinedException("Error Occur : ",ex);
			}
			return false;
		}
		
// EX 29
		// last n transaction of account   customer CASE 13
		public List<Transaction> getLastNTransactionInfo(long accountNumber,int days)throws UserDefinedException{
			long currentTime=utili.MillisToDate.getCurrentTimeInMillis();
			long lastNTime=utili.MillisToDate.getLongMillis(days);
			String tQuery="select * from Transaction where AccountNumber=? and DateOfTransaction between "+lastNTime+" and "+currentTime+" order by TransactionNumber desc";
			List<Transaction>list=new ArrayList<Transaction>();
			try(PreparedStatement pStatement=con.getPreparedStatement(tQuery)){
				pStatement.setLong(1, accountNumber);
				try(ResultSet resultSet=pStatement.executeQuery()){
					while(resultSet.next()) {
					Transaction transaction=new Transaction();
					transaction.setAccountNumber(resultSet.getLong("AccountNumber"));
					transaction.setBranch(resultSet.getString("Branch"));
					transaction.setAmount(resultSet.getLong("Amount"));
					transaction.setCustomerId(resultSet.getInt("customerId"));
					transaction.setDateOfTransaction(resultSet.getLong("DateOfTransaction"));
					transaction.setTransferAccountFrom(resultSet.getLong("TransferAccountFrom"));
					transaction.setReceivedAccountFrom(resultSet.getLong("ReceivedAccountFrom"));
					transaction.setCurrentBalance(resultSet.getLong("currentBalance"));
					transaction.setTransactionType(resultSet.getString("Transactiontype"));
					transaction.setTransactionNumber(resultSet.getInt("TransactionNumber"));
					transaction.setModeOfTransaction(resultSet.getString("ModeOfTransaction"));
					list.add(transaction);
					}
				}
			}
			catch(SQLException ex) {
				throw new UserDefinedException("Error Occur : ",ex);
			}
			return list;
		}
		
// EX 30
		// insert values in login admin CASE 18
		public boolean insertValuesInLogin(Login login)throws UserDefinedException{
			String iQuery="insert into login (cName,passcode,role) values (?,?,?)";
			try(PreparedStatement pStatement=con.getPreparedStatement(iQuery)){
				pStatement.setString(1,login.getName());
				pStatement.setString(2,getEncryptedPasscode(login.getPassCode()));
				pStatement.setString(3, login.getRole());
				if(pStatement.executeUpdate()==1) {
					return true;
				}
			}
			catch(SQLException ex) {
				throw new UserDefinedException("Error Occur : ",ex);
			}
			return false;
		}
		
// EX 31
		//insert values in User info admin CASE 19
		public boolean insertValuesInUserInfo(User user)throws UserDefinedException{
			String iQuery="insert into Userinfo  values (?,?,?,?,?,?)";
			try(PreparedStatement pStatement=con.getPreparedStatement(iQuery)){
				pStatement.setInt(1,user.getCustomerId());
				pStatement.setString(2, user.getName());
				pStatement.setString(3, user.getMobileNumber());
				pStatement.setString(4, user.getEmail());
				pStatement.setString(5, user.getStatus());
				pStatement.setString(6, user.getDateOfBirth());
				if(pStatement.executeUpdate()==1) {
					return true;
				}
			}
			catch(SQLException ex) {
				throw new UserDefinedException("Error Occur : ",ex);
			}
			return false;
		}
		
// EX 32
		// insert values in Customerinfo admin CASE 20
		public boolean insertValuesInCustomerInfo(Customer customer)throws UserDefinedException{
			String iQuery="insert into Customerinfo values(?,?,?,?,?) ";
			try(PreparedStatement pStatement=con.getPreparedStatement(iQuery)){
				pStatement.setInt(1, customer.getCustomerId());
				pStatement.setLong(2,customer.getAadhar());
				pStatement.setString(3, customer.getPanNumber());
				pStatement.setInt(4, customer.getZipcode());
				pStatement.setString(5, customer.getStatus());
				if(pStatement.executeUpdate()==1) {
					return true;
				}
			}
			catch(SQLException ex) {
				throw new UserDefinedException("Error Occur : ",ex);
			}
			return false;
		}
		
// EX 33
		// insert values in Accountinfo admin CASE 21
		public boolean inserValuesInAccountInfo(Account account)throws UserDefinedException{
			String iQuery="insert into Accountinfo values (?,?,?,?,?,?,?)";
			try(PreparedStatement pStatement=con.getPreparedStatement(iQuery)){
				pStatement.setLong(1,account.getAccountNumber());
				pStatement.setInt(2,account.getCustomerId());
				pStatement.setString(3,account.getBranch());
				pStatement.setString(4,account.getAccountType());
				pStatement.setDouble(5,account.getBalance());
				pStatement.setString(6,account.getAccountStatus());
				pStatement.setString(7, account.getIfsc());
				if(pStatement.executeUpdate()==1) {
					return true;
				}
			}
			catch(SQLException ex) {
				throw new UserDefinedException("Error Occur : ",ex);
			}
			return false;
		}
		
// EX 34
		// modify login update only passcode
		public boolean updatePasscodeInLogin(Login login)throws UserDefinedException{
			String uQuery="update login set passcode=? where customerId=?";
			try(PreparedStatement pStatement=con.getPreparedStatement(uQuery)){
				pStatement.setString(1,getEncryptedPasscode(login.getPassCode()));
				pStatement.setInt(2, login.getCustomerId());
				if(pStatement.executeUpdate()==1) {
					return true;
				}
			}
			catch(SQLException ex) {
				throw new UserDefinedException("Error Occur : ",ex);
			}
			return false;
		}
		
// EX 35
	// modify userinfo update cName and email and dob customer CASE 16
		public boolean updateUserInfo(User user)throws UserDefinedException{
			int length=0,count=0;
			String column="",uQuery="";
			StringBuilder sBuilder=new StringBuilder();
			List<String>list=new ArrayList<String>();
			if(user.getName()!=null) {
				sBuilder.append("cName='"+user.getName()+"'");
				count++;
			}
			if(user.getEmail()!=null) {
				if(count>=1) {
					sBuilder.append(",");
				}
				sBuilder.append("email='"+user.getEmail()+",");
				count++;
			}
			if(user.getDateOfBirth()!=null) {
				if(count>=1) {
					sBuilder.append(",");
				}
				sBuilder.append("DOB="+user.getDateOfBirth());
			}
			uQuery="update Userinfo set "+sBuilder.toString()+" where customerId=?";
			try(PreparedStatement pStatement=con.getPreparedStatement(uQuery)){
				pStatement.setInt(1, user.getCustomerId());
				System.out.println(pStatement.toString());
				if(pStatement.executeUpdate()==1) {
					return true;
				}
			}
			catch(SQLException ex) {
				throw new UserDefinedException("Error Occur : ",ex);
			}
			return false;
		}
		
// EX 36
		// modify Customerinfo by customer pojo
		public boolean updateCustomrInfo(Customer customer)throws UserDefinedException{
			int length=0,count=0;
			String uQuery="";
			StringBuilder sBuilder=new StringBuilder();
			if(customer.getAadhar()!=0) {
				sBuilder.append("Aadhar="+customer.getAadhar());
				count++;
			}
			if(customer.getPanNumber()!=null) {
				if(count>=1)
				{
					sBuilder.append(",");
				}
				sBuilder.append("panNumber="+customer.getPanNumber());
				count++;
			}
			if(customer.getZipcode()!=0) {
				if(count>=1) {
					sBuilder.append(",");
				}
				sBuilder.append("zipcode="+customer.getZipcode());
			}
			uQuery="update Customerinfo set "+sBuilder.toString()+" where customerId=?";
			try(PreparedStatement pStatement=con.getPreparedStatement(uQuery)){
				pStatement.setInt(1, customer.getCustomerId());
				if(pStatement.executeUpdate()==1) {
					return true;
				}
			}
			catch(SQLException ex) {
				throw new UserDefinedException("Error Occur : ",ex);
			}
			return false;
		}
		
// EX 37
		// passcode encryption
		public String getEncryptedPasscode(String passCode)throws UserDefinedException{
			try {
				MessageDigest messageDigest=MessageDigest.getInstance("MD5");
				messageDigest.update(passCode.getBytes());
				byte[]bytes=messageDigest.digest();
				StringBuilder sBuilder=new StringBuilder();
				int length=bytes.length;
				for(int i=0;i<length;i++) {
					sBuilder.append(Integer.toString((bytes[i]&0xff)+0x100,16).substring(1));
				}
				return sBuilder.toString();
			}
			catch(NoSuchAlgorithmException ex) {
				throw new UserDefinedException("Error Occur : ",ex);
			}
		}
}
