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
import java.util.List;
import java.util.stream.IntStream;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

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
     * @param main
     * @return 
     */
    public static Update buildTargetUpdateFrame(ArrayList<Game> gamesList, Boolean isAllCheckBoxSelected, TableModel model){
        Update updateFrame=null;
        ArrayList<Game> games = new ArrayList<Game>();
        if(!isAllCheckBoxSelected){
            for (int i = 0; i < gamesList.size(); i++) {
                Boolean isSelected = (Boolean)model.getValueAt(i, 2);
                if (isSelected==null || isSelected==false) {
                    continue;
                }
                Game game = gamesList.get(i);
                games.add(game);
            }
            if (games.isEmpty()) {
                return null;
            }
        }else{
            games=gamesList;
        }
        updateFrame = new Update(games);
        return updateFrame;
    }
}
