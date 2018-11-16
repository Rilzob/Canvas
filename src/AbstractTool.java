import java.awt.*;
import java.awt.event.*;

public class AbstractTool implements MouseMotionListener,MouseListener{
    private int PressX;
    private int PressY;
    public Graphics graphics;// 绘图区域的图形上下文
    public Color color; // 工具当前的颜色
    public ImageFrame frame;

    public int getPressX(){ return this.PressX; }

    public int getPressY(){ return this.PressY; }

    public void setPressX(int x){ this.PressX = x; }

    public void setPressY(int y){ this.PressY = y; }

    public Graphics getGraphics(){ return this.graphics; }

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

