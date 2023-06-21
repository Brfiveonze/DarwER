package core;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class DrawPanel extends JPanel implements MouseListener,MouseMotionListener{
    private data data_string = new data();
    private Point mouse_location = new Point(); 
    private int[] object_num = {0,0,0};
    private Tab_Panel parent;
    private String pic_path = data_string.get_Img_path();
    private String[] pic_name = data_string.get_Img_name();
    private ArrayList<ObjectType> object_list = new ArrayList<>();
    private ObjectType focus_object = null;
    
    DrawPanel(Tab_Panel parent){
        super();
        this.parent = parent;
        // Arrays.stream(new File("./pic").list()).forEach(System.out::println);
        this.setLayout(null);
        addMouseListener(this);
        addMouseMotionListener(this);
    }
    private void add_new_graphics(int toolbox_btn_num, int x, int y) {
        try {
            ObjectType o = new ObjectType(ImageIO.read(new File(pic_path + pic_name[toolbox_btn_num])), toolbox_btn_num, ++object_num[toolbox_btn_num]);
            o.setLocation(x, y);
            object_list.add(o);
            this.add(o);
            this.revalidate();
            this.repaint();
        } catch (IOException e) {
            System.out.println("Read image is fail");
        }
    }
    private ObjectType get_focus_object(Point p){
        ObjectType ot = null;
        for(ObjectType i : object_list)
            if(i.get_Focus_state())
                i.change_Focus();
        for(ObjectType i : object_list){
            if(i.getBounds().contains(p)){
                i.change_Focus();
                ot = i;
                break;
            }
        }
        return ot;
    }
    @Override
    public void mousePressed(MouseEvent e) {
        this.focus_object = get_focus_object(e.getPoint());
        if(focus_object!=null)
            System.out.println(focus_object.get_num());
        else{
            if(parent.toolbox_btn_num < 3)
                add_new_graphics(parent.toolbox_btn_num,e.getX(), e.getY());
            else
                mouse_location.setLocation(e.getX(), e.getY());
        }
    }
    @Override
    public void mouseDragged(MouseEvent e) {
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
