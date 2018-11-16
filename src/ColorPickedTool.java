import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class ColorPickedTool extends AbstractTool{
    private JPanel Panel;

    public ColorPickedTool(ImageFrame frame){
        this.frame = frame;
        this.graphics = frame.DrawPanel.getGraphics();
        this.Panel = frame.currentColorPanel;
    }

    @Override
    public void mousePressed(MouseEvent e){
        super.mouseDragged(e);
//        frame.initBufferedImage();
////        if (e.getX() > 0 && e.getY() > 0){
////            int rgb =  this.frame.bufferedImage.getRGB(e.getX(),e.getY());
////            // 前8位
////            int int8 = (int) Math.pow(2, 8);
////            // 前16位
////            int int16 = (int) Math.pow(2, 16);
////            // 前24位
////            int int24 = (int) Math.pow(2, 24);
////            // 分别取0-7位,8-15位,16-23位
////            int r = (rgb & (int24 - int16)) >> 16;
////            int g = (rgb & (int16 - int8)) >> 8;
////            int b = (rgb & (int8 - 1));
////            // 设置颜色
////            this.color = new Color(r, g, b);
////            Panel.setBackground(this.color);

        if (e.getX() > 0 && e.getY() > 0){
            try {
                Robot robot = new Robot();
                Color pixel = robot.getPixelColor(e.getX(),e.getY());
//                int R = (pixel.getRed() & 0xff0000) >> 16;
//                int G = (pixel.getGreen() & 0xff00) >> 8;
//                int B = (pixel.getBlue() & 0xff);
//                Color col = new Color(R, G, B);
                // 前8位
                int int8 = (int) Math.pow(2, 8);
                // 前16位
                int int16 = (int) Math.pow(2, 16);
                // 前24位
                int int24 = (int) Math.pow(2, 24);
                // 分别取0-7位,8-15位,16-23位
                int R = (pixel.getRed() & (int24 - int16)) >> 16;
                int G = (pixel.getGreen() & (int16 - int8)) >> 8;
                int B = (pixel.getBlue() & (int8 - 1));
                Color col = new Color(R,G,B);

                System.out.println("Red   = " + col.getRed());
                System.out.println("Green = " + col.getGreen());
                System.out.println("Blue  = " + col.getBlue());
                System.out.println("Color = " + col.toString());
                Panel.setBackground(col);
            }catch (AWTException ex){
                ex.printStackTrace();
            }
        }
    }
}
