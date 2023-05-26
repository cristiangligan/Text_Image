package view.main;

import controller.Controller;
import model.User;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.TreeSet;

public class HostUserPanel extends JPanel {
    private static final int BORDER_SIZE = 4;

    private MainFrame mainFrame;
    private JLabel contactPhotoLabel;
    private JLabel contactNameLabel;
    private Image contactPhoto;
    private Controller controller;

    private JButton chatButton;

    private User currentUser;
    public HostUserPanel(MainFrame mainFrame, User user, Controller controller) {
        super(new BorderLayout());

        this.controller = controller;
        currentUser = user;
        setPreferredSize(new Dimension(0, 64));
        setBorder(BorderFactory.createEmptyBorder(BORDER_SIZE, BORDER_SIZE, BORDER_SIZE, BORDER_SIZE));

        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        this.mainFrame = mainFrame;


        contactPhoto = currentUser.image();
        ImageIcon contactPhotoIcon = new ImageIcon(contactPhoto);
        contactPhotoLabel = new JLabel(contactPhotoIcon);
        contactNameLabel = new JLabel(currentUser.name());

        labelPanel.add(contactPhotoLabel);
        labelPanel.add(contactNameLabel);

        add(labelPanel, BorderLayout.LINE_START);

        chatButton = new JButton("Chat");
        chatButton.setPreferredSize(new Dimension(64, 0));
        chatButton.addActionListener(e -> onChatBtnClick());
        add(chatButton, BorderLayout.LINE_END);
    }

    public void onChatBtnClick() {
        TreeSet<User> users = mainFrame.getSelectedUsers();
        mainFrame.startChat(users);
    }
}
