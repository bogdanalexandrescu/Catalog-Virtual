package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Created by teo on 17.04.2017.
 */
public class Client {

    private String IP;
    private int port;
    private Socket socket;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private Object message;
    private Scanner scanner;
    private MessageProcessor processor;

    public Client(String IP, int port){

        this.IP = IP;
        this.port = port;
        scanner = new Scanner(System.in);
        processor = new MessageProcessor(this);


    }

    public void connectToServer() {

        try {
            socket = new Socket(IP, port);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setUpStreams() {
        try {
            output = new ObjectOutputStream(socket.getOutputStream());
            output.flush();
            input = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void start() {


        connectToServer();
        setUpStreams();

        System.out.println("You are connected!!");

        final Thread thread = new Thread(new Runnable()
        {
            @Override
            public void run() {
                while( true ){

                    try {


                        message =  input.readObject();
                        System.out.println(message);
                        processor.process(message);
                        if (message.equals("Thank you"))
                            break;
                        if (message.equals("Say something except Finish")) {
                            String line = "";
                            line = scanner.nextLine();
                            sendMessage(line);
                        }

                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                close();
            }
        });
        thread.start();


    }

    public void sendMessage(Object message) {

        try {
            output.writeObject(message);
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {

        try
        {   scanner.close();
            output.close();
            input.close();
            socket.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

}
