import javax.swing.*;
import java.awt.*;

public class Day extends JPanel {

    Point Start;
    Dimension size;

    public Day(int day, Point start, Dimension dimension){


    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // draw the rectangle here

        g.drawRect(Start.getX(), Start.getY(), size.getWidth(), size.getHeight());

    }

}
