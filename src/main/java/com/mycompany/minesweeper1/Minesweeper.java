package com.mycompany.minesweeper1;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Minesweeper extends JFrame implements GameListener {

    private JLabel statusbar;
    private JPanel startPanel;
    private JPanel gamePanel;
    private Board board;
    private static final int BOARD_WIDTH = 17 * 15 + 1; // 16 columns, 15 pixels per cell, 1 pixel for border
    private static final int BOARD_HEIGHT = 16 * 15 + 1 + 30; // 16 rows, 15 pixels per cell, 1 pixel for border, 30 pixels for status bar

    public Minesweeper() {
        initUI();
    }

    private void initUI() {
        // Initialize start panel
        startPanel = new JPanel();
        startPanel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Welcome to Minesweeper", JLabel.CENTER);
        startPanel.add(titleLabel, BorderLayout.CENTER);

        JButton startButton = new JButton("Start");
        startButton.addActionListener(new StartButtonListener());
        startPanel.add(startButton, BorderLayout.SOUTH);

        // Initialize game panel
        gamePanel = new JPanel();
        gamePanel.setLayout(new BorderLayout());

        statusbar = new JLabel("");
        gamePanel.add(statusbar, BorderLayout.SOUTH);

        board = new Board(statusbar);
        board.setGameListener(this);
        gamePanel.add(board, BorderLayout.CENTER);

        // Set the start panel as the initial content
        getContentPane().add(startPanel);

        setResizable(false);
        setTitle("Minesweeper");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
    }

    private void startNewGame() {
        board.newGame();
        getContentPane().removeAll();
        getContentPane().add(gamePanel);
        revalidate();
        repaint();
    }

    private class StartButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            startNewGame();
        }
    }

    @Override
    public void onGameEnd(boolean won) {
        EventQueue.invokeLater(() -> {
            getContentPane().removeAll();
            JLabel endLabel = new JLabel(won ? "You Won!" : "Game Over", JLabel.CENTER);
            startPanel.add(endLabel, BorderLayout.NORTH);
            getContentPane().add(startPanel);
            revalidate();
            repaint();
        });
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var ex = new Minesweeper();
            ex.setVisible(true);
        });
    }
}
