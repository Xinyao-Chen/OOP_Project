package reminder_GUI;

import javax.swing.*;

import reminder_Manager.ReminderManager;
import reminder_Model.Category;
import reminder_Model.Reminder;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryReminderBrowseGUI extends JFrame {
    private JTextArea reminderListDisplay;
    private Category category;
    private ReminderManager reminderManager;

    public CategoryReminderBrowseGUI(Category category, ReminderManager reminderManager) {
        this.category = category;
        this.reminderManager = reminderManager;

        setTitle("Reminders in Category: " + category.getName());
        setSize(400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        reminderListDisplay = new JTextArea(15, 30);

        displayRemindersInCategory();

        JPanel panel = new JPanel();
        panel.add(new JScrollPane(reminderListDisplay));

        add(panel);
        setVisible(true);
    }

    private void displayRemindersInCategory() {
        List<Reminder> remindersInCategory = reminderManager.getAllReminders()
                .stream()
                .filter(reminder -> reminder.getCategory().equalsIgnoreCase(category.getName()))
                .collect(Collectors.toList());

        reminderListDisplay.setText("");
        for (Reminder reminder : remindersInCategory) {
            reminderListDisplay.append(reminder.toString() + "\n");
        }
    }
}
