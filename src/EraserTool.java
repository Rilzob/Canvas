import java.awt.*;
import java.awt.event.MouseEvent;

public class EraserTool extends AbstractTool{
    // 刷子的实现和橡皮的实现相同只不过是颜色不同而已
    public EraserTool(ImageFrame frame){
        this.frame = frame;
        this.graphics = frame.DrawPanel.getGraphics();
    }

    @Override
    public void mouseDragged(MouseEvent e){
        this.graphics.setColor(Color.WHITE);
        super.mouseDragged(e);
        setPressX(e.getX());
        setPressY(e.getY());
        int size = 20;
        if (getPressX() > 0 && getPressY() > 0){
            int x = getPressX();
            int y = getPressY();
            graphics.fillOval(x, y, size, size);
        }
    }
}
