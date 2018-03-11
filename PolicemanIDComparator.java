import java.util.Comparator;

public class PolicemanIDComparator implements Comparator<Policeman> {
    @Override
    public int compare(Policeman a, Policeman b){
        return a.getID()-b.getID();
    }
}
