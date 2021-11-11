/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author ADMIN
 */
public class SlideShow extends Thread {

    private JLabel jLabel;

    public SlideShow(JLabel jLabel) {
        this.jLabel = jLabel;
    }

    @Override
    public void run() {
        int i = 0;
        String line = "   CHÀO BẠN ĐẾN VỚI HỆ THỐNG QUẢN LÝ ĐÀO TẠO EDUSYS by QUANG HIẾU   ";
        while (true) {
            i++;
            line = line.substring(1, line.length()) + line.charAt(0);
//            line = line.charAt(line.length()-1)+line.substring(0,line.length()-1);
            jLabel.setText(line);
            if (i == line.length()) {
                try {
                    sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SlideShow.class.getName()).log(Level.SEVERE, null, ex);
                }
                i = 0;
            }

            try {
                Thread.sleep(200);

            } catch (InterruptedException ex) {
                Logger.getLogger(SlideShow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
