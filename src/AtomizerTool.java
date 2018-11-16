import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Random;

public class AtomizerTool extends AbstractTool{
    public AtomizerTool(ImageFrame frame){
        this.frame = frame;
        this.graphics = frame.DrawPanel.getGraphics();
    }

    @Override
    public void mouseDragged(MouseEvent e){
        super.mouseDragged(e);
        // 喷枪大小
        int size = 8;
        // 喷枪点数
        int count = 10;
        if (e.getX() > 0 && e.getY() > 0){
            for (int i = 0; i < count;i++){
                int x = new Random().nextInt(size) + 1;
                int y = new Random().nextInt(size) + 1;
                graphics.fillOval(e.getX() + x,e.getY() + y,2,2);
            }
        }
    }
}
