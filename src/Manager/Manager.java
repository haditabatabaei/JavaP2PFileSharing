package Manager;

import Client.ClientCore;
import Constant.ConstantVariables;
import Gui.Gui;
import Server.SelfServer;

import java.util.Scanner;

public class Manager {
    public static SelfServer selfServer;
    public static ClientCore clientCore;
    public static int startCounter;
    public static Gui form;

    public static void main(String[] args) {
        selfServer = new SelfServer();
        clientCore = new ClientCore();
        startCounter = 0;
        form = new Gui();
        form.setVisible(true);
    }
}
