/**
 * Tony Ling
 * STU #: 100747421
 * 
 * CSCI2020 Assignment 2
 * 
 */

package csci2020u.assignment_2;

import java.io.*;
import java.net.*;

/**
 * This class file is used to run the treads from the Server file and apply
 * the user's download and upload function when they select it.
 */
public class ClientHandler implements Runnable {

    private static Socket clientSocket;

    public ClientHandler(Socket client) {
        this.clientSocket = client;
    }

    @Override
    public void run() {
        BufferedReader br;

        try {
            br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String clientSelection;
            
            while((clientSelection = br.readLine()) != null) {
                if(Client.serverView.getSelectionModel().getSelectedItems() != null
                && Client.selectedDownloadButton == true) {
                    
                    downloadFile();

                } else if(Client.serverView.getSelectionModel().getSelectedItems() != null
                && Client.selectedDownloadButton == false) {

                    Client.serverView.getItems().add(Client.serverView.getSelectionModel().getSelectedItems().toString());
                    uploadFile(Client.serverView.getSelectionModel().getSelectedItems().toString());

                } else {
                    System.err.println("Unable to transfer files.");
                }

                br.close();
                break;
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    // The function downloadFile() reads the file content from the user selected file from
    // the serverview and send the file to the local storage.
    public static void downloadFile() {
        try {
            int bytesRead;
            DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
            String fileName = dis.readUTF();
            InputStream is = new FileInputStream(fileName);
            OutputStream os = clientSocket.getOutputStream();
            long fileSize = dis.readLong();
            byte[] buffer = new byte[1024];

            while((bytesRead = is.read(buffer)) > 0) {
                os.write(buffer, 0, bytesRead);

            }

            os.flush();
            os.close();
            is.close();
            clientSocket.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    // The function uploadFile(String fileName) reads the user selected file from the
    // localview and send the file to the server.
    public static void uploadFile(String fileName) {
        try {
            File file = new File("src/csci2020u/assignment_2/shared/" + fileName);
            byte[] fileSize = new byte[(int) file.length()];

            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            DataInputStream dis = new DataInputStream(bis);
            
            dis.readFully(fileSize, 0, fileSize.length);

            OutputStream os = clientSocket.getOutputStream();

            DataOutputStream dos = new DataOutputStream(os);
            dos.writeUTF(file.getName());
            dos.writeLong(fileSize.length);
            dos.write(fileSize, 0, fileSize.length);
            dos.flush();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}