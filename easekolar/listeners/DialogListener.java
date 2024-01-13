package easekolar.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import easekolar.application.Easekolar;
import easekolar.tasks.AddTaskDialog;

public class DialogListener implements ActionListener {

    private AddTaskDialog addDialog;
    private Easekolar sklMainFrame;

    // Constructor for the dialog listener.
    public DialogListener(AddTaskDialog addDialog, Easekolar sklMainFrame) {
        this.addDialog = addDialog;
        this.sklMainFrame = sklMainFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btnClicked = (JButton) e.getSource();
        String strButtonText = btnClicked.getText();

        // Activates the add task button.
        if (strButtonText.equals("Add Task")) {
            if (isInputBlank()) {
                JOptionPane.showMessageDialog(addDialog, 
                                              "Please type a name or a description.", 
                                              "EASEkolar", JOptionPane.WARNING_MESSAGE);
            } else {
                String strTaskName = addDialog.txtTaskName.getText();
                String strTaskDescription = addDialog.txtTaskDescription.getText();

                sklMainFrame.addTaskPanel(strTaskName, strTaskDescription);
                sklMainFrame.checkScrollBar();

                addDialog.dispose();
                sklMainFrame.setVisible(true);
            }
        }
    }

    // Helper method for checking if the textfield and the text area are both empty.
    public boolean isInputBlank() {
        return addDialog.txtTaskName.getText().isBlank()
               && addDialog.txtTaskDescription.getText().isBlank();
    }

}