package view.login;

import controller.Controller;
import model.User;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private static final int BORDER_SIZE = 4;
    private final Controller controller;

    private JPanel mainPanel;

    private ProfileImagePanel profileImagePanel;

    private LoginPanel loginPanel;

    private UserNamePanel userNamePanel;

    public LoginFrame(int width, int height, Controller controller) {
        super("LogIn");

        this.controller = controller;

        mainPanel = (JPanel) getContentPane();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(BORDER_SIZE, BORDER_SIZE, BORDER_SIZE, BORDER_SIZE));

        setResizable(false);
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        profileImagePanel = new ProfileImagePanel();

        mainPanel.add(profileImagePanel, BorderLayout.PAGE_START);

        loginPanel = new LoginPanel(this);

        mainPanel.add(loginPanel, BorderLayout.PAGE_END);

        userNamePanel = new UserNamePanel();

        mainPanel.add(userNamePanel, BorderLayout.CENTER);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setVisible(true);
    }

    public void onLoginButtonClick() {
        ImageIcon imageIcon = (ImageIcon) profileImagePanel.getProfileImageButton().getIcon();
        User currentUser = new User(userNamePanel.getUserNameField().getText(), imageIcon.getImage());
        controller.login(currentUser);
    }
}
