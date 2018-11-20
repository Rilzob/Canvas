import java.awt.*;
import java.awt.event.MouseEvent;

public class PolygonTool extends AbstractTool{
    private int firstX;
    private int firstY;
//    private boolean flag = true; // 用以标记绘制多边形时是否是第一条边

    public PolygonTool(ImageFrame frame){
        this.frame = frame;
        this.graphics = frame.DrawPanel.getGraphics();
    }

    public PolygonTool(ImageFrame frame, int StartX, int StartY, int EndX, int EndY){
        this.frame = frame;
        // this.graphics = frame.DrawPanel.getGraphics();
        this.color = frame.currentColor;
        setStartX(StartX);
        setStartY(StartY);
        setEndX(EndX);
        setEndY(EndY);
    }

//    @Override
//    public void mouseClicked(MouseEvent e){
//        super.mouseClicked(e);
//        if (e.getClickCount() == 2){
//            graphics.drawLine(getStartX(),getStartY(),firstX,firstY);
//            setFlag(true);
//        }
//    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        if (isFlag()){
            setFirstX(e.getX());
            setFirstY(e.getY());
//            setStartX(e.getX());
//            setStartY(e.getY());
        }
//        else {
//            setEndX(e.getX());
//            setEndY(e.getY());
//            // graphics.drawLine(getStartX(),getStartY(),e.getX(),e.getY());
//        }
//        setStartX(e.getX());
//        setStartY(e.getY());
//        setFlag(false);
    }

    public void draw(Graphics2D g2){
        g2.setColor(color);
        g2.drawLine(getStartX(), getStartY(), getEndX(), getEndY());
    }
}
