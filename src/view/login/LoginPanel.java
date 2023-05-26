package view.login;

import view.main.MainFrame;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    private JButton logInButton;
    private static final int BORDER_SIZE = 4;
    private LoginFrame loginFrame;

    public LoginPanel(LoginFrame loginFrame) {
        super(new FlowLayout(FlowLayout.RIGHT));

        this.loginFrame = loginFrame;
        setBorder(BorderFactory.createEmptyBorder(BORDER_SIZE, BORDER_SIZE, BORDER_SIZE, BORDER_SIZE));

        logInButton = new JButton("Log In");
        logInButton.setPreferredSize(new Dimension(64, 64));

        logInButton.addActionListener(e -> onLoginButtonClick());

        add(logInButton);
    }

    public void onLoginButtonClick() {
        loginFrame.onLoginButtonClick();
    }
}
