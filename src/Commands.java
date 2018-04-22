import java.io.*;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.concurrent.CopyOnWriteArraySet;

public class Commands {
    public ConcurrentPolicemanSet set = new ConcurrentPolicemanSet(new Date());
    private String link;
    WorkDatagram workDatagram = new WorkDatagram("localhost", 1337);


    public void setLink(String link) {
        this.link = link;
    }

    /**Метод для определения типа команды
     *
     * @param command
     */
    public void thisCommand(String command) throws IOException {
        if (command.trim().equals("info")){
            this.info();
        }else if(command.trim().equals("load")){
            this.load(this.link);
        }else if(command.trim().equals("save")){
            this.save();
        }else if(command.trim().startsWith("import")){
            String time = command.trim().substring("import".length(),command.trim().length()).trim();
            String time2=time.substring(1,time.length()-1).trim();
            this.importCollection(time2);
        }else if (command.trim().equals("help")){
            this.help();
        }else System.out.println("Команда не найдена");
    }

    /**
     * Реализация команды help
     * Выводин список команд и их предназначение
     */
    private void help(){
        try {
            workDatagram.sendString("Команда info выводит информацию о коллекции \nКоманда load перечитывает коллекцию из файла \nКоманда save сохраняет коллекцию в файл \nКоманда import добавляет в коллекцию данные из файла");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    /**
     * Реализация команды info
     * Выводит информацию о состоянии коллекции
     */
    private void info() throws UnknownHostException{
        CopyOnWriteArraySet<Policeman> cps = set.copyOnWriteArraySet;
        workDatagram.sendString("Тип коллекции: "+cps.getClass()+ "\nРазмер коллекции: "+cps.size() + "\nДата: "+set.date);
    }

    /**
     * Реализация команды load
     * Заново загружает коллекцию из файла
     * @param link - путь до файла
     * @throws IOException
     */
    private void load(String link) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(link)));
        String thisLine;
        int count=0;
        while ((thisLine=bf.readLine())!=null){
            set.copyOnWriteArraySet.add(new WorkJSON().intoJSON(thisLine));
            count++;
        }
        workDatagram.sendString("Загружено в коллекцию "+count+" объектов");
    }

    /**
     * Реализация команды import
     * Добавляет в коллекцию все данные из файла.
     * @param link - путь до файла
     * @throws IOException
     */
    private void importCollection(String link) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(link)));
        String thisLine;
        int count=0;
        while ((thisLine=bf.readLine())!=null){
            set.copyOnWriteArraySet.add(new WorkJSON().intoJSON(thisLine));
            count++;
        }
        workDatagram.sendString("Загрузилось "+count+" объектов");
    }

    /**
     * Реализация команды save
     * Сохраняет коллекцию в файл
     * @throws IOException
     */
    private void save() throws IOException{
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
                workDatagram.sendString(ex.getMessage());
            }
            count++;
        }
        fos.close();
        workDatagram.sendString("Сохранено " + count + " объектов");
}}
