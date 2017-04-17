package client.utilities;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.HBox;

/**
 * Created by teo on 17.04.2017.
 */
public class ButtonLoginListener implements EventHandler<ActionEvent> {

    private HBox hbox;

    public ButtonLoginListener(HBox hbox) {
        this.hbox = hbox;
    }

    @Override
    public void handle(ActionEvent event) {
        System.out.print("ceva");
        hbox.getChildren().clear();
    }
}
