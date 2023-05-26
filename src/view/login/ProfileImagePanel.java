package view.login;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ProfileImagePanel extends JPanel {

    private JButton profileImageButton;
    private static final int BORDER_SIZE = 4;

    public ProfileImagePanel() {
        super(new FlowLayout(FlowLayout.LEFT));
        setBorder(BorderFactory.createEmptyBorder(BORDER_SIZE, BORDER_SIZE, BORDER_SIZE, BORDER_SIZE));

        profileImageButton = new JButton(new ImageIcon(getClass().getResource("/addPhoto.png")));
        profileImageButton.setPreferredSize(new Dimension(86, 86));

        profileImageButton.addActionListener(e -> {
            try {
                onProfileImageButtonClick();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        add(profileImageButton);
    }

    public JButton getProfileImageButton() {
        return profileImageButton;
    }

    private void onProfileImageButtonClick() throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("IMAGES", "png", "jpg", "jpeg");
        fileChooser.setFileFilter(filter);
        int option = fileChooser.showOpenDialog(this);
        if(option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            profileImageButton.setIcon(new ImageIcon(file.getAbsolutePath()));
        }
    }
}
