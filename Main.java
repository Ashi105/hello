import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Main {

public static void main(String[] args) {

String fileName = "data.csv";

File file = new File(fileName);
try{
 Scanner inputStream = new Scanner(file);
while(inputStream.hasNext()){

String data = inputStream.next();
System.out.println(data + "***");

String[] values = data.split(",");
System.out.println(values[4] + "**");


}
inputStream.close();
} catch(FileNotFoundException e) {

e.printStackTrace();
}
