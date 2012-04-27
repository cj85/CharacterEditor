package charactorEditor.Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import SpriteTree.GraphicsTest;
import SpriteTree.LimbNode;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class FrameWorkLoader {
	public static LimbNode load(String file) throws FileNotFoundException {
		Gson gson3 = new Gson();
		Scanner scanner3 = new Scanner(new File(file));
		String wholeFile3 = scanner3.useDelimiter("\\A").next();
		Type collectionType3 = new TypeToken<ArrayList<MyComponent>>() {
		}.getType();
		ArrayList<MyComponent> list = gson3.fromJson(wholeFile3,
				collectionType3);
		System.out.println("turn to ArrayList<MyComponent>");
		HashMap<String, MyComponent> map = new HashMap<String, MyComponent>();
		for (MyComponent m : list) {
			map.put(m.getText(), m);
			m.resetChildren();
		}
		for (MyComponent m : list) {
			m.setParent(map.get(m.getParentForFile()));
			int numberofchilren = m.getChildrenForFile().size();
			for (int i = 0; i < numberofchilren; i++) {
				String s = m.getChildrenForFile().get(i);
				m.addChild(map.get(s));
			}
		}
		for (MyComponent m : list)
			for (int i = 0; i < m.getChildrenForFile().size()
					- m.getChildern().size(); i++)
				m.getChildrenForFile().remove(0);

		MyComponent root = null;
		for (MyComponent m : list)
			if (m.isRoot())
				root = m;
		return buildBodyTree(root, null);

	}

	public static LimbNode buildBodyTree(MyComponent root, LimbNode toReturn) {

		if (root.getParent() == null) {
			toReturn = new LimbNode(root.getText(), GraphicsTest.loadImage(root
					.getImg().toString()), root.getBorderX(), root.getBorderY());
		}

		for (MyComponent m : root.getChildern()) {
			double dx = m.getBorderX() - root.getBorderX();
			double dy = m.getBorderY() - root.getBorderY();
			LimbNode child = new LimbNode(m.getText(), toReturn,
					GraphicsTest.loadImage(m.getImg().toString()), dx, dy, 0);
			toReturn.addChild(child);
		}
		for (int i = 0; i < root.getChildern().size(); i++)
			buildBodyTree(root.getChildern().get(i), toReturn.getChildren()
					.get(i));
		return toReturn;
	}
}