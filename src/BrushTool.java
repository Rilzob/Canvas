import java.awt.*;
import java.awt.event.MouseEvent;

public class BrushTool extends AbstractTool{
    // 刷子的实现和橡皮的实现相同只不过是颜色不同而已
    public BrushTool(ImageFrame frame){
        this.frame = frame;
        this.graphics = frame.DrawPanel.getGraphics();
    }

    @Override
    public void mouseDragged(MouseEvent e){
        super.mouseDragged(e);
        setPressX(e.getX());
        setPressY(e.getY());
        int size = 20;
        if (getPressX() > 0 && getPressY() > 0){
            int x = getPressX();
            int y = getPressY();
            graphics.fillOval(x, y, size, size);
        }
        try {
            Robot robot = new Robot();
            Color pixel = robot.getPixelColor(e.getX(),e.getY());
//            int R = (pixel.getRed() & 0xff0000) >> 16;
//            int G = (pixel.getGreen() & 0xff00) >> 8;
//            int B = (pixel.getBlue() & 0xff);
            int R = pixel.getRed();
            int G = pixel.getGreen();
            int B = pixel.getBlue();
//            // 前8位
//            int int8 = (int) Math.pow(2, 8);
//            // 前16位
//            int int16 = (int) Math.pow(2, 16);
//            // 前24位
//            int int24 = (int) Math.pow(2, 24);
//            // 分别取0-7位,8-15位,16-23位
//            int R = (pixel.getRed() & (int24 - int16)) >> 16;
//            int G = (pixel.getGreen() & (int16 - int8)) >> 8;
//            int B = (pixel.getBlue() & (int8 - 1));
//            Color col = new Color(R,G,B);

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
}
