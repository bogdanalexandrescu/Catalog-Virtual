package client.utilities;

import client.GUI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Created by teo on 21.05.2017.
 */
public class ButtonSaveImport implements EventHandler<ActionEvent> {

    private GUI gui;
    private String option;



    public ButtonSaveImport(GUI gui,String option) {
        this.gui = gui;
        this.option = option;
    }



    @Override
    public void handle(ActionEvent event) {
        if(option.equals("ImportStudents")) {
            System.out.println(option);
            System.out.println(gui.getPathImport().getText());
        }
    }
}
