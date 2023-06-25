package core;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class DrawPanel extends JPanel implements MouseListener,MouseMotionListener{
    private data data_string = new data();
    private Boolean connect_2_object = false;
    private int[] object_num = {0,0,0};
    private Tab_Panel parent;
    private String pic_path = data_string.get_Img_path();
    private String[] pic_name = data_string.get_Img_name();
    private ArrayList<ObjectType> object_list = new ArrayList<>();
    private ArrayList<ObjectType[]> line_set = new ArrayList<>();
    private ObjectType last_focus_object, now_tap_object = null;
    
    DrawPanel(Tab_Panel parent){
        super();
        this.parent = parent;
        // Arrays.stream(new File("./pic").list()).forEach(System.out::println);
        this.setLayout(null);
        addMouseListener(this);
        addMouseMotionListener(this);
    }
    private void add_new_graphics(int x, int y) {
        try {
            ObjectType o = new ObjectType(
                ImageIO.read(
                    new File(pic_path + pic_name[this.parent.toolbox_btn_num])), this.parent.toolbox_btn_num, ++object_num[this.parent.toolbox_btn_num]);
            o.setLocation(x, y);
            object_list.add(o);
            this.add(o);
            System.out.println("tap type: " + o.get_type() + " number: " + o.get_num());
        } catch (IOException e) {
            System.out.println("Read image is fail");
        }
    }
    private void clean_line(){
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
    private void chedck_tap_object_and_set_now_tapping_object(int x, int y){
        //X0
        if(null == this.now_tap_object){
            this.add_new_graphics(x, y);
            this.last_focus_object = this.now_tap_object;
        }
        else{
            //01
            if(null == this.last_focus_object)
                this.last_focus_object = this.now_tap_object;
            //11
            if(this.last_focus_object == this.now_tap_object)
                System.out.println("focus type: " + this.last_focus_object.get_type() + " number: " + this.last_focus_object.get_num());
            else{
                this.last_focus_object = this.now_tap_object;
                System.out.println("tap type: " + this.last_focus_object.get_type() + " number: " + this.last_focus_object.get_num());
            }
        }
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        clean_line();
        if(!this.line_set.isEmpty())
            for(ObjectType[] i : line_set)
                g.drawLine(i[0].getX(), i[0].getY(), i[1].getX(), i[1].getY());
    }
    @Override
    public void mousePressed(MouseEvent e) {
        this.now_tap_object = this.get_focus_object(e.getPoint());
        if(parent.toolbox_btn_num != 3){ //非物件連接模式
            this.connect_2_object = false;
            chedck_tap_object_and_set_now_tapping_object(e.getX(), e.getY());
        }
        else{                            //連接物件模式確認第一個點擊物件
            this.connect_2_object = true;
            if(this.now_tap_object != null)
                this.last_focus_object = this.now_tap_object;
            else
                this.last_focus_object = null;
        }
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        if(this.connect_2_object && this.last_focus_object != null){
            this.getGraphics().drawLine(last_focus_object.getX(), last_focus_object.getY(), e.getX(), e.getY());
        }
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        this.now_tap_object = this.get_focus_object(e.getPoint());
        if(this.connect_2_object && this.last_focus_object != null && this.now_tap_object != null){
            ObjectType[] line = new ObjectType[]{this.last_focus_object, this.get_focus_object(e.getPoint())};
            line_set.add(line);
        }
    }
    @Override
    public void mouseMoved(MouseEvent e) {}
    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
}
