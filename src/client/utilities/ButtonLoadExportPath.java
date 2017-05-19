package client.utilities;

import client.GUI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Created by teo on 19.05.2017.
 */
public class ButtonLoadExportPath implements EventHandler<ActionEvent> {

    private GUI gui;



    public ButtonLoadExportPath(GUI gui) {
        this.gui = gui;

    }



    @Override
    public void handle(ActionEvent event) {
        gui.setPathExport("C/Teo");

    }
}