import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Circle extends Figure implements Runnable{
    MyFrame myFrame;
    FileWriter writer;
    private boolean running = true;
    private boolean paused = false;
    private final Object pauseLock = new Object();

    public void paint(Graphics g){ }

    public Circle() throws IOException {
        writer = new FileWriter("Circles.txt", false);
    }

    @Override
    public void run() {
        while (running) {
            synchronized (pauseLock) {
                if (!running) { // may have changed while waiting to
                    // synchronize on pauseLock
                    break;
                }
                if (paused) {
                    try {
                        synchronized (pauseLock) {
                            pauseLock.wait();
                        }
                    } catch (InterruptedException ex) {
                        break;
                    }
                    if (!running) { // running might have changed since we paused
                        break;
                    }
                }
            }
            int min = 30, max = 450;
            try {
                if (myFrame.getGraphics() != null) {
                    Thread.sleep(500);
                    Random random = new Random();
                    int randomX = min + random.nextInt(max - min);
                    int randomY = min + random.nextInt(max - min);
                    Graphics graphics = myFrame.getGraphics();
                    graphics.setColor(Color.RED);
                    graphics.drawOval(randomX, randomY, 25, 25);
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(randomX);
                    stringBuilder.append(" ");
                    stringBuilder.append(randomY);
                    stringBuilder.append("\r\n");
                    writer.write(stringBuilder.toString());
                }
            } catch (InterruptedException | IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void stopPainting() {
        running = false;
        // you might also want to interrupt() the Thread that is
        // running this Runnable, too, or perhaps call:
        unpause();
        // to unblock
    }

    public void pause() {
        // you may want to throw an IllegalStateException if !running
        paused = true;
    }

    public void unpause() {
        synchronized (pauseLock) {
            paused = false;
            pauseLock.notifyAll(); // Unblocks thread
        }
    }

    public void close() {
        try {
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean isPaused() {
        return paused;
    }
}
