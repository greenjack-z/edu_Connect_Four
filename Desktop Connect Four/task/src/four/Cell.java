package four;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Cell extends JButton {
    private final int col;

    public Cell(int row, int col) {
        this.col = col;
        setFocusPainted(false);

        char[] chars = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        setName("Button" + chars[col] + (row + 1));
        setText(" ");
        setFont(new Font(Font.DIALOG, Font.BOLD, 36));
        setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(100, 100));
        setVisible(true);

        ActionListener cellListener = actionEvent -> ConnectFour.click(this.col);
        addActionListener(cellListener);
    }
}
