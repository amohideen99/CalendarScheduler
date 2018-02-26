import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Calendar {

    JFrame frame;
    JPanel outer;
    JPanel panel;
    JLabel month;


    public Calendar() {

        frame = new JFrame("Calendar");

        outer = new JPanel();
        outer.setLayout(new BoxLayout(outer, BoxLayout.Y_AXIS));

        panel = new JPanel();

        GridLayout layout = new GridLayout(4, 7);

        panel.setLayout(layout);


        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        initMonth(localDate);

        month = new JLabel(localDate.getMonth().toString());
        month.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        month.setAlignmentX(Component.CENTER_ALIGNMENT);

        outer.add(month);
        outer.add(panel);

        frame.add(outer);
        frame.setMinimumSize(new Dimension(800, 500));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void initMonth(LocalDate date) {

        for (int i = 1; i < date.lengthOfMonth() + 1; i++) {

            panel.add(new Day(date, i));
        }
    }

    public static void main(String[] args) {

        Calendar calendar = new Calendar();


    }


}
