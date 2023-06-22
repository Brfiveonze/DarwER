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
    private Boolean connect_2_object = false;
    private int[] object_num = {0,0,0};
    private Tab_Panel parent;
    private String pic_path = data_string.get_Img_path();
    private String[] pic_name = data_string.get_Img_name();
    private ArrayList<ObjectType> object_list = new ArrayList<>();
    private ArrayList<ObjectType[]> line_set = new ArrayList<>();
    private ObjectType[] now_line = new ObjectType[2];
    private ObjectType last_focus_object = null;
    
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
            this.reflash_page();
            System.out.println("tap type: " + o.get_type() + " number: " + o.get_num());
        } catch (IOException e) {
            System.out.println("Read image is fail");
        }
    }
    private void reflash_page(){
        if(!line_set.isEmpty())
            this.line_set.stream().forEach(o -> {getGraphics().drawLine(o[0].getX(), o[0].getY(), o[1].getX(), o[1].getY());});
        this.revalidate();
        this.repaint();
    }
    private void clean_object_focus(){
        for(ObjectType i : object_list)
            if(i.get_Focus_state())
                i.change_Focus();
    }
    private ObjectType get_focus_object(Point p){
        ObjectType ot = null;
        this.clean_object_focus();
        for(ObjectType i : object_list){
            if(i.is_in_graph(p)){
                i.change_Focus();
                ot = i;
                break;
            }
        }
        return ot;
    }
    private void chedck_tap_object_and_set_now_tapping_object(ObjectType now_tap_object, int x, int y){
        //X0
        if(null == now_tap_object){
            this.add_new_graphics(parent.toolbox_btn_num, x, y);
            this.last_focus_object = now_tap_object;
        }
        else{
            //01
            if(null == this.last_focus_object)
                this.last_focus_object = now_tap_object;
            //11
            if(this.last_focus_object == now_tap_object)
                System.out.println("focus type: " + this.last_focus_object.get_type() + " number: " + this.last_focus_object.get_num());
            else{
                this.last_focus_object = now_tap_object;
                System.out.println("tap type: " + this.last_focus_object.get_type() + " number: " + this.last_focus_object.get_num());
            }
        }

        // if(this.last_focus_object!=null && now_tap_object!=null){
        //     if(this.last_focus_object == now_tap_object){
        //         System.out.println("focus type: " + this.last_focus_object.get_type() + " number: " + this.last_focus_object.get_num());
        //     }
        //     else{
        //         this.last_focus_object = now_tap_object;
        //         System.out.println("tap type: " + this.last_focus_object.get_type() + " number: " + this.last_focus_object.get_num());
        //     }
        // }
        // else{
        //     this.add_new_graphics(parent.toolbox_btn_num, x, y);
        //     this.last_focus_object = now_tap_object;
        // }
    }
    @Override
    public void mousePressed(MouseEvent e) {
        ObjectType now_tap_object = this.get_focus_object(e.getPoint());
        if(parent.toolbox_btn_num != 3){ //非物件連接模式
            chedck_tap_object_and_set_now_tapping_object(now_tap_object, e.getX(), e.getY());
        }
        else{
            //
        }
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        if(this.connect_2_object){
            this.reflash_page();
            getGraphics().drawLine((int)mouse_location.getX(), (int)mouse_location.getY(), e.getX(), e.getY());
        }
    }
    @Override
    public void mouseMoved(MouseEvent e) {
        // System.out.println("new X= " + e.getX() + "Y= " + e.getY());
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        if(this.connect_2_object && now_line[0]!= null){
            object_list.stream()
            .filter(i -> i.getBounds().contains(e.getPoint()))
            .findFirst()
            .ifPresent(obj -> {
                now_line[1] = obj;
            });
            line_set.add(now_line);
        }
        this.reflash_page();
    }
    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
}
