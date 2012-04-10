package test;

import java.io.IOException;
import java.util.ArrayList;

public class Test{
	public static void  main(String[] args) throws IOException{
		T t=new T();
        GsonTest.write(t);
        t=(T)GsonTest.load();
        t.print();
	}
}