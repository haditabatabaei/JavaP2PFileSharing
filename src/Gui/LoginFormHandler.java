package Gui;

import Account.Account;
import Constant.ConstantVariables;
import Manager.Manager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginFormHandler implements ActionListener {
    private LoginForm loginForm;

    public LoginFormHandler(LoginForm loginForm) {
        this.loginForm = loginForm;
    }

    private void clearAllFields() {
        loginForm.getConfirmPassField().setText("");
        loginForm.getPassField().setText("");
        loginForm.getUsernameOrEmailField().setText("");
        loginForm.revalidate();
    }

    private void cancelLogin() {
        clearAllFields();
        loginForm.makeHidden();
    }

    private void login() {
        System.out.println("Login function");
        boolean canStartLogin = true;
        String inputUsernameOrEmail = loginForm.getUsernameOrEmailField().getText();
        char[] inputPass = loginForm.getPassField().getPassword();
        char[] inputConfirmPass = loginForm.getConfirmPassField().getPassword();
        StringBuilder passBuilder = null;
        if (passwordsAreEqual(inputPass, inputConfirmPass)) {
            passBuilder = new StringBuilder();
            for (char passChar : inputPass)
                passBuilder.append(passChar);
        } else {
            canStartLogin = false;
        }
        if (inputUsernameOrEmail.isEmpty() || inputPass.length == 0 || inputConfirmPass.length == 0)
            canStartLogin = false;

        if (canStartLogin) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection sqlConnection = DriverManager.getConnection("jdbc:mysql://192.168.1.106:3306/p2pusers", "client", "");
                Statement checkStatement = sqlConnection.createStatement();
                String checkQuery = "select * from users where username =\"" + inputUsernameOrEmail + "\"" + " and password =\"" + passBuilder.toString() + "\";";
                System.out.println(checkQuery);
                ResultSet checkResultSet = checkStatement.executeQuery(checkQuery);
                int count = 0;
                while (checkResultSet.next()) {
                    count++;
                }
                if (count == 0) {
                    System.out.println("User does not exist.");
                } else if (count == 1) {
                    System.out.println("User found. logged in");
                    Account userAccount = new Account();
                    checkResultSet.previous();
                    userAccount.setUsername(checkResultSet.getString("username"));
                    userAccount.setPassword(checkResultSet.getString("password"));
                    userAccount.setEmail(checkResultSet.getString("email"));
                    userAccount.setFullName(checkResultSet.getString("fullname"));
                    userAccount.setLoggedIn(true);
                    clearAllFields();
                    cancelLogin();
                    Manager.clientCore.setAccount(userAccount);
                    Manager.clientCore.getAccount().fullPrint();
                    Manager.form.getLoginItem().setEnabled(false);
                    Manager.form.getLogoutItem().setEnabled(true);
                    JOptionPane.showMessageDialog(null, "Hello " + Manager.clientCore.getAccount().getFullName() + "\nYou Logged in.", "Login Completed.", JOptionPane.INFORMATION_MESSAGE);
                } else
                    System.out.println("Duplicate username :)");

                sqlConnection.close();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    private boolean passwordsAreEqual(char[] pass, char[] confirmPass) {
        if (pass.length != confirmPass.length)
            return false;
        else {
            for (int i = 0; i < pass.length; i++) {
                if (pass[i] != confirmPass[i])
                    return false;
            }
        }
        return true;
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
            case ConstantVariables.COMMAND_LOGIN_ACCOUNT:
                //TODO login function
                login();
                break;
            case ConstantVariables.COMMAND_ClEAR_ALL:
                //TODO clear all function
                clearAllFields();
                break;
            case ConstantVariables.COMMAND_EXIT:
                //TODO cancel or exit login form
                cancelLogin();
                break;

        }
    }
}
