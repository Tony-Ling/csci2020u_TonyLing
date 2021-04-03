package csci2020u.assignment_2;

import java.io.*;
import java.net.*;
import java.util.*;

public class ClientHandler implements Runnable {

    private Socket clientSocket;

    public ClientHandler(Socket client) {
        this.clientSocket = client;
    }

    @Override
    public void run() {
      
    }

    public void downloadFile() {

    }

    public void uploadFile(String fileName) {
        
    }
}