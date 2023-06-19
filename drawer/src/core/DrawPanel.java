package core;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.util.Arrays;

import javax.swing.JPanel;


public class DrawPanel extends JPanel implements MouseListener,MouseMotionListener{
    private Point mouse_location = new Point(); 
    private Tab_Panel parent;
    DrawPanel(Tab_Panel parent){
        super();
        this.parent = parent;
        Arrays.stream(new File("./pic").list()).forEach(System.out::println);
        addMouseListener(this);
        addMouseMotionListener(this);
    }
    private void add_new_graphics(int i){

    }
    @Override
    public void mousePressed(MouseEvent e) {
        if(parent.toolbox_btn_num < 3)
            add_new_graphics(parent.toolbox_btn_num);
        else
            mouse_location.setLocation(e.getX(), e.getY());
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        this.paintComponent(this.getGraphics());
        getGraphics().drawLine((int)mouse_location.getX(), (int)mouse_location.getY(), e.getX(), e.getY());
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
