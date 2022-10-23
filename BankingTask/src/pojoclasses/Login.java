package pojoclasses;

public class Login {
private int customerId;
private String name,passCode,role;
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
public void setPassCode(String passCode) {
	this.passCode=passCode;
}
public String getPassCode() {
	return passCode;
}
public void setRole(String role) {
	this.role=role;
}
public String getRole() {
	return role;
}
}
