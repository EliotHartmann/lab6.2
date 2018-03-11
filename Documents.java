import org.omg.CORBA.Object;

import java.io.*;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class Documents {
    Commands commands = new Commands();

    public void Begin() throws IOException {
        String link="C:\\Users\\User\\Desktop\\file.txt";
        commands.setLink(link);
        this.start(link);
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String string;
            for (; ; ) {
                string = br.readLine();
                if (string.trim().toLowerCase().equals("stop")) {
                    break;
                } else {
                    commands.thisCommand(string.toLowerCase());
                }
            }
            this.stop(link);
        }catch (NullPointerException e){
            this.stop(link);
        }
    }

    private void start(String link) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(link)));
        String thisLine;
        int count=0;
        while ((thisLine=bf.readLine())!=null){
            commands.set.linkedHSPoliceman.add(new WorkJSON().intoJSON(thisLine));
            count++;
        }
        System.out.println("Загружено "+count+" объектов");
    }

    private void stop(String link) throws IOException{
        String thisLine = "";
        FileOutputStream fos = new FileOutputStream(link);
        for (Policeman aLinkedHSPoliceman : commands.set.linkedHSPoliceman) {
            thisLine = (new WorkJSON().toJSON(aLinkedHSPoliceman)) + System.lineSeparator();

            try {
                byte[] buffer = thisLine.getBytes();
                fos.write(buffer, 0, buffer.length);
                fos.flush();
                System.out.println(thisLine);

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        }
        fos.close();

    }
}

