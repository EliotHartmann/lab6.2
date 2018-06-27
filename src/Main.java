import javax.print.Doc;
import javax.swing.*;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Main {
    public static void main(String[] args) throws IOException {

        ReadWriteLock lock = new ReentrantReadWriteLock();
        int count = 0;
        DBManager<User> userdbManager = new DBManager(User.class);
        userdbManager.dropTable();
        userdbManager.create();

        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/lab8", "postgres", "12345");
            ResultSet res = connection.createStatement().executeQuery("SELECT COUNT(*) FROM \"User\"");
            if(res.next())
            count = res.getInt("count");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for(int i =0; i<count; i++) {
            Users.users.add(userdbManager.getObject(userdbManager.executeQuery("SELECT * FROM \"User\"")));
        }

        DBManager<Policeman> policemanDBManager = new DBManager(Policeman.class);
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/lab8", "postgres", "12345");
            ResultSet res = connection.createStatement().executeQuery("SELECT COUNT(*) FROM \"Policeman\"");
            if(res.next())
                count = res.getInt("count");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for(int i =0; i<count; i++) {
            Commands.set.copyOnWriteArraySet.add(policemanDBManager.getObject(policemanDBManager.executeQuery("SELECT * FROM \"Policeman\"")));
        }

//        System.out.println(User.users.size());
        javax.swing.SwingUtilities.invokeLater(() -> new ServerGUI(lock));
        Server server = new Server(9999);
        server.createServer();

//        DBManager dbManager = new DBManager(testClass.class);
//        dbManager.create();
//        testClass testClass = new testClass(1, true);
//        dbManager.insert(testClass);
//        dbManager.delete(testClass);
//        dbManager.dropTable();
//        Object object = dbManager.getObject(dbManager.executeQuery("SELECT * FROM testClass"));
//        for (Field field : object.getClass().getDeclaredFields()) {
//            field.setAccessible(true);
//            System.out.println(field.getType().toString());
//            try {
//                System.out.println(field.get(object));
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//        }


    }
}
