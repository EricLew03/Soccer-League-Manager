package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame implements ActionListener {

    private JButton btnTeam;
    private JButton btnEditTeam;
    private JButton btnViewTeams;
    private JButton btnDeleteTeam;

    public MyFrame() {

        // Create label and image
        JLabel lblTitle = new JLabel("Soccer League Manager");
        ImageIcon icon = new ImageIcon("data/foot.jpg.png");
        Image scaledImage = icon.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        lblTitle.setIcon(scaledIcon);
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("MV Boli", Font.BOLD, 25));
        lblTitle.setVerticalAlignment(JLabel.TOP);
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        lblTitle.setBounds(10, 10, 580, 200);

        // Create buttons
        btnTeam = new JButton("Add Team");
        btnTeam.addActionListener(this);
        btnTeam.setBackground(Color.DARK_GRAY);
        btnTeam.setForeground(Color.WHITE);

        btnEditTeam = new JButton("Edit Team");
        btnEditTeam.addActionListener(this);
        btnEditTeam.setBackground(Color.DARK_GRAY);
        btnEditTeam.setForeground(Color.WHITE);

        btnViewTeams = new JButton("View Teams");
        btnViewTeams.addActionListener(this);
        btnViewTeams.setBackground(Color.DARK_GRAY);
        btnViewTeams.setForeground(Color.WHITE);

        btnDeleteTeam = new JButton("Delete Team");
        btnDeleteTeam.addActionListener(this);
        btnDeleteTeam.setBackground(Color.DARK_GRAY);
        btnDeleteTeam.setForeground(Color.WHITE);

        // Add buttons to panel
        JPanel panelButtons = new JPanel(new GridLayout(1, 4, 10, 10));
        panelButtons.setBackground(Color.BLACK);
        panelButtons.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelButtons.add(btnTeam);
        panelButtons.add(btnEditTeam);
        panelButtons.add(btnViewTeams);
        panelButtons.add(btnDeleteTeam);

        // Add components to frame
        JPanel panelMain = new JPanel(new BorderLayout());
        panelMain.setBackground(Color.BLACK);
        panelMain.add(lblTitle, BorderLayout.NORTH);
        panelMain.add(panelButtons, BorderLayout.CENTER);
        this.getContentPane().add(panelMain);

        // Set frame properties
        this.setTitle("Soccer League");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 370);
        this.setResizable(false);
        this.setVisible(true);
        this.setIconImage(scaledImage);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnTeam) {
            System.out.println("Add Team button clicked");
        } else if (e.getSource() == btnEditTeam) {
            System.out.println("Edit Team button clicked");
        } else if (e.getSource() == btnViewTeams) {
            System.out.println("View Teams button clicked");
        } else if (e.getSource() == btnDeleteTeam) {
            System.out.println("Delete Team button clicked");
        }
    }
}
