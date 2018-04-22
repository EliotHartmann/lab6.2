import org.omg.CORBA.Object;

import java.io.*;
import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

public class Documents {
    private Commands commands = new Commands();

    public void Begin() throws IOException {
        String link="C:\\Users\\Acer\\Desktop\\file.txt";
        commands.setLink(link);
        this.start(link);
        this.sort();
        try {
            String string;
            for (; ; ) {
                string = commands.workDatagram.receiveString();
                System.out.println("Команда принята");
                if (string.trim().toLowerCase().equals("stop")) {
                    break;
                } else {
                    commands.thisCommand(string.toLowerCase());
                }
            }
            this.sort();
            this.stop(link);
        }catch (NullPointerException e){
            this.stop(link);
        }
    }

    private void sort(){
        Set<Policeman> policemanSet = new TreeSet<Policeman>(new Comparator<Policeman>() {
            @Override
            public int compare(Policeman o1, Policeman o2) {
                return o1.toString().compareTo(o2.toString());
            }
        });
        policemanSet.addAll(commands.set.copyOnWriteArraySet);
        commands.set.copyOnWriteArraySet.removeAll(policemanSet);
        commands.set.copyOnWriteArraySet.addAll(policemanSet);
    }

    private void start(String link) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(link)));
        String thisLine;
        int count=0;
        while ((thisLine=bf.readLine())!=null){
            commands.set.copyOnWriteArraySet.add(new WorkJSON().intoJSON(thisLine));
            count++;
        }
        commands.workDatagram.sendString("Загружено "+count+" объектов");
    }

    private void stop(String link) throws IOException{
        String thisLine = "";
        FileOutputStream fos = new FileOutputStream(link);
        for (Policeman cps : commands.set.copyOnWriteArraySet) {
            thisLine = (new WorkJSON().toJSON(cps)) + System.lineSeparator();

            try {
                byte[] buffer = thisLine.getBytes();
                fos.write(buffer, 0, buffer.length);
                fos.flush();
                commands.workDatagram.sendString(thisLine);
            } catch (IOException ex) {
                commands.workDatagram.sendString(ex.getMessage());
            }

        }
        fos.close();

    }
}

