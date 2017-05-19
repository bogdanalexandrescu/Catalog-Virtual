package client.utilities;

import client.GUI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Created by teo on 15.05.2017.
 */
public class ButtonDeleteListener implements EventHandler<ActionEvent> {

    private GUI gui;
    private String name;
    private String subject;



    public ButtonDeleteListener(GUI gui, String name, String title) {
        this.gui = gui;
        this.name = name;
        this.subject = title;

    }

    @Override
    public void handle(ActionEvent event) {
        gui.getProcessor().getClient().sendMessage("Delete");
        gui.getProcessor().getClient().sendMessage(gui.getTextNameHeadmaster().getText());
        gui.getProcessor().getClient().sendMessage(name);
        gui.getProcessor().getClient().sendMessage(subject);


    }
}