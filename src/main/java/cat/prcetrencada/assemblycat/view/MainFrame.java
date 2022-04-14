/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.prcetrencada.assemblycat.view;

import cat.prcetrencada.assemblycat.presenter.Presenter;
import java.awt.*;
import javax.swing.*;
/**
 * MainFrame Frame
 * @author @Narx221 Projecte 'Ce' Trencada
 */
public class MainFrame extends JFrame{

    private JPanel imgPanel;
    private JLabel imgPanelLabel;
    public JPanel mainPanel;
    public Start startPanel;
    
 public MainFrame(){
        initComponents();
    }
    
    public static void main(String[] args){
        MainFrame main = new MainFrame();
        main.setVisible(true);
    }
    
    private void initComponents(){
        //Initialize vars
        mainPanel = new JPanel();
        imgPanel = new JPanel();
        imgPanelLabel = new JLabel();
        startPanel = new Start();
        Dimension frameDimension=new Dimension(700, 400);
        Dimension leftPanelDimension = new Dimension(200, 400);
          
        //JFrame Properties
        setTitle("Cownloader -  El teu actualitzador de videojocs en català");
        setIconImage(new ImageIcon(getClass().getResource("/icon.jpg")).getImage());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setSize(frameDimension);
        setMinimumSize(frameDimension);
        setName("mainFrame");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        
        //mainPanel Properties
        mainPanel.setBackground(new Color(53, 86, 33));
        mainPanel.setToolTipText(null);
        mainPanel.setAlignmentX(0.0F);
        mainPanel.setAlignmentY(0.0F);
        mainPanel.setMinimumSize(new Dimension(500, 400));
        
        //imgPanel Properties
        imgPanel.setAlignmentX(0.0F);
        imgPanel.setAlignmentY(0.0F);
        imgPanel.setFocusable(false);
        imgPanel.setMinimumSize(leftPanelDimension);
        
        //imgPanelLabel Properties
        imgPanelLabel.setSize(leftPanelDimension);
        imgPanelLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/banner.png"))); 
        
        //Create Group Layout for mainPanel
        GroupLayout mainPanelLayout = new GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        
        //Set mainPanel layout properties
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
            .addComponent(startPanel, GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
            .addComponent(startPanel, GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );

        //Create Group Layout for imgPanel
        GroupLayout imgPanelLayout = new GroupLayout(imgPanel);
        imgPanel.setLayout(imgPanelLayout);
        
        //Set imgPanel layout properties
        imgPanelLayout.setHorizontalGroup(
            imgPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
            .addComponent(imgPanelLabel, GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        imgPanelLayout.setVerticalGroup(
            imgPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
            .addComponent(imgPanelLabel, GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)    
        );
        
        //ContentPanel Layout
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(imgPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(mainPanel, GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(imgPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        mainPanel.add(startPanel);
        imgPanel.setVisible(true);
        imgPanelLabel.setVisible(true);
 
        //start panel
        startPanel.setVisible(true);
        pack();
    }
    
    private void formWindowClosing(java.awt.event.WindowEvent evt){                                  
            Presenter.cleanDownloads();
    }
}