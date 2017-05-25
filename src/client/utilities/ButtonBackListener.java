package client.utilities;

import client.GUI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Created by teo on 23.04.2017.
 */
public class ButtonBackListener implements EventHandler<ActionEvent> {

	private GUI gui;

	public ButtonBackListener(GUI gui) {
		this.gui = gui;
	}

	@Override
	public void handle(ActionEvent event) {

		if (gui.getCb().getSelectionModel().getSelectedItem() != null) {

			gui.getProcessor().getClient().sendMessage("Back");

		}

	}
}
