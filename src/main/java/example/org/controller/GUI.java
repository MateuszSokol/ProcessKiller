package example.org.controller;

import example.org.service.KillProcess;
import example.org.utils.ScreenManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    private JFrame mainWindow;
    private JPanel mainPanel;
    private static final int FRAME_WIDTH = 200;
    private static final int FRAME_HEIGHT = 150;
    private final ScreenManager screenManager;
    private final KillProcess killProcess = new KillProcess();
    private JTextField userInputProcessNameTextField = new JTextField("NostaleClientX.exe");// set default text value
    private JButton executeAppButton;

    public GUI()
    {
        screenManager = new ScreenManager();
        createMainWindow();
        createMainPanel();
        centreMainWindow();
        addComponentSToPanel();
        registerEventHandlers();
        configureWindow();

    }

    private void createMainWindow() {
        mainWindow = new JFrame();
        mainWindow.setTitle("Close Process App");
        mainWindow.setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void centreMainWindow()
    {
        Point middle = screenManager.getCenterPosition(FRAME_WIDTH,FRAME_HEIGHT);
        mainWindow.setLocation(middle);
    }

    private void configureWindow() {
        mainWindow.add(mainPanel);
        mainWindow.setVisible(true);
    }

    private void createMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        mainPanel.setLayout(new BorderLayout());
    }

    private void addComponentToPanel(JPanel panel, Component component, String direction) {
        // Dodanie komponentu do panelu, używając przekazanej lokalizacji
        panel.add(component, direction);
    }

    private void modifyButtonProperties(JButton button)
    {
        button.setPreferredSize(new Dimension(180, 60));
        button.setFont(new Font("Arial", Font.BOLD, 11));
        button.setBackground(Color.LIGHT_GRAY);
        button.setFocusPainted(false);
    }
    private JPanel wrapComponentIntoPanel(LayoutManager layoutManager,Component component)
    {
        JPanel panel = new JPanel(layoutManager);
        panel.add(component);
        return panel;
    }

    private JLabel createInstructionLabel(String text)
    {
        return new JLabel(text);
    }

    private JPanel createStyledButtonPanel() {
        executeAppButton = new JButton("Click me!");
        modifyButtonProperties(executeAppButton);
        return wrapComponentIntoPanel(new FlowLayout(), executeAppButton);
    }

    private void addComponentSToPanel()
    {
        JLabel theProcessName = createInstructionLabel("Enter the process name");
        JPanel wrappedButtonPanel = createStyledButtonPanel();


        addComponentToPanel(mainPanel,theProcessName,BorderLayout.NORTH);
        addComponentToPanel(mainPanel,wrappedButtonPanel,BorderLayout.CENTER);
        addComponentToPanel(mainPanel,userInputProcessNameTextField,BorderLayout.SOUTH);

    }
    public String getProcessNameInput()
    {
        return userInputProcessNameTextField.getText();
    }
    private void registerEventHandlers()
    {
        executeAppButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String processName = getProcessNameInput();
                boolean result = killProcess.killProcessByName(processName);
                if(result)
                {
                    JOptionPane.showMessageDialog(mainWindow,"Proces zakończony "+ processName);
                }
                else
                {
                    JOptionPane.showMessageDialog(mainWindow,"Nie udało się zakończyc procesu" + processName);
                }

            }
        });
    }

}
