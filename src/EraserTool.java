import java.awt.*;
import java.awt.event.MouseEvent;

// 橡皮擦工具实现
public class EraserTool extends AbstractTool{
    // 刷子的实现和橡皮的实现相同只不过是颜色不同而已
    public EraserTool(ImageFrame frame){
        this.frame = frame;
        this.graphics = frame.DrawPanel.getGraphics();
    }

    public EraserTool(ImageFrame frame,int StartX, int StartY){
        this.frame = frame;
        this.graphics = frame.DrawPanel.getGraphics();
        setStartX(StartX);
        setStartY(StartY);
    }

    @Override
    public void mouseDragged(MouseEvent e){
        // this.graphics.setColor(Color.WHITE);
        super.mouseDragged(e);
        setStartX(e.getX());
        setStartY(e.getY());
    }

    public void draw(Graphics2D g2){    // 独立绘制方法
        g2.setColor(Color.WHITE);
        g2.fillOval(getStartX(),getStartY(), 20,20);
        // 20是橡皮的尺寸大小
    }
}
