package Gui;

import Constant.ConstantVariables;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class RegisterHandler implements ActionListener {
    private RegisterForm registerForm;

    public RegisterHandler(RegisterForm registerForm) {
        this.registerForm = registerForm;
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
            case ConstantVariables.COMMAND_REGISTER_ACCOUNT:
                registerAccount();
                break;
            case ConstantVariables.COMMAND_ClEAR_ALL:
                clearAllFields();
                break;
            case ConstantVariables.COMMAND_EXIT:
                registerForm.setVisible(false);
                break;
        }
    }

    private void clearAllFields() {
        registerForm.getUsernameField().setText("");
        registerForm.getEmailField().setText("");
        registerForm.getFullnameField().setText("");
        registerForm.getPassField().setText("");
        registerForm.getCondifrmPassField().setText("");
        registerForm.revalidate();
    }

    private boolean theyAreEqual(char[] firstPass, char[] secondPass) {
        if (firstPass.length != secondPass.length)
            return false;
        else {
            for (int i = 0; i < firstPass.length; i++)
                if (firstPass[i] != secondPass[i]) {
                    return false;
                }
        }
        return true;
    }

    private void registerAccount() {
        System.out.println("Register Actived.");
        boolean canRegister = true;
        String inputUsername = registerForm.getUsernameField().getText();
        String inputEmail = registerForm.getEmailField().getText();
        String inputFullname = registerForm.getFullnameField().getText();
        char[] inputPass = registerForm.getPassField().getPassword();
        char[] inputConfirmPass = registerForm.getPassField().getPassword();
        StringBuilder pass = new StringBuilder();
        if (!theyAreEqual(inputPass, inputConfirmPass)) {
            canRegister = false;
            System.out.println("Pass confirmation is wrong.");
        } else {
            for (int i = 0; i < inputPass.length; i++)
                pass.append(inputPass[i]);
        }
        if (inputUsername.isEmpty() || inputEmail.isEmpty() || inputPass.length == 0 || inputConfirmPass.length == 0) {
            canRegister = false;
            System.out.println("Something is empty.");
        }

        String returnMessage;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/p2pusers", "root", "");
//here sonoo is database name, root is username and password
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE id >= 1");
            while (rs.next()) {
                if (rs.getString(2).equals(inputUsername) || rs.getString(4).equals(inputEmail)) {
                    System.out.println("username or email already exists.");
                    canRegister = false;
                    break;
                }
            }

            if (canRegister) {
                Statement stmt2 = con.createStatement();
                System.out.println(inputUsername + " " + pass.toString() + " " + inputEmail + " " + inputFullname);
                boolean rs2 = stmt2.execute("INSERT INTO users (username,password,email,fullname,AccountNumber) VALUES (" + inputUsername + "," + pass.toString() + "," + inputEmail.replace("@","[AT]") + "," + inputFullname + "," + 6 + ")");
                System.out.println("Account created.");
            } else
                System.out.println("Can not register.");

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
