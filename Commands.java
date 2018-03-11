import java.io.*;
import java.util.Date;
import java.util.LinkedHashSet;

public class Commands {
    public LinkedHSPolicemen set = new LinkedHSPolicemen(new Date());
    private String link;

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
        System.out.println("Команда info выводит информацию о коллекции \nКоманда load перечитывает коллекцию из файла \nКоманда save сохраняет коллекцию в файл \nКоманда import добавляет в коллекцию данные из файла");
    }

    /**
     * Реализация команды info
     * Выводит информацию о состоянии коллекции
     */
    private void info(){
        LinkedHashSet linked = set.linkedHSPoliceman;
        System.out.println("Тип коллекции: "+linked.getClass()+"\nКоличество элементов: "+linked.size()+"\nДата: "+set.date);
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
            set.linkedHSPoliceman.add(new WorkJSON().intoJSON(thisLine));
            count++;
        }
        System.out.println("Загружено в коллекцию "+count+" объектов");
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
            set.linkedHSPoliceman.add(new WorkJSON().intoJSON(thisLine));
            count++;
        }
        System.out.println("Загрузилось "+count+" объектов");
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
        for (Policeman aLinkedHSPoliceman : this.set.linkedHSPoliceman) {
            thisLine = System.lineSeparator()+ (new WorkJSON().toJSON(aLinkedHSPoliceman));

            try {
                byte[] buffer = thisLine.getBytes();
                fos.write(buffer, 0, buffer.length);
                fos.flush();
                } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            count++;
        }
        fos.close();
        System.out.println("Сохранено " + count + " объектов");
}}
