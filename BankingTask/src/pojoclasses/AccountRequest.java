package pojoclasses;

public class AccountRequest {
private int customerId;
private long accountNumber;
private String status,statusResult;
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
public void setStatus(String status) {
	this.status=status;
}
public String getStatus() {
	return status;
}
public void setStatusResult(String statusresult) {
	this.statusResult=statusresult;
}
public String getStatusResult() {
	return statusResult;
}
}
