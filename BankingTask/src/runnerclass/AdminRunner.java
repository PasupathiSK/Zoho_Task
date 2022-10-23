package runnerclass;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import userlayer.AdminTask;
import userlayer.AmountTransaction;
import check.UserDefinedException;
import pojoclasses.Account;
import pojoclasses.AccountRequest;
import pojoclasses.Customer;
import pojoclasses.Login;
import pojoclasses.Request;
import pojoclasses.Transaction;
import pojoclasses.User;
import login.UserLogin;
import utili.MillisToDate;

public class AdminRunner {
static Scanner input=new Scanner(System.in);
static int cId=0;
static String name="";
public static int getInt(int inputNum) {
	boolean flag=false;
	while(!flag) {
		if(input.hasNextInt()) {
			inputNum=input.nextInt();
			flag=true;
		}
		else {
			System.out.println("Enter a valid integer : ");
		}
	}
	return inputNum;
}
public static long getLong(long inputNum) {
	return inputNum=input.nextLong();
}
public static String getString(String iString){
	return iString=input.next();
}
//public static String getZonedDateTime(long tMillis) {
//	Instant instant=Instant.ofEpochMilli(tMillis);
//	ZonedDateTime zDateTime=instant.atZone(ZoneId.of("Asia/Kolkata"));
//	DateTimeFormatter dTimeFormatter=DateTimeFormatter.ofPattern("dd:MM:yyyy HH:ss");
//	return zDateTime.format(dTimeFormatter);
//}
// login table
//	login table  :  create table login(customerId bigint(30) primary key,cName varchar(50),passcode varchar(50),role varchar(30),unique (customerId));
// Accountinfo table
//  create table Accountinfo(AccountNumber bigint(100),customerId bigint(30),Branch varchar(50),Accounttype varchar(50),Balance bigint,primary key(AccountNumber,customerId),foreign key (customerId) references login(customerId),foreign key (customerId) references Customerinfo(customerId));
// Transaction table
// create table Transaction(TransactionNumber bigint(50),AccountNumber bigint(100),Branch varchar(50),Amount bigint(100),customerId bigint(30),Date varchar(30),TransferAccountFrom varchar(50),ReceivedAccountFrom varchar(50),primary key(AccountNumber,customerId),foreign key (AccountNumber) references Accountinfo(AccountNumber),foreign key (customerId) references Customerinfo(customerId),foreign key (customerId) references login(customerId));
// Customerinfo table
// create table Userinfo(customerId bigint(30) primary key,cName varchar(50),mNumber varchar(30),gmail varchar(50),zipcode bigint,foreign key (customerId) references login(customerId));
// create table Customerinfo(customerId bigint  primary key,Aadhar bigint(30),panNumber varchar(30),status varchar(30),foreign key(customerId) refer
//ences Userinfo(customerId));
public static void main(String args[])throws UserDefinedException{
	int inputNum=0,checkNum=0,choice=-1;
	String iString="",checkString="",role="";
	boolean flag=false;
	AdminTask bTask=new AdminTask();
	UserLogin login=new UserLogin();
	AmountTransaction aTask=new AmountTransaction();
	List<Integer>idList=new ArrayList<Integer>();
	System.out.println("if you want to stop just enter 0 : ");
	System.out.println("Enter a 1 to login : ");
	while(choice!=0) {
		try {
		System.out.println("1. Admin Login\n2. Display User info by id\n3.Display a Customer info by id and Account Number\n4.Transaction Detailse"
				+ "\n5. Get Balance\n6. customer id to get all accountinfo\n7. All details of Customer info"
				+ "\n8. All details of Accountinfo\n9. All details of User info\n10. Active and Deactive Account\n11. All Resquest Pending info \n12. Request to approve withdraw\n13. Block Account\n14. All Request for Account Status\n15. Block customer by customerId\n16. All Login info\n17. Login info by customerId\n18. insert in login\n19. insert in Userinfo\n20. insert in Customerinfo\n21. insert in Accountinfo");
		System.out.println("Enter your choice : ");
		choice=getInt(choice);
		switch(choice) {
		case 1:
		{
			String passcode="";
			System.out.println("Enter a Customer Id : ");
			cId=getInt(cId);
			System.out.println("Enter a name : ");
			name=getString(name);
			System.out.println("Enter a passcode : ");
			passcode=getString(passcode);
			role=login.userLogin(cId,name,passcode);
			System.out.println(role);
			if(role.equals("admin")) {
				flag=true;
				System.out.println("Login validated : ");
			}
//			}
			else {
				System.out.println("Login Not validated : ");
			}
			System.out.println(role);
			break;
		}
		case 2:
		{
//			System.out.println("Display a User info : ");
//			if(flag) {
//					System.out.println("Enter a customer Id : ");
//					cId=getInt(cId);
//				User uPojo=bTask.getUserInfo(cId);
//				System.out.println("UserId="+cId+" , Name="+uPojo.getName()+" , Mobile="+uPojo.getMobileNumber()+" , Gmail="+uPojo.getEmail()+" , DOB="+uPojo.getDateOfBirth()+" , status="+uPojo.getStatus());
//			}
//			else {
//				System.out.println("Not authorized : ");
//			}
			break;
		}
		case 3:
		{
			long aNumber=0;
			System.out.println("Display a Account info : ");
			if(flag) {
				Account aPojo=new Account();
					System.out.println("Enter a Customer Id : ");
					cId=getInt(cId);
				System.out.println("Enter a Account Number : ");
				aNumber=getLong(aNumber);
				Map<Long,Account>nMap=new HashMap<Long,Account>();
				Map<Integer,Map>map=bTask.getAccountInfo(cId,aNumber);
				for(Map.Entry<Integer,Map> entry:map.entrySet()) {
					nMap=entry.getValue();
				}
				for(Map.Entry <Long,Account> entry:nMap.entrySet()) {
					aNumber=(long) entry.getKey();
					aPojo=(Account) entry.getValue();
				}
				System.out.println("CustomerId : "+cId+" AccountNumber : "+aNumber+" Branch : "+aPojo.getBranch()+" Accounttype :"+aPojo.getAccountType()+" Balance : "+aPojo.getBalance()+" , AccountStatus="+aPojo.getAccountStatus()+" ,ifsc="+aPojo.getIfsc());
			}
			else {
				System.out.println("Not authorized : ");
			}
			break;
		}
		case 4:
		{
			long aNumber=0;
			System.out.println("Transaction detatils : ");
			if(flag) {
				 Transaction tPojo=new Transaction();
					System.out.println("Enter a Customer Id : ");
					cId=getInt(cId);
				System.out.println("Enter a Account Number : ");
				aNumber=getLong(aNumber);
				Map<Integer,Transaction>map=bTask.getTransactionInfo(cId,aNumber);
				for(Map.Entry<Integer, Transaction>entry:map.entrySet()) {
					tPojo=(Transaction)entry.getValue();
					long millis=tPojo.getDateOfTransaction();
					String dTime=MillisToDate.getZonedDateTime(millis);
					System.out.println("Transaction Number="+tPojo.getTransactionNumber()+" , Account Number="+tPojo.getAccountNumber()+" , Branch="+tPojo.getBranch()+" , Amount : "+tPojo.getAmount()+" , CustomerId="+tPojo.getCustomerId()+" , Date Of Transaction="+dTime+" , Transfer Account From="+tPojo.getTransferAccountFrom()+" , Received Account From="+tPojo.getReceivedAccountFrom()+" , Current Balance="+tPojo.getCurrentBalance()+" , Transationtype="+tPojo.getTransactionType()+" , ModeOfTransaction="+tPojo.getModeOfTransaction());
				}
			}
			else {
				System.out.println("Not authorized : ");
			}
			break;
		}
		case 5:
		{
			long aNumber=0;
			if(flag) {
			System.out.println("Enter Account Number : ");
			aNumber=getLong(aNumber);
			System.out.println("Balance : "+aTask.getBalance(aNumber));
			}
			break;
		}
		case 6:
		{
			if(flag) {
					System.out.println("Enter Customer Id : ");
					cId=getInt(cId);
				List<Account>list=bTask.getCustomerIdToGetAllAccountInfo(cId);
				for(Account aPojo:list) {
					System.out.println("AccountNumber="+aPojo.getAccountNumber()+" , CustomerId="+aPojo.getCustomerId()+" , Branch="+aPojo.getBranch()+" , Accounttype="+aPojo.getAccountType()+" , Balance="+aPojo.getBalance()+" , AccountStatus="+aPojo.getAccountStatus()+" ,ifsc="+aPojo.getIfsc());
				}
			}
			break;
		}
		case 7:
		{
			int length=0;
			if(flag) {
					System.out.println("All details of Customer info : ");
					System.out.println("Enter a how many customer info : ");
					length=getInt(length);
					int[]customerId=new int[length];
					System.out.println("Enter a customerId : ");
					for(int i=0;i<length;i++) {
						customerId[i]=getInt(customerId[i]);
					}
					List<Customer>list=bTask.getAllCustomerInfo(customerId);
					for(Customer cPojo:list) {
						System.out.println("customerId="+cPojo.getCustomerId()+" , Aadhar="+cPojo.getAadhar()+" , PanNumber="+cPojo.getPanNumber()+" , Zipcode="+cPojo.getZipcode()+" , status="+cPojo.getStatus());
					}
				}
				else {
					System.out.println("Admin only allowed : ");
				}
			break;
		}

		case 8:
		{
			if(flag) {
					System.out.println("All details of Account info : ");
					List<Account>list=bTask.getAllAccountInfo();
					Account aPojo=new Account();
					for(Object object:list) {
						aPojo=(Account) object;
						System.out.println("AccountNumber="+aPojo.getAccountNumber()+" , customerId="+aPojo.getCustomerId()+" , Branch="+aPojo.getBranch()+" , Accounttype="+aPojo.getAccountType()+" , Balance="+aPojo.getBalance()+" , AccountStatus="+aPojo.getAccountStatus()+" , ifse="+aPojo.getIfsc());
					}
			}
					else 
					{
						System.out.println("Admin only allowed : ");
					}
			break;
		}
		case 9:
		{
			int length=0;
			if(flag) {
					System.out.println("Diplay all User info : ");
					System.out.println("Enter a how many user info : ");
					length=getInt(length);
					int[]customerId=new int[length];
						System.out.println("Enter a customer Id : ");
						for(int i=0;i<length;i++) {
						       customerId[i]=getInt(customerId[i]);
						}
						length=customerId.length;
						System.out.println(length);
					List<User>list=bTask.getAllUserInfo(customerId);
					for(User uPojo:list) {
						System.out.println("CustomerId="+uPojo.getCustomerId()+" , Name="+uPojo.getName()+" , mobile="+uPojo.getMobileNumber()+" , Status="+uPojo.getStatus()+" , Email="+uPojo.getEmail()+" , DateOfBirth="+uPojo.getDateOfBirth());
					}
				}
				else {
					System.out.println("Admin only allowed : ");
				}
			break;
		}
		case 10:
		{
			long accountNumber=0;
			int customerId=0,newChoice=0,approveChoice=0;;
			if(flag) {
				System.out.println("Active and Deactive Account : ");
				System.out.println("Enter a customer id : ");
				customerId=getInt(customerId);
				System.out.println("Enter a which account you want : ");
				accountNumber=getLong(accountNumber);
				System.out.println("Enter your choice : \n1. accept approve\n2. reject");
				approveChoice=getInt(approveChoice);
				boolean newValue=(approveChoice==1)?true:(approveChoice==2)?false:null;
				if(newValue) {
				System.out.println("Enter your choice : \n1. Active \n2.InActive");
				newChoice=getInt(newChoice);
			    boolean value=(newChoice==1)?true:(newChoice==2)?false:null;
			    bTask.deactiveAccount(customerId, accountNumber, value);
			    bTask.updateAccountstatusApprove(customerId, accountNumber, true);
			    System.out.println("Status Updated : ");
			    if(!bTask.checkCustomerInfoStatus(customerId)) {
			    	if(bTask.updateCustomerInfoStatus(customerId)) {
			    	          System.out.println("Login portal inactive :");
			    	}
			    }
			}
				else {
					bTask.updateAccountstatusApprove(customerId,accountNumber, false);
					System.out.println("Request Rejected Succefully : ");
				}
			}
			else 
			{
				System.out.println("Admin only allowed : ");
			}
	break;
		}
		case 11:
		{
			if(flag) {
				System.out.println("Request Pending info : ");
				Request rPojo=new Request();
				List<Request>list=aTask.getWithdrawRequest();
				int length=list.size();
				for(int i=0;i<length;i++) {
					rPojo=new Request();
					rPojo=(Request)list.get(i);
					System.out.println("CustomerId="+rPojo.getCustomerId()+" , AccountNumber="+rPojo.getAccountNumber()+" , Amount="+rPojo.getAmount()+" , status="+rPojo.getStatus());
				}
			}
			break;
		}
		
		case 12:
		{
			int customerId=0,newChoice=0;
			long accountNumber=0;
			if(flag) {
				System.out.println("Request info to approve withdraw : ");
				Request rPojo=new Request();
				Transaction tPojo=new Transaction();
				List<Request>list=aTask.getWithdrawRequest();
				System.out.println("Enter a customerId : ");
				customerId=getInt(customerId);
				tPojo.setCustomerId(customerId);
				System.out.println("Enter a Account Number : ");
				accountNumber=getLong(accountNumber);
				tPojo.setAccountNumber(accountNumber);
				System.out.println("Enter your choice \n1. approve\n2. reject");
				newChoice=getInt(newChoice);
				boolean value=(newChoice==1)?true:false;
				int length=list.size();
				for(int i=0;i<length;i++) {
					rPojo=new Request();
					rPojo=(Request)list.get(i);
					if(value) {
						tPojo.setAmount(rPojo.getAmount());
						tPojo.setModeOfTransaction("withdraw");
					if(aTask.makeTransaction(tPojo)) {
						System.out.println("Amount withdraw : "+customerId);
					}
					}
					else {
						System.out.println("Amount Not withdraw : ");
					}
					if(aTask.updateWithdrawApprove(rPojo,value)) {
						System.out.println("Status updated : "+customerId);
					}
					else {
						System.out.println("Status Not approved : ");
					}
				}
			}
			break;
		}
		case 13:
		{
			int customerId=0;
			long accountNumber=0;
			if(flag) {
				System.out.println("Bolcked Account : ");
				System.out.println("Enter a CustomerId : ");
				customerId=getInt(customerId);
				System.out.println("Enter a Account Number : ");
				accountNumber=getLong(accountNumber);
				if(bTask.blockedAccount(customerId, accountNumber)) {
					System.out.println("Account Blocked : ");
				}
				else {
					System.out.println("Account Not Blocked : ");
				}
			}
			break;
		}
		case 14:
		{
			if(flag) {
				System.out.println("All request Account status : ");
				List<AccountRequest>list=bTask.getRequestForAccountStatus();
				AccountRequest aPojo=new AccountRequest();
				int length=list.size();
				for(int i=0;i<length;i++) {
					aPojo=(AccountRequest)list.get(i);
					System.out.println("customerId="+aPojo.getCustomerId()+" , AccountNumber="+aPojo.getAccountNumber()+" , status="+aPojo.getStatus()+" , statusresult="+aPojo.getStatusResult());
				}
			}
			break;
		}
		case 15:
		{
			int customerId=0;
			if(flag) {
				System.out.println("Block Customer : ");
				System.out.println("Enter a custoemrId : ");
				customerId=getInt(customerId);
				if(bTask.blockedCustomer(customerId)) {
					System.out.println("Blocked Customer : ");
				}
				else {
					System.out.println("Not Blocked Customer : ");
				}
			}
		}
		case 16:
		{
			int length=0;
			if(flag) {
				System.out.println("All Login info : ");
				System.out.println("Enter a how many login info : ");
				length=getInt(length);
				int[]customerId=new int[length];
				for(int i=0;i<length;i++) {
					customerId[i]=getInt(customerId[i]);
				}
				List<Login>list=bTask.getAllLoginInfo(customerId);
				for(Login lPojo:list) {
					System.out.println("CustomerId="+lPojo.getCustomerId()+" , name="+lPojo.getName()+" , Passcode="+lPojo.getPassCode()+" , role="+lPojo.getRole());
				}
			}
			break;
		}
		case 17:
		{
//			int customerId=0;
//			if(flag) {
//				System.out.println("Login info by customerId : ");
//				System.out.println("Enter a customerId : ");
//				customerId=getInt(customerId);
//				Login lPojo=new Login();
//				lPojo=bTask.getLoginInfoByUserId(customerId);
//				if(lPojo!=null) {
//				    System.out.println("CustomerId="+lPojo.getCustomerId()+" , name="+lPojo.getName()+" , Passcode="+lPojo.getPassCode()+" , role="+lPojo.getRole());
//				}
//				else {
//					System.out.println("Not validated details : ");
//				}
//			}
			break;
		}
		case 18:
		{
			int newChoice=0,length=0;
			String name="",passCode="",newRole="";
			if(flag) {
				Login lPojo=new Login();
				System.out.println("Enter a how many value insert : ");
				length=getInt(length);
				for(int i=0;i<length;i++) {
				System.out.println("Enter a Customer Name : ");
				name=getString(name);
				lPojo.setName(name);
				System.out.println("Enter a Passcode : ");
				passCode=getString(passCode);
				lPojo.setPassCode(passCode);
				System.out.println("Enter your choice : \n1. customer\n2. admin");
				newChoice=getInt(newChoice);
				if(newChoice<3&&newChoice>0) {
					newRole=(newChoice==1)?"customer":"admin";
					lPojo.setRole(newRole);
					if(bTask.insertValuesInLogin(lPojo)) {
						System.out.println("Row inserted : ");
					}
					else {
						System.out.println("Not inserted : ");
					}
				}
				else {
					System.out.println("Invalid choice : ");
				}
				}
			}
			break;
		}
		case 19:
		{
			int length=0,customerId=0;
			String name="",mobile="",email="",dateOfBirth="";
			if(flag) {
				User uPojo=new User();
				System.out.println("Enter a how many value insert : ");
				length=getInt(length);
				for(int i=0;i<length;i++) {
					System.out.println("Enter a customerId : ");
					customerId=getInt(customerId);
					uPojo.setCustomerId(customerId);
					System.out.println("Enter a Customer Name : ");
					name=getString(name);
					uPojo.setName(name);
					System.out.println("Enter a Mobile Number : ");
					mobile=getString(mobile);
					uPojo.setMobileNumber(mobile);
					System.out.println("Enter a Mail : ");
					email=getString(email);
					uPojo.setEmail(email);
					System.out.println("Enter a Date Of Birth : ");
					dateOfBirth=getString(dateOfBirth);
					if(bTask.insertValuesInUserInfo(uPojo)) {
						System.out.println("Row Inserted : ");
					}
					else {
						System.out.println("Not Inserted : ");
					}
			}
		}
			break;
		}
		case 20:
		{
			int length=0,customerId=0,zipcode=0,newChoice=0;
			long aadhar=0;
			String panNumber="",status="";
			if(flag) {
				Customer cPojo=new Customer();
				System.out.println("Enter a how many value insert : ");
				length=getInt(length);
				for(int i=0;i<length;i++) {
					System.out.println("Enter a customerId : ");
					customerId=getInt(customerId);
					cPojo.setCustomerId(customerId);
					System.out.println("Enter Aadhar Number : ");
					aadhar=getLong(aadhar);
					cPojo.setAadhar(aadhar);
					System.out.println("Enter Pan Number : ");
					panNumber=getString(panNumber);
					cPojo.setPanNumber(panNumber);
					System.out.println("Enter a Zipcode : ");
					zipcode=getInt(zipcode);
					cPojo.setZipcode(zipcode);
					System.out.println("Enter your choice : \n1. active\n2. inactive");
					newChoice=getInt(newChoice);
					if(newChoice<3&&newChoice>0) {
						status=(newChoice==1)?"active":"inactive";
						cPojo.setStatus(status);
						if(bTask.insertValuesInCustomerInfo(cPojo)) {
							System.out.println("Row Inserted : ");
						}
						else {
							System.out.println("Not Inserted : ");
						}
					}
					else {
						System.out.println("Invalid choice : ");
					}
					}
					
				}
			break;
		}
		case 21:
		{
			int length=0,customerId=0,newChoice=0;
			long accountNumber=0;
			String branch="",accountType="",status="",ifsc="";
			if(flag) {
				Account aPojo=new Account();
				System.out.println("Enter a how many value insert : ");
				length=getInt(length);
				for(int i=0;i<length;i++) {
					System.out.println("Enter a customerId : ");
					customerId=getInt(customerId);
					aPojo.setCustomerId(customerId);
					System.out.println("Enter Account Number : ");
					accountNumber=getLong(accountNumber);
					aPojo.setAccountNumber(accountNumber);
					System.out.println("Enter a Branch : ");
					branch=getString(branch);
					aPojo.setBranch(branch);
					System.out.println("Enter Account Type : ");
					accountType=getString(accountType);
					aPojo.setAccountType(accountType);
					System.out.println("Enter your choice : \n1. active\n2. inactive");
					newChoice=getInt(newChoice);
					if(newChoice<3&&newChoice>0) {
						status=(newChoice==1)?"active":"inactive";
						aPojo.setAccountStatus(status);
						System.out.println("Enter a ifsc code : ");
						ifsc=getString(ifsc);
						aPojo.setIfsc(ifsc);
						if(bTask.inserValuesInAccountInfo(aPojo)) {
							System.out.println("Row Inserted : ");
						}
						else {
							System.out.println("Not Inserted : ");
						}
					}
					else {
						System.out.println("Invalid choice : ");
					}
					}
			}
			break;
		}
		case 22:
		{
			if(flag) {
				System.out.println("All User Activity : ");
				List<User>list=bTask.getAllUserActivity();
				User uPojo=new User();
				int length=list.size();
				for(int i=0;i<length;i++) {
					uPojo=list.get(i);
					System.out.println("customerId="+uPojo.getCustomerId()+" , status="+uPojo.getStatus());
				}
			}
			break;
		}
		case 0:
		{
			System.out.println("exit");
			flag=false;
			break;
		}
		default : 
		{
			System.out.println("Invalid choice...");
		}
		}
		}
		catch(UserDefinedException ex) {
			ex.printStackTrace();
		}
	}
}
}

