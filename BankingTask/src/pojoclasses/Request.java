package pojoclasses;

public class Request {
private int customerId;
private long accountNumber,amount;
private String status;
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
public void setAmount(long amount) {
	this.amount=amount;
}
public long getAmount() {
	return amount;
}
public void setStatus(String status) {
	this.status=status;
}
public String getStatus() {
	return status;
}
}
