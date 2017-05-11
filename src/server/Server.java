package server;

import database.Database;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by teo on 17.04.2017.
 */
public class Server {
    private static ServerSocket serverSocket;
    private static Socket clientSocket;
    private ExecutorService threadPool;
    private TeacherThread teacherThread;
    private final int portNumber = 9999;
    private Database db;

    public Server() {

        db = new Database();
        threadPool = Executors.newCachedThreadPool();


        try {
            serverSocket = new ServerSocket(portNumber);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void start() throws IOException {

        System.out.println("Waiting...");

        while (true) {
            try {

                clientSocket = serverSocket.accept();

                teacherThread = new TeacherThread(clientSocket,this,"Teacher",db);
                threadPool.execute(teacherThread);

                System.out.println("A new teacher has connected");


            } catch (IOException e) {
                System.out.println("No more clients can connect!");
            }
        }



    }



}
