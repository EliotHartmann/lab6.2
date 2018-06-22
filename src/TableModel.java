import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class TableModel extends AbstractTableModel{

    private ArrayList<Policeman> policemen;
    TableModel(CopyOnWriteArraySet<Policeman> policemanhashset){
        super();
        this.policemen = new ArrayList<>(policemanhashset);
    }


    public String getColumnName(int c) {
        String result = "";
        switch (c) {
            case 0:
                result = "Name";
                break;
            case 1:
                result =  "Age";
                break;
            case 2:
                result = "Colour";
                break;
        }
        return result;
    }



    @Override
    public int getRowCount() {
        return policemen.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int r, int c) {
        switch (c) {
            case 0:
                return policemen.get(r).getName();
            case 1:
                return policemen.get(r).getAge();
            case 2:
                return policemen.get(r).getColour();
            default:
                return "";
        }
    }
    public void updateModel(String n, int a, String c){
        policemen.add(new PolicemanBuilder().name(n).age(a).colour(c).build());
        fireTableDataChanged();
    }
}
