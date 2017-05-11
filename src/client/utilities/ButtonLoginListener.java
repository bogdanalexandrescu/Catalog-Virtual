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

        gui.getProcessor().getClient().sendMessage("Login");
        gui.getProcessor().getClient().sendMessage(gui.getTxtUserName().getText());
        System.out.println(gui.getTxtUserName().getText());
        gui.getProcessor().getClient().sendMessage(gui.getPf().getText());
        System.out.println(gui.getPf().getText());

        if(gui.getCb().getSelectionModel().getSelectedItem() != null) {
            if (gui.getCb().getSelectionModel().getSelectedItem().equals("Teacher")) {
                gui.getProcessor().getClient().sendMessage("profesor");
                //gui.teacherMode(gui.getBp(),1,"Students");
            } else {
                gui.getProcessor().getClient().sendMessage("director");
                //gui.headmasterMode(gui.getBp());
            }
        }

    }
}
