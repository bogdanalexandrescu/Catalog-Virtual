package client.utilities;

import client.GUI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ButtonAddAdmin implements EventHandler<ActionEvent> {

	private GUI gui;
	private String string;

	public ButtonAddAdmin(GUI gui, String string) {
		this.gui = gui;
		this.string = string;
	}

	@Override
	public void handle(ActionEvent event) {
		if (string.equals("addSubject")) {
			gui.getProcessor().getClient().sendMessage("addSubjectAdmin");
			gui.getProcessor().getClient().sendMessage(gui.getTextNameHeadmaster().getText());
			gui.getProcessor().getClient().sendMessage(gui.getAddName().getText());
		} else if (string.equals("addTeacher")) {
			gui.getProcessor().getClient().sendMessage("addTeacherAdmin");
			gui.getProcessor().getClient().sendMessage(gui.getTextNameHeadmaster().getText());
			gui.getProcessor().getClient().sendMessage(gui.getAddName().getText());
			gui.getProcessor().getClient().sendMessage(gui.getAddPass().getText());
			gui.getProcessor().getClient().sendMessage(gui.getAddSubject().getText());
		} else if (string.equals("addStudent")) {
			gui.getProcessor().getClient().sendMessage("addStudentInterfeceAdmin");

		}

	}
}
