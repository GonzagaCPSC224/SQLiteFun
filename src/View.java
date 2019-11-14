import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
    Controller controller;
    JList jList;

    public View(Controller c) {
        super("Contact Database Viewer");
        controller = c;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 400));
        setVisible(true);
        setupUI();
        pack();
    }

    private void setupUI() {
        jList = new JList();
        JScrollPane scrollPane = new JScrollPane(jList);
        getContentPane().add(scrollPane);
    }
}
