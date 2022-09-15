package task;
import custome.UserDefinedException;
import java.util.Objects;
public class Task{
//check Null
public boolean objectNullCheck(Object object){
return object==null;
}
public void checkNull(Object object)throws UserDefinedException{
if(object==null){
throw new UserDefinedException("Error occure...\nString is null...");
}
}
public void checkEmpty(String inputString)throws UserDefinedException{
if(inputString.isEmpty()){
throw new UserDefinedException("Error occure...\nString is empty...");
}
}
//find string length
public int stringLength(String inputString)throws UserDefinedException{
if(Objects.isNull(inputString)){
throw new UserDefinedException("Error occure...\nString length is Null");
}
int size=inputString.length();
return size;
}
//conver string to char array
public char[] toCharArray(String inputString)throws UserDefinedException{
checkNull(inputString);
checkEmpty(inputString);
char []charArray=inputString.toCharArray();
return charArray;
}
//find last char in character array
public String stringLastCharacter(String inputString)throws UserDefinedException{
int length=stringLength(inputString);
String lastChar=inputString.substring(length-1);
return lastChar;
}
//find char occurance in string
public int stringCharOccurance(String inputString,char character)throws UserDefinedException{
char charArray[];
charArray=inputString.toCharArray();
int count=0;
int length=stringLength(inputString);
for(int i=0;i<length;i++){
if(charArray[i]==character){
count++;
}
}
return count;
}
//find the greatest position of the given char in string
public int findGreatestPosition(String inputString,char character)throws UserDefinedException{
checkNull(inputString);
checkEmpty(inputString);
int position=inputString.lastIndexOf(character);
return position;
}
//find last N position of string
public String lastNPosition(String inputString,int length)throws UserDefinedException{
if(stringLength(inputString)<length||length<0){
String exceptionResult=String.format("Error occure...\nIndex length of string is %d your given length %d",stringLength(inputString),length);
throw new UserDefinedException(exceptionResult);
}
int size=stringLength(inputString);
String lastNString=inputString.substring(size-length,size);
return lastNString;
}
//find fist N position of the given string
public String firstNPosition(String inputString,int length)throws UserDefinedException{
if(stringLength(inputString)<length||length<0){
String exceptionResult=String.format("Error occure...\nIndex length of string is %d your given length %d",stringLength(inputString),length);
throw new UserDefinedException(exceptionResult);
}
int size=stringLength(inputString);
String firstNString=inputString.substring(0,length);
return firstNString;
}
//replace first N of string with replacestring
public String replaceFirstN(String inputString,String replaceString)throws UserDefinedException{
int length=stringLength(replaceString);
if(length>stringLength(inputString)){
String exceptionResult=String.format("Error occure...\nIndex length of string is %d your give string length %d",stringLength(inputString),length);
throw new UserDefinedException(exceptionResult);
}
String newString=replaceString+inputString.substring(length);
return newString;
}
//find the string start with startstring
public boolean stringStart(String inputString,String startString)throws UserDefinedException{
if(stringLength(inputString)<stringLength(startString)){
String resultException=String.format("Index length miss match Exception occure...\nActual string length is %d your check string legnth %d",stringLength(inputString),stringLength(startString));
throw new UserDefinedException(resultException);
}
int length=stringLength(startString);
return inputString.startsWith(startString);
}
//find the string end with endstring
public boolean stringEnd(String inputString,String endString)throws UserDefinedException{
if(stringLength(inputString)<stringLength(endString)){
String exceptionResult=String.format("Index length miss match Exception occure...\nActual string length is %d your check string length %d",stringLength(inputString),stringLength(endString));
throw new UserDefinedException(exceptionResult);
}
int length=stringLength(endString);
return inputString.endsWith(endString);
}
//lowercase string to uppercase
public String lowertoUpper(String inputString)throws UserDefinedException{
int length=stringLength(inputString);
for(int i=0;i<length;i++){
if(!Character.isLetter(inputString.charAt(i))){
throw new UserDefinedException("Error occure...\nGive only Alphabetic words...");
}
}
String upperCaseString=inputString.toUpperCase();
return upperCaseString;
}
//uppercase string to lowercase
public String uppertoLower(String inputString)throws UserDefinedException{
int length=stringLength(inputString);
for(int i=0;i<length;i++){
if(!Character.isLetter(inputString.charAt(i))){
throw new UserDefinedException("Error occure...\nGive only Alphabetic words...");
}
}
String lowerCaseString=inputString.toLowerCase();
return lowerCaseString;
}
//reverse a string
public String reverseString(String inputString)throws UserDefinedException{
checkNull(inputString);
checkEmpty(inputString);
StringBuilder sbuilder=new StringBuilder(inputString);
sbuilder.reverse();
String newString=sbuilder.toString();
return newString;
}
//multiple string enter by user
public String[] line_multipleString(String inputString)throws UserDefinedException{
checkEmpty(inputString);
int count=1;
int length=stringLength(inputString);
String []newStringArray=inputString.split(" ");
for(int i=0;i<length;i++){
if(inputString.charAt(i)==' '){
count++;
}
}
return newStringArray;
}
//multiple string with concat 
public String multipleStringConcat(String inputString)throws UserDefinedException{
checkEmpty(inputString);
String concatString="";
int count=1;
int length=stringLength(inputString);
String []newStringArray=inputString.split(" ");
for(int i=0;i<length;i++){
if(inputString.charAt(i)==' '){
count++;
}
}
for(int i=0;i<count;i++){
concatString=concatString.concat(newStringArray[i]);
}
return concatString;
}
//multiple string in array
public String[] multipleStringArray(String inputString)throws UserDefinedException{
checkEmpty(inputString);
String newString;
int count=1;
int length=stringLength(inputString);
String [] newStringArray=inputString.split(" ");
for(int i=0;i<length;i++){
if(inputString.charAt(i)==' '){
count++;
}
}
return newStringArray;
}
//merge each multiple string
public String mergeMultipleString(String inputArray[])throws UserDefinedException{
checkNull(inputArray);
String newString="";
int length=inputArray.length;
return newString.join(inputArray,"-");
}
//check two strings are equal
public boolean stringEqual(String firstString,String secondString)throws UserDefinedException{
checkEmpty(firstString);
checkEmpty(secondString);
if(objectNullCheck(firstString)||objectNullCheck(secondString)){
throw new UserDefinedException("Enter two strings properly...");
}
return firstString.equals(secondString);
}
//chech two strings are equal without case sentivity
public boolean stringEqualInCase(String firstString,String secondString)throws UserDefinedException{
checkEmpty(firstString);
checkEmpty(secondString);
if(objectNullCheck(firstString)||objectNullCheck(secondString)){
throw new UserDefinedException("Enter two strings properly...");
}
return firstString.equalsIgnoreCase(secondString);
}
//remove beginning and end space in string
public String removeSpaceString(String inputString)throws UserDefinedException{
checkNull(inputString);
checkEmpty(inputString);
String newString=inputString.trim();
return newString;
}
}
