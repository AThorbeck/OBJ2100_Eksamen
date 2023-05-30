import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

//@author Andreas

public class FolderSelectionButton {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Folder Selection Button");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
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
                System.out.println("Selected Folder: " + selectedFolderPath);
            }
        });
        
        // Add the button to the frame
        frame.getContentPane().add(button);
        frame.pack();
        frame.setVisible(true);
    }
}
