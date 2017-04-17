package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by teo on 17.04.2017.
 */
public class TeacherThread implements Runnable {

    private ObjectOutputStream output;
    private ObjectInputStream input;
    private Socket socket;
    private Server server;
    private String name;



    public TeacherThread(Socket socket, Server server,String name)
    {

        this.socket = socket;
        this.server = server;
        this.name = name;

    }

    @Override
    public void run() {
        try {
            input = new ObjectInputStream(socket.getInputStream());
            output = new ObjectOutputStream(socket.getOutputStream());
            output.flush();

            Object message = "";

            sendMessage("Congratulation! It works!");

            while(true) {

                sendMessage("Say something except Finish");
                message = readMessage();


                if (message.equals("Finish")) {
                    sendMessage("Thank you");

                    break;
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }catch(ClassNotFoundException cnfe)
        {
            cnfe.printStackTrace();
        }
        close();
    }

    public Object readMessage() throws IOException, ClassNotFoundException {
        return input.readObject();
    }

    public void sendMessage(String message){
        try {
            output.writeObject(message);
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void close()
    {
        try{
            input.close();
            output.close();
            socket.close();
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
    }

}




