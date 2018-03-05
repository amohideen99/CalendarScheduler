import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static java.time.format.DateTimeFormatter.ofPattern;

public class Day extends JPanel implements MouseListener {

    Color background;
    Color foreground;
    JLabel date;
    Border blackline;
    LocalDate localDate;
    int dayNum;
    ArrayList<Event> events;
    Box dateBox;
    User currentLogin;

    public Day(User user, LocalDate localdate, int day, ArrayList<Event> list) {

        currentLogin = user;
        background = Color.WHITE;
        foreground = Color.BLACK;
        blackline = BorderFactory.createLineBorder(Color.black);
        date = new JLabel("" + day);
        localDate = localdate.of(localdate.getYear(), localdate.getMonth(), day);
        dayNum = day;
        events = list;
        dateBox = Box.createHorizontalBox();

        populateEvents(user);
    }


    public void populateEvents(User user) {

        GridLayout layout = new GridLayout(5, 1);

        setLayout(layout);

        setBackground(background);
        date.setForeground(foreground);

        dateBox.add(Box.createGlue());
        dateBox.add(date);
        dateBox.add(Box.createGlue());


        add(dateBox);
        setBorder(blackline);
        addMouseListener(this);

        for (int i = 0; i < events.size(); i++) {

            if (events.get(i).getDateTime().toLocalDate().equals(localDate)) {


                if (user.isParent() || user.getName().equals(events.get(i).getRecipient().getName())) {

                    String result = LocalTime.parse(events.get(i).getDateTime().toLocalTime().toString(), DateTimeFormatter.ofPattern("HH:mm")).format(DateTimeFormatter.ofPattern("hh:mm a"));

                    int num = i;
                    Box event = Box.createHorizontalBox();
                    event.setOpaque(true);

                    Color chosenColor = events.get(i).getRecipient().getColor();
                    event.setBackground(chosenColor);
                    event.add(Box.createGlue());

                    JLabel eventBlurb = new JLabel(events.get(i).Name + " - " + result);
                    eventBlurb.setForeground(Color.WHITE);

                    event.add(eventBlurb);
                    event.add(Box.createGlue());

                    event.addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {

                            if (e.getButton() == MouseEvent.BUTTON3) {

                                events.remove(num);
                                Calendar.initMonth(user, localDate);
                            } else {

                                new EventDialog(currentLogin, num);
                            }
                        }

                        @Override
                        public void mousePressed(MouseEvent e) {


                        }

                        @Override
                        public void mouseReleased(MouseEvent e) {

                        }

                        @Override
                        public void mouseEntered(MouseEvent e) {

                            event.setBackground(Color.BLACK);
                        }

                        @Override
                        public void mouseExited(MouseEvent e) {

                            event.setBackground(chosenColor);
                        }
                    });

                    add(event);

                }
            }
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {

        new EventDialog(currentLogin, localDate, dayNum);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

        background = Color.BLACK;
        foreground = Color.WHITE;
        setBackground(background);
        date.setForeground(foreground);
    }

    @Override
    public void mouseExited(MouseEvent e) {

        background = Color.WHITE;
        foreground = Color.BLACK;
        setBackground(background);
        date.setForeground(foreground);
    }
}
