import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.locks.ReadWriteLock;



    public class ServerGUI extends JFrame {
    public Documents documents;
    public Commands commands;
    public static UserTM userModel = new UserTM(Users.users);
    DBManager<Policeman> policemanDBManager = new DBManager(Policeman.class);
    int count = 0;



        ServerGUI(ReadWriteLock lock) {
        super("Server");
        ResourceBundle ruBundle = ResourceBundle.getBundle("resource.resource", new Locale("ru", "RU"));
        ReadWriteLock lock1 = lock;

        documents = new Documents(lock);
        commands = new Commands(lock);
        TableModel tModel = new TableModel(Commands.set.copyOnWriteArraySet);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1400, 600);

        Container container = this.getContentPane();
        container.setLayout(null);

        JPanel txtPanel = new JPanel();
        txtPanel.setBounds(20, 20, 1400, 100);
        txtPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        TextField name = new TextField(10);
        JPanel namePanel = new JPanel();
        txtPanel.add(name);
        txtPanel.add(new Label("Name"));

        JPanel agePanel = new JPanel();
        JSlider ageSlider = new JSlider(0, 100, 0);
        ageSlider.setPaintTicks(true);
        ageSlider.setPaintLabels(true);
        ageSlider.setMajorTickSpacing(20);
        ageSlider.setMinorTickSpacing(5);
        txtPanel.add(ageSlider);
        txtPanel.add(new Label("Age"));

        String[] colours = {
                "YELLOW",
                "BLUE",
                "GREEN"
        };

        JComboBox colourComboBox = new JComboBox(colours);
        txtPanel.add(colourComboBox);
        JTable table = new JTable(tModel);
        JMenuBar menuBar = new JMenuBar();
        JMenu mainMenu = new JMenu("Collection control");
        JScrollPane jScrollPane = new JScrollPane(table);
        table.setPreferredScrollableViewportSize(new Dimension(400, 200));
        JTable usersTable = new JTable(userModel);
        JScrollPane jScrollPane2 = new JScrollPane(usersTable);
        usersTable.setPreferredScrollableViewportSize(new Dimension(400, 200));
        JPanel pTable = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pTable.setBounds(20, 200, 700, 400);


        JButton addButton = new JButton("ADD");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Commands.set.copyOnWriteArraySet.add(
                        new PolicemanBuilder().name(name.getText())
                                .ID_Policeman(count+1)
                                .age(ageSlider.getValue())
                                .colour(colourComboBox.getSelectedItem().toString())
                                .x1(-300 + (int)(Math.random()*600))
                                .y1(-300 + (int)(Math.random()*600))
                                .height((int)(Math.random()*200))
                                .width((int)(Math.random()*200))
                                .createTime()
                                .build()
                                );
                count++;
                tModel.updateModel(name.getText(), ageSlider.getValue(), colourComboBox.getSelectedItem().toString());
            }
        });

        pTable.add(jScrollPane);
        txtPanel.add(addButton);


        JMenuItem help = new JMenuItem("Help");
        mainMenu.add(help);
        help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyDialog helpDialog = new MyDialog(ServerGUI.this, commands.help());
                helpDialog.setVisible(true);
            }
        });

        JMenuItem info = new JMenuItem("Info");
        mainMenu.add(info);
        info.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyDialog infoDialog = new MyDialog(ServerGUI.this, commands.info());
                infoDialog.setVisible(true);
            }
        });

        JMenuItem save = new JMenuItem("Save");
        mainMenu.add(save);
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    for (Policeman policeman : Commands.set.copyOnWriteArraySet)
                    policemanDBManager.insert(policeman);
            }
        });

        JMenuItem load = new JMenuItem("Load");
        mainMenu.add(load);
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    MyDialog loadDialog = new MyDialog(ServerGUI.this,commands.load(Commands.link));
                    loadDialog.setVisible(true);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        JMenu language = new JMenu("Language");
        JMenuItem ru = new JMenuItem(ruBundle.getString("Language"));
        language.add(ru);
        mainMenu.add(language);

        TextField login = new TextField(10);
        JLabel loginL = new JLabel("login");
        JPanel loginPanel = new JPanel();

        loginPanel.add(login);
        loginPanel.add(loginL);


        JButton banButton = new JButton("ban");
        JPanel banPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        banPanel.setBounds(740, 200, 700, 400);
        banPanel.add(jScrollPane2);
        banPanel.add(loginPanel);
        banPanel.add(banButton);

            ru.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    loginPanel.remove(loginL);
                    loginPanel.add(new JLabel(ruBundle.getString("ban")));
                    container.revalidate();
                    container.repaint();

                }
            });


            banButton.addActionListener(e -> {
            String alogin = login.getText();
            for(User user: Users.users){
                if(user.getLogin().equals(alogin)){
                    user.setBanned(true);
                }
            }
        });

        menuBar.add(mainMenu);

        container.add(txtPanel);
        container.add(namePanel);
        container.add(agePanel);
        container.add(pTable);
        container.add(banPanel);
        setJMenuBar(menuBar);
        setVisible(true);
    }
}




