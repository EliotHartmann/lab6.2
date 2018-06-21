

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.locks.ReadWriteLock;

public class ClientGUI extends JFrame {


    ClientGUI(ReadWriteLock lock){
        super("ClientGUI");
        Commands commands = new Commands(lock);
        try {
            commands.load(Commands.link);
        } catch (IOException e) {
            e.printStackTrace();
        }

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel panel = new JPanel(null);
        panel.setSize(new Dimension(800, 800));
        panel.setBackground(Color.GRAY);
        Container container = this.getContentPane();

        int n = Commands.set.copyOnWriteArraySet.size();

        ArrayList<Policeman> policemen= new ArrayList<>(Commands.set.copyOnWriteArraySet);
        for (int i =0; i<n; i++){
            JButton button = new JButton();
            int x = 800-policemen.get(i).x1;
            int y = 800-policemen.get(i).y1;
            int w = policemen.get(i).x3-policemen.get(i).x1;
            int h = policemen.get(i).y2-policemen.get(i).y1;
            Colours colour = Colours.valueOf(policemen.get(i).getColour().toUpperCase());
            Rectangle bounds = new Rectangle(x, y, w, h);
            button.setBounds(bounds);
            button.setToolTipText("Name: "+ policemen.get(i).getName() + ", Age: " + policemen.get(i).getAge());
            switch(colour){
                case BLUE:
                    button.setBackground(Color.BLUE);
                    break;
                case GREEN:
                    button.setBackground(Color.GREEN);
                    break;
                case YELLOW:
                    button.setBackground(Color.YELLOW);
                    break;
                case PINK:
                    button.setBackground(Color.PINK);
                    break;
                default:
                    button.setBackground(Color.WHITE);
                    break;
            }
            panel.add(button);
        }

        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JCheckBox greenBox = new JCheckBox("Green");
        JCheckBox yellowBox = new JCheckBox("Yellow");
        JCheckBox blueBox = new JCheckBox("Blue");

        GridLayout layout = new GridLayout(1, 3, 5, 12);
        JPanel boxPanel = new JPanel();
        boxPanel.setLayout(layout);
        boxPanel.add(greenBox);
        boxPanel.add(yellowBox);
        boxPanel.add(blueBox);
        filterPanel.add(boxPanel);

        JSlider ageSlider = new JSlider(0, 100, 0);
        JPanel sliderPanel = new JPanel();
        sliderPanel.add(ageSlider);
        sliderPanel.add(new JLabel("Age"));
        filterPanel.add(sliderPanel);

        TextField name = new TextField(10);
        JPanel namePanel = new JPanel();
        namePanel.add(name);
        namePanel.add(new JLabel("Name"));
        filterPanel.add(namePanel);

        JButton startButton = new JButton("Start");
        startButton.addActionListener(e -> {

            panel.removeAll();
            if (greenBox.isSelected()){
                for (int i = 0; i<n; i++){
                    if (policemen.get(i).getName().equals(name.getText()) && policemen.get(i).getAge() == ageSlider.getValue()) {
                        JButton button = new JButton();
                        int x = 800-policemen.get(i).x1;
                        int y = 800-policemen.get(i).y1;
                        int w = policemen.get(i).x3-policemen.get(i).x1;
                        int h = policemen.get(i).y2-policemen.get(i).y1;
                        Colours colour = Colours.valueOf(policemen.get(i).getColour().toUpperCase());
                        Rectangle bounds = new Rectangle(x, y, w, h);
                        button.setBounds(bounds);
                        button.setToolTipText("Name: "+ policemen.get(i).getName() + ", Age: " + policemen.get(i).getAge());
                        button.setBackground(Color.RED);
                        panel.add(button);
                    }
                }
            }else if (blueBox.isSelected()){
                for (int i = 0; i<n; i++){
                    if (policemen.get(i).getName().equals(name.getText()) && policemen.get(i).getAge() == ageSlider.getValue()) {
                        JButton button = new JButton();
                        int x = 800-policemen.get(i).x1;
                        int y = 800-policemen.get(i).y1;
                        int w = policemen.get(i).x3-policemen.get(i).x1;
                        int h = policemen.get(i).y2-policemen.get(i).y1;
                        Colours colour = Colours.valueOf(policemen.get(i).getColour().toUpperCase());
                        Rectangle bounds = new Rectangle(x, y, w, h);
                        button.setBounds(bounds);
                        button.setToolTipText("Name: "+ policemen.get(i).getName() + ", Age: " + policemen.get(i).getAge());
                        button.setBackground(Color.RED);
                        panel.add(button);
                    }
            }

        }else if(yellowBox.isSelected()) {
                for (int i = 0; i < n; i++) {
                    if (policemen.get(i).getName().equals(name.getText()) && policemen.get(i).getAge() == ageSlider.getValue()) {
                        JButton button = new JButton();
                        int x = 800 - policemen.get(i).x1;
                        int y = 800 - policemen.get(i).y1;
                        int w = policemen.get(i).x3 - policemen.get(i).x1;
                        int h = policemen.get(i).y2 - policemen.get(i).y1;
                        Colours colour = Colours.valueOf(policemen.get(i).getColour().toUpperCase());
                        Rectangle bounds = new Rectangle(x, y, w, h);
                        button.setBounds(bounds);
                        button.setToolTipText("Name: " + policemen.get(i).getName() + ", Age: " + policemen.get(i).getAge());
                        button.setBackground(Color.RED);
                        panel.add(button);
                    }
                }
            }
            container.repaint();

        });
    filterPanel.add(startButton);
    filterPanel.setBounds(850, 0, 300, 200);
    setSize(new Dimension(900, 900));
    container.add(panel);
    container.add(filterPanel);
    container.setLayout(null);
    setVisible(true);

    }


}
