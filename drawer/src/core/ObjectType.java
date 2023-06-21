package core;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class ObjectType extends JPanel{
    private boolean focus = false;
    private int object_type, num, size_width = 100,size_heigh=50,location_x, location_y;
    private BufferedImage Image;
    ObjectType(BufferedImage bfImg, int type,int num){
        super();
        this.num = num;
        this.object_type = type;
        this.setOpaque(false);
        this.Image = bfImg;
        this.setSize(bfImg.getWidth(), bfImg.getHeight());
        this.setVisible(true);
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g.create();
        g2d.setClip(get_image_shape());
        g2d.drawImage(Image, location_x, location_y, this);
        g2d.dispose();
    }
    protected Shape get_image_shape(){
        Area area = new Area();
        Rectangle rectangle = new Rectangle();
        int y1,y2;
        for (int x = 0; x < this.Image.getWidth(); x++) {
            y1 = Integer.MAX_VALUE;
            y2 = -1;
            for (int y = 0; y < this.Image.getHeight(); y++) {
                int rgb = this.Image.getRGB(x, y);
                rgb = rgb >>> 24;
                if (rgb > 0) {
                    if (y1 == Integer.MAX_VALUE) {
                        y1 = y;
                        y2 = y;
                    }
                    if (y > (y2 + 1)) {
                        rectangle.setBounds(x, y1, 1, y2 - y1 + 1);
                        area.add(new Area(rectangle));
                        y1 = y;
                    }
                    y2 = y;
                }
            }
            if ((y2 - y1) >= 0) {
                rectangle.setBounds(x, y1, 1, y2 - y1 + 1);
                area.add(new Area(rectangle));
            }
        }
        return area;
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
    public int get_num(){
        return this.num;
    }
    
}
