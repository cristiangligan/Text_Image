package view.login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class UserNamePanel extends JPanel {
    private static final int BORDER_SIZE = 4;

    private JTextField userNameField;

    public UserNamePanel() {
        super(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(BORDER_SIZE, BORDER_SIZE, BORDER_SIZE, BORDER_SIZE));



        userNameField = new JTextField("username@email.com");
        userNameField.setForeground(Color.gray);

        userNameField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                userNameField.setText("");
                userNameField.setForeground(Color.BLACK);
            }

            public void focusLost(FocusEvent e) {
            }
        });

        add(userNameField, BorderLayout.CENTER);
    }

    public JTextField getUserNameField() {
        return userNameField;
    }
}

