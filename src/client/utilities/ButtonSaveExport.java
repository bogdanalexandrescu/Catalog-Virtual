package client.utilities;

import client.GUI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Created by teo on 19.05.2017.
 */
public class ButtonSaveExport implements EventHandler<ActionEvent> {

    private GUI gui;
    private String option;

    public ButtonSaveExport(GUI gui, String option) {
        this.gui = gui;
        this.option = option;
    }

    @Override
    public void handle(ActionEvent event) {

        if (option.equals("ExportStudents")) {
            System.out.println(option);
            System.out.println(gui.getPathExport().getText());
            gui.getProcessor().getClient().sendMessage(option);
            gui.getProcessor().getClient().sendMessage(gui.getPathExport().getText());
            gui.getPathExport().setText("");
        }
        if (option.equals("ExportSituation")) {
            System.out.println(gui.getStudentExport().getText());
            System.out.println(option);
            System.out.println(gui.getPathExport().getText());
            gui.getProcessor().getClient().sendMessage(option);
            gui.getProcessor().getClient().sendMessage(gui.getStudentExport().getText());
            gui.getProcessor().getClient().sendMessage(gui.getPathExport().getText());
            gui.getStudentExport().setText("");
            gui.getPathExport().setText("");
        }

    }
}
