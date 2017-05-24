package client.utilities;

import client.GUI;
import database.Database;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.sql.SQLException;

import static java.lang.Integer.parseInt;

/**
 * Created by teo on 11.05.2017.
 */
public class ButtonAddMark implements EventHandler<ActionEvent> {

    private GUI gui;
    private String name;
    private String subject;




    public ButtonAddMark(GUI gui, String name, String subject) {
        this.gui = gui;
        this.name = name;
        this.subject = subject;

    }





    @Override
    public void handle(ActionEvent event) {
        if(!gui.getMark().get(gui.getStudents().indexOf(name) - 1).getText().equals("")){
            gui.getProcessor().getClient().sendMessage("AddMark");
            gui.getProcessor().getClient().sendMessage(name);
            gui.getProcessor().getClient().sendMessage(subject);
            gui.getProcessor().getClient().sendMessage(gui.getTextNameTeacher().getText());
            gui.getProcessor().getClient().sendMessage(Integer.parseInt(gui.getMark().get(gui.getStudents().indexOf(name) - 1).getText()));
            gui.getProcessor().getClient().sendMessage(gui.getData().get(gui.getStudents().indexOf(name) - 1).getText());
        }

    }
}
