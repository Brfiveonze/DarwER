package core;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class MainWin extends JFrame{
    private int win_heigth = 750;
    private int win_width = 900;
    public data data_class = new data();
    public DrawTabPanelPage draw_panel = new DrawTabPanelPage(this);
    public Toolbox tool_box = new Toolbox(this);
    public Dimension screen_size = Toolkit.getDefaultToolkit().getScreenSize();
    MainWin(){
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation((int)(screen_size.getWidth()-win_width)/2, (int)(screen_size.getHeight()-win_heigth)/2);
        this.setSize(win_width, win_heigth);
        this.setLayout(new BorderLayout());
        addPanel();
        this.setVisible(true);
    }
    private void addPanel(){
        this.add(draw_panel,BorderLayout.CENTER);
        this.add(tool_box,BorderLayout.WEST);
    }
}
