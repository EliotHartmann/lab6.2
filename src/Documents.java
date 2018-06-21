import java.io.*;
import java.util.concurrent.locks.ReadWriteLock;

public class Documents {
    private ReadWriteLock lock;
    public Commands commands;

    public Documents(ReadWriteLock lock){
        this.lock=lock;
        commands = new Commands(lock);
    }


    public String Begin(String string) throws IOException {
        String link = "C:\\Users\\Acer\\Desktop\\file.txt";
        commands.setLink(link);
        //return this.writeSomeObjects(link);

        this.start(link);
       try {
           System.out.println("Принята команда " + string);
                if (string.trim().toLowerCase().equals("stop")) {
                } else {
                    return(commands.thisCommand(string.toLowerCase()));
                }
            commands.sort();
            return (this.stop(link));
        }catch (NullPointerException e){
           return (this.stop(link));
        }catch (FileNotFoundException e){
           System.out.println("Файл не найден");
           return "0";
       }
    }

    public String writeSomeObjects(String link) throws IOException {
        ObjectOutputStream ous = new ObjectOutputStream(new FileOutputStream(link));
        ous.writeObject(commands.set.copyOnWriteArraySet.add(new Policeman("Андрей", 22, "abc")));
        ous.flush();
        ous.writeObject(commands.set.copyOnWriteArraySet.add(new Policeman("Иван", 32, "abcа")));
        ous.flush();
        ous.writeObject(commands.set.copyOnWriteArraySet.add(new Policeman("Павел", 42, "abcab")));
        ous.close();
        return "Объекты загружены";
    }

    private String start(String link) throws IOException {

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(link));
        int count=0;
        try {
            while (true){
                Policeman policeman = (Policeman) ois.readObject();
                commands.set.copyOnWriteArraySet.add(policeman);
                count++;
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally{
            return("Загружено "+count+" объектов");
        }
    }

    private String stop(String link) throws IOException{
        try {lock.readLock().lock();
        ObjectOutputStream ous = new ObjectOutputStream(new FileOutputStream(link));
            try {
            for (Policeman cps : commands.set.copyOnWriteArraySet) {
            ous.writeObject(cps);
            ous.flush();
            ous.close();
        }
            } catch (IOException ex) {
                return ex.getMessage();
            }
        }finally {
            lock.readLock().unlock();
            return ("Остановка программы...");
        }

    }
}

