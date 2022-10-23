package pojoclasses;

public class Customer extends User{
// create table Customerinfo(customerId bigint  primary key,Aadhar bigint(30),panNumber varchar(30),status varchar(30),foreign key(customerId) refer
//	ences Userinfo(customerId));
	private int customerId,zipcode;
	private long aadhar;
	private String panNumber,status;
	public void setCustomerId(int customerId) {
		this.customerId=customerId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setAadhar(long aadhar) {
		this.aadhar=aadhar;
	}
	public long getAadhar() {
		return aadhar;
	}
	public void setPanNumber(String panNumber) {
		this.panNumber=panNumber;
	}
	public String getPanNumber() {
		return panNumber;
	}
	public void setZipcode(int zipcode) {
		this.zipcode=zipcode;
	}
	public int getZipcode() {
		return zipcode;
	}
	public void setStatus(String status) {
		this.status=status;
	}
	public String getStatus() {
		return status;
	}
}
