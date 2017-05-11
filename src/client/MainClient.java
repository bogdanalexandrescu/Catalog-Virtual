package client;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by teo on 17.04.2017.
 */
public class MainClient extends Application{
    public static void main(String[] args) {
        launch(args);


    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Client client = new Client ("localhost",9999);
        client.start();
    }
}
