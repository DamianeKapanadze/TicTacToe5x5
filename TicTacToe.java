import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class TicTacToe extends JFrame implements ActionListener {
    int counter = 0;
    String winner = "none";

    private JButton[][] boardButtons; // [][] two dimensional array

    public TicTacToe() {
        setTitle("Game of Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);

        boardButtons = new JButton[5][5];
        JPanel boardPanel = new JPanel(new GridLayout(5, 5));

        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                boardButtons[row][col] = new JButton(Integer.toString(row * 5 + col));
                boardPanel.add(boardButtons[row][col]);
                boardButtons[row][col].addActionListener(this);
            }
        }

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(boardPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new TicTacToe();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int x = Integer.parseInt(e.getActionCommand()) / 5;
        int y = Integer.parseInt(e.getActionCommand()) % 5;

        if (counter % 2 == 0) {
            boardButtons[x][y].setText("X");
            boardButtons[x][y].setEnabled(false);
        } else {
            boardButtons[x][y].setText("O");
            boardButtons[x][y].setEnabled(false);
        }
        counter++;
        check();
        if (winner != "none") {
            this.dispose();
            endScreen();
        } else if (counter == 25) {
            this.dispose();
            endScreen();
        }
    }

    public void check() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (j + 2 < 5) {
                    if (boardButtons[i][j].getText() == boardButtons[i][j + 1].getText()
                            && boardButtons[i][j].getText() == boardButtons[i][j + 2].getText()) {
                        winner = boardButtons[i][j].getText();
                    }
                }
                if (i + 2 < 5) {
                    if (boardButtons[i][j].getText() == boardButtons[i + 1][j].getText()
                            && boardButtons[i + 2][j].getText() == boardButtons[i][j].getText()) {
                        winner = boardButtons[i][j].getText();
                    }
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i < 3 && j < 3) {
                    if (boardButtons[i][j].getText() == boardButtons[i + 1][j + 1].getText()
                            && boardButtons[i + 2][j + 2].getText() == boardButtons[i + 1][j + 1].getText()) {
                        winner = boardButtons[i][j].getText();
                    }
                }

                if (i > 1 && j < 3) {
                    if (boardButtons[i][j].getText() == boardButtons[i - 1][j + 1].getText()
                            && boardButtons[i - 2][j + 2].getText() == boardButtons[i - 1][j + 1].getText()) {
                        winner = boardButtons[i][j].getText();
                    }
                }

            }
        }
    }

    public void endScreen() {
        JFrame frame = new JFrame("End Screen");
        frame.setSize(400, 400);
        JPanel panel = new JPanel();
        JLabel label;
        if (winner == "none")
            label = new JLabel("It was a draw");
        else
            label = new JLabel(winner + " won the game");

        JButton playAgain = new JButton("Play Again");
        JButton quit = new JButton("Quit");

        panel.add(label);
        panel.add(playAgain);
        panel.add(quit);

        playAgain.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                winner = "none";
                counter = 0;
                new TicTacToe();
            }
        });

        quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                System.exit(0);
            }
        });

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}