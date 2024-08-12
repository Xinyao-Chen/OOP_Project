package reminder_GUI;

import javax.swing.*;

import reminder_Manager.CategoryManager;
import reminder_Manager.ReminderManager;
import reminder_Model.Category;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CategoryGUI extends JFrame {
    private JTextArea categoryListDisplay;
    private JButton btnAddNew;
    private JButton btnDelete;
    private JButton btnBrowseReminders;
    private CategoryManager categoryManager;
    private ReminderManager reminderManager;

    public CategoryGUI(ReminderManager reminderManager) {
        setTitle("Categories");
        setSize(400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        this.reminderManager = reminderManager;

        categoryListDisplay = new JTextArea(10, 30);
        btnAddNew = new JButton("Add New");
        btnDelete = new JButton("Delete");
        btnBrowseReminders = new JButton("Browse Reminders");

        categoryManager = new CategoryManager();

        // Adding some categories for demonstration
        categoryManager.addCategory(new Category("Work", "Task"));
        categoryManager.addCategory(new Category("Personal", "Event"));

        displayCategories();

        btnAddNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddNewCategoryGUI(new AddNewCategoryGUI.AddCategoryListener() {
                    @Override
                    public void onCategoryAdded(String name, String type) {
                        categoryManager.addCategory(new Category(name, type));
                        displayCategories();
                    }
                });
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteCategory();
            }
        });

        btnBrowseReminders.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                browseRemindersInCategory();
            }
        });

        JPanel panel = new JPanel();
        panel.add(btnAddNew);
        panel.add(btnDelete);
        panel.add(btnBrowseReminders);
        panel.add(new JScrollPane(categoryListDisplay));

        add(panel);
        setVisible(true);
    }

    private void displayCategories() {
        categoryListDisplay.setText("");
        List<Category> categories = categoryManager.getAllCategories();
        for (Category category : categories) {
            categoryListDisplay.append(category.toString() + "\n");
        }
    }

    private void deleteCategory() {
        String selectedText = categoryListDisplay.getSelectedText();
        if (selectedText != null) {
            Category selectedCategory = getCategoryByText(selectedText);
            if (selectedCategory != null) {
                categoryManager.deleteCategory(selectedCategory);
                displayCategories();
            }
        }
    }

    private void browseRemindersInCategory() {
        String selectedText = categoryListDisplay.getSelectedText();
        if (selectedText != null) {
            Category selectedCategory = getCategoryByText(selectedText);
            if (selectedCategory != null) {
                new CategoryReminderBrowseGUI(selectedCategory, reminderManager);
            }
        }
    }

    private Category getCategoryByText(String text) {
        List<Category> categories = categoryManager.getAllCategories();
        for (Category category : categories) {
            if (category.toString().equals(text.trim())) {
                return category;
            }
        }
        return null;
    }
}
