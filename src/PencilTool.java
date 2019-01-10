import java.awt.*;
import java.awt.event.MouseEvent;

public class PencilTool extends AbstractTool{
    public PencilTool(ImageFrame frame){
        this.frame = frame;
        this.graphics = frame.DrawPanel.getGraphics();
    }

    public PencilTool(ImageFrame frame,int StartX, int StartY, int EndX, int EndY){
        this.frame = frame;
        this.color = frame.currentColor;
        setStartX(StartX);
        setStartY(StartY);
        setEndX(EndX);
        setEndY(EndY);
    }

    @Override
    public void mousePressed(MouseEvent e){
        super.mousePressed(e);
        setStartX(e.getX());
        setStartY(e.getY());
    }

    @Override
    public void mouseDragged(MouseEvent e){
        super.mouseDragged(e);
        setEndX(e.getX());
        setEndY(e.getY());
    }

    public void draw(Graphics2D g2){
        g2.setColor(color);
        g2.drawLine(getStartX(), getStartY(), getEndX(), getEndY());
    }
}
