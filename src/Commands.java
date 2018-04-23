import java.io.*;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.locks.ReadWriteLock;

public class Commands {

    public ConcurrentPolicemanSet set = new ConcurrentPolicemanSet(new Date());
    public String link;
    private ReadWriteLock lock;

    public Commands(ReadWriteLock lock) {
        this.lock=lock;
    }

    public void setLink(String link) {
        this.link = link;
    }

    /**Метод для определения типа команды
     *
     * @param command
     */
    public String thisCommand(String command) throws IOException {
        if (command.trim().equals("info")) {
            return this.info();
        } else if (command.trim().equals("load")) {
            return this.load(this.link);
        } else if (command.trim().equals("save")) {
            return this.save();
        } else if (command.trim().startsWith("import")) {
            String time = command.trim().substring("import".length(), command.trim().length()).trim();
            String time2 = time.substring(1, time.length() - 1).trim();
            return this.importCollection(time2);
        } else if (command.trim().equals("help")) {
            return this.help();
        } else
            return ("Команда не найдена");
    }


    /**
     * Реализация команды help
     * Выводин список команд и их предназначение
     */
    private String help(){
        return "info - Выводит информацию о состоянии коллекции\n load - Заново загружает коллекцию из файла\n import{path} -  Добавляет в коллекцию все данные из файла.\n save - Сохраняет коллекцию в файл\n stop - Останавливает работу программы";
    }

    /**
     * Реализация команды info
     * Выводит информацию о состоянии коллекции
     */
    private String info(){
        CopyOnWriteArraySet<Policeman> cps = set.copyOnWriteArraySet;
        return "Тип коллекции: "+cps.getClass()+ "\nРазмер коллекции: "+cps.size() + "\nДата: "+set.date;
    }

    /**
     * Реализация команды load
     * Заново загружает коллекцию из файла
     * @param link - путь до файла
     * @throws IOException
     */
    private String load(String link) throws IOException {
        try {
            lock.writeLock().lock();
            BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(link)));
            String thisLine;
            int count = 0;
            while ((thisLine = bf.readLine()) != null) {
                set.copyOnWriteArraySet.add(new WorkJSON().intoJSON(thisLine));
                count++;
            }
            return "Загружено в коллекцию " + count + " объектов";
        }finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * Реализация команды import
     * Добавляет в коллекцию все данные из файла.
     * @param link - путь до файла
     * @throws IOException
     */
    private String importCollection(String link) throws IOException {
        try {
            lock.writeLock().lock();
            BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(link)));
            String thisLine;
            int count = 0;
            while ((thisLine = bf.readLine()) != null) {
                set.copyOnWriteArraySet.add(new WorkJSON().intoJSON(thisLine));
                count++;
            }
            return "Загрузилось " + count + " объектов";
        }finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * Реализация команды save
     * Сохраняет коллекцию в файл
     * @throws IOException
     */
    private String save() throws IOException{
        String thisLine = "";
        int count = 0;
        FileOutputStream fos = new FileOutputStream(link);
        for (Policeman aLinkedHSPoliceman : this.set.copyOnWriteArraySet) {
            thisLine = System.lineSeparator()+ (new WorkJSON().toJSON(aLinkedHSPoliceman));

            try {
                byte[] buffer = thisLine.getBytes();
                fos.write(buffer, 0, buffer.length);
                fos.flush();
                } catch (IOException ex) {

                return ex.getMessage();
            }
            count++;
        }
        fos.close();
        return "Сохранено " + count + " объектов";
}

    public void sort(){
        try {lock.writeLock().lock();
            Set<Policeman> policemanSet = new TreeSet<Policeman>(new Comparator<Policeman>() {
                @Override
                public int compare(Policeman o1, Policeman o2) {
                    return o1.toString().compareTo(o2.toString());
                }
            });
            policemanSet.addAll(set.copyOnWriteArraySet);
            set.copyOnWriteArraySet.removeAll(policemanSet);
            set.copyOnWriteArraySet.addAll(policemanSet);
        }finally {
            lock.writeLock().unlock();
        }
    }
}
