import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public abstract class Shape extends JLabel implements MouseMotionListener,MouseListener {
    public ImageFrame frame;
    public int startX;
    public int startY;
    public int endX;
    public int endY;
    
    public void setstartX(int startX){ this.startX = startX; }
    public void setstartY(int startY){ this.startY = startY; }
    public void setendX(int endX){ this.endX = endX; }
    public void setendY(int endY){ this.endY = endY; }


    @Override
    public void mouseDragged(MouseEvent e){
        // TODO Auto-generated method stub
        System.out.println("鼠标拖动到位置:" + e.getX() + "," + e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e){
        // TODO Auto-generated method stub
        System.out.println("鼠标移动");
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        System.out.println("鼠标进入窗口");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        System.out.println("鼠标离开窗口");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        System.out.println("鼠标释放");
    }

    public void mousePressed(MouseEvent e){
        // TODO Auto-generated method stub
        System.out.println("鼠标按下");
    }

    public void mouseClicked(MouseEvent e){
        // TODO Auto-generated method stub
        System.out.println("鼠标点击");
    }
}
