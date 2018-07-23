package Gui;

import Client.ClientCore;
import Constant.ConstantVariables;
import Manager.Manager;
import Server.SelfServer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class P2PGuiHandler implements ActionListener {
    private static Gui gui;
    private static SelfServer selfServer;
    private static ClientCore clientCore;
    private int firstTimeLaunch;

    public P2PGuiHandler(Gui gui, SelfServer selfServer, ClientCore clientCore) {
        P2PGuiHandler.gui = gui;
        P2PGuiHandler.selfServer = selfServer;
        P2PGuiHandler.clientCore = clientCore;
        firstTimeLaunch = 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command;
        if (e.getSource() instanceof JButton)
            command = ((JButton) e.getSource()).getActionCommand();
        else if (e.getSource() instanceof JMenuItem)
            command = ((JMenuItem) e.getSource()).getActionCommand();
        else
            command = "none";

        switch (command) {
            case ConstantVariables.COMMAND_EXIT:
                System.exit(0);
                break;
            case ConstantVariables.COMMAND_START_SERVER:
                if (selfServer.isActive()) {
                    System.out.println(">server already started.");
                    Gui.serverTextArea.append(">server already started.\n");
                } else if (firstTimeLaunch == 0) {
                    selfServer.setActive(true);
                    selfServer.start();
                    firstTimeLaunch++;
                    System.out.println("Server started.");
                    Gui.serverTextArea.append(">server started.\n");
                } else {
                    selfServer.setActive(true);
                    System.out.println("Server resumed.");
                    Gui.serverTextArea.append(">server resumed.\n");
                }
                break;
            case ConstantVariables.COMMAND_STOP_SERVER:
                selfServer.stopServer();
                break;
            case ConstantVariables.COMMAND_CLIENT_START:
                clientCore.connect();
                break;
            case ConstantVariables.COMMAND_CLIENT_STOP:
                clientCore.disconnect();
                break;
            case ConstantVariables.COMMAND_HELP:
                System.out.println(ConstantVariables.COMMAND_HELP_Message);
                break;
            case ConstantVariables.COMMAND_BROWSE:
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Select File:");
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fileChooser.setMultiSelectionEnabled(false);
                int resultValue = fileChooser.showOpenDialog(null);
                if (resultValue == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    clientCore.setClientFile(file);
                    Manager.form.getFileSavePathTextField().setText(file.getAbsolutePath());
                }
                break;
            case ConstantVariables.COMMAND_SEND_FILE:
                clientCore.sendFile();
            case ConstantVariables.COMMAND_SHOW_REGISTER_WINDOW:
                Manager.form.getRegisterForm().setVisible(true);
                break;
            default:
                System.out.println("Wrong command.see available commands by typing help");
        }
    }
}
