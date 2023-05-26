package view.main;

import model.User;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;

public class UsersListPanel extends JPanel {

    private static final int BORDER_SIZE = 4;

    private DefaultMutableTreeNode onlineGuests;
    private DefaultTreeModel treeModel;
    private JTree tree;


    public UsersListPanel() {
        super(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(BORDER_SIZE, BORDER_SIZE, BORDER_SIZE, BORDER_SIZE));

        // setup the tree
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Root node");
        treeModel = new DefaultTreeModel(rootNode);
        onlineGuests = new DefaultMutableTreeNode("Online guest users");
        rootNode.add(onlineGuests);

        tree = new JTree(treeModel);
        tree.setRootVisible(false);
        tree.setShowsRootHandles(true);
        JScrollPane scrollPane = new JScrollPane(tree);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void updateOnlineUsers(TreeSet<User> users) {
        for(User user : users) {
            onlineGuests.add(new DefaultMutableTreeNode(user));
        }
        treeModel.reload();
    }

    public TreeSet<User> getSelectedUsers() {
        TreePath[] paths = tree.getSelectionPaths();
        TreeSet<User> selectedUsers = new TreeSet<>();
        if(paths != null) {
            for (int i = 0; i < paths.length; i++) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) paths[i].getLastPathComponent();
                User user = (User) node.getUserObject();
                selectedUsers.add(user);
            }
        }
        return selectedUsers;
    }
}
