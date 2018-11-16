import java.awt.event.MouseEvent;

public interface Tool {

    public static String PencilTool = "PENCIL_TOOL";


    public void mouseDragged(MouseEvent e);

    public void mouseMoved(MouseEvent e);

    public void mouseReleased(MouseEvent e);

    public void mousePressed(MouseEvent e);

    public void mouseClicked(MouseEvent e);
}
