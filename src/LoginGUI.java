//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.Arrays;
//
//public class LoginGUI extends JFrame{
//    LoginGUI(){
//            setSize(250, 200);
//            setTitle("Enter Login/Pass");
//            JTextField logintxt = new JTextField();
//            JPasswordField pwdtxt = new JPasswordField();
//            JLabel loginlbl = new JLabel("Login:");
//            JLabel pwdlbl = new JLabel("Password:");
//            JButton submitbtn = new JButton("Submit");
//            JPanel formPanel = new JPanel(new GridLayout(5,1));
//            formPanel.add(loginlbl);
//            formPanel.add(logintxt);
//            formPanel.add(pwdlbl);
//            formPanel.add(pwdtxt);
//            formPanel.add(submitbtn);
//            add(formPanel);
//            submitbtn.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    char[] pswd = pwdtxt.getPassword();
//
//                    for(User user:User.users){
//                        if(user.getLogin().equals(logintxt.getText()) && user.getPassword().equals(Arrays.toString(pswd))){
//
//                        }else
//
//                    }
//                }
//            });
//            setVisible(true);
//    }
//}
