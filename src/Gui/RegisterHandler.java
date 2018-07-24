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
            Connection sqlConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/p2pusers", "root", "");
            Statement checkStatement = sqlConnection.createStatement();
            ResultSet checkResultSet = checkStatement.executeQuery("SELECT * FROM users WHERE id >= 1");
            while (checkResultSet.next()) {
                if (checkResultSet.getString(2).equals(inputUsername) || checkResultSet.getString(4).equals(inputEmail)) {
                    System.out.println("username or email already exists.");
                    canRegister = false;
                    JOptionPane.showMessageDialog(null, "Username and(or) email address already registered.\nTry another.", "Register Error", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
            }

            if (canRegister) {
                Statement insertStatement = sqlConnection.createStatement();
                String insertQuery = "insert into users (username,password,email,fullname) values (" + '"' + inputUsername + "\",\"" + pass.toString() + "\",\"" + inputEmail + "\",\"" + inputFullname + '"' + ");";
                System.out.println(insertQuery);
                int insertResponse = insertStatement.executeUpdate(insertQuery);
                System.out.println("Account created.");
                String messageToShow = "Account with details below created successfully :\nUsername : " + inputUsername + "\nEmail : " + inputEmail + "\nPassword : " + "Given Pass\nFull Name : " + inputFullname;
                JOptionPane.showMessageDialog(null, messageToShow, "Registration Completed Successfully.", JOptionPane.INFORMATION_MESSAGE);
                clearAllFields();
                Manager.Manager.form.getRegisterForm().setVisible(false);
            } else
                System.out.println("Can not register.");

            sqlConnection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
