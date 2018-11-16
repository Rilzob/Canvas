import javax.swing.*;
import java.awt.*;

public class ToolFactory {
    public static AbstractTool getToolInstance(String buttonName,ImageFrame frame){
        switch (buttonName) {
            case "直线":
                return new LineTool(frame);
            case "矩形":
                return new RectTool(frame);
            case "圆矩形":
                return new RoundRectTool(frame);
            case "多边形":
                return new PolygonTool(frame);
            case "椭圆形":
                return new Round(frame);
            case "刷子":
                return new BrushTool(frame);
            case "橡皮":
                return new EraserTool(frame);
            case "喷枪":
                return new AtomizerTool(frame);
            case "拾色器":
                return new ColorPickedTool(frame);
            default:
                return new PencilTool(frame);
        }

    }
}
