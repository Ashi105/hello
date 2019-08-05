import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class Main {
    public static void main(String[] args) {
    BufferedReader br = null;
    try {
    	br = new BufferedReader(new FileReader("block_0.csv"));
    	String[] line;
    	while((line = br.readLine()) != null) {
    		System.out.println(line[0]);
    	}
    	
    } catch(IOException e) {
    	e.printStackTrace();
    } finally {
    	try {
    	br.close();
    	}
    	catch(IOException e) {
        	e.printStackTrace();}
    }
}}