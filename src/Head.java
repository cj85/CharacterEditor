public class Head {
	private String name;
	private String property;
    
	public Head(){}
	
	public Head(String n){
		name=n;
	}
	
	public String getName() {
		return name;
	}

	public String getProperty() {
		return property;
	}

	public void setName(String n) {
		name = n;
	}

	public void setProperty(String p) {
		property = p;
	}
}