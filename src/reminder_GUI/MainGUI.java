package reminder_GUI;

import javax.swing.*;

import reminder_Manager.ReminderManager;
import reminder_Model.Reminder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class MainGUI extends JFrame {
    private JButton btnAddReminder;
    private JButton btnBrowse;
    private JButton btnCategorize;
    private JButton btnSmartListManagement;
    private ReminderManager reminderManager;

    public MainGUI() {
        setTitle("Reminder Application");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize the shared ReminderManager instance
        reminderManager = new ReminderManager();

        // Adding some reminders for demonstration
        reminderManager.addReminder(new Reminder("Finish report", new Date(), "Work", "Pending"));
        reminderManager.addReminder(new Reminder("Team meeting", new Date(), "Work", "Completed"));
        reminderManager.addReminder(new Reminder("Birthday Party", new Date(), "Personal", "Pending"));
        reminderManager.addReminder(new Reminder("Doctor's appointment", new Date(), "Personal", "Completed"));

        btnAddReminder = new JButton("Add Reminder");
        btnBrowse = new JButton("Browse");
        btnCategorize = new JButton("Categorize");
        btnSmartListManagement = new JButton("Smart List Management");

        btnBrowse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BrowseGUI(reminderManager);
            }
        });

        btnCategorize.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CategoryGUI(reminderManager);
            }
        });

        JPanel panel = new JPanel();
        panel.add(btnAddReminder);
        panel.add(btnBrowse);
        panel.add(btnCategorize);
        panel.add(btnSmartListManagement);

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainGUI();
            }
        });
    }
}
