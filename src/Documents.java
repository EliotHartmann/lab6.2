import java.io.*;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.*;
import java.util.concurrent.locks.ReadWriteLock;

public class Documents {
    private ReadWriteLock lock;
    public Commands commands;

    public Documents(ReadWriteLock lock){
        commands = new Commands(lock);
    }


    public String Begin(String string) throws IOException {
        String link="C:\\Users\\Acer\\Desktop\\file.txt";
        commands.setLink(link);
        this.start(link);
       try {
           System.out.println("Принята команда " + string);
            for (; ;) {
                if (string.trim().toLowerCase().equals("stop")) {
                    break;
                } else {
                    return(commands.thisCommand(string.toLowerCase()));
                }
            }
            commands.sort();
            System.exit(0);
            return (this.stop(link));
        }catch (NullPointerException e){
           return (this.stop(link));
        }
    }


    private String start(String link) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(link)));
        String thisLine;
        int count=0;
        while ((thisLine=bf.readLine())!=null){
            commands.set.copyOnWriteArraySet.add(new WorkJSON().intoJSON(thisLine));
            count++;
        }
       return("Загружено "+count+" объектов");
    }

    private String stop(String link) throws IOException{
        String thisLine = "";
        try {lock.writeLock().lock();
        FileOutputStream fos = new FileOutputStream(link);
        for (Policeman cps : commands.set.copyOnWriteArraySet) {
            thisLine = (new WorkJSON().toJSON(cps)) + System.lineSeparator();

            try {
                byte[] buffer = thisLine.getBytes();
                fos.write(buffer, 0, buffer.length);
                fos.flush();
                return thisLine;
            } catch (IOException ex) {
                return ex.getMessage();
            }
        }
        fos.close();
        return ("Остановка программы...");
    }finally {
            lock.writeLock().unlock();
        }
    }
}

