import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ClientThread implements Runnable{
    String string;
    private Socket socket;
    private WorkJSON workJSON = new WorkJSON();
    private boolean answer;
    private String userLogin;
    private boolean userStatus;



    public void run(){
           try{
                PrintStream ps = new PrintStream(socket.getOutputStream());
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String message;
                while(true){
                    message = br.readLine();
                    if(message.equals("start")){
                        System.out.println("start");
                        for(Policeman policeman : Commands.set.copyOnWriteArraySet){
                            ps.println(WorkJSON.toJSON(policeman));
                            System.out.println(WorkJSON.toJSON(policeman));
                            ps.flush();
                        }
                        ps.println("end");
                        System.out.println("end");
                    }
                    if(message.equals("check")) {
                        String login = br.readLine();
                        String pswd = br.readLine();
                    System.out.println(login);
                    System.out.println(pswd);
                    for (User user : Users.users) {
                       if (user.getPassword().equals(pswd) && user.getLogin().equals(login)) {
                           if(!user.isBanned()) {
                               userLogin = user.getLogin();
                               userStatus = true;
                               user.setStatus(true);
                               ServerGUI.userModel.updateModeltrue(login);
                               System.out.println(user.getLogin());
                               ps.println("true");
                               ps.flush();
                               answer = true;
                           }else
                                ps.println("ban");
                                ps.flush();
                                answer = true;
                       }
                   }
                   if (!answer) {
                       ps.println("false");
                       ps.flush();
                   } else
                       answer = false;
               }if(message.equals("new")){
                        String login = br.readLine();
                        String pswd = br.readLine();
                        Users.users.add(new User(login, pswd));
                        ServerGUI.userModel.addAndUpdate(login, pswd);
                        System.out.println("new user " + login);
                        DBManager<User> userDBManager= new DBManager<>(User.class);
                        for(User user : Users.users){
                            userDBManager.insert(user);
                        }
                    }
                }
        } catch (IOException e1) {
               System.out.println("Пользователь отключен");
               for (User user : Users.users){
                   if(user.getLogin().equals(userLogin) && user.isStatus()==userStatus){
                       user.setStatus(false);
                       ServerGUI.userModel.updateModelfalse(userLogin);
                   }
               }
           }
    }

    ClientThread(int count, Socket socket, ReadWriteLock lock) {
        this.socket=socket;
        int ID = count;
        Documents documents = new Documents(lock);
        Thread thread = new Thread(this);
        thread.start();
    }

}
