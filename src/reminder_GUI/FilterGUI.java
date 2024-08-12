package reminder_GUI;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FilterGUI extends JFrame {
    private JTextField txtDateFilter;
    private JTextField txtCategoryFilter;
    private JTextField txtStatusFilter;
    private JButton btnApplyFilter;

    public interface FilterListener {
        void onFilterApply(String date, String category, String status);
    }

    private FilterListener listener;

    public FilterGUI(FilterListener listener) {
        this.listener = listener;

        setTitle("Filter Reminders");
        setSize(300, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        txtDateFilter = new JTextField(20);
        txtCategoryFilter = new JTextField(20);
        txtStatusFilter = new JTextField(20);
        btnApplyFilter = new JButton("Apply Filter");

        btnApplyFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listener != null) {
                    listener.onFilterApply(txtDateFilter.getText(), txtCategoryFilter.getText(), txtStatusFilter.getText());
                    dispose();
                }
            }
        });

        JPanel panel = new JPanel();
        panel.add(new JLabel("Date Filter:"));
        panel.add(txtDateFilter);
        panel.add(new JLabel("Category Filter:"));
        panel.add(txtCategoryFilter);
        panel.add(new JLabel("Status Filter:"));
        panel.add(txtStatusFilter);
        panel.add(btnApplyFilter);

        add(panel);
        setVisible(true);
    }
}
