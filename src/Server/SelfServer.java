package Server;

import Constant.ConstantVariables;
import Gui.Gui;
import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SelfServer extends Thread {
    private ArrayList<Socket> clientSockets;
    private ServerSocket serverSocket;
    private ExecutorService executorService;
    private boolean isActive;

    public SelfServer() {
        clientSockets = new ArrayList<>();
        try {
            serverSocket = new ServerSocket(ConstantVariables.SERVERPORT1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        executorService = Executors.newCachedThreadPool();
        isActive = false;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void stopServer() {
        if (isActive) {
            for (Socket clientSocket : clientSockets)
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            System.out.println(">all clients disconnected.");
            Gui.serverTextArea.append(">all clients disconnected.\n");
            isActive = false;
            System.out.println(">listening for clients stopped.\n");
            Gui.serverTextArea.append(">listening for clients stopped.\n");

        } else {
            System.out.println(">Server already stopped.\n");
            Gui.serverTextArea.append(">Server already stopped.\n");
        }
    }

    @Override
    public void run() {
        while (isActive) {
            Gui.serverTextArea.append(">Listening for clients...\n");
            System.out.println("SERVER>Listening for clients...\n");
            try {
                Socket client = serverSocket.accept();
                String message = "New connection info : \n" + "IP : " + client.getInetAddress().getHostAddress() + "\nDo YOU APPROVE this connection?";
                System.out.println("SERVER>connection from : " + client.getInetAddress().getHostAddress() + " want to connect.");
                int resultValue = JOptionPane.showConfirmDialog(null, message, "Connection APPROVE?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                System.out.println("SERVER>showing approve message showed");
                DataOutputStream tempOut = new DataOutputStream(client.getOutputStream());
                System.out.println("SERVER>sender ready.sending result...");
                if (resultValue == 0) {
                    System.out.println("SERVER>connection from : " + client.getInetAddress().getHostAddress() + " accepted.");
                    Gui.serverTextArea.append(">connection from : " + client.getInetAddress().getHostAddress() + " accepted.\n");
                    tempOut.writeInt(0);
                    tempOut.flush();
                    //         tempOut.close();
                    clientSockets.add(client);
                    executorService.execute(new MultiClientHandler(client));
                } else {
                    tempOut.writeInt(1);
                    tempOut.flush();
                    System.out.println("SERVER>connection from : " + client.getInetAddress().getHostAddress() + " force disconnected.");
                    Gui.serverTextArea.append(">connection from : " + client.getInetAddress().getHostAddress() + " force disconnected.\n");
                    client.close();
                }
                System.out.println("SERVER>results handled.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
