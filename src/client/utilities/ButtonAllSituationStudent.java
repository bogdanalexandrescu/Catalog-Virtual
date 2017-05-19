package client.utilities;

import client.GUI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Created by teo on 19.05.2017.
 */
public class ButtonAllSituationStudent implements EventHandler<ActionEvent> {

    private GUI gui;
    private String name;





    public ButtonAllSituationStudent(GUI gui, String name) {
        this.gui = gui;
        this.name = name;

    }





    @Override
    public void handle(ActionEvent event) {
        gui.getProcessor().getClient().sendMessage("StudentAllSituation");
        gui.getProcessor().getClient().sendMessage(name);


    }
}