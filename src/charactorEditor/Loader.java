package charactorEditor;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;

import charactorEditor.drag.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Loader{
	public static ArrayList<MyComponent> load(String file) throws FileNotFoundException{
		Gson gson = new Gson();
		
		 Scanner scanner = new Scanner(new File(file));
		        String wholeFile = scanner.useDelimiter("\\A").next();
		        Type collectionType = new TypeToken<ArrayList<MyComponent>>(){}.getType();
		        ArrayList<MyComponent> ints = gson.fromJson(wholeFile, collectionType);
		       return ints;
	}
}