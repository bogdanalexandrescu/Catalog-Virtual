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
    private String message;
    private Scanner scanner;

    public Client(String IP, int port){

        this.IP = IP;
        this.port = port;
        scanner = new Scanner(System.in);

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

        while( true ){

            try {
                message = (String) input.readObject();
                System.out.println(message);
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

    public void sendMessage(String message) {

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
