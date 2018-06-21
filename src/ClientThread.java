import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ClientThread implements Runnable{
    String string;
    private Socket socket;
    private Documents documents;
    private Commands commands;
    private WorkJSON workJSON = new WorkJSON();
    private int ID;
    private String login;
    private String pswd;
    public String answer;

    public String getLogin() {
        return login;
    }

    public String getPswd() {
        return pswd;
    }

    public void run(){
        try {
            try(
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                PrintStream ps = new PrintStream(socket.getOutputStream());
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))){
                int n = Commands.set.copyOnWriteArraySet.size();

                ArrayList<Policeman> policemen= new ArrayList<>(Commands.set.copyOnWriteArraySet);
                ps.println("start");
                ps.flush();
                System.out.println("start");
                ps.println(String.valueOf(n));
                ps.flush();
                System.out.println(n);
                for (int i=0; i<n; i++ ){
                    ps.println(workJSON.toJSON(policemen.get(i)));
                    System.out.println(workJSON.toJSON(policemen.get(i)));
                    ps.flush();
                }
                ps.println("stop");
                System.out.println("stop");
//                login = br.readLine();
//                pswd = br.readLine();
//                for (User user: User.users){
//                    if(user.getLogin().equals(login)&&user.getPassword().equals(pswd)){
//                        answer = "true";
//                        ps.println(answer);
//                        break;
//                    }
//                }
//                if(!answer.equals("true")){
//                    ps.println("false");
//                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    ClientThread(int count, Socket socket, ReadWriteLock lock) {
        this.socket=socket;
        ID = count;
        documents = new Documents(lock);
        Thread thread = new Thread(this);
        thread.start();
    }

}
