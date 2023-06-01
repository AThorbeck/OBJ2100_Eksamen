package group7.obj2100;


import javax.swing.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class SQLQueryExecutor {

    private static final String URL = "jdbc:mysql://localhost:3306/classicmodels";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    private static Connection conn;
    private static Statement statement;
    private static BufferedWriter writer;
    private static int fileCounter;

    public static void runSqlQueryExecutor() {
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = conn.createStatement();
            writer = null;
            fileCounter = 1;

            SwingUtilities.invokeLater(() -> showTableSelection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void showTableSelection() {
        try {
            DefaultListModel < String > tableListModel = new DefaultListModel < > ();
            ResultSet tableResultSet = conn.getMetaData().getTables(null, null, null, new String[] {
                "TABLE"
            });
            while (tableResultSet.next()) {
                String tableName = tableResultSet.getString("TABLE_NAME");
                if (!tableName.startsWith("pma_")) {
                    tableListModel.addElement(tableName);
                }
            }

            JList < String > tableList = new JList < > (tableListModel);
            JScrollPane tableScrollPane = new JScrollPane(tableList);

            int option = JOptionPane.showConfirmDialog(null, tableScrollPane, "Table Selection", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            }

            int selectedTableIndex = tableList.getSelectedIndex();
            if (selectedTableIndex == -1) {
                JOptionPane.showMessageDialog(null, "Please select a table.", "Error", JOptionPane.ERROR_MESSAGE);
                showTableSelection();
                return;
            }

            String tableName = tableListModel.get(selectedTableIndex);

            showActionSelection(tableName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void showActionSelection(String tableName) {
        String[] options = {
            "Select",
            "Update",
            "Insert",
            "Delete",
            "Go back"
        };

        int selectedAction = JOptionPane.showOptionDialog(
            null,
            "Action Selection:",
            "Action Selection",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null,
            options,
            options[0]
        );

        switch (selectedAction) {
            case 0:
                executeSelectQuery(tableName);
                break;
            case 1:
                executeUpdateQuery(tableName);
                break;
            case 2:
                executeInsertQuery(tableName);
                break;
            case 3:
                executeDeleteQuery(tableName);
                break;
            case 4:
                showTableSelection();
                break;
            default:
                JOptionPane.showMessageDialog(null, "Invalid action number. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                showActionSelection(tableName);
                break;
        }
    }

    private static void executeSelectQuery(String tableName) {
        try {
            String query = JOptionPane.showInputDialog("Enter the SELECT query:");

            if (query != null && !query.isEmpty()) {
                ResultSet resultSet = statement.executeQuery(query);

                if (!resultSet.next()) {
                    JOptionPane.showMessageDialog(null, "No rows found.", "Result", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    resultSet.beforeFirst();
                    printResultSet(resultSet);
                    saveResultsToFile(resultSet, tableName);
                }
            }
        } catch (SQLException e) {
            if (e.getErrorCode() == 1054) {
                JOptionPane.showMessageDialog(null, "Invalid row specified in the query.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                e.printStackTrace();
            }
        }

        showActionSelection(tableName);
    }

    private static void executeUpdateQuery(String tableName) {
        try {
            String query = JOptionPane.showInputDialog("Enter the UPDATE query:");

            if (query != null && !query.isEmpty()) {
                int rowsAffected = statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null, rowsAffected + " rows updated.", "Result", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            if (e.getErrorCode() == 1054) {
                JOptionPane.showMessageDialog(null, "Invalid row specified in the query.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                e.printStackTrace();
            }
        }

        showActionSelection(tableName);
    }

    private static void executeInsertQuery(String tableName) {
        try {
            String query = JOptionPane.showInputDialog("Enter the INSERT query:");

            if (query != null && !query.isEmpty()) {
                int rowsAffected = statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null, rowsAffected + " rows inserted.", "Result", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            if (e.getErrorCode() == 1054) {
                JOptionPane.showMessageDialog(null, "Invalid row specified in the query.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                e.printStackTrace();
            }
        }

        showActionSelection(tableName);
    }

    private static void executeDeleteQuery(String tableName) {
        try {
            String query = JOptionPane.showInputDialog("Enter the DELETE query:");

            if (query != null && !query.isEmpty()) {
                int rowsAffected = statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null, rowsAffected + " rows deleted.", "Result", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            if (e.getErrorCode() == 1054) {
                JOptionPane.showMessageDialog(null, "Invalid row specified in the query.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                e.printStackTrace();
            }
        }

        showActionSelection(tableName);
    }

    private static void printResultSet(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        StringBuilder resultBuilder = new StringBuilder();
        while (resultSet.next()) {
            for (int i = 1; i <= columnCount; i++) {
                resultBuilder.append(resultSet.getString(i)).append(" ");
            }
            resultBuilder.append("\n");
        }

        JTextArea textArea = new JTextArea(resultBuilder.toString());
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new java.awt.Dimension(500, 300));

        JOptionPane.showMessageDialog(null, scrollPane, "Query Result", JOptionPane.PLAIN_MESSAGE);
    }

    private static void saveResultsToFile(ResultSet resultSet, String tableName) {
        try {
            String fileName = System.getProperty("user.home") + "/Desktop/" + tableName + "_" + fileCounter + ".txt";
            File file = new File(fileName);

            while (file.exists()) {
                fileCounter++;
                fileName = System.getProperty("user.home") + "/Desktop/" + tableName + "_" + fileCounter + ".txt";
                file = new File(fileName);
            }

            writer = new BufferedWriter(new FileWriter(file));

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    writer.write(resultSet.getString(i) + " ");
                }
                writer.newLine();
            }

            writer.close();
            JOptionPane.showMessageDialog(null, "Results saved to file: " + fileName, "Result", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
