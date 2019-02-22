package task1;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task19 {

    //main
    private JFrame mainFrame;
    private Container mainPanel;


    private JButton center;

    //form
    private JPanel formPanel;
    private JTextField email;

    private JLabel emailLabel;



    public void run() {
        mainFrame = new JFrame("Hello World");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainPanel = mainFrame.getContentPane();
        mainPanel.setLayout(new BorderLayout());
        JButton register = new JButton("get element");
        final JTextField xpath = new JTextField();
        register.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                String url = email.getText();
                String path = xpath.getText();

                XPathTest xPathTest = new XPathTest();
                try {
                    xPathTest.loadDocumetFromUrl(url);
                    xPathTest.loadExpression(path);
                    xPathTest.nodeSetProccesing();
                    JOptionPane.showMessageDialog(mainPanel, xPathTest.nodeSetProccesing());
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        //form
        formPanel = new JPanel();
        formPanel.setOpaque(false);
        email = new JTextField();
        emailLabel = new JLabel("Path");
        emailLabel.setLabelFor(email);
        formPanel.setLayout(new GridBagLayout());
        JLabel path = new JLabel("xpath");

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.BASELINE_LEADING;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(4, 4, 4, 4);


        gridBagConstraints.gridwidth = GridBagConstraints.RELATIVE;
        formPanel.add(emailLabel, gridBagConstraints);
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.weightx = 1;
        formPanel.add(email, gridBagConstraints);

        gridBagConstraints.gridwidth = GridBagConstraints.RELATIVE;
        gridBagConstraints.weightx = 0;
        formPanel.add(path, gridBagConstraints);
        gridBagConstraints.weightx = 1;
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        formPanel.add(xpath, gridBagConstraints);
        gridBagConstraints.gridwidth = GridBagConstraints.RELATIVE;
        gridBagConstraints.weightx = 0;
        formPanel.add(register, gridBagConstraints);

        mainFrame.pack();
        mainFrame.setMinimumSize(new Dimension(300, 300));
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainFrame.setVisible(true);

    }

    public static void main(String[] args) {
        Task19 frame = new Task19();
        frame.run();
    }



}
