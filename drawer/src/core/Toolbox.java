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
    private MainWin mainWin;
    private String pic_path;
    private JButton entity = new JButton();
    private JButton attributes = new JButton();
    private JButton relationship = new JButton();
    private Tab_Panel tab_Panel;
    Toolbox(MainWin mainWin){
        super();
        this.mainWin =mainWin;
        pic_path = mainWin.data_class.get_Img_path();
        tab_Panel = mainWin.draw_panel.tab_draw_panel;
        readImage();
        this.setLayout(new FlowLayout());
        this.setBackground(Color.GRAY);
        this.setVisible(true);
        addButton();
    }
    private void readImage(){
        try {
            entity.setIcon(new ImageIcon(ImageIO.read(new File(pic_path + "entity.png"))));
            attributes.setIcon(new ImageIcon(ImageIO.read(new File(pic_path + "attributes.png"))));
            relationship.setIcon(new ImageIcon(ImageIO.read(new File(pic_path + "relationship.png"))));
        } catch (IOException e) {
            System.out.println("get image fail!");
        }
    }
    private void addButton(){
        //
        entity.addActionListener(e -> {tab_Panel.toolbox_btn_num = 0;});
        attributes.addActionListener(e -> {tab_Panel.toolbox_btn_num = 1;});
        relationship.addActionListener(e -> {tab_Panel.toolbox_btn_num = 2;});
        entity.setActionCommand("Entity");
        attributes.setActionCommand("Attributes");
        relationship.setActionCommand("Relationship");
        //
        this.add(entity);
        this.add(attributes);
        this.add(relationship);
    }
}
