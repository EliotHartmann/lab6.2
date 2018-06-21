import java.io.*;
import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.locks.ReadWriteLock;

public class Commands{

    static public ConcurrentPolicemanSet<Policeman> set = new ConcurrentPolicemanSet<Policeman>(new Date());
    static String link = "C:\\Users\\Acer\\Desktop\\file.txt";
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
    public String thisCommand(String command) throws IOException{
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
    public String help(){
        return "info - Выводит информацию о состоянии коллекции load - Заново загружает коллекцию из файла import{path} -  Добавляет в коллекцию все данные из файла. save - Сохраняет коллекцию в файл\n stop - Останавливает работу программы";
    }

    /**
     * Реализация команды info
     * Выводит информацию о состоянии коллекции
     */
    public String info(){
        try{
            lock.readLock().lock();
            CopyOnWriteArraySet<Policeman> cps = set.copyOnWriteArraySet;
            return "Тип коллекции: "+cps.getClass()+ " Размер коллекции: "+cps.size() + " Дата: "+set.date;
        }
    finally {
            lock.readLock().unlock();
        }
    }



    /**
     * Реализация команды load
     * Заново загружает коллекцию из файла
     * @param link - путь до файла
     * @throws IOException
     */
    public String load(String link) throws IOException {
        try {
            lock.writeLock().lock();

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(link));
            while (true) {
                Policeman policeman = (Policeman) ois.readObject();
                set.copyOnWriteArraySet.add(policeman);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
            return "Объекты загружены";
        }

    }

    /**
     * Реализация команды import
     * Добавляет в коллекцию все данные из файла.
     * @param link - путь до файла
     * @throws IOException
     */
    public String importCollection(String link) throws IOException {
        try {
            lock.writeLock().lock();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(link));
            while (true) {
                Policeman policeman = (Policeman) ois.readObject();
                set.copyOnWriteArraySet.add(policeman);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
            return "Объекты из файла " + link + "загружены";
        }

    }

    /**
     * Реализация команды save
     * Сохраняет коллекцию в файл
     * @throws IOException
     */
    public String save() throws IOException{
        try{
        lock.readLock().lock();
            int count = 0;
        ObjectOutputStream ous = new ObjectOutputStream(new FileOutputStream(link));
        for (Policeman aLinkedHSPoliceman : set.copyOnWriteArraySet) {
            ous.writeObject(aLinkedHSPoliceman);
            count++;
        }
        ous.flush();
        ous.close();
        return "Сохранено " + count + " объектов";
        }finally {
            lock.readLock().unlock();
        }
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
//    public String[][] collintoarray(){
//        CopyOnWriteArraySet<Policeman> cps = set.copyOnWriteArraySet;
//        int n = cps.size();
//        String[][] a = new String[n][3];
//        Iterator<Policeman> iterator = cps.iterator();
//        for(int i =0; i<n; i++){
//            if (iterator.hasNext()){
//                Policeman policeman = iterator.next();
//                a[i][0] = policeman.getName();
//                a[i][1] = String.valueOf(policeman.getAge());;
//                a[i][2] = policeman.getDescribition();
//        }else
//            break;
//    }
//    return a;
//}
}
