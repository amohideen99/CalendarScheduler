import java.time.LocalDateTime;

public class Event {

    String Name;
    LocalDateTime Time;
    String Location;
    User Responsibility;
    User Recipient;
    boolean Existince;
    String Description;

    public Event(String name, LocalDateTime time, String location, User responsibility, User recipient) {

        Name = name;
        Time = time;
        Location = location;
        Responsibility = responsibility;
        Recipient = recipient;
        Existince = true;
    }

    public void setDateTime(LocalDateTime time) {

        Time = time;
    }

    public LocalDateTime getDateTime() {

        return Time;
    }

    public String getName() {

        return Name;
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


    public void setDescription(String string){

        Description = string;
    }

    public String getDescription(){

        return Description;
    }

    public User getRecipient() {

        return Recipient;
    }



}
