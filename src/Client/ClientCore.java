package Client;

import Account.Account;
import Constant.ConstantVariables;
import Gui.Gui;
import Manager.Manager;

import javax.swing.*;
import java.io.*;
import java.net.Socket;

public class ClientCore {
    private Socket myConnection;
    public static JTextArea clientTextArea;
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;
    private File clientFile;
    private Account account;

    public ClientCore() {
        clientTextArea = new JTextArea();
        clientFile = new File("clientTest.rar");
        account = new Account();
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public File getClientFile() {
        return clientFile;
    }

    public void setClientFile(File clientFile) {
        this.clientFile = clientFile;
        System.out.println("CLIENT>file set to " + clientFile.getAbsolutePath());
    }

    public void disconnect() {
        try {
            myConnection.close();
            System.out.println("Client Connection closed.");
            Gui.serverStatusLabel.setText("Status : Disconnected.");
        } catch (IOException e) {
            //clientTextArea.append("No connection to close\n");
            System.out.println("No connection to close\n");
        }

    }

    public void sendFile() {
        System.out.println("CLIENT>Sending file to server");
        try {
            dataOutputStream.writeUTF(clientFile.getName());
            dataOutputStream.flush();
            dataOutputStream.writeLong(clientFile.length());
            dataOutputStream.flush();

            BufferedInputStream bfi = new BufferedInputStream(new FileInputStream(clientFile));
            byte[] buffer = new byte[ConstantVariables.BUFFER_SIZE];
            long fullSize = clientFile.length();
            long sentSize = 0;
            long currentReadBytes = 0;
            long startTime = System.currentTimeMillis();
            int prevPercent = 0;
            while ((currentReadBytes = bfi.read(buffer)) > 0) {
                dataOutputStream.write(buffer);
                sentSize += currentReadBytes;
                int newPercent = calculatePercent(sentSize, fullSize);
                if (newPercent != prevPercent) {
                    prevPercent = newPercent;
                    Manager.form.getProgressBar1().setValue(newPercent);
                }
                System.out.println(calculatePercent(sentSize, fullSize));
                //System.out.println(clientFile.length() + " " + (clientFile.length() - 4096));
                dataOutputStream.flush();
            }
            bfi.close();
            //dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int calculatePercent(long smallAmount, long fullAmount) {
        int percent;
        percent = (int) (((float) smallAmount / (float) fullAmount) * 100);
        return percent;
    }

    public void receiveMessage() {
        System.out.println("CLIENT>Receiving from server");
        try {
            String messageFromServer = dataInputStream.readUTF();
            System.out.println("SERVER TO CLIENT>" + messageFromServer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void connect() {
        try {
            //  myConnection = new Socket(ConstantVariables.TESTHOSTIP, ConstantVariables.TESTPORT);
            myConnection = new Socket(ConstantVariables.TESTHOSTIP, 8076);
            //clientTextArea.append("CLIENT>This client connected to server\n");
            System.out.println("CLIENT>This client connected to server");
            System.out.println("CLIENT>Client side streamers ready.");
            //   Gui.serverStatusLabel.setText("Status : Pending Server...");

            DataInputStream tempIn = new DataInputStream(myConnection.getInputStream());
            switch (tempIn.readInt()) {
                case 0:
                    Gui.serverStatusLabel.setText("Status : Connected");
                    //tempIn.close();
                    dataInputStream = new DataInputStream(myConnection.getInputStream());
                    dataOutputStream = new DataOutputStream(myConnection.getOutputStream());
                    break;
                case 1:
                    Gui.serverStatusLabel.setText("Status : Disconnected");
                    tempIn.close();
                    myConnection.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
