import java.io.IOException;
import java.net.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Server {
private int port;

    Server(int port){
        this.port = port;
    }
    public void createServer(){
        try(ServerSocket serverSocket = new ServerSocket(port,0,InetAddress.getLocalHost())){
            System.out.println("Server has been started");
            ReadWriteLock lock = new ReentrantReadWriteLock();
            int ID=0;
            while(true){
                Socket socket = serverSocket.accept();
                System.out.println("Новый пользователь - "+ ID);
                new ClientThread(ID, socket, lock);
                ID++;
            }
        }catch (UnknownHostException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
