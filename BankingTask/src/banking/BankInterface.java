package banking;

import java.util.List;



import java.util.Map;

import check.UserDefinedException;
import pojoclasses.Account;
import pojoclasses.AccountRequest;
import pojoclasses.Customer;
import pojoclasses.Login;
import pojoclasses.Request;
import pojoclasses.Transaction;
import pojoclasses.User;

public interface BankInterface {


	
	/**
	 * 
	 * @param user
	 * @param flag
	 * @throws UserDefinedException
	 */
	public void userActivityStatus(int customerId,boolean flag)throws UserDefinedException ; //
	
	/**
	 * 
	 * @return
	 * @throws UserDefinedException
	 */
	public List<User> getAllUserActivity()throws UserDefinedException;

// Get User Info in User 
	/**
	 * 
	 * @param customerId
	 * @return
	 * @throws UserDefinedException
	 */
	public User getUserInfo(int customerId)throws UserDefinedException;  // swami -
	/**
	 * 
	 * @param customerId
	 * @return
	 * @throws UserDefinedException
	 */
	public List<User> getAllUserInfo(int...customerId)throws UserDefinedException;
	
// Get Customer info in Customer 
	/**
	 * 
	 * @param customerId
	 * @return
	 * @throws UserDefinedException
	 */
	public Customer getCustomerInfo(int customerId)throws UserDefinedException; // swami -
	/**
	 * 
	 * @param customerId
	 * @return
	 * @throws UserDefinedException
	 */
	public List<Customer> getAllCustomerInfo(int...customerId)throws UserDefinedException;
	
// get Account info in Account
	/**
	 * 
	 * @param account
	 * @return
	 * @throws UserDefinedException
	 */
	public Map<Integer,Map> getAccountInfo(int customerId,long accountNumber)throws UserDefinedException; //
	/**
	 * 
	 * @return
	 * @throws UserDefinedException
	 */
	public List<Account> getAllAccountInfo()throws UserDefinedException;
	/**
	 * 
	 * @param account
	 * @return
	 * @throws UserDefinedException
	 */
	public List<Account> getCustomerIdToGetAllAccountInfo(int customerId)throws UserDefinedException;  //
	/**
	 * 
	 * @param aNumber
	 * @return
	 * @throws UserDefinedException
	 */
	public long getBalance(long aNumber)throws UserDefinedException;
	
// get Transaction info in Transacation
	/**
	 * 
	 * @param transaction
	 * @return
	 * @throws UserDefinedException
	 */
	public Map<Integer,Transaction> getTransactionInfo(int customerId,long accountNumber)throws UserDefinedException;  //
	/**
	 * 
	 * @param accountNumber
	 * @param length
	 * @return
	 * @throws UserDefinedException
	 */
	public List<Transaction> getLastNTransactionInfo(long accountNumber,int length)throws UserDefinedException;
	/**
	 * 
	 * @param transaction
	 * @return
	 * @throws UserDefinedException
	 */
	public boolean makeTransaction(Transaction transaction)throws UserDefinedException;

// Update Account status
	/**
	 * 
	 * @param customerId
	 * @param accountNumber
	 * @param flag
	 * @throws UserDefinedException
	 */
	public void deactiveAccount(int customerId,long accountNumber,boolean flag)throws UserDefinedException;
	
// Withdraw Request
	/**
	 * 
	 * @param customerId
	 * @param accountNumber
	 * @param amount
	 * @return
	 * @throws UserDefinedException
	 */
	public boolean setWithdrawRequest(int customerId,long accountNumber,long amount)throws UserDefinedException;
	/**
	 * 
	 * @return
	 * @throws UserDefinedException
	 */
	public List<Request> getWithdrawRequest()throws UserDefinedException;
	
// Withdraw Approve
	/**
	 * 
	 * @param request
	 * @param flag
	 * @return
	 * @throws UserDefinedException
	 */
	public boolean updateWithdrawApprove(Request request,boolean flag)throws UserDefinedException;
	
// Block Account
	/**
	 * 
	 * @param customerId
	 * @param accountNumber
	 * @return
	 * @throws UserDefinedException
	 */
	public boolean blockAccount(int customerId,long accountNumber)throws UserDefinedException; // swami - resolved
	
// Check Customer info  status 
	/**
	 * 
	 * @param customerId
	 * @return
	 * @throws UserDefinedException
	 */
	public boolean checkCustomerInfoStatus(int customerId)throws UserDefinedException;
	/**
	 * 
	 * @param customerId
	 * @return
	 * @throws UserDefinedException
	 */
	public boolean updateCustomerInfoStatus(int customerId)throws UserDefinedException;
	
// Request For Account status
	/**
	 * 
	 * @param customerId
	 * @param accountNumber
	 * @param status
	 * @return
	 * @throws UserDefinedException
	 */
	public boolean setRequestForAccountStatus(int customerId,long accountNumber,String status)throws UserDefinedException; // swami -
	/**
	 * 
	 * @return
	 * @throws UserDefinedException
	 */
	public List<AccountRequest> getRequestForAccountStatus()throws UserDefinedException;
	
// Account info status to allow user 
	/**
	 * 
	 * @param accountNumber
	 * @return
	 * @throws UserDefinedException
	 */
	public boolean getAccountInfoAccountStatus(long accountNumber)throws UserDefinedException;
	
// Block Customer
	/**
	 * 
	 * @param customerId
	 * @return
	 * @throws UserDefinedException
	 */
	public boolean blockCustomer(int customerId)throws UserDefinedException;//swami - resolved
	
// Get Login info
	/**
	 * 
	 * @param customerId
	 * @return
	 * @throws UserDefinedException
	 */
	public List<Login> getAllLoginInfo(int...customerId)throws UserDefinedException;
	
// Approve Account status
	/**
	 * 
	 * @param customerId
	 * @param accountNumber
	 * @param flag
	 * @return
	 * @throws UserDefinedException
	 */
	public boolean updateAccountStatus(int customerId,long accountNumber,boolean flag)throws UserDefinedException;
	
// Insert in Login
	/**
	 * 
	 * @param login
	 * @return
	 * @throws UserDefinedException
	 */
	public boolean insertValuesInLogin(Login login)throws UserDefinedException;
	
// Insert in User
	/**
	 * 
	 * @param user
	 * @return
	 * @throws UserDefinedException
	 */
	public boolean insertValuesInUserInfo(User user)throws UserDefinedException;
	
// Insert in Customer
	/**
	 * 
	 * @param customer
	 * @return
	 * @throws UserDefinedException
	 */
	public boolean insertValuesInCustomerInfo(Customer customer)throws UserDefinedException;
	
// Insert in Account
	/**
	 * 
	 * @param account
	 * @return
	 * @throws UserDefinedException
	 */
	public boolean inserValuesInAccountInfo(Account account)throws UserDefinedException;
	
// UPdate 
	/**
	 * 
	 * @param login
	 * @return
	 * @throws UserDefinedException
	 */
	public boolean updatePasscodeInLogin(Login login)throws UserDefinedException;
	/**
	 * 
	 * @param customerId
	 * @param column
	 * @param value
	 * @return
	 * @throws UserDefinedException
	 */
	public boolean updateUserInfo(User user)throws UserDefinedException;
	/**
	 * 
	 * @param customerId
	 * @param column
	 * @param value
	 * @return
	 * @throws UserDefinedException
	 */
	public boolean updateCustomrInfo(Customer customer)throws UserDefinedException;
	
// Encrypt Passcode
	/**
	 * 
	 * @param passCode
	 * @return
	 * @throws UserDefinedException
	 */
	public String getEncryptedPasscode(String passCode)throws UserDefinedException;

//	public boolean withdrawMoney(long amount,long aNumber)throws UserDefinedException;	
//	public boolean depositMoney(long amount,long aNumber)throws UserDefinedException;	
//	public boolean transferMoney(long sAccount,long rAccount,long amount)throws UserDefinedException;
}
