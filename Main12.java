import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import org.json.JSONArray;
import org.json.JSONException;
public class Main {
    public static void main(String[] args) {
    BufferedReader br = null;
   JSONArray array=new JSONArray();
    try {
    	br = new BufferedReader(new FileReader("block_0.csv"));
    	String line;
    	while((line = br.readLine()) != null) {
    	//	System.out.println(line);
        String[] b = line.split(",");
      //  System.out.println(b[0]);
        array.put(b[0]);
      //  System.out.println(Arrays.toString(b));
    	}

   System.out.println(array);
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
