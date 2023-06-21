package core;

import java.awt.Color;
import java.awt.FlowLayout;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Toolbox extends JPanel{
    private MainWin mainwin;
    private String pic_path;
    private String[] icon_name;
    private JButton entity = new JButton();
    private JButton attributes = new JButton();
    private JButton relationship = new JButton();
    private JButton line = new JButton("line");
    private Tab_Panel tab_Panel;
    Toolbox(MainWin mainwin){
        super();
        this.mainwin =mainwin;
        pic_path = this.mainwin.data_class.get_Icon_path();
        icon_name = this.mainwin.data_class.get_Icon_name();
        tab_Panel = this.mainwin.draw_panel.tab_draw_panel;
        
        readImage();
        this.setLayout(new FlowLayout());
        this.setBackground(Color.GRAY);
        this.setVisible(true);
        addButton();
    }
    private void readImage(){
        try {
            entity.setIcon(new ImageIcon(ImageIO.read(new File(pic_path + icon_name[0]))));
            attributes.setIcon(new ImageIcon(ImageIO.read(new File(pic_path + icon_name[1]))));
            relationship.setIcon(new ImageIcon(ImageIO.read(new File(pic_path + icon_name[2]))));
            // line.setIcon(new ImageIcon(ImageIO.read(new File(pic_path + icon_name[3]))));
        } catch (IOException e) {
            System.out.println("get image fail!");
        }
    }
    private void addButton(){
        //
        entity.addActionListener(e -> {tab_Panel.toolbox_btn_num = 0;});
        attributes.addActionListener(e -> {tab_Panel.toolbox_btn_num = 1;});
        relationship.addActionListener(e -> {tab_Panel.toolbox_btn_num = 2;});
        line.addActionListener(e -> {tab_Panel.toolbox_btn_num = 3;});
        // entity.setActionCommand("Entity");
        // attributes.setActionCommand("Attributes");
        // relationship.setActionCommand("Relationship");
        // line.setActionCommand("line");
        this.add(entity);
        this.add(attributes);
        this.add(relationship);
        this.add(line);
    }
}
