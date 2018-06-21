import javax.print.Doc;
import javax.swing.*;
import java.io.IOException;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Main {
    public static void main(String[] args) throws IOException {
        /*Spectator spectator = new Spectator();
        Shorty Зязя = new Shorty("Зязя");
        Shorty Зюзя = new Shorty("Зюзя");
        Shorty Зизя = new Shorty("Зизя");
        CinemaSeat cinemaSeat = new CinemaSeat();
        MovieShow movieShow = new MovieShow();
        Noize noize = new Noize();
        Light light = new Light();
        Battle battle = new Battle();
        CinemaRoom cinemaRoom = new CinemaRoom();

        System.out.println(Зязя.toThinkAboutTakingSeat());
        System.out.println(Зюзя.toThinkAboutTakingSeat());
        System.out.println(Зизя.toThinkAboutTakingSeat());

        System.out.println(Зязя.takeSeat());
        System.out.println(Зюзя.takeSeat());
        System.out.println(Зизя.takeSeat());

        System.out.println(cinemaRoom.startMovie());
        battle.startBattle();
        System.out.println(spectator.toScream());
        spectator.toWatchHorror();
        noize.setNoizeVolume(70);
        System.out.println(cinemaRoom.becomeQuiter());
        System.out.println(cinemaRoom.becomeQuiter());
        System.out.println(cinemaRoom.becomeQuiter());
        System.out.println(cinemaRoom.NoizeOff());

        System.out.println(Зязя.goToSleep());
        System.out.println(Зюзя.goToSleep());
        System.out.println(Зизя.goToSleep());

        spectator.getTired();
        System.out.println(spectator.goToSleep());
        System.out.println(spectator.wakeUp());*/
        //Documents documents = new Documents();
        //documents.Begin();

//        Commands.set.copyOnWriteArraySet.removeAll(Commands.set.copyOnWriteArraySet);
        ReadWriteLock lock = new ReentrantReadWriteLock();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ServerGUI(lock);
            }
        });
        Server server = new Server(9999);
        server.createServer();

        /*while (true){
            Documents documents = new Documents();
            documents.commands.workDatagram.receiveString();
            new ClientThread();
//        }*/
//        String link = "C:\\Users\\Acer\\Desktop\\file.txt";

//
//        System.out.println(commands.info());
//        commands.collintoarray();


//       documents.writeSomeObjects(link);

//
//        javax.swing.SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new ClientGUI(lock);
//            }
//        });
    }
}
