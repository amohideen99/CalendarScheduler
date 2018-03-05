import java.awt.*;

public class User {

    boolean Parent;
    boolean Driver;
    String Name;
    String Phone;
    String Password;
    Color Preferred;

    public User(boolean parent, boolean driver, String name, String phone, String password, Color preferred) {

        Parent = parent;
        Driver = driver;
        Name = name;
        Phone = phone;
        Password = password;
        Preferred = preferred;

    }

    public boolean isDriver() {

        return Driver;
    }

    public String toString() {

        return Name;
    }

    public Color getColor() {

        return Preferred;
    }


}
