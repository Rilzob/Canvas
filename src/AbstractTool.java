import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// 图像顶层抽象，实现设置，获取具体参数的方法
public class AbstractTool extends JLabel implements MouseMotionListener,MouseListener{
    private int StartX = 0;
    private int StartY = 0;
    private int EndX = 0;
    private int EndY = 0;
    private boolean flag = true;  // 用以标记绘制多边形时是否是第一条边
    public Graphics graphics;// 绘图区域的图形上下文
    public Color color; // 工具当前的颜色
    public ImageFrame frame;
    private int firstX = 0; // 绘制多边形时记录第一个点
    private int firstY = 0;

    public int getStartX(){ return this.StartX; }

    public int getStartY(){ return this.StartY; }

    public void setStartX(int x){ this.StartX = x; }

    public void setStartY(int y){ this.StartY = y; }

    public int getEndX(){ return this.EndX; }

    public int getEndY(){ return this.EndY; }

    public void setEndX(int EndX){ this.EndX = EndX; }

    public void setEndY(int EndY){ this.EndY = EndY; }

    public Graphics getGraphics(){ return this.graphics; }

    public void setFlag(boolean flag){ this.flag = flag; }

    public boolean isFlag() { return flag; }

    public int getFirstX(){ return this.firstX; }

    public int getFirstY(){ return this.firstY; }

    public void setFirstX(int firstX){ this.firstX = firstX; }

    public void setFirstY(int firstY){ this.firstY = firstY; }


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

    public void draw(Graphics2D g2){

    }
}

