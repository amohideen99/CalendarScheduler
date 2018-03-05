import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        name = new JTextField("Full Name");

        Box nameBox = Box.createHorizontalBox();
        nameBox.add(Box.createHorizontalStrut(3));
        nameBox.add(new JLabel("Name: "));
        nameBox.add(Box.createHorizontalStrut(25));
        nameBox.add(name);
        nameBox.add(Box.createHorizontalStrut(3));

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

        parent = new JCheckBox("Parent Account");

        driver = new JCheckBox("Driver");


        Color[] colors = {Color.BLUE, Color.GREEN, Color.MAGENTA, Color.ORANGE, Color.YELLOW};

        String[] colorNames = {"Blue", "Green", "Magenta", "Orange", "Yellow"};
        JComboBox colorList = new JComboBox(colorNames);

        Box colorBox = Box.createHorizontalBox();
        colorBox.add(Box.createHorizontalStrut(3));
        colorBox.add(new JLabel("Color: "));
        colorBox.add(Box.createGlue());
        colorBox.add(colorList);
        colorBox.add(Box.createHorizontalStrut(3));


        JButton createUser = new JButton("Create User");
        createUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Calendar.addUser(new User(parent.isSelected(), driver.isSelected(), name.getText(), phone.getText(), password.getText(), colors[colorList.getSelectedIndex()]));
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
}
