package charactorEditor.states.Pressed;

import java.awt.event.MouseEvent;

import charactorEditor.Model.MainPaneModel;
import charactorEditor.Model.Model;

public class isRemoveComponentState extends MousePressedState {
	private static isRemoveComponentState instance;

	public static isRemoveComponentState Instance(MainPaneModel mainPaneModel,
			Model model, MouseEvent e) {
		if (instance == null)
			instance = new isRemoveComponentState(mainPaneModel, model, e);
		instance.e = e;
		return instance;
	}

	private isRemoveComponentState(MainPaneModel mainPaneModel, Model model,
			MouseEvent e) {
		super(mainPaneModel, model, e);
	}

	public isRemoveComponentState getState() {
		if (isRemoveComponent())
			return instance;
		return null;
	}

	public void action() {
		if (mainPaneModel.getSelectedComponnet().contains(
				mainPaneModel.getFoucsComponent())) {
			int toRemove = mainPaneModel.getSelectedComponnet().size();
			for (int i = 0; i < toRemove; i++) {
				mainPaneModel.removeCMP(mainPaneModel.getSelectedComponnet()
						.get(0));
			}
		} else {
			mainPaneModel.removeCMP(mainPaneModel.getFoucsComponent());
		}
	}

}