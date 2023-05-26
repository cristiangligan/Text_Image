package view.singlechat;

import model.Message;
import view.common.JWrappingTextPane;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

public class MessagePanel extends JPanel {
    private static final int BORDER_SIZE = 4;
    private static final int BTN_SIZE = 64;
    private SingleChatDialog singleChatDialog;
    private JWrappingTextPane typeTextPane;

    public MessagePanel(SingleChatDialog singleChatDialog) {
        super(new BorderLayout());

        setPreferredSize(new Dimension(0, 144));
        setBorder(BorderFactory.createEmptyBorder(BORDER_SIZE, BORDER_SIZE, BORDER_SIZE, BORDER_SIZE));

        typeTextPane = new JWrappingTextPane();
        JScrollPane scrollPane = new JScrollPane(typeTextPane);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0,0,0,BORDER_SIZE));
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BorderLayout());
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(0, BORDER_SIZE, 0, 0));

        JButton attachButton = new JButton(new ImageIcon(getClass().getResource("/attach.png")));
        attachButton.addActionListener(e -> onAttachBtnClick());
        attachButton.setSize(BTN_SIZE, BTN_SIZE);
        buttonsPanel.add(attachButton, BorderLayout.PAGE_START);

        JButton sendButton = new JButton(new ImageIcon(getClass().getResource("/send.png")));
        sendButton.addActionListener(e -> onSendBtnClick());
        sendButton.setSize(BTN_SIZE, BTN_SIZE);
        buttonsPanel.add(sendButton, BorderLayout.PAGE_END);

        add(buttonsPanel, BorderLayout.LINE_END);
        this.singleChatDialog = singleChatDialog;
    }

    private void onAttachBtnClick() {
        ImageIcon icon = resizeImage(new ImageIcon("/Users/cornel/Desktop/portrait.jpg"), typeTextPane.getWidth());
        typeTextPane.insertIcon(icon);
        StyledDocument doc = typeTextPane.getStyledDocument();
        try {
            doc.insertString(doc.getLength(), "\n", null);
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }
        typeTextPane.requestFocus();
    }

    private void onSendBtnClick() {
        StringBuilder stringBuilder = new StringBuilder();
        Image image = null;
        StyledDocument doc = typeTextPane.getStyledDocument();
        ElementIterator iterator = new ElementIterator(doc);
        Element element;
        while(iterator.next() != null) {
            element = iterator.current();
            if(element.isLeaf()) {
                AbstractDocument.LeafElement leaf = (AbstractDocument.LeafElement) element;
                if(leaf.isDefined(AbstractDocument.ElementNameAttribute)) {
                    AttributeSet attributeSet = element.getAttributes();
                    if (attributeSet.containsAttribute(AbstractDocument.ElementNameAttribute, StyleConstants.IconElementName)) {
                        ImageIcon imageIcon = (ImageIcon) StyleConstants.getIcon(attributeSet);
                        image = imageIcon.getImage();
                        System.out.println("image");
                    }
                } else {
                    try {
                        String text = doc.getText(leaf.getStartOffset(), (leaf.getEndOffset() - leaf.getStartOffset()));
                        stringBuilder.append(text);
                        System.out.println(text);
                    } catch (BadLocationException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        Message message = new Message(image, stringBuilder.toString());
        singleChatDialog.handleMessage(message);

        typeTextPane.setText("");
        typeTextPane.requestFocus();
    }

    private ImageIcon resizeImage(ImageIcon icon, int width) {
        float iconWidth = icon.getIconWidth();
        float iconHeight = icon.getIconHeight();
        float aspectRatio = iconWidth / iconHeight;
        float newIconWidth = width / 2;
        float newIconHeight = newIconWidth / aspectRatio;
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance((int) newIconWidth, (int) newIconHeight, Image.SCALE_DEFAULT);
        return new ImageIcon(newImage);
    }
}
