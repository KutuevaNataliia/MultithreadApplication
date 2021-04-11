import javax.swing.*;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame {
    private JButton suspendCircleButton;
    private JPanel panel1;
    private JButton resumeCircleButton;
    private JButton suspendSquareButton;
    private JButton resumeSquareButton;

    public MyFrame() {
        setContentPane(panel1);
        setVisible(true);
        setSize(640, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void setSuspendSquareButton(ActionListener actionListener) {
        suspendSquareButton.addActionListener(actionListener);
    }

    public void setResumeSquareButton(ActionListener actionListener) {
        resumeSquareButton.addActionListener(actionListener);
    }

    public void setSuspendCircleButton(ActionListener actionListener) {
       suspendCircleButton.addActionListener(actionListener);
    }

    public void setResumeCircleButton(ActionListener actionListener) {
        resumeCircleButton.addActionListener(actionListener);
    }
}
