/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FormInformation;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author AdMin
 */
public class GradiantColor extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Your gradient colors
        Color color1 = new Color(0xEF, 0xE9, 0xF4); // #EFE9F4
        Color color2 = new Color(0x50, 0x78, 0xF2); // #5078F2

        int width = getWidth();
        int height = getHeight();

        GradientPaint gradient = new GradientPaint(0, 0, color1, width, height, color2); 
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, width, height);
    }
}
