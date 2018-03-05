import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class Calendar {


    JMenuBar menuBar;
    JMenu edit;
    JMenuItem addUser;
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

    public Calendar() {

        users = new ArrayList<>();
        drivers = new ArrayList<>();
        events = new ArrayList<>();

        frame = new JFrame("Calendar");

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


        horizontalBox = Box.createHorizontalBox();
        horizontalBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, (int) horizontalBox.getMinimumSize().getHeight()));


        inner = new JPanel();


        panel = new JPanel();

        GridLayout layout = new GridLayout(5, 7);

        panel.setLayout(layout);


        Date date = new Date();
        localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        initMonth(localDate);

        month = new JLabel(localDate.getMonth().toString());
        month.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        month.setAlignmentX(Component.CENTER_ALIGNMENT);


        nextMonth = new JButton("Next Month");
        nextMonth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                LocalDate newDate = localDate.plusMonths(1);
                initMonth(newDate);
                month.setText(newDate.getMonth().toString());
                localDate = newDate;
            }
        });

        prevMonth = new JButton("Previous Month");
        prevMonth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                LocalDate newDate = localDate.plusMonths(-1);
                initMonth(newDate);
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
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
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


    public static void initMonth(LocalDate date) {

        panel.removeAll();

        for (int i = 1; i < date.lengthOfMonth() + 1; i++) {

            panel.add(new Day(date, i, events));
        }

        panel.revalidate();
        panel.repaint();
    }


    public static void main(String[] args) {

        new Calendar();


    }


}
