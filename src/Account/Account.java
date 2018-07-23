package Account;

import java.net.Socket;

public class Account {
    private String username;
    private String email;
    private String password;
    private String fullName;
    private boolean isLoggedIn;
    private String assignedIP;
    private Socket accountSocket;


    public Socket getAccountSocket() {
        return accountSocket;
    }

    public String getFullName() {
        return fullName;
    }

    public String getAssignedIP() {
        return assignedIP;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }


    /* Setters */

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setAccountSocket(Socket accountSocket) {
        this.accountSocket = accountSocket;
    }

    public void setAssignedIP(String assignedIP) {
        this.assignedIP = assignedIP;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
