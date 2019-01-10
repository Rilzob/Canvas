import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class ImageFrame extends JFrame implements Serializable {

    public String buttonName = "铅笔"; // 初始化状态的工具为铅笔
    public String menuitem = "";
    public AbstractTool currentTool;
    public DrawSpace DrawPanel; // 画图区域Panel
    public Color currentColor; // 当前正在使用的颜色
    public JButton currentColorButton = new JButton(); // 当前颜色部分Button

    private void createMenuBar(){
        // 创建一个JMenuBar放置菜单
        JMenuBar menuBar = new JMenuBar();
        // 菜单文字数组，与下面的menuItemArr一一对应
        String[] menuArr = {"文件(F)", "帮助(H)"};
        // 菜单项文字数组
        String[][] menuItemArr = {{"新建(N)", "打开(O)", "保存(S)", "-", "退出(X)"}, {"关于"}};
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
                        if (menuitem.equals("保存(S)")){
                            ImageService.saveFile(this);
                        }
                        else if (menuitem.equals("新建(N)")) {
                            ImageService.newone(this);
                        }
                        else if (menuitem.equals("打开(O)")) {
                            ImageService.open(this);
                        }
                        else if (menuitem.equals("退出(X)")) {
                            System.exit(0);
                        }
                        else if (menuitem.equals("关于")) {
                            ImageService.about(this);
                        }
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
        ToolPanel.setPreferredSize(new Dimension(100,400));
        JToolBar toolBar = new JToolBar("工具");
        // 设置为垂直排列
        toolBar.setOrientation(toolBar.VERTICAL);
        // 设置为不可以拖动
        toolBar.setFloatable(false);
        toolBar.setPreferredSize(new Dimension(90,225));
        //toolBar.setMargin(new Insets(2,2,2,2));
        toolBar.setLayout(new GridLayout(5,2));
        // 工具数组
        String[] toolarr = { "铅笔", "刷子",
                "喷枪", "橡皮", "直线", "多边形", "矩形",
                "椭圆形", "圆矩形" };
        for(int i = 0;i < toolarr.length;i++) {
            // 以图标创建一个新的button
            JButton button = new JButton(toolarr[i]);
            button.setSize(40,40);
            try {
                BufferedImage buttonIcon = ImageIO.read(new File("./img/"+toolarr[i]+".png"));
                button.setIcon(new ImageIcon(buttonIcon));
            } catch (IOException e) {
                e.printStackTrace();
            }
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
        this.currentColorButton.setBackground(Color.black);
        this.currentColorButton.setPreferredSize(new Dimension(30,30));
        this.currentColorButton.setBorder(BorderFactory.createEmptyBorder());
        this.currentColorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {    // 添加更多颜色选择
                currentColorButton.setBackground(JColorChooser.showDialog(null, "test", currentColor));
            }
        });
        this.currentColorButton.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {   // 响应currentColorButton颜色变化
                currentColor = currentColorButton.getBackground();
            }
        });
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
                    currentColorButton.setBackground(currentColor);
                    //currentTool.setColor(button.getBackground());
                    currentTool.getGraphics().setColor(currentColor);
                }
                });
            colorBar.add(button);
        }
        ColorPanel.add(currentColorButton);
        ColorPanel.add(colorBar);
        this.add(ColorPanel, BorderLayout.SOUTH);
    }

    public void init(){
        // 确保一个漂亮的外观风格
        JFrame.setDefaultLookAndFeelDecorated(true);

        // 创建并设置窗口
        this.setTitle("Simple Canvas");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // this.setSize(600, 400); // setSize()和pack()不能同时存在
        this.setPreferredSize(new Dimension(600, 400));
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                DrawPanel.setPreferredSize(e.getComponent().getPreferredSize());
            }
        });


        ImageService.initDrawSpace(this);
        createMenuBar();
        createColorPanel();
        createToolPanel();

        JScrollPane Scroll = new JScrollPane(DrawPanel);
        this.add(Scroll);
        Scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        Scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // 显示窗口
        this.pack();    // 自适应组件大小
        this.setVisible(true);

    }
}