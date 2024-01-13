package easekolar.listeners;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import easekolar.application.Easekolar;

public class SidebarListener implements ActionListener {

    private Easekolar sklMainFrame;

    public SidebarListener(Easekolar sklMainFrame) {
        this.sklMainFrame = sklMainFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btnClicked = (JButton) e.getSource();
        String strButtonText = btnClicked.getText();

        if (strButtonText.equals("Home")) {
            sklMainFrame.getSubjectPanel().removeAll();
            sklMainFrame.loadHome(); // TODO: Edit this so it retrieves the tasks.
            sklMainFrame.revalidate();
            sklMainFrame.repaint();
        } else {
            JPanel pnlSelectedPanel = makeSubjectPanel(strButtonText);
            switchToPanel(pnlSelectedPanel);
        }
    }

    // TODO: PUT CONTENT.
    public JPanel makeSubjectPanel(String contentText) {
        JPanel pnlSwitchedPanel = new JPanel();
        pnlSwitchedPanel.add(new JLabel(contentText));
        return pnlSwitchedPanel;
    }

    public void switchToPanel(JPanel pnlSwitchedPanel) {
        sklMainFrame.getSubjectPanel().removeAll();
        sklMainFrame.getSubjectPanel().add(pnlSwitchedPanel, BorderLayout.CENTER);
        sklMainFrame.revalidate();
        sklMainFrame.repaint();
    }

}