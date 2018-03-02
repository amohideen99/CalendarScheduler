import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Calendar {


    Box horizontalBox;
    JFrame frame;
    JPanel inner;
    JPanel panel;
    JPanel outer;
    JLabel month;
    JButton nextMonth;
    JButton prevMonth;
    LocalDate localDate;


    public Calendar() {

        frame = new JFrame("Calendar");

        outer = new JPanel();
        outer.setLayout(new BoxLayout(outer, BoxLayout.PAGE_AXIS));

        horizontalBox = Box.createHorizontalBox();
        horizontalBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, (int) horizontalBox.getMinimumSize().getHeight()));



        inner = new JPanel();


        panel = new JPanel();

        GridLayout layout = new GridLayout(4, 7);

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


        frame.add(outer);
        frame.setMinimumSize(new Dimension(800, 500));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void initMonth(LocalDate date) {

        panel.removeAll();

        for (int i = 1; i < date.lengthOfMonth() + 1; i++) {

            panel.add(new Day(date, i));
        }

        panel.revalidate();
        panel.repaint();
    }


    public static void main(String[] args) {

        Calendar calendar = new Calendar();


    }


}
