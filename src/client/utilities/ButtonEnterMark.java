package client.utilities;

import client.GUI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Created by teo on 11.05.2017.
 */
public class ButtonEnterMark implements EventHandler<ActionEvent> {

    private GUI gui;
    private String name;
    private String subject;



    public ButtonEnterMark(GUI gui, String name, String subject) {
        this.gui = gui;
        this.name = name;
        this.subject = subject;

    }

    @Override
    public void handle(ActionEvent event) {
        if(!gui.getMessage().equals("Note: successfully added")){
            gui.getProcessor().getClient().sendMessage("AddMark");
            gui.getProcessor().getClient().sendMessage(name);
            gui.getProcessor().getClient().sendMessage(subject);
            gui.getProcessor().getClient().sendMessage(Integer.parseInt(gui.getMarkStudent().getText()));
        }



    }
}