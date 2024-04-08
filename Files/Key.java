import javax.swing.*;
import java.awt.*;

/**
 *
 * @author nicol
 */

public class Key extends JButton {

    private static final int SIDES = 6;
    private static final int ROTATION = 30;
    private Color borderColor = Color.BLACK;
    
    private String text = "";

    public Key(String letter, JTextArea textArea, int fontSize, ClavierFrame clavierFrame) {
        Font police = new Font("Arial", Font.PLAIN, fontSize);
        setFont(police);
        setText(letter);
        setContentAreaFilled(false);
        
        addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                text = textArea.getText();
                switch (letter) {
                    case "_":
                        text += " ";
                        clavierFrame.reset();
                        break;
                    case "\u2190":
                        if (text.length()>0) {
                            text = text.substring(0, text.length() - 1);
                            clavierFrame.supp();
                        }
                        
                        break;
                    default:
                        text += letter;
                        clavierFrame.predict(letter);
                        break;
                }
                
                textArea.setText(text);
            }
        });
    }

    public void changeBorderColor(Color color) {
        borderColor = color;
    }

    @Override
    protected void paintComponent(Graphics g) {

        if (getModel().isPressed()) {
            g.setColor(Color.LIGHT_GRAY);
        } else {
            g.setColor(Color.white);
        }
        

        int[] xPoints = new int[SIDES];
        int[] yPoints = new int[SIDES];
        int buttonWidth = getWidth()-1;
        int buttonHeight = getHeight()-1;

        // Calculer les points pour créer un hexagone
        for (int i = 0; i < SIDES; i++) {
            double angle = Math.toRadians(i * 360.0 / SIDES + ROTATION);
            xPoints[i] = (int) (buttonWidth / 2 + (buttonWidth / 2) * Math.sin(angle));
            yPoints[i] = (int) (buttonHeight / 2 + (buttonHeight / 2) * Math.cos(angle));
        }
        
        Graphics2D g2d = (Graphics2D) g;

        g2d.setStroke(new BasicStroke(2f));

        g2d.fillPolygon(xPoints, yPoints, SIDES);
        g2d.setColor(borderColor);
        g2d.drawPolygon(xPoints, yPoints, SIDES);

        super.paintComponent(g2d);
    }
}
