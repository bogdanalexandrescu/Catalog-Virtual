package client.utilities;

import client.GUI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Created by teo on 21.05.2017.
 */
public class ButtonLoadImportPath implements EventHandler<ActionEvent> {

    private GUI gui;



    public ButtonLoadImportPath(GUI gui) {
        this.gui = gui;

    }



    @Override
    public void handle(ActionEvent event) {
        gui.setPathImport("C/Teo/Import");

    }
}