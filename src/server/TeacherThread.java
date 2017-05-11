package server;

import database.Database;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by teo on 17.04.2017.
 */
public class TeacherThread implements Runnable {

    private ObjectOutputStream output;
    private ObjectInputStream input;
    private Socket socket;
    private Server server;
    private String name;
    private Database db;



    public TeacherThread(Socket socket, Server server,String name, Database db)
    {

        this.socket = socket;
        this.server = server;
        this.name = name;
        this.db = db;

    }

    @Override
    public void run() {
        try {
            input = new ObjectInputStream(socket.getInputStream());
            output = new ObjectOutputStream(socket.getOutputStream());
            output.flush();

            Object message = "";

            //sendMessage("Congratulation! It works!");

            while(true) {

                //sendMessage("Say something except Finish");
                message = readMessage();

                if (message.equals("Login")) {

                    String name = (String) readMessage();
                    System.out.println(name);
                    String password = (String) readMessage();
                    System.out.println(password);
                    String job =  (String) readMessage();
                    System.out.println(job);
                    if(db.checkAccount(name,password,job) == true){
                        System.out.println("AcceptLogin");
                        sendMessage("AcceptLogin");
                    }
                    else{
                        System.out.println("DeclineLogin");
                        sendMessage("DeclineLogin");
                    }



                }

                if (message.equals("Insert Names")) {
                    ArrayList<String> strings = db.selectNumeElevi();
                    for(int i = 0 ; i < strings.size(); i++){
                        //System.out.println(strings.get(i));
                        sendMessage(strings.get(i));

                    }


                }

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
        } catch (SQLException e) {
            e.printStackTrace();
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




