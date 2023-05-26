package view.singlechat;

import controller.Controller;
import model.Message;
import model.User;

import javax.swing.*;
import java.awt.*;

public class SingleChatDialog extends JDialog {
    private static final int BORDER_SIZE = 4;
    private final Controller controller;
    private final JPanel mainPanel;
    private GuestUserPanel guestUserPanel;
    private ConversationPanel conversationPanel;
    private MessagePanel messagePanel;

    public SingleChatDialog(int width, int height, User user, JFrame parentFrame, Controller controller) {
        //setup mainframe and panel
        super(parentFrame, "Chat", true);
        mainPanel = (JPanel)getContentPane();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(BORDER_SIZE, BORDER_SIZE, BORDER_SIZE, BORDER_SIZE));

        setResizable(false);
        setSize(width, height);

        guestUserPanel = new GuestUserPanel(user);
        mainPanel.add(guestUserPanel, BorderLayout.PAGE_START);

        conversationPanel = new ConversationPanel();
        mainPanel.add(conversationPanel, BorderLayout.CENTER);

        messagePanel = new MessagePanel(this);
        mainPanel.add(messagePanel, BorderLayout.PAGE_END);

        this.controller = controller;
        setVisible(true);
    }

    public GuestUserPanel getGuestUserPanel() {
        return guestUserPanel;
    }

    public void handleMessage(Message message) {
        conversationPanel.appendMessage(message);
        controller.handleMessage(message);
    }


}
