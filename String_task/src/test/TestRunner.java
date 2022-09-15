package test;
import task.Task;
import custome.UserDefinedException;
import java.util.Scanner;
import java.util.Arrays;


public class TestRunner {

static Scanner input=new Scanner(System.in);
public static int getIntInput(int inputNum){
boolean flag=false;
while(!flag){
if(input.hasNextInt()){
inputNum=input.nextInt();
flag=true;
}
else{
input.next();
System.out.println("enter a valid integer input...");
}
}
return inputNum;
}

public static String getStringInput(String inputString){
inputString=input.next();
return inputString;
}
public static String getLineStringInput(String inputString){
input.nextLine();
inputString=input.nextLine();
return inputString;
}
public static char getCharInput(char character){
boolean flag=false;
while(!flag){
char checkCharacter=input.next().charAt(0);
if(Character.isLetter(checkCharacter)){
character=checkCharacter;
flag=true;
}
else{
System.out.println("Enter a valid character input...");
}
}
return character;
}
public static void main(String args[]){
String inputString="";
int inputNum=0,choice=-1;
char character=' ';
boolean flag=false;
Task task=new Task();
System.out.println("if you want to stop just enter 0 :");
while(choice!=0){
if(flag){
break;
}
else{
System.out.println("Enter your choice: ");
choice=getIntInput(choice);
}
switch(choice){
case 1:
{
if(args.length!=0){
try{
int size=task.stringLength(args[0]);
System.out.println("Result is: "+size);
}
catch(UserDefinedException ex){
//System.out.println("String index legnth is null...\nSo NULL Exception Occure...\nGive Proper length contain String...\nGive input in terminal run command...");
System.out.println(ex.getMessage());
flag=true;
}
}
else{
System.out.println("string not...");
flag=true;
}
break;
}
case 2:
{
try{
System.out.println("convert string to char array :");
System.out.println("Enter a value for string to char Array: ");
inputString=getStringInput(inputString);
System.out.println("Result is: ");
System.out.print(Arrays.toString(task.toCharArray(inputString)));
}
catch(UserDefinedException ex){
System.out.println(ex.getMessage());
}
System.out.println( );
break;
}
case 3:
{
try{
System.out.println("find last char in string :");
System.out.println("Enter a value for find last char in string: ");
inputString=getStringInput(inputString);
System.out.println("Result is: "+task.stringLastCharacter(inputString));
}
catch(UserDefinedException ex){
System.out.println(ex.getMessage());
}
break;
}
case 4:
{
try{
System.out.println("find character occurance in string :");
System.out.println("Enter a value: ");
inputString=getStringInput(inputString);
System.out.println("Enter a which character you want to know occurance :");
character=getCharInput(character);
System.out.println("Result is: \n"+task.stringCharOccurance(inputString,character));
}
catch(UserDefinedException ex){
System.out.println(ex.getMessage());
}
break;
}
case 5:
{
try{
System.out.println("find greatest position of the character :");
System.out.println("Enter a value: ");
inputString=getStringInput(inputString);
System.out.println("Enter a which charachter you want to know greatest position :");
character=getCharInput(character);
int result=task.findGreatestPosition(inputString,character);
if(result!=-1){
System.out.println(task.findGreatestPosition(inputString,character));
}
else{
System.out.println("Given character not in string...\nPostion is consider -1...");
}
}
catch(UserDefinedException ex){
System.out.println(ex.getMessage());
}
break;
}
case 6:
{
System.out.println("find last n number position of character in string :");
System.out.println("Enter a value: ");
inputString=getStringInput(inputString);
System.out.println("Enter a which n number of last position you want: ");
inputNum=getIntInput(inputNum);
try{
String result=task.lastNPosition(inputString,inputNum);
System.out.println("Result is: "+result);
}
catch(UserDefinedException ex){
System.out.println(ex.getMessage());
}
break;
}
case 7:
{
System.out.println("find firs n number position of character in string :"); 
System.out.println("Enter a value: ");
inputString=getStringInput(inputString);
System.out.println("Enter a which n number of first position you want: ");
inputNum=getIntInput(inputNum);
try{
String result=task.firstNPosition(inputString,inputNum);
System.out.println("Result is: "+result);
}
catch(UserDefinedException ex){
System.out.println(ex.getMessage());
}
break;
}
case 8:
{
System.out.println("Replace a string :");
System.out.println("Enter a value: ");
inputString=getStringInput(inputString);
System.out.println("Enter a replace value: ");
String replaceString=getStringInput(inputString);
try{
String result=task.replaceFirstN(inputString,replaceString);
System.out.println("Result is: "+result);
}
catch(UserDefinedException ex){
System.out.println(ex.getMessage());
}
break;
}
case 9:
{
System.out.println("find string start :");
System.out.println("Enter a value: ");
inputString=getStringInput(inputString);
System.out.println("Enter a start check value: ");
String checkStartString=getStringInput(inputString);
try{
boolean result=task.stringStart(inputString,checkStartString);
if(result){
System.out.println("yes, string start with "+checkStartString);
}
else{
System.out.println("not "+checkStartString+" started...");
}
}
catch(UserDefinedException ex){
System.out.println(ex.getMessage());
}
break;
}
case 10:
{
System.out.println("find string end: ");
System.out.println("Enter a value: ");
inputString=getStringInput(inputString);
System.out.println("Enter a end check value: ");
String checkEndString=getStringInput(inputString);
try{
boolean result=task.stringEnd(inputString,checkEndString);
if(result){
System.out.println("yes, string end with "+checkEndString);
}
else{
System.out.println("not "+checkEndString+" in end...");
}
}
catch(UserDefinedException ex){
System.out.println(ex.getMessage());
}
break;
}
case 11:
{
System.out.println("convert string lower to upper :");
System.out.println("Enter a value: ");
inputString=getStringInput(inputString);
try{
String result=task.lowertoUpper(inputString);
System.out.println("Result is: "+result);
}
catch(UserDefinedException ex){
System.out.println(ex.getMessage());
}
break;
}
case 12:
{
System.out.println("convert string upper to lower :");
System.out.println("Enter a value: ");
inputString=getStringInput(inputString);
try{
String result=task.uppertoLower(inputString);
System.out.println("Result is: "+result);
}
catch(UserDefinedException ex){
System.out.println(ex.getMessage());
}
break;
}
case 13:
{
System.out.println("Reverse a string :");
System.out.println("Enter a value: ");
inputString=getStringInput(inputString);
try{
String result=task.reverseString(inputString);
System.out.println("Result is: "+result);
}
catch(UserDefinedException ex){
System.out.println(ex.getMessage());
}
break;
}
case 14:
{
try{
System.out.println("string line to multiple string :");
System.out.println("Enter a value: ");
inputString=getLineStringInput(inputString);
String stringArray[]=task.line_multipleString(inputString);
System.out.println("Result is: ");
for(int i=0;i<stringArray.length;i++){
System.out.println(stringArray[i]);
}
}
catch(UserDefinedException ex){
System.out.println(ex.getMessage());
}
break;
}
case 15:
{
try{
System.out.println("string line to multiple string concat :");
System.out.println("Enter a value: ");
inputString=getLineStringInput(inputString);
System.out.println("Result is: \n"+task.multipleStringConcat(inputString));
}
catch(UserDefinedException ex){
System.out.println(ex.getMessage());
}
break;
}
case 16:
{
System.out.println("multiple line string Array: ");
System.out.println("Enter a value: ");
inputString=getLineStringInput(inputString);
try{
String stringArray[]=task.multipleStringArray(inputString);
System.out.println("Result is: ");
System.out.print("{");
for(int i=0;i<stringArray.length;i++){
System.out.print('"'+stringArray[i]+'"');
if(i!=stringArray.length-1){
System.out.print(",");
}
}
System.out.print("}");
}
catch(UserDefinedException ex){
System.out.println(ex.getMessage());
}
System.out.println( );
break;
}
case 17:
{
try{
System.out.println("merge multiple string: ");
System.out.println("Enter  how many string you want to enter: ");
inputNum=getIntInput(inputNum);
String []stringArray=new String[inputNum];
//String stringArray[]=null;
System.out.println("Enter a value: ");
for(int i=0;i<inputNum;i++){
int position=i+1;
System.out.print("String "+position+": ");
stringArray[i]=getStringInput(inputString);
}
System.out.print("Result is: \n"+task.mergeMultipleString(stringArray));
}
catch(UserDefinedException ex){
System.out.println(ex.getMessage());
}
System.out.println();
break;
}
case 18:
{
try{
System.out.println("check two string are equal :");
System.out.println("Enter a first value: ");
inputString=getStringInput(inputString);
System.out.println("Enter a second value: ");
String newString=getStringInput(inputString);
boolean result =task.stringEqual(inputString,newString);
if(result){
System.out.println("Is the String Matching: "+result);
}
else{
System.out.println("Is the Stirng Matching: "+result);
}
}
catch(UserDefinedException ex){
System.out.println(ex.getMessage());
}
break;
}
case 19:
{
try{
System.out.println("check two string are equal with non case sensitive: ");
System.out.println("Enter a first value: ");
inputString=getStringInput(inputString);
System.out.println("Enter a second value: ");
String newString=getStringInput(inputString);
boolean result=task.stringEqualInCase(inputString,newString);
if(result){
System.out.println("Is the String Matching: "+result);
}
else{
System.out.println("Is the String Matching: "+result);
}
}
catch(UserDefinedException ex){
System.out.println(ex.getMessage());
}
break;
}
case 20:
{
try{
System.out.println("Remove space beginning and end of string :");
System.out.println("Enter a value: ");
inputString=getLineStringInput(inputString);
System.out.println("Result is: \n"+task.removeSpaceString(inputString));
}
catch(UserDefinedException ex){
System.out.println(ex.getMessage());
}
break;
}
case 0:
{
System.out.println("exit");
break;
}
default:{
System.out.println("Invalid choice...");
}
}
}
}
}