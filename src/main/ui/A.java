package ui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class A extends JFrame  implements ActionListener {

    private JButton addTeamButton;

    A() {

        Border border = BorderFactory.createLineBorder(Color.black, 3);

        JLabel label = new JLabel();
        ImageIcon scaledImage = new ImageIcon(new ImageIcon("data/football.jpg").getImage()
                .getScaledInstance(150, 150, Image.SCALE_DEFAULT));

        label.setText("Soccer League Manager");
        label.setIcon(scaledImage);

        //label color and fonts
        label.setForeground(Color.BLACK);
        label.setFont(new Font("MV Boli", Font.BOLD,25));

        //gap between icon and text
        label.setIconTextGap(0);

        //background of text
        label.setBackground(Color.white);
        label.setOpaque(true);

        label.setBorder(border);

        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);

        label.setBounds(0, 0,700, 180);






        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.red);
        buttonPanel.setBounds(0,180,350,520);

        JPanel teamPanel = new JPanel();
        teamPanel.setBackground(Color.black);
        teamPanel.setBounds(350, 180, 350,260);

        JLabel teamlabel = new JLabel("List of Teams");
        teamlabel.setFont(new Font("Times New Roman", Font.BOLD,14));

        teamPanel.add(teamlabel);

        JPanel matchPanel = new JPanel();
        matchPanel.setBackground(Color.green);
        matchPanel.setBounds(350, 440, 350, 260);

        JLabel matchLabel = new JLabel("Match Record");
        matchLabel.setFont(new Font("Times New Roman", Font.BOLD,14));

        matchPanel.add(matchLabel);


        addTeamButton = new JButton();
        addTeamButton.addActionListener(this);
        addTeamButton.setSize(200, 100);
        addTeamButton.setText("ADD TEAM");
        addTeamButton.setFont(new Font("Times New Roman", Font.BOLD,10));
        addTeamButton.setForeground(Color.cyan);
        addTeamButton.setBackground(Color.lightGray);
        addTeamButton.setBorder(BorderFactory.createEmptyBorder());

        buttonPanel.add(addTeamButton);



        this.setTitle("Soccer League");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(700,700);
        this.setVisible(true);
        this.setResizable(false);
        this.add(label);
        this.setLayout(null);

        this.add(buttonPanel);
        this.add(teamPanel);
        this.add(matchPanel);


        this.setIconImage(scaledImage.getImage());
        this.getContentPane().setBackground(Color.white);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addTeamButton) {
            System.out.println("A");
        }
    }
}
