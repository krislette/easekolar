package easekolar.tasks;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.TextArea;
import java.awt.TextField;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import easekolar.application.Easekolar;
import easekolar.listeners.HoverListener;
import easekolar.listeners.DialogListener;

public class AddTaskDialog extends JDialog {

    public Easekolar sklMainFrame;
    public JPanel pnlSubjectTags;
    public JPanel pnlMiscTags;
    public JPanel pnlAddTask;
    public JLabel lblTaskName, lblDescription;
    public JLabel lblSubjectTags, lblMiscTags;
    public JLabel lblDeadline;
    public TextField txtTaskName;
    public TextArea txtTaskDescription;
    public JButton[] arrSubjectTags;
    public JButton[] arrMiscTags;
    public JButton btnAddTask;
    public String[] arrMiscTitles = {"Project", 
                                     "Activity", 
                                     "Quizzes", 
                                     "Assignment", 
                                     "Exam"};

    public AddTaskDialog(Easekolar sklMainFrame) {
        super(sklMainFrame, "New Task", true);
        this.sklMainFrame = sklMainFrame;

        setSize(700, 450);
        setLocationRelativeTo(sklMainFrame);
        setBackground(Color.WHITE);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        
        // Transparent cover because I am fucking poor at logic.
        JPanel coverPanel = new JPanel();
        coverPanel.setBackground(new Color(0, 0, 0, 150));
        coverPanel.setSize(sklMainFrame.getSize());
        coverPanel.setLocation(1, 0); // I TRIED MY BEST HERE PLEASE.
        
        // This block is for ADDING CONTENT... wip!
        makeAddTaskPanel();
        
        // Adding the cover panel to the main frame...
        sklMainFrame.getLayeredPane().add(coverPanel, JLayeredPane.PALETTE_LAYER);
        sklMainFrame.repaint();

        // After retries... apparently, this should be BELOW I am an idiot.
        setVisible(true);

        // Remove the cover panel after closing the pop-up.
        sklMainFrame.getLayeredPane().remove(coverPanel);
        sklMainFrame.repaint();
    }

    public void makeAddTaskPanel() {
        makeTaskName();
        makeTaskDescription();

        getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT));
        getContentPane().add(lblTaskName);
        getContentPane().add(txtTaskName);
        getContentPane().add(lblDescription);
        getContentPane().add(txtTaskDescription);

        makeSubjectTagsPanel();
        getContentPane().add(pnlSubjectTags);

        makeMiscTagsPanel();
        getContentPane().add(pnlMiscTags);

        makeAddTaskButton();
        getContentPane().add(pnlAddTask);
    }

    public void makeTaskName() {
        lblTaskName = new JLabel("Task Name");
        txtTaskName = new TextField();
        txtTaskName.setPreferredSize(new Dimension(675, 25));
    }

    public void makeTaskDescription() {
        lblDescription = new JLabel("Task Description");
        txtTaskDescription = new TextArea();
        txtTaskDescription.setPreferredSize(new Dimension(675, 150));
    }

    // TODO: MAKE A HELPER METHOD FOR SUBJECT TAGS AND MISC TAGS
    public void makeSubjectTagsPanel() {
        arrSubjectTags = new JButton[8];
        lblSubjectTags = new JLabel("Subject Tags");
        getContentPane().add(lblSubjectTags);

        pnlSubjectTags = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlSubjectTags.setPreferredSize(new Dimension(675, 30));

        for (int i = 0; i < arrSubjectTags.length; i++) {
            arrSubjectTags[i] = new JButton(sklMainFrame.getSidebarTitles(i + 1));
            arrSubjectTags[i].addActionListener(null); // ! Don't forget to fix the action listener for this.
            arrSubjectTags[i].addMouseListener(new HoverListener());
            arrSubjectTags[i].setPreferredSize(new Dimension(79, 20));
            arrSubjectTags[i].setFocusable(false);
            pnlSubjectTags.add(arrSubjectTags[i]);
        }
    }

    public void makeMiscTagsPanel() {
        arrMiscTags = new JButton[5];
        lblMiscTags = new JLabel("Other Tags");
        getContentPane().add(lblMiscTags);

        pnlMiscTags = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlMiscTags.setPreferredSize(new Dimension(675, 30));

        for (int i = 0; i < arrMiscTags.length; i++) {
            arrMiscTags[i] = new JButton(arrMiscTitles[i]);
            arrMiscTags[i].addActionListener(null); // ! Don't forget to fix the action listener for this.
            arrMiscTags[i].addMouseListener(new HoverListener());
            arrMiscTags[i].setPreferredSize(new Dimension(79, 20));
            arrMiscTags[i].setFocusable(false);
            pnlMiscTags.add(arrMiscTags[i]);
        }
    }

    public void makeAddTaskButton() {
        btnAddTask = new JButton("Add Task");
        btnAddTask.setPreferredSize(new Dimension(500, 50));
        btnAddTask.addMouseListener(new HoverListener());
        btnAddTask.addActionListener(new DialogListener(this, sklMainFrame));
        btnAddTask.setFocusable(false);

        pnlAddTask = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnlAddTask.setPreferredSize(new Dimension(675, 60));
        pnlAddTask.add(btnAddTask);
    }

}