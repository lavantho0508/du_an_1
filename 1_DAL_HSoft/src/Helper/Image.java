/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;


import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;

/**
 *
 * @author BUI_QUANG_HIEU
 */
public class Image {
    public static java.awt.Image getAppIcon(){
         URL url = Image.class.getResource("/Icon/logo.png");
         return new ImageIcon(url).getImage();
    }
    
    public static void save(File src){
        File dst = new File("images", src.getName());
        if (!dst.getParentFile().exists()) {
            dst.getParentFile().mkdirs();
        }
        try {
            Path from = Paths.get(src.getAbsolutePath());
            Path to = Paths.get(dst.getAbsolutePath());
            Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
    public static ImageIcon read(String fileName){
        File path = new File("logos", fileName);
        return new ImageIcon(path.getAbsolutePath());
    }
}
