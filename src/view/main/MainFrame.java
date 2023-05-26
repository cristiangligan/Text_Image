package view.main;

import controller.Controller;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.util.TreeSet;

public class MainFrame extends JFrame {
    private static final int BORDER_SIZE = 4;
    private final Controller controller;

    private final JPanel mainPanel;
    private HostUserPanel hostUserPanel;
    private UsersListPanel usersListPanel;

    private User currentUser;

    public MainFrame(int width, int height, User user, Controller controller) {
        super("Main");

        this.controller = controller;
        currentUser = user;
        mainPanel = (JPanel) getContentPane();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(BORDER_SIZE, BORDER_SIZE, BORDER_SIZE, BORDER_SIZE));

        setResizable(false);
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        hostUserPanel = new HostUserPanel(this, currentUser, this.controller);
        add(hostUserPanel, BorderLayout.PAGE_START);

        usersListPanel = new UsersListPanel();
        add(usersListPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    public void updateUserList(TreeSet<User> users) {
        usersListPanel.updateOnlineUsers(users);
    }

    public TreeSet<User> getSelectedUsers() {
        TreeSet<User> selectedUsers = usersListPanel.getSelectedUsers();
        return selectedUsers;
    }

    public void startChat(TreeSet<User> users) {
        controller.startChat(users);
    }
}
