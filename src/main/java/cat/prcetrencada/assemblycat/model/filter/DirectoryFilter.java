/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.prcetrencada.assemblycat.model.filter;

import java.io.File;
import java.io.FileFilter;

/**
 * Filtre de File limitat a directoris.
 * @author @Narx221 Projecte 'Ce' Trencada
 */
public class DirectoryFilter implements FileFilter{

    /**
     * Filtre customitzat en el que l'arxiu és elegible si és un directori i coincideix el nom de la carpeta
     * amb la llista de jocs suportats.
     * @param pathname
     * @return 
     */
    @Override
    public boolean accept(File pathname) {
        return pathname.isDirectory();
    }
    
}
