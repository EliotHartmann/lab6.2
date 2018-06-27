import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.CopyOnWriteArraySet;

public class UserTM extends AbstractTableModel{

        private ArrayList<User> users;
        UserTM(HashSet<User> users){
            super();
            this.users = new ArrayList<>(users);
        }


        public String getColumnName(int c) {
            String result = "";
            switch (c) {
                case 0:
                    result = "Login";
                    break;
                case 1:
                    result =  "Status";
                    break;
            }
            return result;
        }



        @Override
        public int getRowCount() {
            return users.size();
        }

        @Override
        public int getColumnCount() {
            return 2;
        }

        @Override
        public Object getValueAt(int r, int c) {
            switch (c) {
                case 0:
                    return users.get(r).getLogin();
                case 1:
                    return users.get(r).isStatus();
                default:
                    return "";
            }
        }

        public void updateModeltrue(String login){
            for(User user: users){
                if(user.getLogin().equals(login))
                    user.setStatus(true);
            }
            fireTableDataChanged();
        }
    public void updateModelfalse(String login){
        for(User user: users){
            if(user.getLogin().equals(login))
                user.setStatus(false);
        }
        fireTableDataChanged();
    }

        public void addAndUpdate(String l, String s){
            users.add(new User(l, s));
            fireTableDataChanged();
        }
    }


