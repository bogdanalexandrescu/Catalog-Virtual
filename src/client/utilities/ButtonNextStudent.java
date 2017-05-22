package client.utilities;

import client.GUI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.ArrayList;

/**
 * Created by teo on 22.05.2017.
 */
public class ButtonNextStudent implements EventHandler<ActionEvent> {

    private GUI gui;


    public ButtonNextStudent(GUI gui) {
        this.gui = gui;

    }

    @Override
    public void handle(ActionEvent event) {
        ArrayList<String> data = new ArrayList<String>();

        for (int i = 0; i < gui.getCbs().size(); i++){
            if(gui.getCbs().get(i).isSelected()){
                data.add(gui.getSubjects().get(i + 1));
            }
        }
        if(data.size() != 0){
            gui.getProcessor().getClient().sendMessage("Add Student");
            gui.getProcessor().getClient().sendMessage(gui.getAddName().getText() + " " +gui.getAddSubject().getText());
            gui.getProcessor().getClient().sendMessage(data);
        }
        else{
            gui.getProcessor().getClient().sendMessage("Add Student Simple");
            gui.getProcessor().getClient().sendMessage(gui.getAddName().getText() + " " +gui.getAddSubject().getText());
        }

    }
}