import java.awt.*;
import java.awt.event.MouseEvent;

public class Round extends AbstractTool{
    public Round(ImageFrame frame){
        this.frame = frame;
        this.graphics = frame.DrawPanel.getGraphics();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        int x = getPressX() > e.getX() ? e.getX():getPressX();
        int y = getPressY() > e.getY() ? e.getY():getPressY();
        graphics.drawOval(x, y, Math.abs(getPressX()-e.getX()), Math.abs(getPressY()-e.getY()));
    }
}
