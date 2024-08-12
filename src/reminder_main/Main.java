package reminder_main;

import reminder_GUI.MainGUI;

public class Main {
    public static void main(String[] args) {
        // Initialize the MainGUI which will serve as the entry point for the application
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainGUI();
            }
        });
    }
}
