package core;

import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class ObjectType extends JPanel{
    private boolean focus = false;
    private int object_type;
    private BufferedImage Image;
    private int size_width = 100,size_heigh=50;
    private int location_x, location_y;
    ObjectType(BufferedImage bfImg, int type){
        super();
        this.object_type = type;
        // getGraphics().drawImage();
        this.Image = bfImg;
        setVisible(true);
    }
    public void set_Size(int x,int y){
        size_width = x; size_heigh = y;
    }
    public void change_Focus(){
        focus = !focus;
    }
    public boolean get_Focus_state(){
        return focus;
    }
    public int get_type(){
        return object_type;
    }

}
