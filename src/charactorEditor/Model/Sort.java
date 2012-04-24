package charactorEditor.Model;

public class Sort {
	final static int HEAD = 0;
	final static int BODY = 1;

	public final static String[] NAME = new String[2];
	public final static String[] CMP = new String[2];

	static {
		NAME[0] = "head";
		NAME[1] = "body";

		CMP[0] = "Head";
		CMP[1] = "Body";

	}

}