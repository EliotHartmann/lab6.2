import java.io.*;
import java.util.ArrayList;

public class User {
    private String login;
    private String password;

    static ArrayList<User> users = new ArrayList<>();
    String link = "C:\\Users\\Acer\\usersdb.txt";

    public void save(String link){
        try{
            ObjectOutputStream ous = new ObjectOutputStream(new FileOutputStream(link));
            for (User user : users) {
                ous.writeObject(user);
            }
            ous.flush();
            ous.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void load(String link){
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(link));
            while (true) {
                User user = (User) ois.readObject();
                users.add(user);
            }
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
