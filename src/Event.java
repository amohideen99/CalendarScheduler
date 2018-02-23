import java.time.LocalDateTime;

public class Event {

    String Name;
    LocalDateTime Time;
    String Location;
    User Responsibility;
    User Recipient;
    int EventNum;
    boolean Existince;
    String Description;

    public Event(String name, LocalDateTime time, String location, User responsibility, User recipient, int eventNum){

        Name = name;
        Time = time;
        Location = location;
        Responsibility = responsibility;
        Recipient = recipient;
        EventNum = eventNum;
        Existince = true;
    }

    public void setTime(LocalDateTime time){

        Time = time;
    }

    public LocalDateTime getTime(){

        return Time;
    }

    public void setLocation(String location){

        Location = location;
    }

    public String getLocation(){

        return Location;
    }

    public void setResponsibility(User user){

        Responsibility = user;
    }

    public User getResponsibility(){

        return Responsibility;
    }

    public void cancelEvent(){

        Existince = false;
    }

    public int getEventNum(){

        return EventNum;
    }

    public void setDescription(String string){

        Description = string;
    }

    public String getDescription(){

        return Description;
    }

}
