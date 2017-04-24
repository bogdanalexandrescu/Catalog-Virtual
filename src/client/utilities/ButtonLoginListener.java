package client.utilities;

import client.GUI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.HBox;

/**
 * Created by teo on 17.04.2017.
 */
public class ButtonLoginListener implements EventHandler<ActionEvent> {

    private GUI gui;

    public ButtonLoginListener(GUI gui) {
        this.gui = gui;
    }

    @Override
    public void handle(ActionEvent event) {

        if(gui.getCb().getSelectionModel().getSelectedItem() != null) {
            if (gui.getCb().getSelectionModel().getSelectedItem().equals("Teacher")) {
                gui.teacherMode(gui.getBp(),1,"Students");
            } else {
                gui.headmasterMode(gui.getBp());
            }
        }

    }
}
