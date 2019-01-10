import java.awt.*;
import java.awt.event.MouseEvent;

public class PolygonTool extends AbstractTool{

    public PolygonTool(ImageFrame frame){
        this.frame = frame;
        this.graphics = frame.DrawPanel.getGraphics();
    }

    public PolygonTool(ImageFrame frame, int StartX, int StartY, int EndX, int EndY){
        this.frame = frame;
        this.color = frame.currentColor;
        setStartX(StartX);
        setStartY(StartY);
        setEndX(EndX);
        setEndY(EndY);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        if (isFlag()){
            setFirstX(e.getX());
            setFirstY(e.getY());
        }
    }

    public void draw(Graphics2D g2){
        g2.setColor(color);
        g2.drawLine(getStartX(), getStartY(), getEndX(), getEndY());
    }
}
