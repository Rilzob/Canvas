import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class DrawSpace extends JPanel {
    public AbstractTool currentTool = new AbstractTool();
    public ArrayList<AbstractTool> abstractTools = new ArrayList<>();
    private Image bgImage;

    public Image getBgImage(){ return this.bgImage; }

    public void setBgImage(Image bgImage){ this.bgImage = bgImage; }

    public AbstractTool getToolInstance(String buttonName, ImageFrame frame) {
        switch (buttonName) {
            case "直线":
                this.currentTool = new LineTool(frame);
                return currentTool;
            case "矩形":
                this.currentTool = new RectTool(frame);
                return currentTool;
            case "圆矩形":
                this.currentTool = new RoundRectTool(frame);
                return currentTool;
            case "多边形":
                this.currentTool = new PolygonTool(frame);
                return currentTool;
            case "椭圆形":
                this.currentTool = new OvalTool(frame);
                return currentTool;
            case "刷子":
                return new BrushTool(frame);
            case "橡皮":
                return new EraserTool(frame);
            case "喷枪":
                return new AtomizerTool(frame);
            case "铅笔":
                this.currentTool = new PencilTool(frame);
                return currentTool;
            default:
                return new AbstractTool();
        }
    }

    public void paintComponent(Graphics g, Image bgImage) {
        super.paintComponent(g);
        g.drawImage(bgImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g){
        super.paint(g); // 调用父类paint刷新屏幕
        Graphics2D g2 = (Graphics2D) g;
        paintComponent(g, getBgImage());
        for (AbstractTool tool: abstractTools){
            tool.draw(g2);
        }
    }

    public BufferedImage getImage(){
        BufferedImage image = new BufferedImage(getWidth(), getHeight(),
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        g2d.fillRect(0,0, image.getWidth(),image.getHeight());
        paint(g2d);
        return image;
    }


}

