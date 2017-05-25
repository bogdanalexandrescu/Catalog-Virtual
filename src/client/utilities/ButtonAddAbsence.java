package client.utilities;

import client.GUI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Created by teo on 11.05.2017.
 */
public class ButtonAddAbsence implements EventHandler<ActionEvent> {

	private GUI gui;
	private String name;
	private String subject;

	public ButtonAddAbsence(GUI gui, String name, String subject) {
		this.gui = gui;
		this.name = name;
		this.subject = subject;

	}

	@Override
	public void handle(ActionEvent event) {
		gui.getProcessor().getClient().sendMessage("AddAbsence");
		gui.getProcessor().getClient().sendMessage(name);
		gui.getProcessor().getClient().sendMessage(subject);
		gui.getProcessor().getClient().sendMessage(gui.getTextNameTeacher().getText());
		gui.getProcessor().getClient().sendMessage(gui.getData().get(gui.getStudents().indexOf(name) - 1).getText());

	}
}