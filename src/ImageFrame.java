import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class ImageFrame extends JFrame{

    public String buttonName = "铅笔"; // 初始化状态的工具为铅笔
    public String menuitem = "";
    public AbstractTool currentTool;
    public DrawSpace DrawPanel; // 画图区域Panel
    public Color currentColor; // 当前正在使用的颜色
    public JPanel currentColorPanel = new JPanel(); // 当前颜色部分Panel
//    public BufferedImage bufferedImage;


    public BufferedImage getImage(){
        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        g2d.fillRect(0,0,image.getWidth(),image.getHeight());
        paint(g2d);
        return image;
    }

    private void createMenuBar(){
        // 创建一个JMenuBar放置菜单
        JMenuBar menuBar = new JMenuBar();
        // 菜单文字数组，与下面的menuItemArr一一对应
        String[] menuArr = {"文件(F)", "查看(V)", "颜色(C)", "帮助(H)"};
        // 菜单项文字数组
        String[][] menuItemArr = {{"新建(N)", "打开(O)", "保存(S)", "-", "退出(X)"},
                {"工具箱(T)", "颜料盒(C)"}, {"编辑颜色"}, {"帮助主题", "关于"}};
        // 遍历menuArr与menuItemArr去创建菜单
        for(int i = 0; i < menuArr.length;i++){
            // 新建一个JMenu菜单
            JMenu menu = new JMenu(menuArr[i]);
            for(int j = 0; j < menuItemArr[i].length;j++){
                if(menuItemArr[i][j].equals("-")){
                    // 设置菜单分隔符
                    menu.addSeparator();
                }
                else{
                    // 新建一个JMenuItem菜单项
                    JMenuItem menuItem = new JMenuItem(menuItemArr[i][j]);
                    // menuItem.addActionListener(menuListener);
                    // 把菜单项加到JMenu菜单里面
                    menu.add(menuItem);
                    menuItem.addActionListener(e -> {
                        menuitem = e.getActionCommand();
                        System.out.println("按下的菜单是" + menuitem);
//                        currentTool = DrawPanel.getToolInstance(buttonName,this);
//                        currentTool.getGraphics().setColor(currentColor);
                    });
                }
            }
            // 把菜单加到JMenuBar上
            menuBar.add(menu);
        }

        // 设置JMenubar
        this.setJMenuBar(menuBar);
    }

    private void createToolPanel(){
        JPanel ToolPanel = new JPanel();
        ToolPanel.setPreferredSize(new Dimension(70,400));
        JToolBar toolBar = new JToolBar("工具");
        // 设置为垂直排列
        toolBar.setOrientation(toolBar.VERTICAL);
        // 设置为不可以拖动
        toolBar.setFloatable(false);
        toolBar.setPreferredSize(new Dimension(60,150));
        //toolBar.setMargin(new Insets(2,2,2,2));
        toolBar.setLayout(new GridLayout(5,2));
        // 工具数组
        String[] toolarr = { "铅笔", "刷子", "拾色器",
                "喷枪", "橡皮", "直线", "多边形", "矩形",
                "椭圆形", "圆矩形" };
        for(int i = 0;i < toolarr.length;i++) {
            // 以图标创建一个新的button
            JButton button = new JButton(toolarr[i]);
            // 把button加到工具栏中
            toolBar.add(button);
            button.addActionListener(e -> {
                buttonName = e.getActionCommand();
                System.out.println("按下的按钮是" + buttonName);
                currentTool = DrawPanel.getToolInstance(buttonName,this);
                // currentTool.getGraphics().setColor(currentColor);
                });
        }
        ToolPanel.add(toolBar);
        this.add(ToolPanel, BorderLayout.WEST);
    }

    private void createColorPanel(){
        JPanel ColorPanel = new JPanel();
        ColorPanel.setPreferredSize(new Dimension(600,50));
        JToolBar colorBar = new JToolBar("颜色");
        colorBar.setFloatable(false);
        colorBar.setPreferredSize(new Dimension(120,40));
        colorBar.setLayout(new GridLayout(2,6,2,2));
        Color [] colorarr = {Color.BLACK,Color.BLUE,Color.cyan,Color.gray,Color.green,Color.lightGray,Color.magenta,
        Color.orange,Color.pink,Color.red,Color.white,Color.yellow};
        // 正在使用的颜色
        //JPanel currentColorPanel = new JPanel();
        this.currentColorPanel.setBackground(Color.black);
        this.currentColorPanel.setPreferredSize(new Dimension(30,30));
        //创建这些颜色的button
        for(int i = 0; i < colorarr.length; i++){
            JButton button = new JButton();
            button.setBackground(colorarr[i]); // mac os设置背景颜色有点不一样，解决方法https://zhidao.baidu.com/question/1861122272734219827.html
            button.setOpaque(true); //foreground设置透明
            button.setBorderPainted(false);
            button.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    currentColor = button.getBackground();
                    currentColorPanel.setBackground(currentColor);
                    //currentTool.setColor(button.getBackground());
                    currentTool.getGraphics().setColor(currentColor);
                }
                });
            colorBar.add(button);
        }
        ColorPanel.add(currentColorPanel);
        ColorPanel.add(colorBar);
        this.add(ColorPanel, BorderLayout.SOUTH);
    }

    public void init(){
        // 确保一个漂亮的外观风格
        JFrame.setDefaultLookAndFeelDecorated(true);

        // 创建并设置窗口
        this.setTitle("未命名-图片");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // this.setSize(600, 400); // setSize()和pack()不能同时存在
        this.setPreferredSize(new Dimension(600, 400));
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                DrawPanel.setPreferredSize(e.getComponent().getPreferredSize());
            }
        });


//        this.DrawPanel = new JPanel();
//        DrawPanel.setBackground(Color.WHITE);
        ImageService.initDrawSpace(this);
        createMenuBar();
        createColorPanel();
        createToolPanel();

        JScrollPane Scroll = new JScrollPane(DrawPanel);
        this.add(Scroll);
        Scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        Scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // 显示窗口
        this.pack();
        this.setVisible(true);

//        // JPanel.getGraphics()只有在JFrame.setVisible()后才能不返回NULL
//        this.currentTool = DrawPanel.getToolInstance("铅笔",this);
//
//        // 创建鼠标运动监听器
//        MouseMotionListener motionListener = new MouseMotionAdapter() {
//            public void mouseDragged(MouseEvent e) {
//                currentTool.mouseDragged(e);
//                DrawPanel.repaint();
//                DrawPanel.abstractTools.add(new LineTool(currentTool.getStartX(),currentTool.getStartY(),currentTool.getEndX(),currentTool.getEndY()));
//            }
//
//            public void mouseMoved(MouseEvent e) {
//                currentTool.mouseMoved(e);
//                DrawPanel.repaint();
//            }
//        };
//        MouseListener mouseListener = new MouseAdapter() {
//            public void mouseClicked(MouseEvent e) {
//                currentTool.mouseClicked(e);
//                DrawPanel.repaint();
//            }
//
//            public void mousePressed(MouseEvent e) {
//                currentTool.mousePressed(e);
//                DrawPanel.repaint();
//            }
//
//            public void mouseReleased(MouseEvent e) {
//                currentTool.mouseReleased(e);
//                DrawPanel.repaint();
////                DrawPanel.abstractTools.add(currentTool);
////                currentTool = new AbstractTool();
//            }
//        };
//        setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
//        DrawPanel.addMouseMotionListener(motionListener);
//        DrawPanel.addMouseListener(mouseListener);
    }
}