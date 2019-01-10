import java.awt.*;
import java.awt.event.MouseEvent;

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
//        if (e.getX() > 0 && e.getY() > 0){
//            for (int i = 0; i < count;i++){
//                int x = new Random().nextInt(size) + 1;
//                int y = new Random().nextInt(size) + 1;
//                graphics.fillOval(e.getX() + x,e.getY() + y,2,2);
//            }
//        }
    }

    public void draw(Graphics2D g2){
        g2.setColor(color);
        g2.fillOval(getStartX()+getEndX(),getStartY()+getEndY(),2,2);
    }
}
