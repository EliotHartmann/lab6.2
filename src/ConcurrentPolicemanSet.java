import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

public class ConcurrentPolicemanSet<P> extends CopyOnWriteArraySet {
    Date date;
    CopyOnWriteArraySet<Policeman> copyOnWriteArraySet = new CopyOnWriteArraySet<>();
    ConcurrentPolicemanSet(Date date){
        this.date = date;
    };

    public void sort(CopyOnWriteArraySet<Policeman> cowAS){
        Set<Policeman> policemanSet = new TreeSet<Policeman>(new Comparator<Policeman>() {
            @Override
            public int compare(Policeman o1, Policeman o2) {
                return o1.toString().compareTo(o2.toString());
            }
        });
        policemanSet.addAll(cowAS);
    }
}


