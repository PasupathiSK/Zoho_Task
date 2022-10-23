package pojoclasses;

public class User extends Login{
	
private String name,mobile,email,dateOfBirth,status;

private int customerId;

public void setCustomerId(int customerId) {
	this.customerId=customerId;
}
public int getCustomerId() {
	return customerId;
}
public void setName(String name) {
	this.name=name;
}
public String getName() {
	return name;
}
public void setMobileNumber(String mobile) {
	this.mobile=mobile;
}
public String getMobileNumber() {
	return mobile;
}
public void setEmail(String email) {
	this.email=email;
}
public String getEmail() {
	return email;
}
public void setDateOfBirth(String dateOfBirth) {
	this.dateOfBirth=dateOfBirth;
}
public String getDateOfBirth() {
	return dateOfBirth;
}
public void setStatus(String status) {
	this.status=status;
}
public String getStatus() {
	return status;
}
}
