import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.concurrent.locks.ReadWriteLock;

public class ClientThread implements Runnable{
    String string;
    private Socket socket;
    private Documents documents;
    private int ID;
    public void run(){
        try {
            try(DataInputStream in = new DataInputStream(socket.getInputStream()); DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {
                try {
                    while (true){
                        out.writeUTF(documents.Begin(in.readUTF()));
                        out.flush();
                    }
                }catch (SocketException e){
                System.err.println("Пользователь : "+ID+" отключён");
            }
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
