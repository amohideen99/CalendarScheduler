import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class Calendar {


    JMenuBar menuBar;
    JMenu edit;
    JMenuItem addUser;
    JMenuItem viewUsers;
    Box horizontalBox;
    JFrame frame;
    JPanel inner;
    static JPanel panel;
    JPanel outer;
    JLabel month;
    JButton nextMonth;
    JButton prevMonth;
    static LocalDate localDate;
    static ArrayList<User> users;
    static ArrayList<User> drivers;
    static ArrayList<Event> events;

    public Calendar(User perspective, ArrayList<User> userArrayList) {

        users = userArrayList;
        drivers = populateDriverList(userArrayList);
        events = populateEventList();

        frame = new JFrame("Calendar - " + perspective.getName());

        outer = new JPanel();
        outer.setLayout(new BoxLayout(outer, BoxLayout.PAGE_AXIS));

        menuBar = new JMenuBar();
        edit = new JMenu("Edit");
        addUser = new JMenuItem("Create User...");
        addUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new UserDialog();
            }
        });
        edit.add(addUser);
        menuBar.add(edit);

        viewUsers = new JMenuItem("View Users...");
        viewUsers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame usersList = new JFrame("User Accounts");
                JPanel userPanel = new JPanel();
                userPanel.setLayout(new GridLayout(0, 1, 3, 10));

                for (int i = 0; i < users.size(); i++) {

                    Box box = Box.createHorizontalBox();
                    box.add(Box.createHorizontalStrut(3));
                    // box.add(Box.createGlue());
                    box.add(new JLabel(String.valueOf(i + 1) + ") " + users.get(i).getName() + " - " + users.get(i).getPermissions()));
                    box.add(Box.createGlue());
                    box.add(Box.createHorizontalStrut(3));
                    userPanel.add(box);
                }

                usersList.add(userPanel);
                usersList.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                usersList.setVisible(true);

                //usersList.pack();
                usersList.setMinimumSize(usersList.getPreferredSize());
                usersList.setMaximumSize(usersList.getPreferredSize());
            }
        });
        edit.add(viewUsers);

        horizontalBox = Box.createHorizontalBox();
        horizontalBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, (int) horizontalBox.getMinimumSize().getHeight()));


        inner = new JPanel();


        panel = new JPanel();

        GridLayout layout = new GridLayout(5, 7);

        panel.setLayout(layout);


        Date date = new Date();
        localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        initMonth(perspective, localDate);

        month = new JLabel(localDate.getMonth().toString());
        month.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        month.setAlignmentX(Component.CENTER_ALIGNMENT);


        nextMonth = new JButton("Next Month");
        nextMonth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                LocalDate newDate = localDate.plusMonths(1);
                initMonth(perspective, newDate);
                month.setText(newDate.getMonth().toString());
                localDate = newDate;
            }
        });

        prevMonth = new JButton("Previous Month");
        prevMonth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                LocalDate newDate = localDate.plusMonths(-1);
                initMonth(perspective, newDate);
                month.setText(newDate.getMonth().toString());
                localDate = newDate;

            }
        });
        horizontalBox.add(Box.createHorizontalStrut(3));
        horizontalBox.add(prevMonth);
        horizontalBox.add(Box.createGlue());
        horizontalBox.add(month);
        horizontalBox.add(Box.createGlue());
        horizontalBox.add(nextMonth);
        horizontalBox.add(Box.createHorizontalStrut(3));


        outer.add(horizontalBox);
        outer.add(panel);

        frame.setJMenuBar(menuBar);
        frame.add(outer);
        frame.setMinimumSize(new Dimension(1200, 800));
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {

            public void windowClosing(java.awt.event.WindowEvent windowEvent) {

                BufferedWriter writerUser = null;
                BufferedWriter writeEvents = null;
                try {
                    writerUser = new BufferedWriter(new FileWriter("Users.txt", false));
                    writeEvents = new BufferedWriter(new FileWriter("Events.txt", false));


                    for (int i = 0; i < users.size(); i++) {

                        writerUser.append(users.get(i).toSaveLine() + "\n");
                    }

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

                    for (int i = 0; i < events.size(); i++) {


                        writeEvents.append(events.get(i).getName() + "," + events.get(i).getDateTime().format(formatter) + "," + events.get(i).getLocation() + ",#" + events.get(i).getResponsibility().toSaveLine() + "#" + events.get(i).getRecipient().toSaveLine() + "\n");
                    }


                    writerUser.close();
                    writeEvents.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.exit(0);
            }

        });
        frame.setVisible(true);
    }

    protected ArrayList<User> populateDriverList(ArrayList<User> users) {

        ArrayList<User> tempDrivers = new ArrayList<>();

        for (int i = 0; i < users.size(); i++) {

            if (users.get(i).isDriver()) {

                tempDrivers.add(users.get(i));
            }
        }

        return tempDrivers;
    }

    protected ArrayList<Event> populateEventList() {

        ArrayList<Event> tempEvents = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Events.txt"));


            String line;

            while ((line = reader.readLine()) != null) {

                if (!line.equals("")) {

                    String[] parts = line.split(",");
                    String[] userParts = line.split("#");

                    tempEvents.add(new Event(parts[0], LocalDateTime.parse(parts[1], formatter), parts[2], new User(userParts[1]), new User(userParts[2])));
                }
            }
        } catch (FileNotFoundException e) {

            return tempEvents;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return tempEvents;


    }

    public static void addUser(User newUser) {

        users.add(newUser);

        if (newUser.isDriver()) {

            drivers.add(newUser);
        }
    }

    public ArrayList<User> getUsers() {

        return users;
    }

    public ArrayList<User> getDrivers() {

        return drivers;
    }

    /* events stored chronologically
    in the array (newest in front) */

    public static void addEvent(Event event) {

        if (events.isEmpty()) {

            events.add(event);
            System.out.println(events.toString());
            return;

        } else {


            for (int i = 0; i < events.size(); i++) {

                if (event.getDateTime().isBefore(events.get(i).getDateTime())) {

                    events.add(i, event);
                    System.out.println(events.toString());
                    return;

                }


                events.add(event);
                return;

            }
        }


    }

    public void removeEvent(int num) {

        events.remove(num);
    }


    public static void initMonth(User user, LocalDate date) {

        panel.removeAll();

        for (int i = 1; i < date.lengthOfMonth() + 1; i++) {

            panel.add(new Day(user, date, i, events));
        }

        panel.revalidate();
        panel.repaint();
    }


}
