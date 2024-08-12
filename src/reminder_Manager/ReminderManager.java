package reminder_Manager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import reminder_Model.Reminder;

public class ReminderManager {
    private List<Reminder> reminders;

    public ReminderManager() {
        reminders = new ArrayList<>();
    }

    public void addReminder(Reminder reminder) {
        reminders.add(reminder);
    }

    public void deleteReminder(Reminder reminder) {
        reminders.remove(reminder);
    }

    public void updateReminder(Reminder oldReminder, Reminder newReminder) {
        int index = reminders.indexOf(oldReminder);
        if (index != -1) {
            reminders.set(index, newReminder);
        }
    }

    public List<Reminder> getAllReminders() {
        return new ArrayList<>(reminders);
    }

    public List<Reminder> filterReminders(String date, String category, String status) {
        return reminders.stream()
                .filter(r -> (date.isEmpty() || r.getDate().toString().contains(date)) &&
                             (category.isEmpty() || r.getCategory().equalsIgnoreCase(category)) &&
                             (status.isEmpty() || r.getStatus().equalsIgnoreCase(status)))
                .collect(Collectors.toList());
    }
}
