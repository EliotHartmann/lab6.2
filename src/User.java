import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;

public class User implements Serializable{
    private String login;
    private boolean status;
    private boolean banned;
    private String password;


    public User(String login, String password){
        this.login = login;
        this.password = password;
    }

    static HashSet<User> users = new HashSet<>();
    static String link = "C:\\Users\\Acer\\Desktop\\usersdb.txt";

    public static void save(){
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
    public static void load(){
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(link));
            while (true) {
                User user = (User) ois.readObject();
                users.add(user);
            }
        } catch (ClassNotFoundException | IOException e) {
            System.out.println("ну да конец файла, а ты чего ожидал?");
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }
}
