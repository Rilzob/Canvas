import java.awt.*;
import java.awt.event.MouseEvent;

// 刷子实现
public class BrushTool extends AbstractTool{
    // 刷子的实现和橡皮的实现相同只不过是颜色不同而已
    public BrushTool(ImageFrame frame){
        this.frame = frame;
        this.graphics = frame.DrawPanel.getGraphics();
    }

    public BrushTool(ImageFrame frame,int StartX, int StartY){
        this.frame = frame;
        this.color = frame.currentColor;
        this.graphics = frame.DrawPanel.getGraphics();
        setStartX(StartX);
        setStartY(StartY);
    }

    @Override
    public void mouseDragged(MouseEvent e){
        super.mouseDragged(e);
        setStartX(e.getX());
        setStartY(e.getY());
        try {
            Robot robot = new Robot();
            Color pixel = robot.getPixelColor(e.getX(),e.getY());
            int R = pixel.getRed();
            int G = pixel.getGreen();
            int B = pixel.getBlue();

            System.out.println("R   = " + R);
            System.out.println("G = " + G);
            System.out.println("B  = " + B);
            Color col = new Color(R, G, B);
            System.out.println("Red   = " + col.getRed());
            System.out.println("Green = " + col.getGreen());
            System.out.println("Blue  = " + col.getBlue());
            System.out.println("Color = " + col.toString());
        }catch (AWTException ex){
            ex.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){    // 独立绘制方法
        g2.setColor(color);
        g2.fillOval(getStartX(),getStartY(), 40,40);
        // 20是橡皮的尺寸大小
    }
}
