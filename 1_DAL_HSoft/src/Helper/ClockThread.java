/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author BUI QUANG HIEU
 */
public class ClockThread extends Thread {
//    private JButton button;

//    private JLabel jLabel;
    private JLabel jLabel1;

    public ClockThread(JLabel jLabel1) {
//        this.jLabel = jLabel;
        this.jLabel1 = jLabel1;
    }

    @Override
    public void run() {
        int timeCapCha = 60;
        while (true) {
            timeCapCha--;

            var st1 = timeCapCha + "s";
            jLabel1.setText(String.valueOf(st1));
//            jLabel.setText(st);
            try {
                Thread.sleep(1000);
                if (timeCapCha == 0) {
                    stop();
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(ClockThread.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
