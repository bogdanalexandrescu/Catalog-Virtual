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
                    String password = (String) readMessage();
                    String job =  (String) readMessage();
                    if(db.checkAccount(name,password,job) == true){
                        System.out.println("AcceptLogin");
                        sendMessage("AcceptLogin");
                        ArrayList<String> data = new ArrayList<String>();
                        data.add("Teacher");
                        data.add(name);
                        data.add(db.selectMaterieByProfesor(name));
                        data.add("" + db.selectEleviByMaterie(db.selectMaterieByProfesor(name)).size());
                        sendMessage(data);
                        ArrayList<String> students = new ArrayList<String>();
                        students = db.selectEleviByMaterie("Matematica");
                        students.add(0,"Students");
                        sendMessage(students);


                    }
                    else{
                        System.out.println("DeclineLogin");
                        sendMessage("DeclineLogin");
                    }



                }

                if (message.equals("AddMark")) {

                    String name = (String) readMessage();
                    String subject = (String) readMessage();
                    int mark =  (int) readMessage();
                    if(mark >= 1 && mark <= 10){
                        db.insertNotaElev(name,subject,mark);
                        sendMessage("successfully added");
                    }
                    else{

                        sendMessage("unsuccessfully added");
                    }


                }

                if (message.equals("AddAbsence")) {

                    String name = (String) readMessage();
                    String subject = (String) readMessage();
                    db.insertAbsentaElev(name,subject);



                }

                if (message.equals("Logout")) {
                    sendMessage("Logout");


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

    public void sendMessage(Object message){
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




