package view.singlechat;

import model.Message;
import view.common.JWrappingTextPane;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class ConversationPanel extends JPanel {
    private static final int BORDER_SIZE = 4;

    public JWrappingTextPane getChatTextPane() {
        return chatTextPane;
    }

    private JWrappingTextPane chatTextPane;


    public ConversationPanel() {
        super(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(BORDER_SIZE, BORDER_SIZE, BORDER_SIZE, BORDER_SIZE));

        chatTextPane = new JWrappingTextPane();
        chatTextPane.setEditable(false);
        chatTextPane.setFocusable(false);
        JScrollPane scrollPane = new JScrollPane(chatTextPane);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void appendMessage(Message message) {
        try {
            if(message.image() != null) {
                ImageIcon messageIcon  = new ImageIcon(message.image());
                chatTextPane.insertIcon(messageIcon);
            }
            StyledDocument doc = chatTextPane.getStyledDocument();
            doc.insertString(doc.getLength(), message.text(), null);
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }

    }
}
