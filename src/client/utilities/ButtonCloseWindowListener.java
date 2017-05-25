package client.utilities;

import client.GUI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

/**
 * Created by teo on 23.05.2017.
 */
public class ButtonCloseWindowListener implements EventHandler<WindowEvent> {

	private GUI gui;

	public ButtonCloseWindowListener(GUI gui) {
		this.gui = gui;
	}

	@Override
	public void handle(WindowEvent event) {
		gui.getProcessor().getClient().sendMessage("Close App");
		System.exit(0);
	}
}