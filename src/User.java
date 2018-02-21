public class User {

    boolean Parent;
    boolean Driver;
    String Name;
    String Phone;
    String Password;
    int BufferTime;             //time to travel - place between events in minutes

    public User(boolean parent, boolean driver, String name, String phone, String password, int buffer) {

        Parent = parent;
        Driver = driver;
        Name = name;
        Phone = phone;
        Password = password;
        BufferTime = buffer;

    }


}
