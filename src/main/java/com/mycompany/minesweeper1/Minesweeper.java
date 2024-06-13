package com.mycompany.minesweeper1;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Minesweeper extends JFrame implements GameListener {

    private final JLabel statusbar;
    private Board board;
    private JLabel gameResultLabel;
    
    //label and panel intialization
    public Minesweeper() {
        //title and jframe code
        setTitle("Minesweeper");
        setSize(260, 290);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        statusbar = new JLabel("");
        add(statusbar, BorderLayout.SOUTH);
        
        var startPanel = new JPanel();
        var titleLabel = new JLabel("Minesweeper", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));

        var startButton = new JButton("Start");
        startButton.setFont(new Font("Serif", Font.PLAIN, 14));
        startButton.addActionListener(new StartButtonListener());

        gameResultLabel = new JLabel("", SwingConstants.CENTER);
        gameResultLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        
        //start panel info
        startPanel.setLayout(new BorderLayout());
        startPanel.add(titleLabel, BorderLayout.NORTH);
        startPanel.add(gameResultLabel, BorderLayout.CENTER);
        startPanel.add(startButton, BorderLayout.SOUTH);

        add(startPanel);

        board = new Board(statusbar);
        board.setGameListener(this);

        setVisible(true);
    }
    //button listen for start button
    private class StartButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            startNewGame();
        }
    }
    
    //brings up game panel when press start button
    private void startNewGame() {
        getContentPane().removeAll();
        add(statusbar, BorderLayout.SOUTH);
        add(board);
        board.newGame();
        revalidate();
        repaint();
    }
    
    //game result code
    @Override
    public void onGameEnd(boolean won) {
        // Game ended, show the start panel again with a win/lose message
        getContentPane().removeAll();
        var startPanel = new JPanel();
        var titleLabel = new JLabel("Minesweeper", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));

        var startButton = new JButton("Start");
        startButton.setFont(new Font("Serif", Font.PLAIN, 14));
        startButton.addActionListener(new StartButtonListener());
        //sets the text as either
        gameResultLabel.setText(won ? "You won!" : "You lost!");
        
        //bring user to start screen
        startPanel.setLayout(new BorderLayout());
        startPanel.add(titleLabel, BorderLayout.NORTH);
        startPanel.add(gameResultLabel, BorderLayout.CENTER);
        startPanel.add(startButton, BorderLayout.SOUTH);

        add(startPanel);
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        new Minesweeper();
    }
}
