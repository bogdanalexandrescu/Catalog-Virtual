package client.utilities;

import client.GUI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Created by teo on 21.04.2017.
 */
public class ButtonEditStudentListener implements EventHandler<ActionEvent> {

	private GUI gui;

	public ButtonEditStudentListener(GUI gui) {
		this.gui = gui;
	}

	@Override
	public void handle(ActionEvent event) {

		gui.getProcessor().getClient().sendMessage("SeeStudents");
		// gui.teacherMode(gui.getBp(),2,"Students");
	}
}