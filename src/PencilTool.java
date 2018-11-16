import java.awt.event.MouseEvent;

public class PencilTool extends AbstractTool{
    public PencilTool(ImageFrame frame){
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
    public void mouseDragged(MouseEvent e){
        super.mouseDragged(e);
        if (getPressX() > 0 && getPressY() > 0){
        graphics.drawLine(getPressX(), getPressY(), e.getX(), e.getY());
        setPressX(e.getX());
        setPressY(e.getY());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        // 初始化
        setPressX(0);
        setPressY(0);
    }
}
