import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;

public class Day extends JPanel implements MouseListener {

    JLabel date;
    Border blackline;
    LocalDate localDate;
    int dayNum;

    public Day(LocalDate localdate, int day) {

        blackline = BorderFactory.createLineBorder(Color.black);

        date = new JLabel("" + day);
        add(date);
        setBorder(blackline);
        addMouseListener(this);

        setBackground(Color.WHITE);
        date.setForeground(Color.BLACK);

        localDate = localdate;
        dayNum = day;
    }


    @Override
    public void mouseClicked(MouseEvent e) {

        new EventDialog(localDate, dayNum);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

        setBackground(Color.BLACK);
        date.setForeground(Color.WHITE);
    }

    @Override
    public void mouseExited(MouseEvent e) {

        setBackground(Color.WHITE);
        date.setForeground(Color.BLACK);
    }
}
