package view.singlechat;

import model.User;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.TreeSet;

public class GuestUserPanel extends JPanel {
    private static final int BORDER_SIZE = 4;

    private JLabel contactPhotoLabel;
    private JLabel contactNameLabel;


    public GuestUserPanel(User user) {
        super(new FlowLayout(FlowLayout.LEFT));
        setPreferredSize(new Dimension(0, 64));
        setBorder(BorderFactory.createEmptyBorder(BORDER_SIZE, BORDER_SIZE, BORDER_SIZE, BORDER_SIZE));
        ImageIcon userIcon = new ImageIcon(user.image());
        contactPhotoLabel = new JLabel(userIcon);
        contactNameLabel = new JLabel(user.name());
        add(contactPhotoLabel);
        add(contactNameLabel);
    }
}
