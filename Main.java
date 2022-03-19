import java.awt.AWTException;
import java.awt.PopupMenu;
import java.awt.MenuItem;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Main {

    private static JFrame window;

    private static void createAndShowGUI() {
        String os = System.getProperty("os.name");
        String iconFilename = "calendar32.png";
        
        // Use higher-resolution icon if user is on a Mac
        if (os.startsWith("Mac")) {
            iconFilename = "calendar.png";
        }
        if (SystemTray.isSupported()) {
            SystemTray systemTray = SystemTray.getSystemTray();
            TrayIcon icon = null;
            try {
                icon = new TrayIcon(ImageIO.read(new File(iconFilename)));
            } catch (IOException e) {
                System.out.println("Error loading tray icon");
                return;
            }
            
            PopupMenu popupMenu = new PopupMenu();

            MenuItem showOption = new MenuItem("Show...");
            showOption.addActionListener(Main::show);
            popupMenu.add(showOption);

            MenuItem exitOption = new MenuItem("Exit");
            exitOption.addActionListener(Main::exit);
            popupMenu.add(exitOption);

            icon.setPopupMenu(popupMenu);
            try {
                systemTray.add(icon);
            } catch (AWTException e) {
                System.out.println("Unable to create tray icon!");
            }
        }

        window = new JFrame("System Tray Test");
        window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        Box horizontal = Box.createHorizontalBox();
        Box vertical = Box.createVerticalBox();
        
        horizontal.add(Box.createHorizontalStrut(10));
        horizontal.add(vertical);
        horizontal.add(Box.createHorizontalStrut(10));

        vertical.add(Box.createVerticalStrut(10));
        vertical.add(new JLabel("Try closing me!"));
        vertical.add(new JLabel("Then try to open me from the system tray."));
        vertical.add(Box.createVerticalStrut(10));

        window.add(horizontal);
        window.pack();
        window.setVisible(true);
    }

    private static void exit(ActionEvent e) {
        System.exit(0);
    }

    private static void show(ActionEvent e) {
        window.setVisible(true);
        window.toFront();
        window.requestFocus();
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::createAndShowGUI);
    }
}