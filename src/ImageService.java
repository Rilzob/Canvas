import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;

public class ImageService {
    // 初始化画图
    public static void initDrawSpace(ImageFrame frame){
        frame.DrawPanel = new DrawSpace();
        frame.DrawPanel.setBackground(Color.WHITE);
    }

    // 保存文件
    public static void saveFile(ImageFrame frame){
        JFileChooser saveFileChooser = new JFileChooser();  // 选择路径，文件名
        FileNameExtensionFilter jpgFilter = new FileNameExtensionFilter("JPG图像", "jpg");
        saveFileChooser.setFileFilter(jpgFilter);
        saveFileChooser.showSaveDialog(frame);
        File file = saveFileChooser.getSelectedFile();
        if (saveFileChooser.getFileFilter() == jpgFilter) {
            file = new File(file.getAbsoluteFile() + ".jpg");
        }
        if (file != null){  // 检查文件名
            if (file.getName().endsWith(".jpg")) {
                try {
                    ImageIO.write(frame.DrawPanel.getImage(),
                            "jpg", file);  // 写入文件
                    JOptionPane.showMessageDialog(frame,
                            "保存成功！");
                } catch (IOException ae) {
                    JOptionPane.showMessageDialog(frame,
                            "保存出错！请重试！");
                    ae.printStackTrace();
                }
            }
        }
    }

    // 新建文件
    public static void newone(ImageFrame frame) {
        if (frame.DrawPanel.abstractTools.size() != 1) { // 画布非空
            int yn = JOptionPane.showConfirmDialog(frame,"画布不空,是否保存文件?",
                    "保存文件", JOptionPane.YES_NO_OPTION);
            if (yn == JOptionPane.YES_OPTION) {
                saveFile(frame);
            }
            else {
                frame.DrawPanel.abstractTools.clear();  // 清空画板
                frame.DrawPanel.setBgImage(null);   // 清空背景
            }
            frame.repaint();    // 刷新画板
        }
    }

    // 打开文件
    public static void open(ImageFrame frame) {
        JFileChooser saveFileChooser = new JFileChooser();  // 选择路径，文件名
        FileNameExtensionFilter jpgFilter = new FileNameExtensionFilter("JPG图像", "jpg");
        saveFileChooser.setFileFilter(jpgFilter);
        saveFileChooser.showSaveDialog(frame);
        File file = saveFileChooser.getSelectedFile();
        try {
            Image bgImage = ImageIO.read(file);
            frame.DrawPanel.setBgImage(bgImage);    // 设为背景
            frame.DrawPanel.paint(frame.DrawPanel.getGraphics());   // 重绘
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // about
    public static void about(ImageFrame frame) {
        JOptionPane.showMessageDialog(frame, "by: Rilzob, zzndb", "关于",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
