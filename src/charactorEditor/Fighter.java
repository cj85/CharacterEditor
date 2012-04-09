package charactorEditor;
import java.util.ArrayList;

public class Fighter{
	public static ArrayList<String> item=new ArrayList<String>();
	static{
		item.add("name");
		item.add("health");
		item.add("speed");
		item.add("x");
		item.add("y");
		item.add("img");
	}
	private String name;
    private String health;
    private String speed;
    private String x;
    private String y;
    private String img;
	public Fighter(){
		
	}
	public String get(String item){
		if(item.equals("name"))return name;
		if(item.equals("health"))return health;
		if(item.equals("speed"))return speed;
		if(item.equals("x"))return x;
		if(item.equals("y"))return y;
		if(item.equals("img"))return img;
		return null;
	}
	public void set(String item, String value){
		if(item.equals("name")) name=value;
		if(item.equals("health")) health=value;
		if(item.equals("speed")) speed=value;
		if(item.equals("x")) x=value;
		if(item.equals("y")) y=value;
		if(item.equals("img")) img=value;
	}
}