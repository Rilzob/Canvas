import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DrawSpace extends JPanel {
    public AbstractTool currentTool = new AbstractTool();
    public ArrayList<AbstractTool> abstractTools = new ArrayList<>();

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
//            case "拾色器":
//                return new ColorPickedTool(frame);
            case "铅笔":
                this.currentTool = new PencilTool(frame);
                return currentTool;
            default:
                return new AbstractTool();
        }
    }

    @Override
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        for (AbstractTool tool: abstractTools){
            tool.draw(g2);
        }
    }

}

