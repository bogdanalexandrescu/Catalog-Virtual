package client.utilities;

import client.GUI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Created by teo on 19.05.2017.
 */
public class ButtonLoadExportPath implements EventHandler<ActionEvent> {

	private GUI gui;
	private FileChooser fc;

	public ButtonLoadExportPath(GUI gui) {
		this.gui = gui;
	}

	@Override
	public void handle(ActionEvent event) {
		fc = new FileChooser();
		fc.setTitle("Select a file to load datas");
		String path;
		path = fc.showOpenDialog(new Stage()).getAbsolutePath().replace("\\", "\\\\");
		gui.setPathExport(path);
	}
}