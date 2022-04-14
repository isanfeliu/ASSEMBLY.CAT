/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.prcetrencada.assemblycat.presenter;

import cat.prcetrencada.assemblycat.model.entitieslayer.Platform;
import cat.prcetrencada.assemblycat.model.enums.PersistanceTech;
import cat.prcetrencada.assemblycat.model.filter.ArchiveFilter;
import cat.prcetrencada.assemblycat.presenter.fetch.DataFetcherPresenter;
import java.awt.Container;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.GroupLayout; 
import javax.swing.JOptionPane;

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
    
    /**
     * Mètode que neteja les descàrregues
     */
    public static void cleanDownloads(){
        try {
            ArrayList<Platform> platformList=DataFetcherPresenter.getInstance().fetchConfigFileData(PersistanceTech.JSON);
            if (platformList!=null){
                for (Platform platform : platformList) {
                    File file  = new File(platform.getDirectory());
                    File[] listFiles = file.listFiles(new ArchiveFilter());
                    if (listFiles!=null) {
                        for (File listFile : listFiles) {
                            listFile.deleteOnExit();
                        }
                    }
                }
            }
        } catch (IOException | ParseException ex) {
            JOptionPane.showMessageDialog(null, "No s'ha pogut netejar els fitxers.");
        }
    }
}
