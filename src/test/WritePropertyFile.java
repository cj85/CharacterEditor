package test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class WritePropertyFile {
	private static ArrayList<String> myProperties = new ArrayList<String>();
	static {
		myProperties.add("health");
		myProperties.add("speed");
		myProperties.add("damage");
	}

	public static void write() throws IOException {

		  Gson gson = new Gson();

		        String jsonString2 = gson.toJson(myProperties);
		        System.out.println("Generated json text: " + jsonString2);
		        
		        //let's write our JSON string to a file
		        FileWriter fileOut2 = new FileWriter("Properties.json");
		        BufferedWriter out2 = new BufferedWriter(fileOut2);
		        out2.write(jsonString2);
		        out2.close();
		        
		        Scanner scanner2 = new Scanner(new File("Properties.json"));
		        String wholeFile2 = scanner2.useDelimiter("\\A").next();
		                
		        //this line involves some non-trivial sorcery, but you don't really
		        // need to understand it
		        Type collectionType2 = new TypeToken<ArrayList<String>>(){}.getType();
		        
		       ArrayList<String> ints3 = gson.fromJson(wholeFile2, collectionType2);
		        for(String s:ints3)
		        	System.out.println(s);
	
}
}