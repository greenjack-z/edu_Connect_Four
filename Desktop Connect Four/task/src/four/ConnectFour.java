package four;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ConnectFour extends JFrame {
    private static int moves = 42;
    private static String player = "X";
    private static final Cell[][] cells = new Cell[6][7];

    public ConnectFour() {
        setLocation(500, 100);
        setSize(900, 800);
        setTitle("Connect Four");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));

        JPanel fieldPanel = new JPanel();
        add(fieldPanel);
        makeCells();
        addCells(fieldPanel);

        JButton resetButton = new JButton();
        resetButton.setName("ButtonReset");
        resetButton.setText("Reset");
        ActionListener buttonListener = actionEvent -> resetField();
        resetButton.addActionListener(buttonListener);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(resetButton);
        add(buttonPanel);

        setVisible(true);
    }
    private static void makeCells() {
        for(int row = 0; row < 6; row++) {
            for(int col = 0; col < 7; col++) {
                cells[row][col] = new Cell(row, col);
            }
        }
    }
    private static void addCells(JPanel panel) {
        LayoutManager layout = new GridLayout(6, 7);
        panel.setLayout(layout);
        for(int i = 5; i > -1; i--) {
            for(Cell cell : cells[i]) {
                panel.add(cell);
            }
        }
    }
    private static void resetField() {
        for(Cell[] row : cells) {
            for(Cell cell : row) {
                cell.setBackground(Color.LIGHT_GRAY);
                cell.setText(" ");
            }
        }
        moves = 42;
        player = "X";
    }

    public static void click(int col) {
        if(moves > 0) {
            dropChipAtCol(0, col);
        }
    }

    private static int dropChipAtCol(int row, int col) {
        if(cells[row][col].getText().equals(" ")) {
            cells[row][col].setText(player);
            checkTurn(player, row, col);
            player = player.equals("X") ? "O" : "X";
            moves--;
        } else if (row < 5) {
            row = dropChipAtCol(row + 1, col);
        }
        return row;
    }

    private static void checkTurn(String player, int row, int col) {
        checkVertical(player, col);
        checkHorizontal(player, row);
        checkRightDiagonal(player, row, col);
        checkLeftDiagonal(player, row, col);
    }

    private static void checkVertical(String player, int col) {
        List<Cell> winCells = new ArrayList<>();
        for(int i = 0; i < 6; i++) {
            if (cells[i][col].getText().equals(player)) {
                winCells.add(cells[i][col]);
                if(winCells.size() > 3) {
                    markWin(winCells);
                }
            } else {
                winCells.clear();
            }
        }
    }

    private static void checkHorizontal(String player, int row) {
        List<Cell> winCells = new ArrayList<>();
        for(int i = 0; i < 7; i++) {
            if(cells[row][i].getText().equals(player)) {
                winCells.add(cells[row][i]);
                if(winCells.size() > 3) {
                    markWin(winCells);
                }
            } else {
                winCells.clear();
            }
        }
    }

    private static void checkRightDiagonal(String player, int row, int col) {
        List<Cell> winCells = new ArrayList<>();
        int startRow = Math.max(row - col, 0);
        int startCol = Math.max(col - row, 0);
        for(int i = startRow, j = startCol; i < 6 && j < 7; i++, j++) {
            if(cells[i][j].getText().equals(player)) {
                winCells.add(cells[i][j]);
                if(winCells.size() > 3) {
                    markWin(winCells);
                }
            } else {
                winCells.clear();
            }
        }

    }

    private static void checkLeftDiagonal(String player, int row, int col) {
        List<Cell> winCells = new ArrayList<>();
        int startRow = Math.max(row + (col - 6), 0);
        int startCol = Math.min(col + row, 6);
        for(int i = startRow, j = startCol; i < 6 && j > -1; i++, j--) {
            if(cells[i][j].getText().equals(player)) {
                winCells.add(cells[i][j]);
                if(winCells.size() > 3) {
                    markWin(winCells);
                }
            } else {
                winCells.clear();
            }
        }
    }

    private static void markWin(List<Cell> list) {
        for(Cell cell : list) {
            cell.setBackground(Color.CYAN);
        }
        moves = 0;
        player = player.equals("X") ? "O" : "X";
    }
}

