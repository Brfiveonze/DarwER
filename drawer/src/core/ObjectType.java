package core;

import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class ObjectType extends JPanel{
    private boolean focus = false;
    private BufferedImage Image;
    private int size_width = 100,size_heigh=50;
    private int location_x, location_y;
    ObjectType(){
        super();
        // getGraphics().drawImage();
        setVisible(focus);
    }
    private void get_Image(BufferedImage bfImg){
        Image = bfImg;
    }
    public void set_Size(int x,int y){
        size_width = x; size_heigh = y;
    }
    public void change_Focus(){
        focus = !focus;
    }
    public void set_Location(int x, int y){
        location_x = x;
        location_y =y;
    }
    public boolean get_Focus_state(){
        return focus;
    }
}
