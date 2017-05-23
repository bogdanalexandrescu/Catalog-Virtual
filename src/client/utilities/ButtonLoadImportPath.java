package client.utilities;

import client.GUI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Created by teo on 21.05.2017.
 */
public class ButtonLoadImportPath implements EventHandler<ActionEvent> {

    private GUI gui;
    private FileChooser fc;


    public ButtonLoadImportPath(GUI gui) {
        this.gui = gui;

    }



    @Override
    public void handle(ActionEvent event) {
        fc = new FileChooser();
        fc.setTitle("Select a file to load datas");
        String path;
        path = fc.showOpenDialog(new Stage()).getAbsolutePath().replace("\\","\\\\");
        System.out.println(path);
        gui.setPathImport(path);
    }
}