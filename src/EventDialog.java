import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.awt.*;
import java.time.LocalDate;

public class EventDialog extends JFrame {

    JLabel month;
    JLabel dayOfMonth;
    JPanel panel;
    JTextField eventName;
    SpinnerNumberModel year;
    JComboBox monthList;
    JComboBox daysList;
    JComboBox timeList;

    public EventDialog(LocalDate localDate, int day) {

        setBackground(Color.BLACK);

        GridLayout layout = new GridLayout(10, 1);

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

            months[i - 1] = i + "";
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


        Box daySet = Box.createHorizontalBox();
        daySet.add(Box.createHorizontalStrut(3));
        daySet.add(dayOfMonth);
        daySet.add(Box.createGlue());
        daySet.add(daysList);

        Box yearSet = Box.createHorizontalBox();
        yearSet.add(Box.createHorizontalStrut(3));
        yearSet.add(new JLabel("Year: "));
        yearSet.add(Box.createGlue());

        JSpinner spinner = new JSpinner(year);
        JSpinner.NumberEditor editor = new JSpinner.NumberEditor(spinner, "#");
        spinner.setEditor(editor);
        yearSet.add(spinner);

        String[] times = new String[97];

        for (int i = 0; i < 24; i++) {

            for (int j = 0; j < 61; j += 15) {

                times[(int) (i * (4) + (j / 15))] = String.valueOf(i) + ":" + String.valueOf(j);

            }
        }

        timeList = new JComboBox(times);

        Box timeSet = Box.createHorizontalBox();
        timeSet.add(Box.createHorizontalStrut(3));
        timeSet.add(new JLabel("Time: "));
        timeSet.add(Box.createGlue());
        timeSet.add(timeList);







        layout.setVgap(10);
        panel.add(eventName);
        panel.add(monthSet);
        panel.add(daySet);
        panel.add(yearSet);
        panel.add(timeSet);


        setTitle("Create New Event");
        add(panel);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        setMinimumSize(new Dimension(200, 400));


    }
}
