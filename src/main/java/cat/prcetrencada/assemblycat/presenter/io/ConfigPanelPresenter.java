/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.prcetrencada.assemblycat.presenter.io;
import cat.prcetrencada.assemblycat.model.entitieslayer.Platform;
import cat.prcetrencada.assemblycat.model.enums.Os;
import cat.prcetrencada.assemblycat.model.enums.PersistanceTech;
import static cat.prcetrencada.assemblycat.model.enums.PersistanceTech.JSON;
import static cat.prcetrencada.assemblycat.presenter.os.OsPresenter.identifyOS;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Presenter del Panell de Configuració
 * @author @Narx221 Projecte 'Ce' Trencada
 */
public class ConfigPanelPresenter extends IOPresenter{

    /**
     * Agrega una nova fila.
     * @param model
    **/
    public static void addPlatform(DefaultTableModel model){
        Platform[] rowData=null;
        model.addRow(rowData);
    }

    /**
     * Elimina la última fila.
     * Si només queda una columna per eliminar, no fa res.
     * @param model
    **/
    public static void deletePlatform(DefaultTableModel model){
        if(model.getRowCount()>1){
            model.removeRow(model.getRowCount()-1);
        }
    }
    
    /**
     * Converteix el valors de la taula de plataformes del DefaultTableModel
     * a un array JSON.
     * Si algun nom no esta definit, en genera un.
     * Si algun directori no esta definit, llança un NullPointerException.
     * @param dt
     * @return 
    **/
    public static JSONArray convertTableValuesToJSONArray(DefaultTableModel dt){
        JSONArray platformArray = new JSONArray();
        //Iterar fila
        for (int i = 0; i < dt.getRowCount(); i++) {
            //Iterar columna
            JSONObject platform = new JSONObject();
            
            //Iterar columnes
            for (int j = 0; j < dt.getColumnCount(); j++) {
                switch (j) {
                    case 0 -> {
                        String valueAt = (String) dt.getValueAt(i, j);
                        if (valueAt == null || valueAt.isEmpty()){
                            valueAt="Plataforma "+i;
                        }
                        platform.put("name", valueAt);
                    }
                    case 1 -> {
                        String valueAt = (String) dt.getValueAt(i, j);
                        if (valueAt == null || valueAt.isBlank()){
                            throw new NullPointerException("Directory is null");
                        }
                        platform.put("directory", valueAt.toString());
                    }
                }
            }
            platformArray.put(JSONObject.valueToString(platform));
        }
        return platformArray;
    }
    
    /**
     * Guarda els valors obtinguts al fitxer de text
     * @param table
     * @param tech
     * @throws java.io.IOException
    **/
    public static void saveConfiguration(JTable table, PersistanceTech tech) throws IOException{
            StringBuilder pathBuilder = new StringBuilder(System.getProperty("user.dir"));
            pathBuilder.append("/cownloader.conf");
            FileWriter fw = getFileWriter(pathBuilder);

            if (table.isEditing()){
              table.getCellEditor().stopCellEditing();
            }
            
            switch (tech) {
                case JSON -> {
                    //Crear JSON File
                    DefaultTableModel dt =(DefaultTableModel) table.getModel();
                    JSONArray platformArray = convertTableValuesToJSONArray(dt);
                    //Escriure canvis
                    platformArray.write(fw);
                    fw.close();
                }
                default -> {
                    return;
                }
            }
    } 
    
    /**
     * Posa el valor a la primera fila de la ruta per defecte del SO de Steam.
     * @param dt 
     */
    public static void setSteamDefaultPath(DefaultTableModel dt){
        Os oS =identifyOS();
        switch (oS) {
            case WINDOWS -> dt.setValueAt("C:\\Program Files (x86)\\Steam\\steamapps\\common", 0, 1);
            case LINUX -> {
                StringBuilder sb = new StringBuilder(System.getProperty("user.home"));
                sb.append("/.steam/steam/SteamApps/common");
                dt.setValueAt(sb.toString(), 0, 1);
            }
            default -> dt.setValueAt("Ruta predeterminada de MACOS de Steam", 0, 1);
        }
    }
    
    /**
     * Reinicia el model de la taula al per defecte.
     * @param jTable 
     */
    public static void resetModel(JTable jTable){
        jTable.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {
            {"Steam", null}
        },
        new String [] {
            "Nom", "Ruta"
        }
        ));
    }
}

