package client.utilities;

import client.GUI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.security.Key;
import java.util.ArrayList;

/**
 * Created by teo on 22.05.2017.
 */
public class EnterKeyAddStudentListener implements EventHandler<KeyEvent> {

	private GUI gui;

	public EnterKeyAddStudentListener(GUI gui) {
		this.gui = gui;

	}

	@Override
	public void handle(KeyEvent event) {
		if (event.getCode().equals(KeyCode.ENTER)) {
			if (!gui.getAddName().getText().equals("") && !gui.getAddSubject().getText().equals("")) {
				ArrayList<String> data = new ArrayList<String>();

				for (int i = 0; i < gui.getCbs().size(); i++) {
					if (gui.getCbs().get(i).isSelected()) {
						data.add(gui.getSubjects().get(i + 1));
					}
				}
				if (data.size() != 0) {
					gui.getProcessor().getClient().sendMessage("Add Student");
					gui.getProcessor().getClient()
							.sendMessage(gui.getAddName().getText() + " " + gui.getAddSubject().getText());
					gui.getProcessor().getClient().sendMessage(data);
				} else {
					gui.getProcessor().getClient().sendMessage("Add Student Simple");
					gui.getProcessor().getClient()
							.sendMessage(gui.getAddName().getText() + " " + gui.getAddSubject().getText());
				}

			}
		}

	}
}
