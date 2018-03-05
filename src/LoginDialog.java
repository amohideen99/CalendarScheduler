import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LoginDialog extends JFrame {

    JPanel panel;
    JTextField userField;
    JPasswordField passField;
    JButton login;
    JButton createAccount;

    public LoginDialog() {

        panel = new JPanel();

        GridLayout layout = new GridLayout(3, 1);
        layout.setVgap(10);
        panel.setLayout(layout);

        userField = new JTextField();
        passField = new JPasswordField();

        login = new JButton("Login");
        createAccount = new JButton("Create Account");

        Box userBox = Box.createHorizontalBox();
        userBox.add(Box.createHorizontalStrut(3));
        userBox.add(new JLabel("Username: "));
        userBox.add(Box.createGlue());
        userBox.add(userField);
        userBox.add(Box.createHorizontalStrut(3));

        Box passBox = Box.createHorizontalBox();
        passBox.add(Box.createHorizontalStrut(3));
        passBox.add(new JLabel("Password: "));
        passBox.add(Box.createGlue());
        passBox.add(passField);
        passBox.add(Box.createHorizontalStrut(3));

        Box loginBox = Box.createHorizontalBox();
        loginBox.add(Box.createHorizontalStrut(3));
        loginBox.add(createAccount);
        loginBox.add(Box.createGlue());
        loginBox.add(login);
        loginBox.add(Box.createHorizontalStrut(3));

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!attemptLogin(userField.getText(), passField.getText())) {
                    passField.setText("");
                }
            }
        });

        createAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new UserDialog();
            }
        });

        panel.add(userBox);
        panel.add(passBox);
        panel.add(loginBox);

        add(panel);
        setTitle("Login");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setMinimumSize(new Dimension(250, 150));

    }

    public boolean attemptLogin(String username, String password) {

        ArrayList<User> users = initUsers();
        for (int i = 0; i < users.size(); i++) {

            if (users.get(i).getName().equals(username) && users.get(i).getPassword().equals(password)) {

                new Calendar(users.get(i), users);
                dispose();
                return true;
            }
        }

        return false;


    }

    public ArrayList<User> initUsers() {

        ArrayList<User> tempUsers = new ArrayList<>();

        try {

            BufferedReader reader = new BufferedReader(new FileReader("src/Users.txt"));

            String line;// = reader.readLine();


            while ((line = reader.readLine()) != null) {

                if (!line.equals("")) {
                    tempUsers.add(new User(line));
                }

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempUsers;
    }

    public static void main(String[] args) {

        new LoginDialog();

    }


}
