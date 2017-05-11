package client;

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
        else if (messageReceived instanceof Integer)
        {
            //System.out.println((Integer) messageReceived);
            sendTotal(messageReceived.toString());
        }
        */if (messageReceived instanceof String)
        {
            String message = messageReceived.toString();
            processString(message);
        }

    }

    private void processString(String message)
    {
        if(message.contains("DeclineLogin"))
        {
            gui.setPf("");
            gui.setTxtUserName("");
        }
        if(message.contains("AcceptLogin"))
        {
            //gui.teacherMode(gui.getBp(),1,"Students");
        }

    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
