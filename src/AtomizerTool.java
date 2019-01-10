import java.awt.*;
import java.awt.event.MouseEvent;

// 喷枪实现
public class AtomizerTool extends AbstractTool{
    public AtomizerTool(ImageFrame frame){
        this.frame = frame;
        this.graphics = frame.DrawPanel.getGraphics();
    }

    public AtomizerTool(ImageFrame frame,int StartX, int StartY, int EndX, int EndY){
        this.frame = frame;
        this.graphics = frame.DrawPanel.getGraphics();
        this.color = frame.currentColor;
        setStartX(StartX);
        setStartY(StartY);
        setEndX(EndX);
        setEndY(EndY);
    }

    @Override
    public void mouseDragged(MouseEvent e){
        super.mouseDragged(e);
//        // 喷枪大小
//        int size = 8;
//        // 喷枪点数
//        int count = 10;
        setStartX(e.getX());
        setStartY(e.getY());
    }

    public void draw(Graphics2D g2){    // 独立绘制方法
        g2.setColor(color);
        g2.fillOval(getStartX()+getEndX(),getStartY()+getEndY(),2,2);
    }
}
