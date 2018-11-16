import java.awt.*;
import java.awt.event.MouseEvent;

public class PolygonTool extends AbstractTool{
    private int firstX;
    private int firstY;
    private boolean flag = true; // 用以标记绘制多边形时是否是第一条边

    public PolygonTool(ImageFrame frame){
        this.frame = frame;
        this.graphics = frame.DrawPanel.getGraphics();
    }

    @Override
    public void mouseClicked(MouseEvent e){
        super.mouseClicked(e);
        if (e.getClickCount() == 2){
            graphics.drawLine(getPressX(),getPressY(),firstX,firstY);
            flag = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        if (flag){
            firstX = e.getX();
            firstY = e.getY();
        }else {
            graphics.drawLine(getPressX(),getPressY(),e.getX(),e.getY());
        }
        setPressX(e.getX());
        setPressY(e.getY());
        flag = false;
    }
}
