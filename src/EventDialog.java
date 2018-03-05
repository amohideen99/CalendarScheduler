import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class EventDialog extends JFrame {

    JLabel month;
    JLabel dayOfMonth;
    JPanel panel;
    JTextField eventName;
    SpinnerNumberModel year;
    JComboBox monthList;
    JComboBox daysList;
    JComboBox timeList;
    JComboBox recipientList;
    JComboBox driverList;
    JTextField locationField;
    JButton apply;
    String[] times = new String[97];
    User currentLogin;

    public EventDialog(User user, LocalDate localDate, int day) {

        currentLogin = user;
        setBackground(Color.BLACK);

        GridLayout layout = new GridLayout(9, 1);

        panel = new JPanel();

        panel.setLayout(layout);
        eventName = new JTextField("Event Name");
        eventName.setHorizontalAlignment(JTextField.CENTER);
        eventName.selectAll();
        eventName.setBackground(Color.BLACK);
        eventName.setForeground(Color.WHITE);
        eventName.setBorder(null);

        month = new JLabel("Month: ");
        dayOfMonth = new JLabel("Day: ");


        String[] months = new String[12];
        for (int i = 1; i < 13; i++) {

            months[i - 1] = new DateFormatSymbols().getMonths()[i - 1];
        }

        String[] days = new String[localDate.lengthOfMonth()];

        for (int i = 1; i < localDate.lengthOfMonth() + 1; i++) {

            days[i - 1] = "" + i;
        }

        monthList = new JComboBox(months);
        monthList.setSelectedIndex(localDate.getMonthValue() - 1);

        daysList = new JComboBox(days);
        daysList.setSelectedIndex(day - 1);

        year =
                new SpinnerNumberModel(localDate.getYear(), //initial value
                        localDate.getYear() - 100, //min
                        localDate.getYear() + 100, //max
                        1);                //step


        Box monthSet = Box.createHorizontalBox();
        monthSet.add(Box.createHorizontalStrut(3));
        monthSet.add(month);
        monthSet.add(Box.createGlue());
        monthSet.add(monthList);
        monthSet.add(Box.createHorizontalStrut(3));


        Box daySet = Box.createHorizontalBox();
        daySet.add(Box.createHorizontalStrut(3));
        daySet.add(dayOfMonth);
        daySet.add(Box.createGlue());
        daySet.add(daysList);
        daySet.add(Box.createHorizontalStrut(3));

        Box yearSet = Box.createHorizontalBox();
        yearSet.add(Box.createHorizontalStrut(3));
        yearSet.add(new JLabel("Year: "));
        yearSet.add(Box.createGlue());

        JSpinner spinner = new JSpinner(year);
        JSpinner.NumberEditor editor = new JSpinner.NumberEditor(spinner, "#");
        spinner.setEditor(editor);
        yearSet.add(spinner);
        yearSet.add(Box.createHorizontalStrut(3));


        int forCount = 0;
        String amPm = " AM";

        for (int z = 1; z < 3; z++) {

            for (int i = 1; i < 13; i++) {

                for (int j = 0; j < 4; j++) {

                    String hour = "00" + String.valueOf(i);
                    String minute = "00" + String.valueOf(j * 15);


                    times[forCount] = hour.substring(hour.length() - 2, hour.length()) + ":" + minute.substring(minute.length() - 2, minute.length()) + amPm;
                    forCount++;
                }

                if (i == 11 && z == 1) {

                    amPm = " PM";
                }

                if (i == 11 && z == 2) {

                    amPm = " AM";
                }
            }

        }

        timeList = new JComboBox(times);
        timeList.setSelectedIndex(24);

        Box timeSet = Box.createHorizontalBox();
        timeSet.add(Box.createHorizontalStrut(3));
        timeSet.add(new JLabel("Time: "));
        timeSet.add(Box.createGlue());
        timeSet.add(timeList);
        timeSet.add(Box.createHorizontalStrut(3));

        recipientList = new JComboBox(Calendar.users.toArray());

        Box recipientSet = Box.createHorizontalBox();
        recipientSet.add(Box.createHorizontalStrut(3));
        recipientSet.add(new JLabel("Recipient: "));
        recipientSet.add(Box.createGlue());
        recipientSet.add(recipientList);
        recipientSet.add(Box.createHorizontalStrut(3));

        driverList = new JComboBox(Calendar.drivers.toArray());

        Box driverSet = Box.createHorizontalBox();
        driverSet.add(Box.createHorizontalStrut(3));
        driverSet.add(new JLabel("Driver: "));
        driverSet.add(Box.createGlue());
        driverSet.add(driverList);
        driverSet.add(Box.createHorizontalStrut(3));

        locationField = new JTextField();

        Box locationSet = Box.createHorizontalBox();
        locationSet.add(Box.createHorizontalStrut(3));
        locationSet.add(new JLabel("Location: "));
        locationSet.add(Box.createHorizontalStrut(25));
        locationSet.add(locationField);
        locationSet.add(Box.createHorizontalStrut(3));

        Box applyCancel = Box.createHorizontalBox();
        applyCancel.add(Box.createHorizontalStrut(3));
        applyCancel.add(Box.createGlue());
        apply = new JButton("Apply");
        apply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                createEvent(localDate);
            }
        });

        applyCancel.add(apply);

        applyCancel.add(Box.createHorizontalStrut(3));

        layout.setVgap(10);
        panel.add(eventName);
        panel.add(monthSet);
        panel.add(daySet);
        panel.add(yearSet);
        panel.add(timeSet);
        panel.add(recipientSet);
        panel.add(driverSet);
        panel.add(locationSet);
        panel.add(applyCancel);


        setTitle("Create New Event");
        add(panel);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        setMinimumSize(new Dimension(200, 400));


    }


    public EventDialog(User user, int index) {

        this(user, Calendar.events.get(index).getDateTime().toLocalDate(), Calendar.events.get(index).getDateTime().getDayOfMonth());

        setTitle("Edit Event");
        eventName.setText(Calendar.events.get(index).getName());
        monthList.setSelectedIndex(Calendar.events.get(index).getDateTime().getMonthValue() - 1);
        daysList.setSelectedIndex(Calendar.events.get(index).getDateTime().getDayOfMonth() - 1);
        timeList.setSelectedIndex(((Calendar.events.get(index).getDateTime().getHour() - 1) * 4) + (Calendar.events.get(index).getDateTime().getMinute() / 15));

        recipientList.setSelectedIndex(0);

        for (int i = 0; i < Calendar.users.size(); i++) {

            if (Calendar.users.get(i).equals((Calendar.events.get(index).getRecipient()))) {
                recipientList.setSelectedIndex(i);
            }

        }

        driverList.setSelectedIndex(0);

        for (int i = 0; i < Calendar.drivers.size(); i++) {

            if (Calendar.drivers.get(i).equals((Calendar.events.get(index).getResponsibility()))) {
                driverList.setSelectedIndex(i);
            }

        }

        locationField.setText(Calendar.events.get(index).getLocation());

        for (ActionListener al : apply.getActionListeners()) {
            apply.removeActionListener(al);
        }

        apply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                updateEvent(index);
            }
        });
        System.out.println("Event Dialog(event)");
    }

    protected void createEvent(LocalDate localDate) {

        String name = eventName.getText();
        LocalTime time;
        String timeString = times[timeList.getSelectedIndex()];

        if (timeString.contains("AM")) {

            time = LocalTime.of(Integer.valueOf(timeString.substring(0, 2)), Integer.valueOf(timeString.substring(3, 5)));
            System.out.println(Integer.valueOf(timeString.substring(0, 2)));
        } else {

            time = LocalTime.of((12 + Integer.valueOf(timeString.substring(0, 2))) % 24, Integer.valueOf(timeString.substring(3, 5)));
            System.out.println(2 * Integer.valueOf(timeString.substring(0, 2)));
        }

        LocalDateTime dateTime = LocalDateTime.of(localDate, time);

        String location = locationField.getText();


        Calendar.addEvent(new Event(name, dateTime, location, Calendar.drivers.get(driverList.getSelectedIndex()), Calendar.users.get(recipientList.getSelectedIndex())));
        Calendar.initMonth(currentLogin, Calendar.localDate);
        dispose();
    }

    protected void updateEvent(int index) {

        String name = eventName.getText();
        LocalTime time;
        String timeString = times[timeList.getSelectedIndex()];

        if (timeString.contains("AM")) {

            time = LocalTime.of(Integer.valueOf(timeString.substring(0, 2)), Integer.valueOf(timeString.substring(3, 5)));

        } else {

            time = LocalTime.of((12 + Integer.valueOf(timeString.substring(0, 2))) % 24, Integer.valueOf(timeString.substring(3, 5)));

        }

        LocalDateTime dateTime = LocalDateTime.of(LocalDate.of(year.getNumber().intValue(), monthList.getSelectedIndex() + 1, daysList.getSelectedIndex() + 1), time);

        String location = locationField.getText();


        Calendar.events.set(index, new Event(name, dateTime, location, Calendar.drivers.get(driverList.getSelectedIndex()), Calendar.users.get(recipientList.getSelectedIndex())));
        Calendar.initMonth(currentLogin, Calendar.localDate);
        dispose();
    }
}
