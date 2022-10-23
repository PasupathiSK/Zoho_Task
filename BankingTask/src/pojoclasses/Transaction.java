package pojoclasses;

public class Transaction {
private int customerId,transactionNumber;
private long accountNumber,dateOfTransaction,transferAccount,transferAmount,receivedAccount;
private double currentBalance;
private String branch,transactionType,modeOfTransaction;
public void setCustomerId(int customerId) {
	this.customerId=customerId;
}
public int getCustomerId() {
	return customerId;
}
public void setAccountNumber(long accountNumber) {
	this.accountNumber=accountNumber;
}
public long getAccountNumber() {
	return accountNumber;
}
public void setDateOfTransaction(long dateOfTransaction) {
	this.dateOfTransaction=dateOfTransaction;
}
public long getDateOfTransaction() {
	return dateOfTransaction;
}
public void setTransferAccountFrom(long transferAccount) {
	this.transferAccount=transferAccount;
}
public long getTransferAccountFrom() {
	return transferAccount;
}
public void setAmount(long transferAmount) {
	this.transferAmount=transferAmount;
}
public long getAmount() {
	return transferAmount;
}
public void setReceivedAccountFrom(long receivedAccount) {
	this.receivedAccount=receivedAccount;
}
public long getReceivedAccountFrom() {
	return receivedAccount;
}
public void setCurrentBalance(double currentBalance) {
	this.currentBalance=currentBalance;
}
public double getCurrentBalance() {
	return currentBalance;
}
public void setBranch(String branch) {
	this.branch=branch;
}
public String getBranch() {
	return branch;
}
public void setTransactionType(String transactionType) {
	this.transactionType=transactionType;
}
public String getTransactionType() {
	return transactionType;
}
public void setTransactionNumber(int transactionNumber) {
	this.transactionNumber=transactionNumber;
}
public int getTransactionNumber() {
	return transactionNumber;
}
public void setModeOfTransaction(String modeOfTransaction) {
	this.modeOfTransaction=modeOfTransaction;
}
public String getModeOfTransaction() {
	return modeOfTransaction;
}
}
