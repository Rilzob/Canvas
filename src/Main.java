import java.awt.*;
import java.awt.event.*;
import java.util.Random;

// 应用主类
public class Main {
    public static void main(String[] args){
        // 显示应用GUI
        ImageFrame Frame = new ImageFrame();
        Frame.init();   // 初始化应用
        // JPanel.getGraphics()只有在JFrame.setVisible()后才能不返回NULL
        Frame.currentTool = Frame.DrawPanel.getToolInstance(Frame.buttonName,Frame);

        // 先放进去一个空的，因为先做remove
        Frame.DrawPanel.abstractTools.add(new AbstractTool());

        // 创建鼠标运动监听器
        MouseMotionListener motionListener = new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                if (Frame.buttonName.equals("直线")) {    // 处理直线绘制
                    Frame.currentTool.mouseDragged(e);  // 工具栏鼠标动作
                    Frame.DrawPanel.abstractTools.remove(Frame.DrawPanel.abstractTools.size() - 1);
                    // 调用具体处理类，更新坐标,下同
                    Frame.DrawPanel.abstractTools.add(new LineTool(Frame, Frame.currentTool.getStartX(), Frame.currentTool.getStartY(), Frame.currentTool.getEndX(), Frame.currentTool.getEndY()));
                    Frame.DrawPanel.repaint();  // 重绘
                } else if (Frame.buttonName.equals("矩形")){  // 处理矩形绘制
                    Frame.currentTool.mouseDragged(e);
                    Frame.DrawPanel.abstractTools.remove(Frame.DrawPanel.abstractTools.size() - 1);
                    Frame.DrawPanel.abstractTools.add(new RectTool(Frame, Frame.currentTool.getStartX(), Frame.currentTool.getStartY(), Frame.currentTool.getEndX(), Frame.currentTool.getEndY()));
                    Frame.DrawPanel.repaint();
                } else if (Frame.buttonName.equals("圆矩形")){  // 处理圆矩形绘制
                    Frame.currentTool.mouseDragged(e);
                    Frame.DrawPanel.abstractTools.remove(Frame.DrawPanel.abstractTools.size() - 1);
                    Frame.DrawPanel.abstractTools.add(new RoundRectTool(Frame, Frame.currentTool.getStartX(), Frame.currentTool.getStartY(), Frame.currentTool.getEndX(), Frame.currentTool.getEndY()));
                    Frame.DrawPanel.repaint();
                } else if (Frame.buttonName.equals("椭圆形")){  // 处理椭圆形绘制
                    Frame.currentTool.mouseDragged(e);
                    Frame.DrawPanel.abstractTools.remove(Frame.DrawPanel.abstractTools.size() - 1);
                    Frame.DrawPanel.abstractTools.add(new OvalTool(Frame, Frame.currentTool.getStartX(), Frame.currentTool.getStartY(), Frame.currentTool.getEndX(), Frame.currentTool.getEndY()));
                    Frame.DrawPanel.repaint();
                } else if (Frame.buttonName.equals("铅笔")){  // 处理铅笔绘制
                    Frame.currentTool.mouseDragged(e);
                    // 调用具体处理类，连续绘制坐标,下同
                    Frame.DrawPanel.abstractTools.add(new PencilTool(Frame, Frame.currentTool.getStartX(), Frame.currentTool.getStartY(), Frame.currentTool.getEndX(), Frame.currentTool.getEndY()));
                    Frame.currentTool.setStartX(Frame.currentTool.getEndX());
                    Frame.currentTool.setStartY(Frame.currentTool.getEndY());
                    Frame.DrawPanel.repaint();
                } else if (Frame.buttonName.equals("喷枪")){  // 处理喷枪绘制
                    Frame.currentTool.mouseDragged(e);
                    for (int i = 0; i < 10; i++){  // 10是喷枪所喷出来的点数
                        Frame.currentTool.setEndX(new Random().nextInt(8) + 1);  // 8可以理解为size
                        Frame.currentTool.setEndY(new Random().nextInt(8) + 1);
                        Frame.DrawPanel.abstractTools.add(new AtomizerTool(Frame, Frame.currentTool.getStartX(), Frame.currentTool.getStartY(), Frame.currentTool.getEndX(), Frame.currentTool.getEndY()));
                    }
                    Frame.DrawPanel.repaint();
                } else if (Frame.buttonName.equals("橡皮")){  // 处理橡皮
                    Frame.currentTool.mouseDragged(e);
                    Frame.DrawPanel.abstractTools.add(new EraserTool(Frame, Frame.currentTool.getStartX(), Frame.currentTool.getStartY()));
                    Frame.DrawPanel.repaint();
                } else if (Frame.buttonName.equals("刷子")){  // 处理刷子绘制
                    Frame.currentTool.mouseDragged(e);
                    Frame.DrawPanel.abstractTools.add(new BrushTool(Frame, Frame.currentTool.getStartX(), Frame.currentTool.getStartY()));
                    Frame.DrawPanel.repaint();
                }
            }

            public void mouseMoved(MouseEvent e) {  // 鼠标移动处理方法
                Frame.currentTool.mouseMoved(e);
                Frame.DrawPanel.repaint();
            }
        };

        MouseListener mouseListener = new MouseAdapter() {  // 鼠标监听器
            public void mouseClicked(MouseEvent e) {
                Frame.currentTool.mouseClicked(e);
                Frame.DrawPanel.repaint();
                if (Frame.buttonName.equals("多边形") && e.getClickCount() == 2){  // 多边形处理判断
                    // 边界连续绘制
                    Frame.DrawPanel.abstractTools.add(new PolygonTool(Frame, Frame.currentTool.getStartX(), Frame.currentTool.getStartY(), Frame.currentTool.getFirstX(), Frame.currentTool.getFirstY()));
                    Frame.currentTool.setFlag(true);
                    Frame.currentTool.setStartX(0);
                    Frame.currentTool.setStartY(0);
                }
            }

            public void mousePressed(MouseEvent e) {    // 鼠标点击处理方法
                Frame.currentTool.mousePressed(e);
                Frame.DrawPanel.repaint();
            }

            public void mouseReleased(MouseEvent e) {   // 鼠标释放处理方法
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
                    if (Frame.currentTool.getStartX() > 0 && Frame.currentTool.getStartY() > 0 ) {
                        Frame.currentTool.setEndX(e.getX());
                        Frame.currentTool.setEndY(e.getY());
                        Frame.DrawPanel.abstractTools.add(new PolygonTool(Frame, Frame.currentTool.getStartX(), Frame.currentTool.getStartY(), Frame.currentTool.getEndX(), Frame.currentTool.getEndY()));
                    }
                    Frame.currentTool.setStartX(e.getX());
                    Frame.currentTool.setStartY(e.getY());
                    Frame.currentTool.setFlag(false);   //  复位多边形flag
                }
            }
        };
        Frame.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));   // 鼠标样式设置
        Frame.DrawPanel.addMouseMotionListener(motionListener); //  设置鼠标运动监听器
        Frame.DrawPanel.addMouseListener(mouseListener);    // 设置鼠标监听器
    }
}
