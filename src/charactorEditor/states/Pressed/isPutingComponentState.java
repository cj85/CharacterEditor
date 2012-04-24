package charactorEditor.states.Pressed;

import java.awt.event.MouseEvent;

import charactorEditor.Model.MainPaneModel;
import charactorEditor.Model.Model;
import charactorEditor.Model.MyComponent;

public class isPutingComponentState extends MousePressedState {
	private static isPutingComponentState instance;

	public static isPutingComponentState Instance(MainPaneModel mainPaneModel,
			Model model, MouseEvent e) {
		if (instance == null)
			instance = new isPutingComponentState(mainPaneModel, model, e);
		instance.e = e;
		return instance;
	}

	private isPutingComponentState(MainPaneModel mainPaneModel, Model model,
			MouseEvent e) {
		super(mainPaneModel, model, e);
	}

	public isPutingComponentState getState() {
		if (isPuttingComponent())
			return instance;
		return null;
	}

	public void action() {
		mainPaneModel.setPutFrame(e.getPoint());
		mainPaneModel.getNearestPoint();
		mainPaneModel.setFoucsComponent(new MyComponent(mainPaneModel
				.getNearest(), model.getWillPut()));
		model.getComponentList().add(mainPaneModel.getFoucsComponent());

		mainPaneModel.getFoucsComponent().setText(
				model.getName(mainPaneModel.getFoucsComponent()));
		model.reSetWillPut();
	}

}