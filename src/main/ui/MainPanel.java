package ui;

import model.League;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainPanel extends JPanel implements ActionListener {

    private League league;
    private JButton matchButton;
    private JButton teamButton;
    private JButton standingsButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton exitButton;
    private JTextArea outputArea;

    public MainPanel() {
        league = new League();
        setLayout(new BorderLayout());

        JPanel menuPanel = new JPanel(new GridLayout(6, 1));
        matchButton = new JButton("Match options");
        teamButton = new JButton("Team options");
        standingsButton = new JButton("League standing");
        saveButton = new JButton("Save league to file");
        loadButton = new JButton("Load league from file");
        exitButton = new JButton("Exit");
        matchButton.addActionListener(this);
        teamButton.addActionListener(this);
        standingsButton.addActionListener(this);
        saveButton.addActionListener(this);
        loadButton.addActionListener(this);
        exitButton.addActionListener(this);

        menuPanel.add(matchButton);
        menuPanel.add(teamButton);
        menuPanel.add(standingsButton);
        menuPanel.add(saveButton);
        menuPanel.add(loadButton);
        menuPanel.add(exitButton);
        add(menuPanel, BorderLayout.WEST);

        outputArea = new JTextArea();
        add(new JScrollPane(outputArea), BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == matchButton) {
            doMatch();
        } else if (e.getSource() == teamButton) {
            doTeam();
        } else if (e.getSource() == standingsButton) {
            List<String> standings = league.getStandings();
            outputArea.setText("");
            for (String row : standings) {
                outputArea.append(row + "\n");
            }
        } else if (e.getSource() == saveButton) {
            save();
        } else if (e.getSource() == loadButton) {
            load();
        } else if (e.getSource() == exitButton) {
            System.exit(0);
        }
    }

    private void doMatch() {
        // Implement match options
    }

    private void doTeam() {
        // Implement team options
    }

    private void save() {
        // Implement save league to file
    }

    private void load() {
        // Implement load league from file
    }
}
