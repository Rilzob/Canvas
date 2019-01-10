import java.awt.*;
import java.awt.event.MouseEvent;

public class RectTool extends AbstractTool{
    private int x;
    private int y;

    public RectTool(ImageFrame frame){
        this.frame = frame;
        this.graphics = frame.DrawPanel.getGraphics();
    }

    public RectTool(ImageFrame frame,int StartX, int StartY, int EndX, int EndY){
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

    // 删除无用方法
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

    public void draw(Graphics2D g2){
        g2.setColor(color);
        g2.drawRect(getX(), getY(), Math.abs(getStartX()-getEndX()), Math.abs(getStartY()-getEndY()));
    }
}
