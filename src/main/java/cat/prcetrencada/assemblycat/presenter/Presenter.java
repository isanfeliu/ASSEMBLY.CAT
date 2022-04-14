/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.prcetrencada.assemblycat.presenter;

import java.awt.Container;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.GroupLayout; 

/**
 * Presenter Base
 * @author @Narx221 Projecte 'Ce' Trencada
 */
public class Presenter{
    /**
     * Redirecciona a un JPanel diferent.
     * @param parent
     * @param targetPanel 
     */
    public static void redirect(Container parent, Container targetPanel){
        parent.removeAll();
        GroupLayout mainPanelLayout = new GroupLayout(parent);
        parent.setLayout(mainPanelLayout);
        targetPanel.setVisible(true);
       //Set Panel layout properties
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
            .addComponent(targetPanel, GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
            .addComponent(targetPanel, GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
       
    } 

    /**
     * Sortir del programa.
     */
    public static void exit(){
        System.exit(0);
    }
    
    /**
     * Obre l'hipervincle de la llista de jocs de ce trencada comunitat
     */
    public void openGameList(){
        try {
            Desktop.getDesktop().browse(new URI("https://cetrencada.cat/json/llistat_ce_trencada_comunitat"));
        } catch (IOException | URISyntaxException e1) {}
    }
}
