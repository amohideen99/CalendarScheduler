import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Calendar {

    JFrame frame;
    JPanel panel;


    public Calendar() {

        frame = new JFrame("Calendar");
        panel = new JPanel();

        GridLayout layout = new GridLayout(3,6);

        panel.setLayout(layout);


        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        initMonth(localDate);
    }

    public void initMonth(LocalDate date){

        for(int i = 0; i < date.lengthOfMonth(); i++ ){

            panel.add(new Day(i));
        }
    }


}
