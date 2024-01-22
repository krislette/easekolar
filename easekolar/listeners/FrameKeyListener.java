package easekolar.listeners;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import easekolar.application.Easekolar;
import easekolar.tasks.AddTaskDialog;

public class FrameKeyListener implements KeyListener {

    private Easekolar sklMainFrame;
    private Color clrLightPink = new Color(255, 182, 193);

    public FrameKeyListener(Easekolar sklMainFrame) {
        this.sklMainFrame = sklMainFrame;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // throw new UnsupportedOperationException("Unimplemented method 'keyPressed'");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Character chrKey = e.getKeyChar();

        if (chrKey == '1') {
            JPanel pnlSelectedPanel = makeSubjectPanel("DS2");
            switchToPanel(pnlSelectedPanel);
        } else if (chrKey == '2') {
            JPanel pnlSelectedPanel = makeSubjectPanel("DSA");
            switchToPanel(pnlSelectedPanel);
        } else if (chrKey == '3') {
            JPanel pnlSelectedPanel = makeSubjectPanel("OOP");
            switchToPanel(pnlSelectedPanel);
        } else if (chrKey == '4') {
            JPanel pnlSelectedPanel = makeSubjectPanel("LogDes");
            switchToPanel(pnlSelectedPanel);
        } else if (chrKey == '5') {
            JPanel pnlSelectedPanel = makeSubjectPanel("ModSim");
            switchToPanel(pnlSelectedPanel);
        } else if (chrKey == '6') {
            JPanel pnlSelectedPanel = makeSubjectPanel("Elective");
            switchToPanel(pnlSelectedPanel);
        } else if (chrKey == '7') {
            JPanel pnlSelectedPanel = makeSubjectPanel("Ethics");
            switchToPanel(pnlSelectedPanel);
        } else if (chrKey == '8') {
            JPanel pnlSelectedPanel = makeSubjectPanel("PathFit");
            switchToPanel(pnlSelectedPanel);
        } else if (chrKey == 'a') {
            new AddTaskDialog(sklMainFrame);
        } else if (chrKey == 'h') {
            sklMainFrame.getSubjectPanel().removeAll();
            sklMainFrame.loadHome(); // TODO: Edit this so it retrieves the tasks.
            sklMainFrame.revalidate();
            sklMainFrame.repaint();
        }
    }

    // TODO: PUT CONTENT.
    public JPanel makeSubjectPanel(String contentText) {
        JPanel pnlSwitchedPanel = new JPanel();
        pnlSwitchedPanel.add(new JLabel(contentText));
        pnlSwitchedPanel.setBackground(clrLightPink);
        return pnlSwitchedPanel;
    }

    public void switchToPanel(JPanel pnlSwitchedPanel) {
        sklMainFrame.getSubjectPanel().removeAll();
        sklMainFrame.getSubjectPanel().add(pnlSwitchedPanel, BorderLayout.CENTER);
        sklMainFrame.revalidate();
        sklMainFrame.repaint();
    }

}