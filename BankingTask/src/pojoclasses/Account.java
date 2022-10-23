package pojoclasses;

public class Account {
private long accountNumber;
private double balance;
private int customerId;
private String branch,accountType,accountStatus,ifsc;
public void setAccountNumber(long accountNumber) {
	this.accountNumber=accountNumber;
}
public long getAccountNumber() {
	return accountNumber;
}
public void setBalance(double balance) {
	this.balance=balance;
}
public double getBalance() {
	return balance;
}
public void setCustomerId(int customerId) {
	this.customerId=customerId;
}
public int getCustomerId() {
	return customerId;
}
public void setBranch(String branch) {
	this.branch=branch;
}
public String getBranch() {
	return branch;
}
public void setAccountType(String accountType) {
	this.accountType=accountType;
}
public String getAccountType() {
	return accountType;
}
public void setAccountStatus(String accountStatus) {
	this.accountStatus=accountStatus;
}
public String getAccountStatus() {
	return accountStatus;
}
public void setIfsc(String ifsc) {
	this.ifsc=ifsc;
}
public String getIfsc() {
	return ifsc;
}
}
