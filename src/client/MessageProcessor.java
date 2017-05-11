package client;

import javafx.application.Platform;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Created by teo on 10.05.2017.
 */
public class MessageProcessor {
    private Client client;
    private GUI gui;
    private MessageProcessor currentProcessor;

    public MessageProcessor(Client client) {
        this.client = client;
        currentProcessor = this;
        gui = new GUI(currentProcessor);
        gui.display();
    }

    public void process(Object messageReceived) {
        /*if (messageReceived instanceof Card)
        {
            //System.out.println((Card) messageReceived);
            sendCard((Card) messageReceived);
        }
        else */
        if (messageReceived instanceof ArrayList<?> && ((ArrayList) messageReceived).get(0) instanceof String)
        {
            System.out.println("merge");
            processArrayListString((ArrayList<String>) messageReceived);
        }
        if (messageReceived instanceof String)
        {
            String message = messageReceived.toString();
            processString(message);
        }

    }
    private void processArrayListString(ArrayList<String> message)
    {
        if(message.get(0).equals("Teacher"))
        {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    // Update UI here.
                    gui.setTextNameTeacher(message.get(1));
                    gui.setTextNameSubject(message.get(2));
                    gui.setTextNumberStudents(message.get(3));
                }
            });
        }
        if(message.get(0).equals("Students"))
        {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    // Update UI here.
                    System.out.println("Afiseaza Students");
                    gui.setStudents(message);
                }
            });
        }

    }

    private void processString(String message)
    {
        if(message.equals("DeclineLogin"))
        {
            gui.setPf("");
            gui.setTxtUserName("");
        }
        if(message.equals("AcceptLogin"))
        {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    // Update UI here.
                    gui.teacherMode(gui.getBp(),1,"Students");
                }
            });

        }
        if(message.equals("unsuccessfully added"))
        {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    // Update UI here.
                    gui.setMessage("Note: unsuccessfully added");
                }
            });

        }
        if(message.equals("successfully added"))
        {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    // Update UI here.
                    gui.setMessage("Note: successfully added");
                }
            });

        }
        if(message.equals("Logout"))
        {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    // Update UI here.
                    gui.loginScreen(gui.getBp());
                    while(!gui.getStages().isEmpty()){
                        Stage stage = gui.getStages().remove(gui.getStages().size() - 1);
                        stage.close();
                    }
                   // Stage stage = (Stage) gui.getMarkStudent().getScene().getWindow();
                    //stage.close();
                }
            });

        }

    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
