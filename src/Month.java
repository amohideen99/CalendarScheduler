import java.util.ArrayList;

public class Month {

    int Month;
    ArrayList<Event> Events;

    public Month(int month) {

        Month = month;
        Events = new ArrayList<>();
    }

    /* events stored chronologically
    in the array (newest in front) */

    public void addEvent(Event event) {

        if (Events.isEmpty()) {

            Events.add(event);
            return;
        }

        for (int i = 0; i < Events.size(); i++) {

            if (event.getTime().isBefore(Events.get(i).getTime())) {

                Events.add(i, event);
                return;
            }
        }
    }

    public void removeEvent(int num) {

        Events.remove(num);
    }


}

