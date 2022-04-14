/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.prcetrencada.assemblycat.presenter;

import cat.prcetrencada.assemblycat.model.entitieslayer.Game;
import cat.prcetrencada.assemblycat.model.action.DownloadAction;
import cat.prcetrencada.assemblycat.view.Update;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

/**
 * Presenter del Panell de Selecció
 * @author @Narx221 Projecte 'Ce' Trencada
 */
public class SelectionPresenter extends Presenter{
    
    /**
     * Itera la ArrayList de Games pasada per paràmetres i afegeix tantes checkbox com jocs trobats amb action, el link de descàrrega.
     * @param gamesList
     * @param selectionPanel 
     */
    public static void setGameCheckBoxActionMap(ArrayList<Game> gamesList, JPanel selectionPanel){
             for (Game game : gamesList) {
               JCheckBox jCheckBox = new JCheckBox(game.getName());
               DownloadAction downloadAction = new DownloadAction();
               Object gameObj= (Object)game; 
               downloadAction.putValue("game", gameObj);
               ActionMap am = new ActionMap();
               am.put(game.getName(), downloadAction);
               jCheckBox.setActionMap(am);
               selectionPanel.add(game.getName(), jCheckBox);
               selectionPanel.add(game.getName(), jCheckBox);
            }
    }
    
    /**
     * Prepara el diàleg de Update amb la llista de jocs seleccionats a descarregar.
     * @param gamesList
     * @param isAllCheckBoxSelected
     * @param thisMainPanel
     * @return 
     */
    public static Update buildTargetUpdateFrame(ArrayList<Game> gamesList, Boolean isAllCheckBoxSelected,JPanel thisMainPanel){
        Update updateFrame=null;        
        JPanel selectionPanel = (JPanel)((JViewport)((JScrollPane)thisMainPanel.getComponent(1)).getComponent(0) ).getComponent(0);
        thisMainPanel.getComponentAt(0,0);
        if(isAllCheckBoxSelected){
            updateFrame = new Update(gamesList);
        }else{
            gamesList.clear();
            for (Component component : selectionPanel.getComponents()) {
                JCheckBox checkbox =(JCheckBox) component;
                if(checkbox.isSelected()){
                    Action action = checkbox.getActionMap().get(checkbox.getActionCommand());
                    Game game = (Game) action.getValue("game");
                    gamesList.add(game);
                }
            }
            if (gamesList.isEmpty()) {
                JOptionPane.showMessageDialog(thisMainPanel.getParent(),"No heu seleccionat cap joc a actualitzar", "Avís",1);
                return updateFrame;
            }
             updateFrame = new Update(gamesList);
        }
    return updateFrame;
    }
}
