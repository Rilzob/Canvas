import java.awt.*;
import java.awt.event.MouseEvent;


public class LineTool extends AbstractTool {
    public LineTool(ImageFrame frame){
        this.frame = frame;
        this.graphics = frame.DrawPanel.getGraphics();
    }

    @Override
    public void mousePressed(MouseEvent e){
        super.mousePressed(e);
        setPressX(e.getX());
        setPressY(e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        graphics.drawLine(getPressX(),getPressY(),e.getX(),e.getY());
    }
}
