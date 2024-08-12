package reminder_GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddNewCategoryGUI extends JFrame {
    private JTextField txtName;
    private JTextField txtType;
    private JButton btnAdd;

    public interface AddCategoryListener {
        void onCategoryAdded(String name, String type);
    }

    private AddCategoryListener listener;

    public AddNewCategoryGUI(AddCategoryListener listener) {
        this.listener = listener;

        setTitle("Add New Category");
        setSize(300, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        txtName = new JTextField(20);
        txtType = new JTextField(20);
        btnAdd = new JButton("Add Category");

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listener != null) {
                    listener.onCategoryAdded(txtName.getText(), txtType.getText());
                    dispose();
                }
            }
        });

        JPanel panel = new JPanel();
        panel.add(new JLabel("Name:"));
        panel.add(txtName);
        panel.add(new JLabel("Type:"));
        panel.add(txtType);
        panel.add(btnAdd);

        add(panel);
        setVisible(true);
    }
}
