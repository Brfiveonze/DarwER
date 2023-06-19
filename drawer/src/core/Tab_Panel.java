package core;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.Graphics2D;

public class Tab_Panel extends JTabbedPane{
    Graphics2D graph2D;  
    private int page_count = 0;
    public MainWin mainWin;
    public Tab_Panel tab_Panel;
    public int now_page_num = 0;
    public int toolbox_btn_num;
    Tab_Panel(MainWin mainWin){
        super(JTabbedPane.TOP,JTabbedPane.SCROLL_TAB_LAYOUT);
        this.mainWin = mainWin;
        tab_Panel = this;
        this.addChangeListener(e -> {
            now_page_num = tab_Panel.getSelectedIndex();
            System.out.println("select page number is " + now_page_num);
        });
        add_new_panel();
    }
    public void add_new_panel(){
        JPanel p = new JPanel();
        JLabel l = new JLabel("New page "+page_count);
        JButton bt = new JButton("X");
        bt.addActionListener(e -> {tab_Panel.remove(page_count);});
        p.add(l);
        p.add(bt);
        this.addTab("New page "+page_count, new DrawPanel(this));
        this.setTabComponentAt(page_count, p);
    }
}
