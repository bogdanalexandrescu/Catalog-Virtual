package client.utilities;

import client.GUI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Created by teo on 17.04.2017.
 */
public class ButtonLogoutListener implements EventHandler<ActionEvent> {

	private GUI gui;

	public ButtonLogoutListener(GUI gui) {
		this.gui = gui;
	}

	@Override
	public void handle(ActionEvent event) {
		gui.getProcessor().getClient().sendMessage("Logout");

	}

}
