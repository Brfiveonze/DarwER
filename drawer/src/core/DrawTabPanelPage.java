package core;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

public class DrawTabPanelPage extends JPanel{
    public MainWin mainWin;
    public Tab_Panel tab_draw_panel = new Tab_Panel(mainWin);
    DrawTabPanelPage(MainWin mainWin){
        super();
        this.setBackground(Color.CYAN);
        this.setLayout(new BorderLayout());
        this.add(tab_draw_panel, BorderLayout.CENTER);
        this.setVisible(true);
    }
}