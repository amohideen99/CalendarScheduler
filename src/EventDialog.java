import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class EventDialog extends JFrame {

    JPanel panel;
    JTextField eventName;
    JPanel combos;
    JComboBox monthList;
    JComboBox daysList;

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

        panel.add(eventName);

        combos = new JPanel();
        GridLayout combosLayout = new GridLayout(1, 2);
        combos.setLayout(combosLayout);

        combos.add(monthList);
        combos.add(daysList);

        panel.add(combos);


        setTitle("Create New Event");
        add(panel);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        setMinimumSize(new Dimension(200, 400));


    }
}
