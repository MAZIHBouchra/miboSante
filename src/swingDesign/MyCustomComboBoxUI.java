/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swingDesign;


 
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import java.awt.*;


public class MyCustomComboBoxUI extends BasicComboBoxUI {

    @Override
    protected ComboPopup createPopup() {
        BasicComboPopup popup = new BasicComboPopup(comboBox) {
            @Override
            protected void paintBorder(Graphics g) {
                // Ici, vous dessinez une bordure personnalisée
                g.setColor(Color.BLUE); // Exemple: bordure bleue
                g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 5, 5); // Forme arrondie
            }
        };
        popup.setOpaque(true); // Pour appliquer la couleur du fond
        popup.setBackground(Color.LIGHT_GRAY); // Exemple: fond gris clair
        return popup;
    }

    @Override
    public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {
        // Vous pouvez customiser l'arrière-plan de l'élément sélectionné ici
        g.setColor(Color.CYAN);
        g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
    }
}

