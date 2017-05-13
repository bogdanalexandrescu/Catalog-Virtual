package client.utilities;

import client.GUI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

/**
 * Created by teo on 12.05.2017.
 */
public class ButtonSituationStudent implements EventHandler<ActionEvent> {

    private GUI gui;
    private String name;
    private String subject;




    public ButtonSituationStudent(GUI gui, String name, String subject) {
        this.gui = gui;
        this.name = name;
        this.subject = subject;

    }





    @Override
    public void handle(ActionEvent event) {
        gui.getProcessor().getClient().sendMessage("StudentSituation");
        gui.getProcessor().getClient().sendMessage(name);
        gui.getProcessor().getClient().sendMessage(subject);

    }
}