import java.awt.*;
import java.awt.event.MouseEvent;

// 椭圆绘制类
public class OvalTool extends AbstractTool{
    private int x;
    private int y;

    public int getX(){ return this.x; }
    public int getY(){ return this.y; }

    public OvalTool(ImageFrame frame){
        this.frame = frame;
        this.graphics = frame.DrawPanel.getGraphics();
    }

    public OvalTool(ImageFrame frame,int StartX, int StartY, int EndX, int EndY){
        this.frame = frame;
        this.color = frame.currentColor;
        setStartX(StartX);
        setStartY(StartY);
        setEndX(EndX);
        setEndY(EndY);
        this.x = getStartX() > getEndX() ? getEndX():getStartX();
        this.y = getStartY() > getEndY() ? getEndY():getStartY();
    }

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
        g2.drawOval(getX(),getY(), Math.abs(getStartX()-getEndX()), Math.abs(getStartY()-getEndY()));
    }
}
