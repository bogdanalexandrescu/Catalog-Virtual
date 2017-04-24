package client;

/**
 * Created by teo on 17.04.2017.
 */
public class MainClient {
    public static void main(String[] args) {

        GUI.launch(GUI.class);
        Client client = new Client ("localhost",9999);
        client.start();

    }
}
