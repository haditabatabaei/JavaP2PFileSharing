/*
 * Created by JFormDesigner on Wed Jul 25 22:11:17 IRDT 2018
 */

package Gui;

import Constant.ConstantVariables;

import java.awt.*;
import javax.swing.*;

/**
 * @author unknown
 */
public class LoginForm extends JFrame {
    public LoginForm() {
        initComponents();
    }

    private void custom() {
        handler = new LoginFormHandler(this);
    }

    private void initComponents() {
        custom();
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        label2 = new JLabel();
        usernameOrEmailField = new JTextField();
        label3 = new JLabel();
        label4 = new JLabel();
        clearAllButton = new JButton();
        cancelButton = new JButton();
        loginButton = new JButton();
        passField = new JPasswordField();
        confirmPassField = new JPasswordField();

        //======== this ========
        setResizable(false);
        setTitle("Login Account");
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("Account Information :");
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(15, 15), label1.getPreferredSize()));

        //---- label2 ----
        label2.setText("Password : ");
        label2.setFont(new Font("Arial", Font.PLAIN, 12));
        contentPane.add(label2);
        label2.setBounds(60, 85, label2.getPreferredSize().width, 25);
        contentPane.add(usernameOrEmailField);
        usernameOrEmailField.setBounds(150, 40, 270, 25);

        //---- label3 ----
        label3.setText("Username : ");
        label3.setFont(new Font("Arial", Font.PLAIN, 12));
        contentPane.add(label3);
        label3.setBounds(60, 45, 116, 25);

        //---- label4 ----
        label4.setText("Confirm Pass :");
        label4.setFont(new Font("Arial", Font.PLAIN, 12));
        contentPane.add(label4);
        label4.setBounds(60, 125, 115, 25);

        //---- clearAllButton ----
        clearAllButton.setText("Clear All");
        clearAllButton.setFont(new Font("Arial", Font.PLAIN, 12));
        contentPane.add(clearAllButton);
        clearAllButton.setBounds(215, 160, 90, clearAllButton.getPreferredSize().height);

        //---- cancelButton ----
        cancelButton.setText("Cancel");
        cancelButton.setFont(new Font("Arial", Font.PLAIN, 12));
        contentPane.add(cancelButton);
        cancelButton.setBounds(215, 190, 90, 25);

        //---- loginButton ----
        loginButton.setText("Login");
        contentPane.add(loginButton);
        loginButton.setBounds(310, 160, 110, 55);
        contentPane.add(passField);
        passField.setBounds(150, 85, 270, 25);
        contentPane.add(confirmPassField);
        confirmPassField.setBounds(150, 125, 270, 25);

        contentPane.setPreferredSize(new Dimension(430, 255));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

        loginButton.setActionCommand(ConstantVariables.COMMAND_LOGIN_ACCOUNT);
        loginButton.addActionListener(handler);

        cancelButton.setActionCommand(ConstantVariables.COMMAND_EXIT);
        cancelButton.addActionListener(handler);

        clearAllButton.setActionCommand(ConstantVariables.COMMAND_ClEAR_ALL);
        clearAllButton.addActionListener(handler);


    }

    public void makeVisible() {
        setVisible(true);
    }

    public void makeHidden() {
        setVisible(false);
    }


    public JPasswordField getPassField() {
        return passField;
    }

    public JTextField getUsernameOrEmailField() {
        return usernameOrEmailField;
    }

    public JPasswordField getConfirmPassField() {
        return confirmPassField;
    }


    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JLabel label2;
    private JTextField usernameOrEmailField;
    private JLabel label3;
    private JLabel label4;
    private JButton clearAllButton;
    private JButton cancelButton;
    private JButton loginButton;
    private JPasswordField passField;
    private JPasswordField confirmPassField;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    private LoginFormHandler handler;
}
