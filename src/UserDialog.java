import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class UserDialog extends JFrame {

    JPanel panel;
    JTextField name;
    JTextField phone;
    JTextField password;
    JCheckBox parent;
    JCheckBox driver;


    public UserDialog() {

        panel = new JPanel();
        GridLayout layout = new GridLayout(7, 1);
        layout.setVgap(10);
        JButton createUser = new JButton("Create User");
        createUser.setEnabled(false);

        name = new JTextField("Full Name");

        Box nameBox = Box.createHorizontalBox();
        nameBox.add(Box.createHorizontalStrut(3));
        nameBox.add(new JLabel("Name: "));
        nameBox.add(Box.createHorizontalStrut(25));
        nameBox.add(name);
        nameBox.add(Box.createHorizontalStrut(3));

        name.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {

                if (!password.getText().equals("") && !name.getText().equals("")) {

                    createUser.setEnabled(true);

                } else
                    createUser.setEnabled(false);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {

                if (!password.getText().equals("") && !name.getText().equals("")) {

                    createUser.setEnabled(true);

                } else
                    createUser.setEnabled(false);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {


            }
        });


        phone = new JTextField();

        Box phoneBox = Box.createHorizontalBox();
        phoneBox.add(Box.createHorizontalStrut(3));
        phoneBox.add(new JLabel("Phone Number: "));
        phoneBox.add(Box.createHorizontalStrut(5));
        phoneBox.add(phone);
        phoneBox.add(Box.createHorizontalStrut(3));

        password = new JTextField();

        Box passBox = Box.createHorizontalBox();
        passBox.add(Box.createHorizontalStrut(3));
        passBox.add(new JLabel("Password: "));
        passBox.add(Box.createHorizontalStrut(25));
        passBox.add(password);
        passBox.add(Box.createHorizontalStrut(3));

        password.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {

                if (!password.getText().equals("") && !name.getText().equals("")) {

                    createUser.setEnabled(true);

                } else
                    createUser.setEnabled(false);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {

                if (!password.getText().equals("") && !name.getText().equals("")) {

                    createUser.setEnabled(true);

                } else
                    createUser.setEnabled(false);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

        parent = new JCheckBox("Parent Account");

        driver = new JCheckBox("Driver");
        parent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (parent.isSelected()) {
                    driver.setSelected(true);
                }
            }
        });


        Color[] colors = {Color.BLUE, Color.GREEN, Color.MAGENTA, Color.ORANGE, Color.YELLOW};

        String[] colorNames = {"Blue", "Green", "Magenta", "Orange", "Yellow"};
        JComboBox colorList = new JComboBox(colorNames);

        Box colorBox = Box.createHorizontalBox();
        colorBox.add(Box.createHorizontalStrut(3));
        colorBox.add(new JLabel("Color: "));
        colorBox.add(Box.createGlue());
        colorBox.add(colorList);
        colorBox.add(Box.createHorizontalStrut(3));


        createUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    writeUser(new User(parent.isSelected(), driver.isSelected(), name.getText(), phone.getText(), password.getText(), colors[colorList.getSelectedIndex()]));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                dispose();
            }
        });

        Box createBox = Box.createHorizontalBox();
        createBox.add(Box.createGlue());
        createBox.add(createUser);
        createBox.add(Box.createHorizontalStrut(3));


        panel.setLayout(layout);

        panel.add(nameBox);
        panel.add(phoneBox);
        panel.add(passBox);
        panel.add(parent);
        panel.add(driver);
        panel.add(colorBox);
        panel.add(createBox);


        setTitle("Create User");
        add(panel);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        setMinimumSize(new Dimension(300, 300));
    }

    protected void writeUser(User user)
            throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter("Users.txt", true));

        writer.append(user.toSaveLine() + "\n");

        if (Calendar.users != null) {

            Calendar.addUser(user);
        }

        writer.close();
        return;
    }
}
