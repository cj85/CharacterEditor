package test;

import java.io.FileNotFoundException;

import charactorEditor.Model.FrameWorkLoader;
import charactorEditor.Model.Loader;

import SpriteTree.LimbNode;

public class FrameWorkLoaderTest{
	static public void main(String[] args) throws FileNotFoundException{
		LimbNode root=FrameWorkLoader.load("C:/Users/Chen/workspace/CharacterEditor/firstfighter.json");
		root.print();
	}
}