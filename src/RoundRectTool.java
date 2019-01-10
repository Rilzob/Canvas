import java.awt.*;
import java.awt.event.MouseEvent;

// 圆角矩形绘制类
public class RoundRectTool extends AbstractTool{
    private int x;
    private int y;

    public RoundRectTool(ImageFrame frame){
        this.frame = frame;
        this.graphics = frame.DrawPanel.getGraphics();
    }

    public RoundRectTool(ImageFrame frame,int StartX, int StartY, int EndX, int EndY){
        this.frame = frame;
        this.graphics = frame.DrawPanel.getGraphics();
        this.color = frame.currentColor;
        setStartX(StartX);
        setStartY(StartY);
        setEndX(EndX);
        setEndY(EndY);
        this.x = getStartX() > getEndX() ? getEndX():getStartX();
        this.y = getStartY() > getEndY() ? getEndY():getStartY();
    }

    public int getX(){ return this.x; }
    public int getY(){ return this.y; }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        setStartX(e.getX());
        setStartY(e.getY());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
        setEndX(e.getX());
        setEndY(e.getY());
        this.x = getStartX() > getEndX() ? getEndX():getStartX();
        this.y = getStartY() > getEndY() ? getEndY():getStartY();
    }

    public void draw(Graphics2D g2){    // 独立绘制方法
        g2.setColor(color);
        g2.drawRoundRect(getX(), getY(), Math.abs(getStartX()-getEndX()), Math.abs(getStartY()-getEndY()),20,20);
    }
}
