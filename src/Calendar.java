import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Calendar {



    JFrame frame;
    JPanel inner;
    JPanel panel;
    JPanel outer;
    JLabel month;
    JButton nextMonth;
    LocalDate localDate;


    public Calendar() {

        frame = new JFrame("Calendar");

        outer = new JPanel(new BoxLayout(outer, BoxLayout.PAGE_AXIS));

        inner = new JPanel();
        inner.setLayout(new BorderLayout());

        panel = new JPanel();

        GridLayout layout = new GridLayout(4, 7);

        panel.setLayout(layout);


        Date date = new Date();
        localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        initMonth(localDate);

        month = new JLabel(localDate.getMonth().toString());
        month.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        month.setAlignmentX(Component.CENTER_ALIGNMENT);



        nextMonth = new JButton();
        nextMonth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                LocalDate newDate = localDate.plusMonths(1);
                initMonth(newDate);
                localDate = newDate;
            }
        });
        inner.add(month, BorderLayout.PAGE_START);
        inner.add(nextMonth, BorderLayout.AFTER_LINE_ENDS);

        outer.add(inner);
        outer.add(panel);

        frame.add(outer);
     //   frame.add(panel);
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
