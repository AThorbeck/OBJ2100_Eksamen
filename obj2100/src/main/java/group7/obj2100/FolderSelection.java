package group7.obj2100;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

//@author Andreas

public class FolderSelection {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Folder Selection Button");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a label for displaying the current folder
        JLabel currentFolderLabel = new JLabel("Current Folder:");
        JTextField currentFolderTextField = new JTextField(20);
        currentFolderTextField.setEditable(false); // Set the text field as read-only

        // Create a button
        JButton button = new JButton("Select Folder");
        button.addActionListener(e -> {
            // Create a file chooser
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // Only allow selection of directories/folders

            // Show the file chooser dialog
            int option = fileChooser.showDialog(frame, "Select");

            // Process the selected folder
            if (option == JFileChooser.APPROVE_OPTION) {
                String selectedFolderPath = fileChooser.getSelectedFile().getAbsolutePath();
                currentFolderTextField.setText(selectedFolderPath);
                System.out.println("Selected Folder: " + selectedFolderPath);
            }
        });

        // Create a panel and add the label, text field, and button
        JPanel panel = new JPanel();
        panel.add(currentFolderLabel);
        panel.add(currentFolderTextField);
        panel.add(button);

        // Add the panel to the frame
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
