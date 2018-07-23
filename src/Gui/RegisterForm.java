/*
 * Created by JFormDesigner on Tue Jul 24 01:58:10 IRDT 2018
 */

package Gui;

import Constant.ConstantVariables;

import java.awt.*;
import javax.swing.*;

/**
 * @author Hadi
 */
public class RegisterForm extends JFrame {
    public RegisterForm() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        label2 = new JLabel();
        usernameField = new JTextField();
        label3 = new JLabel();
        emailField = new JTextField();
        label4 = new JLabel();
        fullnameField = new JTextField();
        label5 = new JLabel();
        passField = new JPasswordField();
        label6 = new JLabel();
        condifrmPassField = new JPasswordField();
        registerButton = new JButton();
        cancelButton = new JButton();
        clearButton = new JButton();

        handler = new RegisterHandler(this);

        //======== this ========
        setResizable(false);
        setTitle("Register Account");
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("Enter Information Below:");
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(10, 10), label1.getPreferredSize()));

        //---- label2 ----
        label2.setText("Username * : ");
        contentPane.add(label2);
        label2.setBounds(10, 55, 85, 25);
        contentPane.add(usernameField);
        usernameField.setBounds(105, 55, 280, 25);

        //---- label3 ----
        label3.setText("Email *  : ");
        contentPane.add(label3);
        label3.setBounds(10, 95, 75, 25);
        contentPane.add(emailField);
        emailField.setBounds(105, 95, 280, 25);

        //---- label4 ----
        label4.setText("Full Name : ");
        contentPane.add(label4);
        label4.setBounds(10, 140, 75, 25);
        contentPane.add(fullnameField);
        fullnameField.setBounds(105, 140, 280, 25);

        //---- label5 ----
        label5.setText("Password * : ");
        contentPane.add(label5);
        label5.setBounds(10, 185, 75, 25);
        contentPane.add(passField);
        passField.setBounds(105, 185, 280, 25);

        //---- label6 ----
        label6.setText("Confirm Pass* : ");
        contentPane.add(label6);
        label6.setBounds(10, 230, 100, 25);
        contentPane.add(condifrmPassField);
        condifrmPassField.setBounds(105, 230, 280, 25);

        //---- registerButton ----
        registerButton.setText("Register");
        contentPane.add(registerButton);
        registerButton.setBounds(290, 275, 96, 55);

        //---- cancelButton ----
        cancelButton.setText("Cancel");
        contentPane.add(cancelButton);
        cancelButton.setBounds(190, 275, 96, 26);

        //---- clearButton ----
        clearButton.setText("Clear All");
        contentPane.add(clearButton);
        clearButton.setBounds(190, 305, 96, 26);

        contentPane.setPreferredSize(new Dimension(395, 375));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

        registerButton.setActionCommand(ConstantVariables.COMMAND_REGISTER_ACCOUNT);
        cancelButton.setActionCommand(ConstantVariables.COMMAND_EXIT);
        clearButton.setActionCommand(ConstantVariables.COMMAND_ClEAR_ALL);

        registerButton.addActionListener(handler);
        cancelButton.addActionListener(handler);
        clearButton.addActionListener(handler);


    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public JButton getClearButton() {
        return clearButton;
    }

    public JButton getRegisterButton() {
        return registerButton;
    }

    public JPasswordField getCondifrmPassField() {
        return condifrmPassField;
    }

    public JPasswordField getPassField() {
        return passField;
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public JTextField getFullnameField() {
        return fullnameField;
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JLabel label2;
    private JTextField usernameField;
    private JLabel label3;
    private JTextField emailField;
    private JLabel label4;
    private JTextField fullnameField;
    private JLabel label5;
    private JPasswordField passField;
    private JLabel label6;
    private JPasswordField condifrmPassField;
    private JButton registerButton;
    private JButton cancelButton;
    private JButton clearButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    private RegisterHandler handler;
}
