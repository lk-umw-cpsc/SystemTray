import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainFrame extends JFrame {

    public MainFrame() {
        super("System Tray Demo");

        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        Box contentBox = Box.createVerticalBox();
        Box outerBox = Box.createHorizontalBox();

        add(outerBox);

        outerBox.add(Box.createHorizontalStrut(12));
        outerBox.add(contentBox);
        outerBox.add(Box.createHorizontalStrut(12));

        contentBox.add(Box.createVerticalStrut(12));
        contentBox.add(new JLabel("System Tray Demo"));
        contentBox.add(Box.createVerticalStrut(12));

        contentBox.setPreferredSize(new Dimension(200, 200));

        pack();
    }
    
}
