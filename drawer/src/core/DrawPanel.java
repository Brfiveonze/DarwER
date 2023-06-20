package core;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class DrawPanel extends JPanel implements MouseListener,MouseMotionListener{
    private data data_string = new data();
    private Point mouse_location = new Point(); 
    private Tab_Panel parent;
    private String pic_path = data_string.get_Img_path();
    private String[] pic_name = data_string.get_Img_name();
    private ArrayList<ObjectType> object_list = new ArrayList<>();
    DrawPanel(Tab_Panel parent){
        super();
        this.parent = parent;
        Arrays.stream(new File("./pic").list()).forEach(System.out::println);
        addMouseListener(this);
        addMouseMotionListener(this);
    }
    private void add_new_graphics(int toolbox_btn_num, int x, int y) {
        try {
            ObjectType o = new ObjectType(ImageIO.read(new File(pic_path + pic_name[toolbox_btn_num])), toolbox_btn_num);
            o.setBackground(Color.black);
            o.setLocation(x, y);
            this.add(o);
            object_list.add(o);
        } catch (IOException e) {
            System.out.println("Read image is fail");
        }
    }
    @Override
    public void mousePressed(MouseEvent e) {
        if(parent.toolbox_btn_num < 3)
            // add_new_graphics(parent.toolbox_btn_num,e.getX(), e.getY());
            this.add(new JLabel("??"));
        else
            mouse_location.setLocation(e.getX(), e.getY());
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        this.paintComponent(this.getGraphics());
        // getGraphics().drawLine((int)mouse_location.getX(), (int)mouse_location.getY(), e.getX(), e.getY());
    }
    @Override
    public void mouseMoved(MouseEvent e) {
        // System.out.println("new X= " + e.getX() + "Y= " + e.getY());
    }
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
}
