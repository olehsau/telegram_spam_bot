import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class InstructionWindow {
    public static JFrame frame;
    public static void show(){
        frame = new JFrame("next step");
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,300);
        JPanel panel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(panel,BoxLayout.Y_AXIS);
        panel.setLayout(boxLayout);
        panel.setBorder(new EmptyBorder(new Insets(30, 30, 20, 30)));
        panel.setBackground(Color.LIGHT_GRAY);
        JLabel label = new JLabel("Now make these simple steps:");
        JLabel step1 = new JLabel("1. Log in");
        JLabel step2 = new JLabel("2. Open any chat");
        JLabel step3 = new JLabel("3. Open members list");
        JLabel step4 = new JLabel("4. Program will start automatically after members list is detected");
        panel.add(label);panel.add(Box.createRigidArea(new Dimension(0,20)));
        panel.add(step1);panel.add(Box.createRigidArea(new Dimension(0,20)));
        panel.add(step2);panel.add(Box.createRigidArea(new Dimension(0,20)));
        panel.add(step3);panel.add(Box.createRigidArea(new Dimension(0,20)));
        panel.add(step4);panel.add(Box.createRigidArea(new Dimension(0,20)));
        frame.add(panel);
        frame.setVisible(true);
    }

    public static void close(){
        InstructionWindow.frame.setVisible(false);
    }
}
