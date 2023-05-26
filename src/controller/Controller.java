package controller;

import model.Message;
import model.User;
import view.login.LoginFrame;
import view.main.MainFrame;
import view.singlechat.SingleChatDialog;

import javax.swing.*;
import java.util.TreeSet;

public class Controller {
    private static Controller instance = null;
    private final LoginFrame loginFrame;

    private MainFrame mainFrame = null;

    private Controller() {
        loginFrame = new LoginFrame(400, 266, this);
    }

    public static Controller getInstance() {
        if(instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public static void main(String[] args) {
        Controller controller = getInstance();
    }

    public void handleMessage(Message message) {
       System.out.println(message);
    }

    public void updateOnlineUsers() {
        TreeSet<User> users = mockOnlineUsersList();
        mainFrame.updateUserList(users);
    }

    public void startChat(TreeSet<User> users) {
        if(users.size() == 1) {
            SingleChatDialog singleChatDialog = new SingleChatDialog(600, 700, users.first(), mainFrame, this);
        }
    }
    private TreeSet<User> mockOnlineUsersList() {
        TreeSet<User> users = new TreeSet<>();
        users.add(new User("arnold@gmail.com" , new ImageIcon(getClass().getResource("/attach.png")).getImage()));
        users.add(new User("xerses@gmail.com", new ImageIcon(getClass().getResource("/profile.png")).getImage()));
        users.add(new User("karapalp@gmail.com", new ImageIcon(getClass().getResource("/send.png")).getImage()));
        return users;
    }

    public void login(User user) {
        mainFrame = new MainFrame(400, 700, user, this);
        updateOnlineUsers();
        loginFrame.dispose();
    }
}