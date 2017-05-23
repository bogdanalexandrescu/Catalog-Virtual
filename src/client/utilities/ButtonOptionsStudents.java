package client.utilities;

import client.GUI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.ArrayList;

/**
 * Created by teo on 23.05.2017.
 */
public class ButtonOptionsStudents implements EventHandler<ActionEvent> {

    private GUI gui;
    String name;
    String option;


    public ButtonOptionsStudents(GUI gui,String name,String option) {

        this.gui = gui;
        this.name = name;
        this.option = option;
    }

    @Override
    public void handle(ActionEvent event) {


           if(option.equals("addSubject")){
               gui.getProcessor().getClient().sendMessage("AddStudentSubject");
               gui.getProcessor().getClient().sendMessage(name);
               gui.getProcessor().getClient().sendMessage(gui.getAddSubject().getText());

           }
        if(option.equals("deleteSubject")){
            gui.getProcessor().getClient().sendMessage("DeleteStudentSubject");
            gui.getProcessor().getClient().sendMessage(name);
            gui.getProcessor().getClient().sendMessage(gui.getAddSubject().getText());

        }
        if(option.equals("deleteMark")){
            gui.getProcessor().getClient().sendMessage("DeleteStudentMark");
            gui.getProcessor().getClient().sendMessage(name);
            gui.getProcessor().getClient().sendMessage(gui.getAddSubject().getText());
            gui.getProcessor().getClient().sendMessage(gui.getDeleteMarkAbsence().getText());


        }
        if(option.equals("deleteAbsence")){
            gui.getProcessor().getClient().sendMessage("DeleteStudentAbsence");
            gui.getProcessor().getClient().sendMessage(name);
            gui.getProcessor().getClient().sendMessage(gui.getAddSubject().getText());
            gui.getProcessor().getClient().sendMessage(gui.getDeleteMarkAbsence().getText());

        }
        if(option.equals("FinishEditStudent")){
            gui.getProcessor().getClient().sendMessage("FinishEditStudent");
            gui.getProcessor().getClient().sendMessage(gui.getAddName().getText());
            gui.getProcessor().getClient().sendMessage(gui.getNameStudent().getText());

        }
    }
}