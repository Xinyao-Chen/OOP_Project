package reminder_GUI;

import javax.swing.*;

import reminder_Manager.ReminderManager;
import reminder_Model.Reminder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BrowseGUI extends JFrame {
    private JTextArea reminderListDisplay;
    private JButton btnFilter;
    private JButton btnDelete;
    private JButton btnEdit;
    private ReminderManager reminderManager;
    private List<Reminder> filteredReminders;

    public BrowseGUI(ReminderManager reminderManager) {
        setTitle("Browse Reminders");
        setSize(600, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        this.reminderManager = reminderManager;
        filteredReminders = reminderManager.getAllReminders();

        reminderListDisplay = new JTextArea(15, 50);
        btnFilter = new JButton("Filter");
        btnDelete = new JButton("Delete");
        btnEdit = new JButton("Edit");

        displayReminders();

        btnFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FilterGUI(new FilterGUI.FilterListener() {
                    @Override
                    public void onFilterApply(String date, String category, String status) {
                        applyFilters(date, category, status);
                    }
                });
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteReminder();
            }
        });

        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editReminder();
            }
        });

        JPanel panel = new JPanel();
        panel.add(btnFilter);
        panel.add(btnDelete);
        panel.add(btnEdit);
        panel.add(new JScrollPane(reminderListDisplay));

        add(panel);
        setVisible(true);
    }

    private void displayReminders() {
        reminderListDisplay.setText("");
        for (Reminder reminder : filteredReminders) {
            reminderListDisplay.append(reminder.toString() + "\n");
        }
    }

    private void applyFilters(String date, String category, String status) {
        filteredReminders = reminderManager.filterReminders(date, category, status);
        displayReminders();
    }

    private void deleteReminder() {
        String selectedText = reminderListDisplay.getSelectedText();
        if (selectedText != null) {
            Reminder selectedReminder = getReminderByText(selectedText);
            if (selectedReminder != null) {
                reminderManager.deleteReminder(selectedReminder);
                filteredReminders = reminderManager.getAllReminders();
                displayReminders();
            }
        }
    }

    private void editReminder() {
        String selectedText = reminderListDisplay.getSelectedText();
        if (selectedText != null) {
            Reminder selectedReminder = getReminderByText(selectedText);
            if (selectedReminder != null) {
                String newName = JOptionPane.showInputDialog("Edit Name:", selectedReminder.getName());
                String newCategory = JOptionPane.showInputDialog("Edit Category:", selectedReminder.getCategory());
                String newStatus = JOptionPane.showInputDialog("Edit Status:", selectedReminder.getStatus());

                Reminder updatedReminder = new Reminder(newName, selectedReminder.getDate(), newCategory, newStatus);
                reminderManager.updateReminder(selectedReminder, updatedReminder);
                filteredReminders = reminderManager.getAllReminders();
                displayReminders();
            }
        }
    }

    private Reminder getReminderByText(String text) {
        for (Reminder reminder : filteredReminders) {
            if (reminder.toString().equals(text.trim())) {
                return reminder;
            }
        }
        return null;
    }
}
