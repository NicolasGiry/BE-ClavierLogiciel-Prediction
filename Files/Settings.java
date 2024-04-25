import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.Dictionary;
import java.util.Hashtable;

public class Settings extends JPopupMenu {
    private int defaultSize = 75, defaultLetter = 3;

    public Settings(ClavierFrame clavierFrame) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JSlider sizeSlider = new JSlider(JSlider.HORIZONTAL, 50, 150, defaultSize);
        sizeSlider.setMajorTickSpacing(25);
        sizeSlider.setPaintTicks(true);
        sizeSlider.setPaintLabels(true);

        JLabel valueLabel = new JLabel("Key size: "+defaultSize);

        JButton defaultButton = new JButton("default");

        JSlider letterSlider = new JSlider(JSlider.HORIZONTAL, 0, 26, defaultLetter);
        letterSlider.setMajorTickSpacing(1);

        letterSlider.setPaintLabels(true);
        Dictionary<Integer, Component> labelTable = new Hashtable<>();
        for (int i = 0; i <= 6; i++) {
            labelTable.put(i, new JLabel(Integer.toString(i)));
        }
        for (int i = 10; i <= 26; i += 5) {
            labelTable.put(i, new JLabel(Integer.toString(i)));
        }
        letterSlider.setLabelTable(labelTable);

        JLabel letterLabel = new JLabel("Number of predicted letters : "+defaultLetter);

        JButton defaultButtonLetter = new JButton("default");

        sizeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider)e.getSource();
                int newSize = (int) source.getValue();
                valueLabel.setText("Key size: " + newSize);
                clavierFrame.setKeySize(newSize);
            }
        });

        defaultButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                valueLabel.setText("Key size: " + defaultSize);
                sizeSlider.setValue(defaultSize);
                clavierFrame.setKeySize(defaultSize);
            }
        });

        letterSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider)e.getSource();
                int nbLetters = (int) source.getValue();
                letterLabel.setText("Number of predicted letters : " + nbLetters);
                clavierFrame.setNbPredicted(nbLetters);
            }
        });

        defaultButtonLetter.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                valueLabel.setText("Number of predicted letters : " + defaultLetter);
                letterSlider.setValue(defaultLetter);
                clavierFrame.setNbPredicted(defaultLetter);
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(defaultButton);

        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        labelPanel.add(valueLabel);

        JPanel containerPanel = new JPanel(new BorderLayout());
        containerPanel.add(labelPanel, BorderLayout.WEST);
        containerPanel.add(buttonPanel, BorderLayout.EAST);

        JPanel buttonPanelLetter = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanelLetter.add(defaultButtonLetter);

        JPanel labelPanelLetter = new JPanel(new FlowLayout(FlowLayout.LEFT));
        labelPanelLetter.add(letterLabel);

        JPanel containerPanelLetter = new JPanel(new BorderLayout());
        containerPanelLetter.add(labelPanelLetter, BorderLayout.WEST);
        containerPanelLetter.add(buttonPanelLetter, BorderLayout.EAST);

        add(containerPanel);
        add(sizeSlider);
        add(containerPanelLetter);
        add(letterSlider);
    }
}

