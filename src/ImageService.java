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


    // 保存图形文件程序段
//    public void saveFile(JPanel panel){
//        JFileChooser fileChooser = new JFileChooser();
//        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
//        int result = fileChooser.showSaveDialog(panel);
//        if(result == JFileChooser.CANCEL_OPTION)
//            return ;
//        File fileName = fileChooser.getSelectedFile();
//        // fileName.canWrite();
//        if (fileName == null || fileChooser.getName().equals("")){
//            JOptionPane.showMessageDialog(fileChooser, "文件名无效", "文件名无效", JOptionPane.ERROR_MESSAGE);
//        } else {
//            try {
//                // fileName.delete();
//                FileOutputStream fos = new FileOutputStream(fileName);
//                ObjectOutputStream output = new ObjectOutputStream(fos);
//            } catch (IOException ioe){
//                ioe.printStackTrace();
//            }
//        }
//    }

    // 保存文件
    public static File saveFile(ImageFrame frame){
        JFileChooser saveFileChooser = new JFileChooser();
        FileNameExtensionFilter jpgFilter = new FileNameExtensionFilter("JPG图像", "jpg");
        FileNameExtensionFilter savFilter = new FileNameExtensionFilter("SAV文件", "sav");
        saveFileChooser.setFileFilter(savFilter);
        saveFileChooser.setFileFilter(jpgFilter);
        saveFileChooser.showSaveDialog(frame);
        File file = saveFileChooser.getSelectedFile();
        if (saveFileChooser.getFileFilter() == jpgFilter) {
            file = new File(file.getAbsoluteFile() + ".jpg");
        } else if (saveFileChooser.getFileFilter() == savFilter){
//            if (!file.getName().endsWith(".sav")) {
                file = new File(file.getAbsoluteFile() + ".sav");
//            }
        }
        return file;
    }
}
