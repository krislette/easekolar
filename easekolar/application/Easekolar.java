package easekolar.application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import easekolar.listeners.AddTaskListener;
import easekolar.listeners.HoverListener;
import easekolar.listeners.SidebarListener;
import easekolar.tasks.Task;

public class Easekolar extends JFrame {

    private ArrayList<Task> arrTasks = new ArrayList<Task>();
    private JPanel pnlHeader, pnlSidebar, pnlBody;
    private JPanel pnlAllTasksPanel, pnlButtonPanel;
    private JPanel pnlTaskPanel;
    private JButton btnAddTask;
    private JButton[] arrSubjectButtons;
    private JScrollPane scrTaskList;
    private Integer intTaskCount = 0;
    private String[] arrSidebarTitles = {"Home",
                                        "DS2", 
                                        "DSA", 
                                        "OOP", 
                                        "LogDes", 
                                        "ModSim", 
                                        "Elective", 
                                        "Ethics", 
                                        "PathFit"};

    public Easekolar() {
        super("EASEkolar");
        setSize(1000, 650);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon imgFavicon = new ImageIcon("easekolar/logo.png");
        setIconImage(imgFavicon.getImage());

        makeHeader();
        makeSidebar();
        makeMainPanel();
        makeButtonPanel();
        makeTaskPanel();
        addComponents();
        // loadHome(); // TODO: There is still no retrieval feature so this is useless but make it useful!!!
        setVisible(true);
    }

    public void makeHeader() {
        pnlHeader = new JPanel(new BorderLayout());
        pnlHeader.setBackground(Color.BLACK);
        pnlHeader.setPreferredSize(new Dimension(getWidth(), 80));
    }

    public void makeSidebar() {
        pnlSidebar = new JPanel(new FlowLayout());
        pnlSidebar.setBackground(Color.BLACK);
        pnlSidebar.setPreferredSize(new Dimension(250, getHeight()));
        JLabel lblTitle = new JLabel("LOGO");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Consolas", Font.BOLD, 77));
        pnlSidebar.add(lblTitle);

        arrSubjectButtons = new JButton[arrSidebarTitles.length];

        for (int i = 0; i < arrSubjectButtons.length; i++) {
            arrSubjectButtons[i] = new JButton(arrSidebarTitles[i]);
            arrSubjectButtons[i].addActionListener(new SidebarListener(this));
            arrSubjectButtons[i].addMouseListener(new HoverListener());
            arrSubjectButtons[i].setPreferredSize(new Dimension(230, 50));
            arrSubjectButtons[i].setFocusable(false);
            pnlSidebar.add(arrSubjectButtons[i]);
        }
    }

    public void makeMainPanel() {
        pnlBody = new JPanel(new BorderLayout());
        pnlBody.setBackground(Color.WHITE);
        pnlBody.setPreferredSize(new Dimension(250, 250));
        pnlBody.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    public void makeTaskPanel() {
        pnlAllTasksPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        pnlAllTasksPanel.setBackground(Color.WHITE);
        scrTaskList = new JScrollPane(pnlAllTasksPanel);
        scrTaskList.setPreferredSize(new Dimension(250, 440));
        scrTaskList.setVerticalScrollBarPolicy(ScrollPaneConstants
                                               .VERTICAL_SCROLLBAR_AS_NEEDED);
    }

    public void makeButtonPanel() {
        btnAddTask = new JButton("+ Add Task");
        btnAddTask.setPreferredSize(new Dimension(getWidth(), 50));
        btnAddTask.setFocusable(false);
        btnAddTask.addActionListener(new AddTaskListener(this));
        btnAddTask.addMouseListener(new HoverListener());
        pnlButtonPanel = new JPanel(new BorderLayout());
        pnlButtonPanel.setBackground(Color.WHITE);
        pnlButtonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
    }

    public void addComponents() {
        add(pnlSidebar, BorderLayout.WEST);
        add(pnlBody, BorderLayout.CENTER);

        pnlButtonPanel.add(btnAddTask, BorderLayout.NORTH);
        pnlBody.add(pnlHeader, BorderLayout.NORTH);
        pnlBody.add(pnlButtonPanel, BorderLayout.CENTER);
        pnlBody.add(scrTaskList, BorderLayout.SOUTH);
    }

    // Helper method for addTaskPanel and loadHome method.
    public void createTaskPanels(String strTaskName, String strTaskDescription) {
        pnlTaskPanel = new JPanel(new BorderLayout());
        pnlTaskPanel.setPreferredSize(new Dimension(50, 50));
        pnlTaskPanel.setBackground(Color.LIGHT_GRAY);

        JLabel lblTaskName = new JLabel(strTaskName);
        JLabel lblTaskDescription = new JLabel(strTaskDescription);
        pnlTaskPanel.add(lblTaskName, BorderLayout.NORTH);
        pnlTaskPanel.add(lblTaskDescription, BorderLayout.CENTER);
        pnlAllTasksPanel.add(pnlTaskPanel);

        revalidate();
        repaint();
    }
    
    public void addTaskPanel(String strTaskName, String strTaskDescription) {
        createTaskPanels(strTaskName, strTaskDescription);
        arrTasks.add(new Task(strTaskName, strTaskDescription));
        intTaskCount++;
    }

    public void loadHome() {
        for (Task tskTask : arrTasks) {
            createTaskPanels(tskTask.getTaskName(), tskTask.getTaskDescription());
        }
    }

    public void checkScrollBar() {
        if (intTaskCount > 8) {
            scrTaskList.setVerticalScrollBarPolicy(ScrollPaneConstants
                                                   .VERTICAL_SCROLLBAR_ALWAYS);
        } else {
            scrTaskList.setVerticalScrollBarPolicy(ScrollPaneConstants
                                                   .VERTICAL_SCROLLBAR_AS_NEEDED);
        }
    }

    /* vvvvvvvvvvvvvvvvvvvvvvvvvvvvvv GETTERS FOR SOME COMPONENTS vvvvvvvvvvvvvvvvvvvvvvvvvvvvvv */
    public JPanel getSubjectPanel() {
        return pnlAllTasksPanel;
    }

    public String getSidebarTitles(int intTitleIndex) {
        return arrSidebarTitles[intTitleIndex];
    }

}