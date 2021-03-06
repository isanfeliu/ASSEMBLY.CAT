/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package cat.prcetrencada.assemblycat.view;

import cat.prcetrencada.assemblycat.model.entitieslayer.Platform;
import cat.prcetrencada.assemblycat.model.enums.PersistanceTech;
import cat.prcetrencada.assemblycat.presenter.fetch.DataFetcherPresenter;
import static cat.prcetrencada.assemblycat.presenter.io.ConfigPanelPresenter.*;
import java.awt.*;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import javax.swing.GroupLayout;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Panell de ConfiguraciĆ³
 * @author @Narx221 Projecte 'Ce' Trencada
 */
public class Config extends javax.swing.JPanel {
    public Config() {
        initComponents();
        try {
            ArrayList<Platform> platformList = DataFetcherPresenter.getInstance().fetchConfigFileData(PersistanceTech.JSON);
            if (platformList!=null){
            Object[][] modelData= new Object [platformList.size()][model.getColumnCount()];
            
            for (Platform platform : platformList) {
                modelData[platformList.indexOf(platform)][0]=platform.getName();
                modelData[platformList.indexOf(platform)][1]=platform.getDirectory();
            }
            DefaultTableModel dt = new DefaultTableModel(modelData, new String[]{"Nom", "Directori de Jocs"});
            jTable1.setModel(dt);
            model=dt;
            }else{
                resetModel(jTable1);
                model = (DefaultTableModel) jTable1.getModel();
                setSteamDefaultPath(model);
            }
        }    
        catch (IOException ex) {
            resetModel(jTable1);
            model = (DefaultTableModel) jTable1.getModel();
            setSteamDefaultPath(model);
            JOptionPane.showMessageDialog(getParent(), "<html>Hi ha hagut un error al llegir l'arxiu de configuraciĆ³. Retornant als valors per defecte.<br>" + ex.getMessage(),"Error" ,0);
        }catch (ParseException ex) {
            resetModel(jTable1);
            model = (DefaultTableModel) jTable1.getModel();
            setSteamDefaultPath(model);
            JOptionPane.showMessageDialog(getParent(), "<html>Hi ha hagut un error desconegut. Retornant als valors per defecte.<br>"+ ex.getMessage(),"Error" ,0);
        } 
        
        jTable1.getColumnModel().getColumn(0).setMaxWidth(100);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        updateButton = new javax.swing.JButton();
        introLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        addRowButton = new javax.swing.JButton();
        removeRowButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 204));

        updateButton.setText("Desa i actualitza");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        introLabel.setFont(new java.awt.Font("Consolas", 1, 24)); // NOI18N
        introLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        introLabel.setText("Afegeix plataformes");
        introLabel.setToolTipText(null);

        jScrollPane2.setBackground(new java.awt.Color(204, 204, 204));
        jScrollPane2.setForeground(new java.awt.Color(204, 204, 204));
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setAlignmentX(0.0F);
        jScrollPane2.setAlignmentY(0.0F);
        jScrollPane2.setColumnHeaderView(null);

        jTable1.setBackground(new java.awt.Color(204, 204, 204));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Steam", null}
            },
            new String [] {
                "Nom", "Directori de jocs"
            }
        ));
        model=(DefaultTableModel)jTable1.getModel();
        jTable1.setAlignmentX(0.0F);
        jTable1.setAlignmentY(0.0F);
        jScrollPane2.setViewportView(jTable1);

        addRowButton.setText("Afegeix");
        addRowButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                addRowButtonMouseReleased(evt);
            }
        });

        removeRowButton.setText("Suprimeix");
        removeRowButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                removeRowButtonMouseReleased(evt);
            }
        });

        exitButton.setText("Desa i surt");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(88, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addRowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(removeRowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(updateButton)))
                .addGap(60, 60, 60))
            .addComponent(introLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(introLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addRowButton)
                    .addComponent(removeRowButton)
                    .addComponent(exitButton)
                    .addComponent(updateButton))
                .addGap(43, 43, 43))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        Container parent = getParent();
        PersistanceTech tech = PersistanceTech.JSON;
        try {
            saveConfiguration(jTable1, tech);
            Select targetPanel = new Select();
            redirect(parent, targetPanel);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(getParent(), "<html>Error al desar l'arxiu de la configuraciĆ³<br></br>Siusplau, torna-ho a intentar.<br></br>"+ex.getMessage(),"Error" ,0);
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(getParent(), "<html>No s'ha desat el contingut del fitxer.<br></br>Totes les rutes/directoris han d'estar definides.","AvĆ­s" ,2);
        }catch (InterruptedException | ExecutionException ex) {
            JOptionPane.showMessageDialog(getParent(), "<html>Error al desar la configuraciĆ³.<br></br>Siusplau, torna-ho a intentar.<br></br>"+ex.getMessage(),"Error" ,0);
        }
        
    }//GEN-LAST:event_updateButtonActionPerformed

    private void addRowButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addRowButtonMouseReleased
        addPlatform(model);
    }//GEN-LAST:event_addRowButtonMouseReleased

    private void removeRowButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_removeRowButtonMouseReleased
        deletePlatform(model);
    }//GEN-LAST:event_removeRowButtonMouseReleased

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        PersistanceTech tech = PersistanceTech.JSON;
        try {
            saveConfiguration(jTable1,tech);
            exit();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this.getParent(), "<html>Error al desat l'arxiu de la configuraciĆ³<br></br>Siusplau, torna-ho a intentar.<br></br>"+ex.getMessage(),"Error" ,0);
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(this.getParent(), "<html>No s'ha desat el contingut del fitxer.<br></br>Tots els directoris han d'estar definits.","AvĆ­s" ,2);
        }catch (Exception ex) {
            JOptionPane.showMessageDialog(this.getParent(), "<html>Error al desar la configuraciĆ³.<br></br>Siusplau, torna-ho a intentar.<br></br>"+ex.getMessage(),"Error" ,0);
        }
    }//GEN-LAST:event_exitButtonActionPerformed

    // Variables declaration    
    public GroupLayout panelGroupLayout;
    public DefaultTableModel model;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addRowButton;
    private javax.swing.JButton exitButton;
    private javax.swing.JLabel introLabel;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton removeRowButton;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
}
