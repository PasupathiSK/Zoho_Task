package runnerclass;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import check.UserDefinedException;
import pojoclasses.Account;
import pojoclasses.Customer;
import pojoclasses.Login;
import pojoclasses.Transaction;
import pojoclasses.User;
import login.UserLogin;
import userlayer.CustomerTask;
import userlayer.AmountTransaction;
import utili.MillisToDate;

public class TestRunnerBank {
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
public static String getStringLine(String inputString) {
	input.nextLine();
	return inputString=input.nextLine();
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
@SuppressWarnings({ "unchecked", "rawtypes" })
public static void main(String args[])throws UserDefinedException{
	int choice=-1;
	String role="";
	boolean flag=false;
	CustomerTask bTask=new CustomerTask();
	AmountTransaction aTask=new AmountTransaction(); 
	UserLogin login = new UserLogin();
	List<Integer>idList=new ArrayList<Integer>();
	System.out.println("if you want to stop just enter 0 : ");
	System.out.println("Enter a 1 to login : ");
	while(choice!=0) {
		try {
		System.out.println("1. User Login \n2. Diplay a User info\n3. Display a Accountinfo\n4. Transaction Details"
				+ "\n5. Deposit Amount\n6. Withdraw Amount\n7. Transefer Amount\n8. Get Balance"
				+ "\n9. CustomerId to All Accountinfo\n10. customer info\n11. set request for withdraw\n12. set request for account status\n13. Last N Transaction of Account Number\n14. update passcode in login\n15. get login info by customerId\n16. update userinfo\n17. update customerinfo\n18. if you want to logout just enter 0");
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
//			passcode=bTask.getEncryptedPasscode(passcode);
			role=login.userLogin(cId,name,passcode);
			if(role!=null&&login.checkLogin(cId)) {
				System.out.println("Yes, Login validated :");
				bTask.userActivityStatus(cId,true);
				idList.add(cId);
				flag=true;
			}
			else {
				System.out.println("Login Not validated : ");
			}
			break;
		}
		
//  get customer info by user id
		case 2:
		{
			System.out.println("Display a User info : ");
			if(flag) {
				List<User>list=bTask.getAllUserInfo(cId);
				for(User uPojo:list) {
					System.out.println("CustomerId="+uPojo.getCustomerId()+" , Name="+uPojo.getName()+" , mobile="+uPojo.getMobileNumber()+" , Status="+uPojo.getStatus()+" , Email="+uPojo.getEmail()+" , DateOfBirth="+uPojo.getDateOfBirth());
				}
			}
			else {
				System.out.println("Not authorized : ");
			}
			break;
		}
		
// get account info by account number 
		case 3:
		{
			long aNumber=0;
			System.out.println("Display a Account info : ");
			if(flag) {
				System.out.println("Enter a Account Number : ");
				aNumber=getLong(aNumber);
				Account aPojo=new Account();
				if(bTask.getAccountInfoAccountStatus(aNumber)) {
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
					System.out.println("Account is inactive : ");
				}
			}
			else {
				System.out.println("Not authorized : ");
			}
			break;
		}
		
	// get transaction details by account number 
		case 4:
		{
			long aNumber=0;
			System.out.println("Transaction detatils : ");
			if(flag) {
			    Transaction tPojo=new Transaction();
				System.out.println("Enter a Account Number : ");
				aNumber=getLong(aNumber);
				if(bTask.getAccountInfoAccountStatus(aNumber)) {
				Map<Integer,Transaction>map=bTask.getTransactionInfo(cId,aNumber);
				for(Map.Entry<Integer, Transaction>entry:map.entrySet()) {
					tPojo=(Transaction)entry.getValue();
					long millis=tPojo.getDateOfTransaction();
					String dTime=MillisToDate.getZonedDateTime(millis);
					System.out.println("Transaction Number="+entry.getKey()+" , Account Number="+tPojo.getAccountNumber()+" , Branch="+tPojo.getBranch()+" , Amount : "+tPojo.getAmount()+" , CustomerId="+tPojo.getCustomerId()+" , Date Of Transaction="+dTime+" , Transfer Account From="+tPojo.getTransferAccountFrom()+" , Received Account From="+tPojo.getReceivedAccountFrom()+" , Current Balance="+tPojo.getCurrentBalance()+" , ModeOfTransaction="+tPojo.getModeOfTransaction());
				}
			}
				else {
					System.out.println("Account is inactive : ");
				}
				
			}
			else {
				System.out.println("Not authorized : ");
			}
			break;
		}
		case 5:
		{
			long aNumber=0,amount=0;
			if(flag) {
			System.out.println("Deposit amount : ");
			Transaction tPojo=new Transaction();
			System.out.println("Enter Account Number : ");
			aNumber=getLong(aNumber);
			tPojo.setAccountNumber(aNumber);
			if(bTask.getAccountInfoAccountStatus(aNumber)) {
			System.out.println("Enter a amount : ");
			amount=getLong(amount);
			tPojo.setAmount(amount);
			tPojo.setModeOfTransaction("deposit");
			if(aTask.makeTransaction(tPojo)) {
				System.out.println("Amount Deposited : ");
			}
			else {
				System.out.println("Amount Not Deposited : ");
			}
			}
			else {
				System.out.println("Account is inactive : ");
			}
			}
			break;
		}
		case 6:
		{
//			long aNumber=0,amount=0;
//			if(flag) {
//			System.out.println("Withdraw amount : ");
//			System.out.println("Enter Account Number : ");
//			aNumber=getLong(aNumber);
//			if(bTask.getAccountInfoAccountStatus(aNumber)) {
//			System.out.println("Enter amount : ");
//			amount=getLong(amount);
//			if(aTask.withdrawMoney(amount, aNumber)) {
//				System.out.println("Amount Withdraw : ");
//				System.out.println("New Balance : "+aTask.getBalance(aNumber));
//			}
//			else {
//				System.out.println("Amount Not Withdrawal : ");
//			}
//			}
//			else {
//				System.out.println("Account is inactive : ");
//			}
//			}
			break;
		}
		case 11:
		{
			long aNumber=0,amount=0;
			if(flag) {
				System.out.println("Withdraw Request : ");
				System.out.println("Enter Account Number : ");
				aNumber=getLong(aNumber);
				if(bTask.getAccountInfoAccountStatus(aNumber)) {
				System.out.println("Enter a Amount : ");
				amount=getLong(amount);
				if(aTask.setWithdrawRequest(cId, aNumber, amount)) {
					System.out.println("Requested Successfully : ");
				}
				else {
					System.out.println("Not Requested : ");
				}
				}
				else {
					System.out.println("Account is inactive : ");
				}
			}
			break;
		}
		case 7:
		{
			long sNumber=0,rNumber=0,amount=0;
			if(flag) {
				System.out.println("Transfer amount : ");
				Transaction tPojo=new Transaction();
				System.out.println("Enter a Sender Account : ");
				sNumber=getLong(sNumber);
				tPojo.setTransferAccountFrom(sNumber);
				if(bTask.getAccountInfoAccountStatus(sNumber)) {
				System.out.println("Enter a Receiver Account : ");
				rNumber=getLong(rNumber);
				tPojo.setReceivedAccountFrom(rNumber);
				if(bTask.getAccountInfoAccountStatus(rNumber)) {
				System.out.println("Enter a amount : ");
				amount=getLong(amount);
				tPojo.setAmount(amount);
				tPojo.setModeOfTransaction("transfer");
				if(aTask.getBalance(sNumber)>amount) {
					if(aTask.makeTransaction(tPojo)) {
						System.out.println("Amount Transfer : ");
					}
				}
				else {
					System.out.println("Insufficient balance : ");
				}
				}
				else {
					System.out.println("Receiver Account is inactive : ");
				}
				}
				else {
					System.out.println("Sender Account is inactive : ");
				}
				
			}
			break;
		}
		case 8:
		{
			long aNumber=0;
			if(flag) {
			System.out.println("Enter Account Number : ");
			aNumber=getLong(aNumber);
			if(bTask.getAccountInfoAccountStatus(aNumber)) {
			System.out.println("Balance : "+aTask.getBalance(aNumber));
			}
			else {
				System.out.println("Account is inactive : ");
			}
			}
			break;
		}
		case 9:
		{
			if(flag) {
				List<Account>list=bTask.getCustomerIdToGetAllAccountInfo(cId);
				for(Account aPojo:list) {
					System.out.println("AccountNumber="+aPojo.getAccountNumber()+" , CustomerId="+aPojo.getCustomerId()+" , Branch="+aPojo.getBranch()+" , Accounttype="+aPojo.getAccountType()+" , Balance="+aPojo.getBalance()+", AccountStatus="+aPojo.getAccountStatus()+" , ifse="+aPojo.getAccountStatus());
				}
			}
			break;
		}
		case 10:
		{

			System.out.println("Display a Customer info : ");
			if(flag) {
//				Customer cPojo=bTask.getCustomerInfo(cId);
//				System.out.println("CustomerId="+cPojo.getCustomerId()+" , Aadhar="+cPojo.getAadhar()+" , PanNumber="+cPojo.getPanNumber()+" , zipcode="+cPojo.getZipcode()+" , status="+cPojo.getStatus());
				List<Customer>list=bTask.getAllCustomerInfo(cId);
				for(Customer cPojo:list) {
					System.out.println("CustomerId="+cPojo.getCustomerId()+" , Aadhar="+cPojo.getAadhar()+" , PanNumber="+cPojo.getPanNumber()+" , zipcode="+cPojo.getZipcode()+" , status="+cPojo.getStatus());
				}
			}
			else {
				System.out.println("Not authorized : ");
			}
			break;
		}
		case 12:
		{
			int newChoice=0;
			long accountNumber=0;
			String status="";
			System.out.println("Set request for Account status : ");
			if(flag) {
				System.out.println("Enter a Account Number : ");
				accountNumber=getLong(accountNumber);
//				System.out.println("Enter your choice : \n1. active\n2. inactive");
//				newChoice=getInt(newChoice);
//				status=(newChoice==1)?"active":"inactive";
				System.out.println("Enter Request Description : ");
				status=getStringLine(status);
				if(bTask.setRequestForAccountStatus(cId, accountNumber, status)) {
					System.out.println("Requested Successfully : ");
				}
				else {
					System.out.println("Requested Not Successfully : ");
				}
			}
			break;
		}
		case 13:
		{
			int length=0,newChoice=0;
			long accountNumber=0;
			if(flag) {
				System.out.println("Last N Transaction : ");
				System.out.println("Enter a Account Number : ");
				accountNumber=getLong(accountNumber);
				System.out.println("Enter your choice : \n1. last 7 days\n2. last 15 days");
				newChoice=getInt(newChoice);
				length=(newChoice==1)?7:(newChoice==15)?15:null;
				Transaction tPojo=new Transaction();
				List<Transaction>list=bTask.getLastNTransactionInfo(accountNumber, length);
				int newLength=list.size();
				for(int i=0;i<newLength;i++) {
					tPojo=(Transaction)list.get(i);
					long millis=tPojo.getDateOfTransaction();
					String dTime=MillisToDate.getZonedDateTime(millis);
					System.out.println("Transaction Number="+tPojo.getTransactionNumber()+" , Account Number="+tPojo.getAccountNumber()+" , Branch="+tPojo.getBranch()+" , Amount : "+tPojo.getAmount()+" , CustomerId="+tPojo.getCustomerId()+" , Date Of Transaction="+dTime+" , Transfer Account From="+tPojo.getTransferAccountFrom()+" , Received Account From="+tPojo.getReceivedAccountFrom()+" , Current Balance="+tPojo.getCurrentBalance()+" , Transationtype="+tPojo.getTransactionType()+" ,ModeOfTransaction="+tPojo.getModeOfTransaction());
					}
			}
			break;
		}
		case 14:
		{
			String passCode="";
			if(flag) {
				Login lPojo=new Login();
				System.out.println("Modify login Passcode : ");
				System.out.println("Enter new Passcode : ");
				passCode=getString(passCode);
				lPojo.setPassCode(passCode);
				lPojo.setCustomerId(cId);
				if(bTask.updatePasscodeInLogin(lPojo)) {
					System.out.println("Data Updated Successfully : ");
				}
				else {
					System.out.println("Not Updated : ");
				}
			}
			break;
		}
		case 15:
		{
			if(flag) {
				System.out.println("Login info : ");
				List<Login>list=bTask.getAllLoginInfo(cId);
				for(Login lPojo:list) {
				System.out.println("customerId="+lPojo.getCustomerId()+" , passcode="+lPojo.getPassCode()+" , name="+lPojo.getName()+" , role="+lPojo.getRole());
				}
			}
			break;
		}
		case 16:
		{
			int newChoice=0,length=0,date=0,month=0,year=0;
			String value="";
			if(flag) {
				User user=new User();
				System.out.println("update data in userinfo : ");
				System.out.println("Enter a how many column you want : ");
				length=getInt(length);
				for(int i=0;i<length;i++) {
					System.out.println("Enter your choice : \n1. name\n2. email\n3. dateOfBirth");
					newChoice=getInt(newChoice);
					System.out.println("Enter a value : ");
					if(newChoice==1) {
						value=getString(value);
						user.setName(value);
					}
					if(newChoice==2) {
						value=getString(value);
						user.setEmail(value);
				}
					if(newChoice==3) {
						value="";
						System.out.println("Enter a date : ");
						date=getInt(date);
						System.out.println("Enter a month : ");
						month=getInt(month);
						System.out.println("Enter a year : ");
						year=getInt(year);
						value=date+"-"+month+"-"+year;
						System.out.println(value);
						user.setDateOfBirth(value);
					}
				}
				user.setCustomerId(cId);
				if(bTask.updateUserInfo(user)) {
					System.out.println("Updated Data Successfully : ");
				}
				else {
					System.out.println("Not Updated : ");
				}
			}
				else {
					System.out.println("Invalid Choice : ");
				}
			break;
		}
		case 17:
		{
			int zipcode=0,newChoice=0,length=0;
			long aadhar=0;
			String panNumber="",column="";
			Object object=null;
			if(flag) {
				Customer cPojo=new Customer();
				System.out.println("update data in customerinfo : ");
				System.out.println("Enter how many details you enter : ");
				length=getInt(length);
				for(int i=0;i<length;i++) {
				System.out.println("Enter your choice : \n1. aadhar\n2. panNumber\n3. zipcode");
				newChoice=getInt(newChoice);
				if(newChoice>0&&newChoice<4) {
					column=(newChoice==1)?"Aadhar":(newChoice==2)?"panNumber":(newChoice==3)?"zipcode":null;
					System.out.println("Enter a value : ");
					if(newChoice==1) {
					aadhar=getLong(aadhar);
					cPojo.setAadhar(aadhar);
					}
					if(newChoice==2) {
						panNumber=getString(panNumber);
						cPojo.setPanNumber(panNumber);
					}
					if(newChoice==3) {
						zipcode=getInt(zipcode);
						cPojo.setZipcode(zipcode);
					}
				}
				}
					cPojo.setCustomerId(cId);
					if(bTask.updateCustomrInfo(cPojo)) {
						System.out.println("Updated Data Successfully : ");
					}
					else {
						System.out.println("Not Updated : ");
					}
			}
			break;
		}
		case 0:
		{
			System.out.println("exit");
			int length=idList.size();
			for(int i=0;i<length;i++) {
			bTask.userActivityStatus(idList.get(i),false);
			}
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
