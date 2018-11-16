import java.awt.event.MouseEvent;

public class RectTool extends AbstractTool{
    public RectTool(ImageFrame frame){
        this.frame = frame;
        this.graphics = frame.DrawPanel.getGraphics();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        setPressX(e.getX());
        setPressY(e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        int x = getPressX() > e.getX() ? e.getX():getPressX();
        int y = getPressY() > e.getY() ? e.getY():getPressY();
        graphics.drawRect(x, y, Math.abs(getPressX()-e.getX()), Math.abs(getPressY()-e.getY()));
    }
}
