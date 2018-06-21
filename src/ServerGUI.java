import javax.print.Doc;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;



public class ServerGUI extends JFrame {


    private ReadWriteLock lock;
//    private int ammount;

    public Documents documents;
    public Commands commands;

    ServerGUI(ReadWriteLock lock) {

        super("Server");
        this.lock = lock;

        documents = new Documents(lock);
        commands = new Commands(lock);

        try {
            commands.load(Commands.link);
        } catch (IOException e) {
            e.printStackTrace();
        }
        TableModel tModel = new TableModel(Commands.set.copyOnWriteArraySet);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1400, 500);
        getContentPane().setLayout(new FlowLayout());

        Container container = this.getContentPane();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));

        TextField txt = new TextField(75);
        JPanel txtPanel = new JPanel();
        txtPanel.add(txt);
        txtPanel.add(new Label("InfoTextField"));

        TextField name = new TextField(10);
        JPanel namePanel = new JPanel();
        txtPanel.add(name);
        txtPanel.add(new Label("Name"));
//        NumberFormat format = NumberFormat.getNumberInstance();
//        JFormattedTextField age = new JFormattedTextField(format);
//        age.setValue(ammount);
//        age.setColumns(10);
        JPanel agePanel = new JPanel();
        JSlider ageSlider = new JSlider(0, 100, 0);
        ageSlider.setPaintTicks(true);
        ageSlider.setPaintLabels(true);
        ageSlider.setMajorTickSpacing(20);
        ageSlider.setMinorTickSpacing(5);
        txtPanel.add(ageSlider);
        txtPanel.add(new Label("Age"));

//        TextField colour = new TextField( 10);
//        JPanel colourPanel = new JPanel();
//        txtPanel.add(colour);
//        txtPanel.add(new Label("Colour"));
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

        JButton addButton = new JButton("ADD");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Commands.set.copyOnWriteArraySet.add(new Policeman(name.getText(), ageSlider.getValue(), colourComboBox.getSelectedItem().toString()));
                tModel.updateModel(name.getText(), ageSlider.getValue(), colourComboBox.getSelectedItem().toString());
//                table.repaint();
//                TableModel tableModel2 = new TableModel(Commands.set.copyOnWriteArraySet);
////                JTable table2 = new JTable(tableModel2);
////                JScrollPane jScrollPane2 = new JScrollPane(table2);
////                table2.setPreferredScrollableViewportSize(new Dimension(400, 100));
////                container.add(jScrollPane2);
////                container.remove(table);
////                container.remove(jScrollPane);
////                container.repaint();
            }
        });



        JMenuItem help = new JMenuItem("Help");
        mainMenu.add(help);
        help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txt.setText(commands.help());
            }
        });

        JMenuItem info = new JMenuItem("Info");
        mainMenu.add(info);
        info.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txt.setText(commands.info());
            }
        });

        JMenuItem save = new JMenuItem("Save");
        mainMenu.add(save);
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    txt.setText(commands.save());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        JMenuItem load = new JMenuItem("Load");
        mainMenu.add(load);
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    txt.setText(commands.load(Commands.link));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        menuBar.add(mainMenu);




        container.add(txtPanel);
        container.add(namePanel);
        container.add(agePanel);
        container.add(addButton);
        container.add(jScrollPane);


        setJMenuBar(menuBar);
        setVisible(true);
    }
}




