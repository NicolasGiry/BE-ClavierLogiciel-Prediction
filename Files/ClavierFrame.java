import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Nicolas GIRY
 */
public class ClavierFrame extends javax.swing.JFrame {
    
    private static final int NB_KEYS = 32;
    private List<String> letters = new ArrayList<>(List.of("E", "A", "I", "S", "N", "R", "T", "O", "L", "U", "D", "C", "M",
                     "P", "G", "B", "V", "H", "F", "Q", "Y", "X", "J", "K", "W", "Z", ".", ",", "?", "!", "_", "\u2190"));
    
    private int keyWidth = 75;
    private int keyHeight = 75;
    private int nbLetters = 3;

    private int[] x = {2*keyWidth,(int) (2.5*keyWidth), 3*keyWidth, 3 * keyWidth, (int) (2.5 * keyWidth), 2 * keyWidth,
        keyWidth, keyWidth, 2 * keyWidth, (int) (2.5 * keyWidth), 3 * keyWidth, (int) (3.5 * keyWidth), 
        (int) (3.5 * keyWidth), (int) (3.5 * keyWidth), 3 * keyWidth, (int) (2.5 * keyWidth), 2 * keyWidth, 
        keyWidth, keyWidth, 2 * keyWidth, 3 * keyWidth, (int) (3.5 * keyWidth), (int) (3.5 * keyWidth), 
        3 * keyWidth, 2 * keyWidth, keyWidth, keyWidth / 2, keyWidth / 2, 4 * keyWidth, 4 * keyWidth, 
        (int) (2.5 * keyWidth), 4 * keyWidth};

    private int[] y = {(int) (2.5 * keyHeight), 2 * keyHeight, (int) (2.5 * keyHeight),(int) (3.5 * keyHeight), 4 * keyHeight,(int) (3.5 * keyHeight),
        (int) (3 * keyHeight), 2 * keyHeight, (int) (1.5 * keyHeight), keyHeight, (int) (1.5 * keyHeight), 2 * keyHeight,
        (int) (3 * keyHeight), 4 * keyHeight, (int) (4.5 * keyHeight), 5 * keyHeight, (int) (4.5 * keyHeight), 4 * keyHeight,
        keyHeight, keyHeight / 2, keyHeight / 2, keyHeight, 5 * keyHeight, (int) (5.5 * keyHeight), (int) (5.5 * keyHeight),
        5 * keyHeight,(int) (3.5 * keyHeight), (int) (2.5 * keyHeight), (int) (2.5 * keyHeight), (int) (3.5 * keyHeight), (int) (3 * keyHeight), (int) (1.5*keyHeight)};

    private Predictor predicteur = new Predictor();
    private Tree arbre; 

    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton[] keys;
    private javax.swing.JButton settingsButton;
     
    public ClavierFrame() {
        predicteur.createTree();
        arbre = predicteur.getRacine();
        initSelfComponents();
        setSize(1150, 1000);
        setResizable(false);
        setTitle("Clavier Logiciel");
        ImageIcon icon = new ImageIcon("icone.png");
        setIconImage(icon.getImage());
    }
    
    private void initSelfComponents() {
        jPanel1 = new javax.swing.JPanel();
        jTextArea1 = new javax.swing.JTextArea();
        ImageIcon settingIcon = new ImageIcon("setting.png");
        Image image = settingIcon.getImage();
        Image newImage = image.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon settingIconScaled = new ImageIcon(newImage);
        settingsButton = new JButton();
        settingsButton.setIcon(settingIconScaled);
        settingsButton.setBorderPainted(false);
        settingsButton.setContentAreaFilled(false);
        Settings settingsMenu = new Settings(this);

        settingsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                settingsMenu.show(settingsButton, 0, settingsButton.getHeight());
            }
        });

        settingsButton.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentMoved(ComponentEvent e) {
                // permet de convertir les coordonnées par rapport au button aux coordonnées de ClavierFrame
                Point newLocation = SwingUtilities.convertPoint(settingsButton, 0, settingsButton.getHeight(), ClavierFrame.this);
                settingsMenu.setLocation(newLocation.x, newLocation.y);
            }
        });

        keys = new javax.swing.JButton[NB_KEYS];
        for (int i=0; i<NB_KEYS; i++) {
            keys[i] = new Key(letters.get(i), jTextArea1, keyWidth/4, this);
            keys[i].setPreferredSize(new Dimension(keyWidth, keyHeight));
        }
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextArea1.setColumns(94);
        jTextArea1.setRows(5);
        jTextArea1.setLineWrap(true);
        jTextArea1.setWrapStyleWord(true);
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane(jTextArea1);


        JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        textPanel.add(jScrollPane1);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(settingsButton);

        JPanel containerPanel = new JPanel(new BorderLayout());
        containerPanel.add(textPanel, BorderLayout.WEST);
        containerPanel.add(buttonPanel, BorderLayout.EAST);
        
        jPanel1.setLayout(new GridBagLayout());
        jPanel1.add(containerPanel);

        setKeySize(75);


        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    //.addComponent(jScrollPane1))
                    .addComponent(containerPanel)
                ).addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                //.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(containerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        
        pack();

        updateClavier();
    }

    public void predict(String letter) {
        arbre = arbre.goTo(letter);
        if (arbre == null) {
            reset();
        }
    }

    public void reset() {
        arbre = predicteur.getRacine();
    }

    public void supp() {
        Tree parent = arbre.getparent();
        if (parent != null) {
            arbre = parent;
        } else {
            reset();
        }
    }

    public void updateClavier() {
        letters = predicteur.updateClavier(letters, arbre.predictNext(true), nbLetters);
        
        for (int i=0; i<NB_KEYS; i++) {
            ((Key) keys[i]).changeLetter(letters.get(i));
        }
    }

    public void setKeySize(int size) {
        keyWidth = size;
        keyHeight = size;

        for (int i = 0; i < NB_KEYS; i++) {
            keys[i].setPreferredSize(new Dimension(keyWidth, keyHeight));
        }

        jPanel1.removeAll();
        // GridBagConstraints constraintsSettingsButton = new GridBagConstraints();
        // constraintsSettingsButton.anchor = GridBagConstraints.NORTHEAST;
        // constraintsSettingsButton.gridwidth = GridBagConstraints.REMAINDER;
        // constraintsSettingsButton.gridy = 0;
        // jPanel1.add(settingsButton, constraintsSettingsButton);

        for (int i = 0; i < NB_KEYS; i++) {
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = x[i];
            constraints.gridy = y[i];
            constraints.insets = new Insets(0, 0, (int) -(keyHeight / 2 + 3), (int) -(keyWidth / 10 + 3));

            if (i <= 5) {
                ((Key) keys[i]).changeBorderColor(Color.DARK_GRAY);
            } else if (i <= 17) {
                ((Key) keys[i]).changeBorderColor(Color.GRAY);
            } else if (i < 30) {
                ((Key) keys[i]).changeBorderColor(Color.lightGray);
            } else {
                ((Key) keys[i]).changeBorderColor(Color.BLACK);
            }

            jPanel1.add(keys[i], constraints);
        }

        jPanel1.revalidate();
        jPanel1.repaint();
    }

    public void setNbPredicted(int nb) {
        nbLetters = nb;
        updateClavier();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ClavierFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClavierFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClavierFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClavierFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClavierFrame().setVisible(true);
            }
        });
    }


}
