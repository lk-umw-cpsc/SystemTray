import javax.swing.JFrame;
import java.awt.AWTException;
import java.awt.Graphics;
import java.awt.PopupMenu;
import java.awt.MenuItem;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SystemTrayManager {

    private JFrame appFrame;

    private TrayIcon trayIcon;

    private BufferedImage iconImage;
    private BufferedImage iconWithExclam;

    public SystemTrayManager(String icon16, String iconLarge, JFrame appFrame) {
        this.appFrame = appFrame;
        String iconFilename = icon16;
        String os = System.getProperty("os.name");
        if (os.startsWith("Mac")) {
            // iconFilename = iconLarge;
        }

        SystemTray systemTray = SystemTray.getSystemTray();

        try {
            trayIcon = new TrayIcon(iconImage = ImageIO.read(new File(iconFilename)));
            BufferedImage exclam = ImageIO.read(new File("exclam.png"));
            
            iconWithExclam = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
            Graphics g = iconWithExclam.getGraphics();
            g.drawImage(iconImage, 0, 0, null);
            g.drawImage(exclam, 0, 0, null);
        } catch (IOException e) {
            System.out.println("Error loading tray icon");
            return;
        }
        
        PopupMenu popupMenu = new PopupMenu();

        MenuItem showOption = new MenuItem("Show...");
        showOption.addActionListener(this::showChosen);
        popupMenu.add(showOption);

        MenuItem displayOption = new MenuItem("Show notification");
        displayOption.addActionListener(this::displayChosen);
        popupMenu.add(displayOption);

        MenuItem swapOption = new MenuItem("Swap tray icon");
        swapOption.addActionListener(this::swapIconChosen);
        popupMenu.add(swapOption);

        popupMenu.addSeparator();

        MenuItem exitOption = new MenuItem("Exit");
        exitOption.addActionListener(this::exitChosen);
        popupMenu.add(exitOption);

        trayIcon.setPopupMenu(popupMenu);
        try {
            systemTray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println("Unable to create tray icon!");
        }
    }
    
    public void showNotification(String title, String message) {
        trayIcon.displayMessage(title, message, MessageType.INFO);
    }

    private void showChosen(ActionEvent e) {
        appFrame.setVisible(true);
    }

    private void displayChosen(ActionEvent e) {
        showNotification("Hello again!", "Here's another notification.");
    }

    private void exitChosen(ActionEvent e) {
        appFrame.dispose();
        System.exit(0);
    }

    private void swapIconChosen(ActionEvent e) {
        trayIcon.setImage(iconWithExclam);
    }
}
