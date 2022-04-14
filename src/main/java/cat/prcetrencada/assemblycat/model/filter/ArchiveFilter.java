/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.prcetrencada.assemblycat.model.filter;

import java.io.File;
import java.io.FileFilter;
import java.util.regex.Pattern;
import org.apache.commons.io.FilenameUtils;
/**
 *
 * @author @Narx221 Projecte 'Ce' Trencada
 */
public class ArchiveFilter implements FileFilter{

    @Override
    public boolean accept(File pathname) {
        Pattern UUID = Pattern.compile("^[{]?[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}[}]?$");
        String fileNameWithExt = pathname.getName();
        String fileNameWithOutExt = FilenameUtils.removeExtension(fileNameWithExt);
        if (fileNameWithExt.contains(".zip") || fileNameWithExt.contains(".rar") || fileNameWithExt.contains(".7z") ) {
            if(UUID.matcher(fileNameWithOutExt).matches()){
                return true;
            }
         }
        return false;
    }
}