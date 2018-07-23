package Gui;

import Constant.ConstantVariables;
import Manager.Manager;

import java.awt.*;
import javax.swing.*;
/*
 * Created by JFormDesigner on Sun Jul 22 08:07:43 IRDT 2018
 */


/**
 * @author Hadi
 */
public class Gui extends JFrame {
    public Gui() {
        initComponents();
    }

    private void createUIComponents() {
        // TODO: add custom component creation code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        menuBar1 = new JMenuBar();
        fileMenu = new JMenu();
        exitItem = new JMenuItem();
        accountMenu = new JMenu();
        registerItem = new JMenuItem();
        loginItem = new JMenuItem();
        logoutItem = new JMenuItem();
        helpMenu = new JMenu();
        howtoItem = new JMenuItem();
        aboutItem = new JMenuItem();
        panel1 = new JPanel();
        label1 = new JLabel();
        scrollPane2 = new JScrollPane();
        list1 = new JList();
        panel2 = new JPanel();
        label2 = new JLabel();
        startListenBtn = new JButton();
        stopListenBtn = new JButton();
        scrollPane1 = new JScrollPane();
        serverTextArea = new JTextArea();
        panel3 = new JPanel();
        label3 = new JLabel();
        clientConnectBtn = new JButton();
        clientDisconnectBtn = new JButton();
        label4 = new JLabel();
        serverNameLabel = new JLabel();
        serverStatusLabel = new JLabel();
        label7 = new JLabel();
        fileSavePathTextField = new JTextField();
        browseButton = new JButton();
        label8 = new JLabel();
        progressBar1 = new JProgressBar();
        sendFileBtn = new JButton();
        cancelFileBtn = new JButton();

        //======== this ========
        setResizable(false);
        setTitle("P2P Simple File Sharing");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon("C:\\Users\\Hadi\\Desktop\\Capture.PNG").getImage());
        setShape(null);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        registerForm = new RegisterForm();
        //======== menuBar1 ========
        {

            //======== fileMenu ========
            {
                fileMenu.setText("File");

                //---- exitItem ----
                exitItem.setText("Exit");
                fileMenu.add(exitItem);
            }
            menuBar1.add(fileMenu);

            //======== accountMenu ========
            {
                accountMenu.setText("Account");

                //---- registerItem ----
                registerItem.setText("Register");
                accountMenu.add(registerItem);

                //---- loginItem ----
                loginItem.setText("Login");
                accountMenu.add(loginItem);

                //---- logoutItem ----
                logoutItem.setText("Logout");
                accountMenu.add(logoutItem);
            }
            menuBar1.add(accountMenu);

            //======== helpMenu ========
            {
                helpMenu.setText("Help");

                //---- howtoItem ----
                howtoItem.setText("How to");
                helpMenu.add(howtoItem);

                //---- aboutItem ----
                aboutItem.setText("About Developer");
                helpMenu.add(aboutItem);
            }
            menuBar1.add(helpMenu);
        }
        setJMenuBar(menuBar1);

        //======== panel1 ========
        {
            panel1.setBackground(new Color(218, 218, 218));
            panel1.setLayout(null);

            //---- label1 ----
            label1.setText("Onlines :");
            panel1.add(label1);
            label1.setBounds(new Rectangle(new Point(5, 5), label1.getPreferredSize()));

            //======== scrollPane2 ========
            {

                //---- list1 ----
                list1.setBackground(Color.gray);
                list1.setForeground(Color.white);
                list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                list1.setVisibleRowCount(5);
                scrollPane2.setViewportView(list1);
            }
            panel1.add(scrollPane2);
            scrollPane2.setBounds(5, 35, 750, 95);
        }
        contentPane.add(panel1);
        panel1.setBounds(5, 5, 765, 145);

        //======== panel2 ========
        {
            panel2.setBackground(new Color(131, 196, 203));
            panel2.setLayout(null);

            //---- label2 ----
            label2.setText("Listening for Client Side :");
            panel2.add(label2);
            label2.setBounds(new Rectangle(new Point(5, 5), label2.getPreferredSize()));

            //---- startListenBtn ----
            startListenBtn.setText("Start Listening");
            panel2.add(startListenBtn);
            startListenBtn.setBounds(5, 275, 135, 25);

            //---- stopListenBtn ----
            stopListenBtn.setText("Stop Listening");
            panel2.add(stopListenBtn);
            stopListenBtn.setBounds(150, 275, 135, 23);

            //======== scrollPane1 ========
            {

                //---- serverTextArea ----
                serverTextArea.setEditable(false);
                scrollPane1.setViewportView(serverTextArea);
            }
            panel2.add(scrollPane1);
            scrollPane1.setBounds(5, 30, 290, 235);

            { // compute preferred size
                Dimension preferredSize = new Dimension();
                for (int i = 0; i < panel2.getComponentCount(); i++) {
                    Rectangle bounds = panel2.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel2.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel2.setMinimumSize(preferredSize);
                panel2.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(panel2);
        panel2.setBounds(5, 160, 300, 310);

        //======== panel3 ========
        {
            panel3.setBackground(new Color(154, 216, 195));
            panel3.setLayout(null);

            //---- label3 ----
            label3.setText("Be Client Side :");
            panel3.add(label3);
            label3.setBounds(new Rectangle(new Point(5, 5), label3.getPreferredSize()));

            //---- clientConnectBtn ----
            clientConnectBtn.setText("Connect");
            panel3.add(clientConnectBtn);
            clientConnectBtn.setBounds(5, 110, 110, 23);

            //---- clientDisconnectBtn ----
            clientDisconnectBtn.setText("Disconnect");
            panel3.add(clientDisconnectBtn);
            clientDisconnectBtn.setBounds(120, 110, 110, 23);

            //---- label4 ----
            label4.setText("Selected Server Info:");
            panel3.add(label4);
            label4.setBounds(new Rectangle(new Point(5, 40), label4.getPreferredSize()));

            //---- serverNameLabel ----
            serverNameLabel.setText("Name :");
            panel3.add(serverNameLabel);
            serverNameLabel.setBounds(20, 60, 210, serverNameLabel.getPreferredSize().height);

            //---- serverStatusLabel ----
            serverStatusLabel.setText("Status : ");
            panel3.add(serverStatusLabel);
            serverStatusLabel.setBounds(20, 80, 210, serverStatusLabel.getPreferredSize().height);

            //---- label7 ----
            label7.setText("Selected File:");
            panel3.add(label7);
            label7.setBounds(new Rectangle(new Point(5, 150), label7.getPreferredSize()));
            panel3.add(fileSavePathTextField);
            fileSavePathTextField.setBounds(85, 145, 265, 25);

            //---- browseButton ----
            browseButton.setText("Browse");
            panel3.add(browseButton);
            browseButton.setBounds(355, 145, 85, 25);

            //---- label8 ----
            label8.setText("Progress :");
            panel3.add(label8);
            label8.setBounds(new Rectangle(new Point(5, 190), label8.getPreferredSize()));

            //---- progressBar1 ----
            progressBar1.setStringPainted(true);
            panel3.add(progressBar1);
            progressBar1.setBounds(5, 215, 430, 20);

            //---- sendFileBtn ----
            sendFileBtn.setText("Send");
            panel3.add(sendFileBtn);
            sendFileBtn.setBounds(5, 245, 95, 25);

            //---- cancelFileBtn ----
            cancelFileBtn.setText("Cancel");
            panel3.add(cancelFileBtn);
            cancelFileBtn.setBounds(105, 245, 95, 25);

            { // compute preferred size
                Dimension preferredSize = new Dimension();
                for (int i = 0; i < panel3.getComponentCount(); i++) {
                    Rectangle bounds = panel3.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel3.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel3.setMinimumSize(preferredSize);
                panel3.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(panel3);
        panel3.setBounds(320, 160, 450, 310);

        contentPane.setPreferredSize(new Dimension(780, 530));
        pack();
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

        handler = new P2PGuiHandler(Manager.form, Manager.selfServer, Manager.clientCore);
        startListenBtn.setActionCommand(ConstantVariables.COMMAND_START_SERVER);
        stopListenBtn.setActionCommand(ConstantVariables.COMMAND_STOP_SERVER);
        clientConnectBtn.setActionCommand(ConstantVariables.COMMAND_CLIENT_START);
        browseButton.setActionCommand(ConstantVariables.COMMAND_BROWSE);
        sendFileBtn.setActionCommand(ConstantVariables.COMMAND_SEND_FILE);
        clientDisconnectBtn.setActionCommand(ConstantVariables.COMMAND_CLIENT_STOP);
        registerItem.setActionCommand(ConstantVariables.COMMAND_SHOW_REGISTER_WINDOW);

        registerItem.addActionListener(handler);
        sendFileBtn.addActionListener(handler);
        startListenBtn.addActionListener(handler);
        stopListenBtn.addActionListener(handler);
        clientConnectBtn.addActionListener(handler);
        browseButton.addActionListener(handler);
        clientDisconnectBtn.addActionListener(handler);

    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JMenuBar menuBar1;
    private JMenu fileMenu;
    private JMenuItem exitItem;
    private JMenu accountMenu;
    private JMenuItem registerItem;
    private JMenuItem loginItem;
    private JMenuItem logoutItem;
    private JMenu helpMenu;
    private JMenuItem howtoItem;
    private JMenuItem aboutItem;
    private JPanel panel1;
    private JLabel label1;
    private JScrollPane scrollPane2;
    private JList list1;
    private JPanel panel2;
    private JLabel label2;
    private JButton startListenBtn;
    private JButton stopListenBtn;
    private JScrollPane scrollPane1;
    public static JTextArea serverTextArea;
    private JPanel panel3;
    private JLabel label3;
    private JButton clientConnectBtn;
    private JButton clientDisconnectBtn;
    private JLabel label4;
    public static JLabel serverNameLabel;
    public static JLabel serverStatusLabel;
    private JLabel label7;
    private JTextField fileSavePathTextField;
    private JButton browseButton;
    private JLabel label8;
    private JProgressBar progressBar1;
    private JButton sendFileBtn;
    private JButton cancelFileBtn;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    private P2PGuiHandler handler;
    private RegisterForm registerForm;


    public JTextField getFileSavePathTextField() {
        return fileSavePathTextField;
    }

    public JProgressBar getProgressBar1() {
        return progressBar1;
    }

    public RegisterForm getRegisterForm() {
        return registerForm;
    }
}
