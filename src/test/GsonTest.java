package test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

class T{
	int a=1;
	void print(){System.out.println("a="+a);}
}

public class GsonTest {
	  
	public static  void write(Object toWrite) throws IOException {

		Gson gson = new Gson();

		        String jsonString2 = gson.toJson(toWrite);
		        System.out.println("Generated json text: " + jsonString2);
		        
		        //let's write our JSON string to a file
		        FileWriter fileOut2 = new FileWriter("GsonTest.json");
		        BufferedWriter out2 = new BufferedWriter(fileOut2);
		        out2.write(jsonString2);
		        out2.close();
	}
	public static Object load( ) throws FileNotFoundException{
		Gson gson = new Gson();
		
		 Scanner scanner2 = new Scanner(new File("GsonTest.json"));
		        String wholeFile2 = scanner2.useDelimiter("\\A").next();
		        Type collectionType2 = new TypeToken<T>(){}.getType();
		       Object ints3 = gson.fromJson(wholeFile2, collectionType2);
		       return ints3;
	}
	       
		   
	

}