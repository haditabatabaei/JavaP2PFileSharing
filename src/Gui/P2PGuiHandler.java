package Gui;

import Account.Account;
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
                if (Manager.clientCore.getAccount().isLoggedIn()) {
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
                    Manager.clientCore.getAccount().setAssignedIP(selfServer.getServerSocket().getInetAddress().getHostAddress());
                } else {
                    Gui.serverTextArea.append(">You Must login (with) your account first.\n");
                }
                break;
            case ConstantVariables.COMMAND_STOP_SERVER:
                selfServer.stopServer();
                Manager.clientCore.getAccount().setAssignedIP("");
                break;
            case ConstantVariables.COMMAND_CLIENT_START:
                if (Manager.clientCore.getAccount().isLoggedIn())
                    clientCore.connect();
                else
                    JOptionPane.showMessageDialog(null, "You must log in first", "Error.Log in required.", JOptionPane.WARNING_MESSAGE);
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
            case ConstantVariables.COMMAND_SHOW_LOGIN_WINDOW:
                Manager.form.getLoginForm().makeVisible();
                break;
            case ConstantVariables.COMMAND_LOGOUT_ACCOUNT:
                logoutAccount();
                break;
            default:
                System.out.println("Wrong command.see available commands by typing help");
        }
    }

    private void logoutAccount() {
        if (Manager.clientCore.getAccount().isLoggedIn()) {
            Manager.form.getLogoutItem().setEnabled(false);
            Manager.form.getLoginItem().setEnabled(true);
            String logoutMessage = "Your Account " + Manager.clientCore.getAccount().getFullName() + "\nLogged Out Successfully";
            JOptionPane.showMessageDialog(null, logoutMessage, "Logout Completed.", JOptionPane.INFORMATION_MESSAGE);
            Manager.clientCore.setAccount(new Account());
        } else
            System.out.println("Already logged out");

    }
}
