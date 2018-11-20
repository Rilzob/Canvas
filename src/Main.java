import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Main {
    public static void main(String[] args){
        // 显示应用GUI
        ImageFrame Frame = new ImageFrame();
        Frame.init();
        // JPanel.getGraphics()只有在JFrame.setVisible()后才能不返回NULL
        Frame.currentTool = Frame.DrawPanel.getToolInstance(Frame.buttonName,Frame);

        // 先放进去一个空的，因为先做remove
        Frame.DrawPanel.abstractTools.add(new AbstractTool());

        // 创建鼠标运动监听器
        MouseMotionListener motionListener = new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                if (Frame.buttonName.equals("直线")) {
                    Frame.currentTool.mouseDragged(e);
                    Frame.DrawPanel.abstractTools.remove(Frame.DrawPanel.abstractTools.size() - 1);
                    Frame.DrawPanel.abstractTools.add(new LineTool(Frame, Frame.currentTool.getStartX(), Frame.currentTool.getStartY(), Frame.currentTool.getEndX(), Frame.currentTool.getEndY()));
                    Frame.DrawPanel.repaint();
                } else if (Frame.buttonName.equals("矩形")){
                    Frame.currentTool.mouseDragged(e);
                    Frame.DrawPanel.abstractTools.remove(Frame.DrawPanel.abstractTools.size() - 1);
                    Frame.DrawPanel.abstractTools.add(new RectTool(Frame, Frame.currentTool.getStartX(), Frame.currentTool.getStartY(), Frame.currentTool.getEndX(), Frame.currentTool.getEndY()));
                    Frame.DrawPanel.repaint();
                } else if (Frame.buttonName.equals("圆矩形")){
                    Frame.currentTool.mouseDragged(e);
                    Frame.DrawPanel.abstractTools.remove(Frame.DrawPanel.abstractTools.size() - 1);
                    Frame.DrawPanel.abstractTools.add(new RoundRectTool(Frame, Frame.currentTool.getStartX(), Frame.currentTool.getStartY(), Frame.currentTool.getEndX(), Frame.currentTool.getEndY()));
                    Frame.DrawPanel.repaint();
                } else if (Frame.buttonName.equals("椭圆形")){
                    Frame.currentTool.mouseDragged(e);
                    Frame.DrawPanel.abstractTools.remove(Frame.DrawPanel.abstractTools.size() - 1);
                    Frame.DrawPanel.abstractTools.add(new OvalTool(Frame, Frame.currentTool.getStartX(), Frame.currentTool.getStartY(), Frame.currentTool.getEndX(), Frame.currentTool.getEndY()));
                    Frame.DrawPanel.repaint();
                } else if (Frame.buttonName.equals("铅笔")){
                    Frame.currentTool.mouseDragged(e);
                    // Frame.DrawPanel.abstractTools.remove(Frame.DrawPanel.abstractTools.size() - 1);
                    Frame.DrawPanel.abstractTools.add(new PencilTool(Frame, Frame.currentTool.getStartX(), Frame.currentTool.getStartY(), Frame.currentTool.getEndX(), Frame.currentTool.getEndY()));
                    Frame.currentTool.setStartX(Frame.currentTool.getEndX());
                    // System.out.printf("1:EndX:%d", Frame.currentTool.getEndX());
                    Frame.currentTool.setStartY(Frame.currentTool.getEndY());
                    // System.out.printf("1:EndY:%d", Frame.currentTool.getEndY());
                    Frame.DrawPanel.repaint();
                } else if (Frame.buttonName.equals("喷枪")){
                    Frame.currentTool.mouseDragged(e);
                    // Frame.DrawPanel.abstractTools.remove(Frame.DrawPanel.abstractTools.size() - 1);
                    for (int i = 0; i < 10; i++){  // 10是喷枪所喷出来的点数
                        Frame.currentTool.setEndX(new Random().nextInt(8) + 1);  // 8可以理解为size
                        Frame.currentTool.setEndY(new Random().nextInt(8) + 1);
                        Frame.DrawPanel.abstractTools.add(new AtomizerTool(Frame, Frame.currentTool.getStartX(), Frame.currentTool.getStartY(), Frame.currentTool.getEndX(), Frame.currentTool.getEndY()));
                    }
                    Frame.DrawPanel.repaint();
                } else if (Frame.buttonName.equals("橡皮")){
                    Frame.currentTool.mouseDragged(e);
                    // Frame.DrawPanel.abstractTools.remove(Frame.DrawPanel.abstractTools.size() - 1);
                    Frame.DrawPanel.abstractTools.add(new EraserTool(Frame, Frame.currentTool.getStartX(), Frame.currentTool.getStartY()));
                    Frame.DrawPanel.repaint();
                } else if (Frame.buttonName.equals("刷子")){
                    Frame.currentTool.mouseDragged(e);
                    // Frame.DrawPanel.abstractTools.remove(Frame.DrawPanel.abstractTools.size() - 1);
                    Frame.DrawPanel.abstractTools.add(new BrushTool(Frame, Frame.currentTool.getStartX(), Frame.currentTool.getStartY()));
                    Frame.DrawPanel.repaint();
                }
            }

            public void mouseMoved(MouseEvent e) {
                Frame.currentTool.mouseMoved(e);
                Frame.DrawPanel.repaint();
            }
        };
        MouseListener mouseListener = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Frame.currentTool.mouseClicked(e);
                Frame.DrawPanel.repaint();
                if (Frame.buttonName.equals("多边形") && e.getClickCount() == 2){
                    Frame.DrawPanel.abstractTools.add(new PolygonTool(Frame, Frame.currentTool.getStartX(), Frame.currentTool.getStartY(), Frame.currentTool.getFirstX(), Frame.currentTool.getFirstY()));
                    Frame.currentTool.setFlag(true);
                    Frame.currentTool.setStartX(0);
                    Frame.currentTool.setStartY(0);
                }
            }

            public void mousePressed(MouseEvent e) {
                Frame.currentTool.mousePressed(e);
                Frame.DrawPanel.repaint();
            }

            public void mouseReleased(MouseEvent e) {
                if (Frame.buttonName.equals("直线")) {
                    Frame.currentTool.mouseReleased(e);
                    Frame.DrawPanel.repaint();
                    Frame.DrawPanel.abstractTools.add(new LineTool(Frame, Frame.currentTool.getStartX(), Frame.currentTool.getStartY(), Frame.currentTool.getEndX(), Frame.currentTool.getEndY()));
                } else if (Frame.buttonName.equals("矩形")){
                    Frame.currentTool.mouseReleased(e);
                    Frame.DrawPanel.repaint();
                    Frame.DrawPanel.abstractTools.add(new RectTool(Frame, Frame.currentTool.getStartX(), Frame.currentTool.getStartY(), Frame.currentTool.getEndX(), Frame.currentTool.getEndY()));
                } else if (Frame.buttonName.equals("圆矩形")){
                    Frame.currentTool.mouseReleased(e);
                    Frame.DrawPanel.repaint();
                    Frame.DrawPanel.abstractTools.add(new RoundRectTool(Frame, Frame.currentTool.getStartX(), Frame.currentTool.getStartY(), Frame.currentTool.getEndX(), Frame.currentTool.getEndY()));
                } else if (Frame.buttonName.equals("椭圆形")){
                    Frame.currentTool.mouseReleased(e);
                    Frame.DrawPanel.repaint();
                    Frame.DrawPanel.abstractTools.add(new OvalTool(Frame, Frame.currentTool.getStartX(), Frame.currentTool.getStartY(), Frame.currentTool.getEndX(), Frame.currentTool.getEndY()));
                } else if (Frame.buttonName.equals("铅笔")){
                    Frame.currentTool.mouseReleased(e);
                    Frame.DrawPanel.repaint();
                    // Frame.DrawPanel.abstractTools.add(new PencilTool(Frame, Frame.currentTool.getStartX(), Frame.currentTool.getStartY(), Frame.currentTool.getEndX(), Frame.currentTool.getEndY()));
                } else if (Frame.buttonName.equals("喷枪")){
                    Frame.currentTool.mouseReleased(e);
                    Frame.DrawPanel.repaint();
                    // Frame.DrawPanel.abstractTools.add(new PencilTool(Frame, Frame.currentTool.getStartX(), Frame.currentTool.getStartY(), Frame.currentTool.getEndX(), Frame.currentTool.getEndY()));
                } else if (Frame.buttonName.equals("橡皮")){
                    Frame.currentTool.mouseReleased(e);
                    Frame.DrawPanel.repaint();
                    // Frame.DrawPanel.abstractTools.add(new PencilTool(Frame, Frame.currentTool.getStartX(), Frame.currentTool.getStartY(), Frame.currentTool.getEndX(), Frame.currentTool.getEndY()));
                } else if (Frame.buttonName.equals("刷子")) {
                    Frame.currentTool.mouseReleased(e);
                    Frame.DrawPanel.repaint();
                } else if (Frame.buttonName.equals("多边形")){
                    Frame.currentTool.mouseReleased(e);
//                    Frame.DrawPanel.abstractTools.add(new PolygonTool(Frame, Frame.currentTool.getStartX(), Frame.currentTool.getStartY(), Frame.currentTool.getEndX(), Frame.currentTool.getEndY()));
//                    Frame.currentTool.setStartX(e.getX());
//                    if (Frame.currentTool.isFlag()){
//                        Frame.currentTool.setFirstX(e.getX());
//                        Frame.currentTool.setFirstY(e.getY());
////                        Frame.currentTool.setStartX(e.getX());
////                        Frame.currentTool.setStartY(e.getY());
//                    }else {
                    if (Frame.currentTool.getStartX() > 0 && Frame.currentTool.getStartY() > 0 ) {
                        Frame.currentTool.setEndX(e.getX());
                        Frame.currentTool.setEndY(e.getY());
                        Frame.DrawPanel.abstractTools.add(new PolygonTool(Frame, Frame.currentTool.getStartX(), Frame.currentTool.getStartY(), Frame.currentTool.getEndX(), Frame.currentTool.getEndY()));
                    }
                        // graphics.drawLine(getStartX(),getStartY(),e.getX(),e.getY());
                    Frame.currentTool.setStartX(e.getX());
                    Frame.currentTool.setStartY(e.getY());
                    Frame.currentTool.setFlag(false);
                }
            }
        };
        Frame.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        Frame.DrawPanel.addMouseMotionListener(motionListener);
        Frame.DrawPanel.addMouseListener(mouseListener);
    }
}
