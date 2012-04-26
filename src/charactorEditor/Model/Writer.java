package charactorEditor.Model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Writer {

	public static void write(Object toWrite, String file) throws IOException {
		if (!file.endsWith(".json"))
			file = file + ".json";
		 Gson gson = new Gson();
//		Gson gson = new GsonBuilder().
//				.create();
		String jsonString = gson.toJson(toWrite);
		FileWriter fileOut = new FileWriter(file);
		BufferedWriter out = new BufferedWriter(fileOut);
		out.write(jsonString);
		out.close();
	}
}