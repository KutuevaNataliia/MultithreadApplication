
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        MyFrame frame = new MyFrame();

        try {
            Circle circle = new Circle();
            circle.myFrame = frame;
            Thread childTread = new Thread(circle);
            childTread.start();
            try {
                Square square = new Square();
                square.myFrame = frame;

                frame.addWindowListener(new java.awt.event.WindowAdapter(){
                    @Override
                    public void windowClosing(WindowEvent windowEvent) {
                        circle.close();
                        square.close();
                    }
                });

                square.start();

                ActionListener actionListener = new ActionListener() {
                    MyException myException = null;
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        String action = event.getActionCommand();
                        switch (action) {
                            case "SuspendSquare":
                            {
                                square.pause();
                                if (circle.isPaused()) {
                                    myException = new MyException();
                                }
                                break;
                            }
                            case "ResumeSquare":
                            {
                                square.unpause();
                                if (myException != null) {
                                    System.out.println(myException.getMessage());
                                    myException = null;
                                }
                                break;
                            }
                            case "SuspendCircle":
                            {
                                circle.pause();
                                if (square.isPaused()) {
                                    myException = new MyException();
                                }
                                break;
                            }
                            case "ResumeCircle":
                            {
                                circle.unpause();
                                if (myException != null) {
                                    System.out.println(myException.getMessage());
                                    myException = null;
                                }
                                break;
                            }
                        }
                    }

                };
                frame.setSuspendSquareButton(actionListener);
                frame.setResumeSquareButton(actionListener);
                frame.setSuspendCircleButton(actionListener);
                frame.setResumeCircleButton(actionListener);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
