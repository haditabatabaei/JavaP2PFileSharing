package Server;

import Constant.ConstantVariables;
import Gui.Gui;

import java.io.*;
import java.net.Socket;

public class MultiClientHandler implements Runnable {
    private Socket client;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private File serverFile;

    public MultiClientHandler(Socket client) {
        this.client = client;
    }

    public Socket getClient() {
        return client;
    }

    @Override
    public void run() {
        getStreamers();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        receiveMessageFromClient();
        //sendMessageToMyClient();
    }

    private void receiveMessageFromClient() {
        System.out.println("SERVER>Receiving from client");
        Gui.serverTextArea.append(">Receiving from client\n");
        try {
            String fileName = dataInputStream.readUTF();
            long size = dataInputStream.readLong();
            float sizeInKB = size / 1024;
            float sizeInMB = sizeInKB / 1024;
            String toShow = "SERVER>" + fileName + " SIZE : ";
            if (sizeInKB < 1) {
                toShow += size + " Bytes";
            } else if (sizeInKB >= 1 && sizeInKB < 1024) {
                toShow += sizeInKB + " KB";
            } else {
                toShow += sizeInMB + " MB";
            }
            System.out.println(toShow);
            Gui.serverTextArea.append(">" + toShow);
            serverFile = new File("Server" + fileName);
            if (serverFile.createNewFile())
                System.out.println("serverFile created.");
            else
                System.out.println("server file existed.");

            BufferedOutputStream bfo = new BufferedOutputStream(new FileOutputStream(serverFile));
            byte[] buffer = new byte[ConstantVariables.BUFFER_SIZE];
            long read;
            long remaining = size;
            long untillNow = 0;
            int prevPercent = 0;
            System.out.println("Reading from client...");
            while ((read = dataInputStream.read(buffer, 0, (int) Math.min(buffer.length, remaining))) > 0) {
                untillNow += read;
                int newPercent = calculatePercent(untillNow, size);
                if (newPercent != prevPercent) {
                    prevPercent = newPercent;
                    Gui.serverTextArea.append(">" + toShow + " - " + newPercent + "%\n");
                }
                remaining -= read;
                bfo.write(buffer, 0, (int) read);
            }
            bfo.close();
            System.out.println("saved file is ready.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private int calculatePercent(long smallAmount, long fullAmount) {
        int percent;
        percent = (int) (((float) smallAmount / (float) fullAmount) * 100);
        return percent;
    }

    private void sendMessageToMyClient() {
        System.out.println("Sending to client");
        try {
            dataOutputStream.writeUTF("File Received.Thank You");
            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getStreamers() {
        try {
            dataInputStream = new DataInputStream(client.getInputStream());
            dataOutputStream = new DataOutputStream(client.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Server side streamers ready.");
    }
}
