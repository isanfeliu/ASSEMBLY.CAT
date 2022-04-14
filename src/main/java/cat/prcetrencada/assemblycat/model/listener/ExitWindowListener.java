/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.prcetrencada.assemblycat.model.listener;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 *
 * @author @Narx221 Projecte 'Ce' Trencada
 */
public interface ExitWindowListener extends WindowListener{
    //Functional Interface method
    @Override
    public void windowClosing(WindowEvent e);
    
    @Override
    default void windowOpened(WindowEvent e){}
    @Override
    default void windowClosed(WindowEvent e){}
    @Override
    default void windowIconified(WindowEvent e){}
    @Override
    default void windowDeiconified(WindowEvent e){}
    @Override
    default void windowActivated(WindowEvent e){}
    @Override
    default void windowDeactivated(WindowEvent e){}
}
