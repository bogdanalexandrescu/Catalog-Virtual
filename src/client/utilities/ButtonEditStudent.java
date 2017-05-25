package client.utilities;

import client.GUI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Created by teo on 23.05.2017.
 */
public class ButtonEditStudent implements EventHandler<ActionEvent> {

	private GUI gui;
	private String name;

	public ButtonEditStudent(GUI gui, String name) {
		this.gui = gui;
		this.name = name;

	}

	@Override
	public void handle(ActionEvent event) {

		gui.getProcessor().getClient().sendMessage("editStudentInterfeceAdmin");
		gui.getProcessor().getClient().sendMessage(name);
	}
}