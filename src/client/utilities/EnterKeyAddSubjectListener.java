package client.utilities;

import client.GUI;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

/**
 * Created by teo on 22.05.2017.
 */
public class EnterKeyAddSubjectListener implements EventHandler<KeyEvent> {

	private GUI gui;
	private int i;

	public EnterKeyAddSubjectListener(GUI gui, int i) {
		this.gui = gui;
		this.i = i;

	}

	@Override
	public void handle(KeyEvent event) {
		if (event.getCode().equals(KeyCode.ENTER)) {
			if (gui.getCbs().get(i).isSelected()) {
				gui.getCbs().get(i).setSelected(false);
			} else {
				gui.getCbs().get(i).setSelected(true);
			}

		}

	}
}
