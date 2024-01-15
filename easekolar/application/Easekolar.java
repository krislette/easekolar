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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import easekolar.listeners.AddTaskListener;
import easekolar.listeners.HoverListener;
import easekolar.listeners.SidebarListener;
import easekolar.tasks.Task;

public class Easekolar extends JFrame {

    private ArrayList<Task> arrTasks = new ArrayList<Task>();
    private Integer intTaskCount = 0;

    private JPanel pnlHeader, pnlSidebar, pnlBody;
    private JPanel pnlAllTasksPanel, pnlButtonPanel;
    private JPanel pnlTaskPanel;

    private JButton btnAddTask;
    private JButton[] arrSubjectButtons;

    private JScrollPane scrTaskList;
    private JScrollPane scrTaskPane;

    private Color clrLightPink = new Color(255, 182, 193);
    private Color clrDarkPink = new Color(204, 108, 122);

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
        setBackground(clrDarkPink);

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
        pnlHeader.setBackground(Color.WHITE);
        pnlHeader.setPreferredSize(new Dimension(getWidth(), 80));

        JPanel headerContentPanel = new JPanel();
        headerContentPanel.setOpaque(false);
    
        JLabel lblHeaderTitle = new JLabel("Header");
        lblHeaderTitle.setForeground(Color.BLACK);
        lblHeaderTitle.setFont(new Font("Consolas", Font.BOLD, 77));
    
        headerContentPanel.add(lblHeaderTitle);
        pnlHeader.add(headerContentPanel, BorderLayout.CENTER);
    }

    public void makeSidebar() {
        pnlSidebar = new JPanel(new FlowLayout());
        pnlSidebar.setBackground(clrDarkPink);
        pnlSidebar.setPreferredSize(new Dimension(250, getHeight()));
        JLabel lblTitle = new JLabel("EASEkolar");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Consolas", Font.BOLD, 30));
        pnlSidebar.add(lblTitle);

        arrSubjectButtons = new JButton[arrSidebarTitles.length];

        for (int i = 0; i < arrSubjectButtons.length; i++) {
            arrSubjectButtons[i] = new JButton(arrSidebarTitles[i]);
            arrSubjectButtons[i].setBorder(null);
            arrSubjectButtons[i].setBackground(Color.WHITE);
            arrSubjectButtons[i].addActionListener(new SidebarListener(this));
            arrSubjectButtons[i].addMouseListener(new HoverListener());
            arrSubjectButtons[i].setPreferredSize(new Dimension(230, 50));
            arrSubjectButtons[i].setFocusable(false);
            pnlSidebar.add(arrSubjectButtons[i]);
        }
    }

    public void makeMainPanel() {
        pnlBody = new JPanel(new BorderLayout());
        pnlBody.setBackground(clrLightPink);
        pnlBody.setPreferredSize(new Dimension(250, 250));
        pnlBody.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    public void makeTaskPanel() {
        pnlAllTasksPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        pnlAllTasksPanel.setBackground(clrLightPink);
        scrTaskList = new JScrollPane(pnlAllTasksPanel);
        scrTaskList.setBorder(null);
        scrTaskList.setPreferredSize(new Dimension(250, 440));
        scrTaskList.setVerticalScrollBarPolicy(ScrollPaneConstants
                                               .VERTICAL_SCROLLBAR_AS_NEEDED);
    }

    public void makeButtonPanel() {
        btnAddTask = new JButton("+ Add Task");
        btnAddTask.setBorder(null);
        btnAddTask.setBackground(clrDarkPink);
        btnAddTask.setForeground(Color.BLACK);
        btnAddTask.setPreferredSize(new Dimension(getWidth(), 50));
        btnAddTask.setFocusable(false);
        btnAddTask.addActionListener(new AddTaskListener(this));
        btnAddTask.addMouseListener(new HoverListener());
        pnlButtonPanel = new JPanel(new BorderLayout());
        pnlButtonPanel.setBackground(clrLightPink);
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
        pnlTaskPanel.setPreferredSize(new Dimension(200, 100));
        pnlTaskPanel.setBackground(clrDarkPink);

        JTextField txtTaskName = new JTextField(strTaskName);
        txtTaskName.setBorder(null);
        txtTaskName.setFont(new Font("Consolas", Font.BOLD, 15));
        txtTaskName.setEditable(true);
        txtTaskName.setBackground(clrDarkPink);

        JTextArea txtTaskDescription = new JTextArea(strTaskDescription);
        txtTaskDescription.setBorder(null);
        txtTaskDescription.setFont(new Font("Consolas", Font.PLAIN, 12));
        txtTaskDescription.setLineWrap(true);
        txtTaskDescription.setWrapStyleWord(true);
        txtTaskDescription.setEditable(true);

        scrTaskPane = new JScrollPane(txtTaskDescription);
        scrTaskPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        pnlTaskPanel.add(txtTaskName, BorderLayout.NORTH);
        pnlTaskPanel.add(scrTaskPane, BorderLayout.CENTER);
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