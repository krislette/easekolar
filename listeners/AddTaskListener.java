package easekolar.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import easekolar.application.Easekolar;
import easekolar.tasks.AddTaskDialog;

public class AddTaskListener implements ActionListener {

    private Easekolar sklMainFrame;

    // I made a constructor cause apparently... I have to pass the main frame.
    // * Consider the fact of having overloading constructors!!!
    public AddTaskListener(Easekolar sklMainFrame) {
        this.sklMainFrame = sklMainFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btnClicked = (JButton) e.getSource();
        String strButtonText = btnClicked.getText();

        // More buttons will be added so I already used switch case.
        switch (strButtonText) {
            case "+ Add Task":
                new AddTaskDialog(sklMainFrame);
                break;
        }
    }

}