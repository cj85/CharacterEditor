package charactorEditor;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import charactorEditor.Model.MainPaneModel;

public class MouseDraggedState{
	private ArrayList<MouseDraggedState> list=new ArrayList<MouseDraggedState>();
	MainPaneModel jc;
	MouseEvent e;
	public MouseDraggedState(MainPaneModel jc,MouseEvent e){
		this.jc=jc;
		this.e=e;
	}
	public void creat(){
		list.add(new isDraggingState(jc,e));
		list.add(new isDragingSizeState(jc,e));
		list.add(new isDragingRectangleState(jc,e));
	}
	public MouseDraggedState getState(){
		for(MouseDraggedState state:list){
			if(state.getState()!=null)
				return state.getState();
		}
		return null;
	}
	public void action(){};
	
}