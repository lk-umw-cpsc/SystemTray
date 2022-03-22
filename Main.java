import java.awt.SystemTray;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JFrame;

import javax.swing.SwingUtilities;

public class Main {

    private static volatile SystemTrayManager trayManager;

    private static void createAndShowGUI() {
        JFrame appFrame = new MainFrame();
        trayManager = new SystemTrayManager("calendar32.png", "calendar.png", appFrame);
    }
    
    public static void main(String[] args) throws InvocationTargetException, InterruptedException {
        if (!SystemTray.isSupported()) {
            System.out.println("System does not support system tray. Exiting.");
        }

        SwingUtilities.invokeAndWait(Main::createAndShowGUI);
        trayManager.showNotification("Hello, world!", "I'm a notification :)");
    }
}