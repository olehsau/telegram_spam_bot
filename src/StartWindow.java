import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartWindow {
    public static JTextArea message1; public static JTextArea message2; public static JTextArea message3;
    public static JTextArea message4; public static JTextArea message5;
    public static JFrame frame;
    public static JPanel panel;
    static void show(){
        frame = new JFrame("Telegram Spam Bot");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,700);
        panel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(panel,BoxLayout.Y_AXIS);
        panel.setLayout(boxLayout);
        panel.setBorder(new EmptyBorder(new Insets(30, 30, 20, 30)));
        panel.setBackground(Color.LIGHT_GRAY);
        //JLabel logo = new JLabel("telegram_spam_bot");
        message1 = new JTextArea("message1",4,30);
        message2 = new JTextArea("message2",4,30);
        message3 = new JTextArea("message3",4,30);
        message4 = new JTextArea("message4",4,30);
        message5 = new JTextArea("message5",4,30);
        JButton startButton = new JButton("Start");
        JButton stopButton = new JButton("Stop and exit");
        startButton.addActionListener(new startActionListener());
        stopButton.addActionListener(new StopActionListener());
        //panel.add(logo);panel.add(Box.createRigidArea(new Dimension(0,15)));
        panel.add(message1);panel.add(Box.createRigidArea(new Dimension(0,20)));
        panel.add(message2);panel.add(Box.createRigidArea(new Dimension(0,20)));
        panel.add(message3);panel.add(Box.createRigidArea(new Dimension(0,20)));
        panel.add(message4);panel.add(Box.createRigidArea(new Dimension(0,20)));
        panel.add(message5);panel.add(Box.createRigidArea(new Dimension(0,20)));
        panel.add(startButton);panel.add(Box.createRigidArea(new Dimension(0,20)));
        panel.add(stopButton);panel.add(Box.createRigidArea(new Dimension(0,20)));
        frame.add(panel);
        frame.setVisible(true);
    }
}

class startActionListener implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        Main.messagesList.add(StartWindow.message1.getText());
        Main.messagesList.add(StartWindow.message2.getText());
        Main.messagesList.add(StartWindow.message3.getText());
        Main.messagesList.add(StartWindow.message4.getText());
        Main.messagesList.add(StartWindow.message5.getText());

    }
}

class StopActionListener implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}