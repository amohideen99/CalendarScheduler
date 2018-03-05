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

    public User(String line) {

        String[] parts = line.split(":");

        Parent = Boolean.parseBoolean(parts[0]);
        Driver = Boolean.parseBoolean(parts[1]);
        Name = parts[2];
        Phone = parts[3];
        Password = parts[4];
        Preferred = Color.decode(parts[5]);

    }

    public boolean isParent() {

        return Parent;
    }

    public String getPassword() {

        return Password;
    }

    public String getName() {

        return Name;
    }

    public String getPhone() {

        return Phone;
    }
    public boolean isDriver() {

        return Driver;
    }

    public String toString() {

        return getName();
    }

    public String getPermissions() {

        StringBuilder toReturn = new StringBuilder();

        if (isParent()) {

            toReturn.append("Parent");
        }

        if (isDriver()) {

            toReturn.append(", Driver");
        }

        if (!isParent() && !isDriver()) {

            toReturn.append("Child");
        }

        return toReturn.toString();
    }

    public Color getColor() {

        return Preferred;
    }

    public String toSaveLine() {

        String toReturn = String.valueOf(isParent()) + ":" + String.valueOf(isDriver()) + ":" + getName() + ":" + getPhone() + ":" + getPassword() + ":" + getColor().getRGB();
        return toReturn;
    }


}
