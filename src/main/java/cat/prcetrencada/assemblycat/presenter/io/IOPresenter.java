/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.prcetrencada.assemblycat.presenter.io;

import cat.prcetrencada.assemblycat.model.enums.CompressType;
import cat.prcetrencada.assemblycat.presenter.Presenter;
import cat.prcetrencada.assemblycat.view.Update;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Presenter que interactua amb I/O
 * @author @Narx221 Projecte 'Ce' Trencada
 */
public class IOPresenter extends Presenter {

    /**
     * Revisa si s'ha creat o no el fitxer de configuració.
     * @return 
     */
    public static boolean isConfigFileCreated(){
        StringBuilder sb = new StringBuilder(System.getProperty("user.dir"));
        sb.append("/cownloader.conf");
        File configFile = new File(sb.toString());
        return configFile.exists();
    }
    
    /**
     * Crea el fitxer de configuració.
     * @throws java.io.IOException
     **/
    public static void createConfigFile() throws IOException{
            StringBuilder sb = new StringBuilder(System.getProperty("user.dir"));
            sb.append("/cownloader.conf");
            File configFile = new File(sb.toString());
            configFile.createNewFile(); 
            //Files.setAttribute(configFile.getPath(), "dos:hidden", true);
  }
    
    /**
     * Retorna el File resultant de la ruta del StringBuilder que és passat.
     * @param path
     * @return
     * @throws FileNotFoundException 
     */
    public static File getFile(StringBuilder path) throws FileNotFoundException{
        File file = new File(path.toString());
        return file;
    }

    /**
     * Retorna el FileReader d'un fitxer especificat.
     * @param path
     * @return
     * @throws FileNotFoundException 
     */
    public static FileReader getFileReader(StringBuilder path) throws FileNotFoundException{
        File file = new File(path.toString());
        FileReader fr = new FileReader(file);
        return fr;
    }

    /**
     * Retorna el FileWriter d'un fitxer especificat.
     * @param path
     * @return
     * @throws IOException 
     */
    public static FileWriter getFileWriter(StringBuilder path) throws IOException{
        File file = new File(path.toString());
        FileWriter fw = new FileWriter(file);
        return fw;
    }
    
    /**
     * Retorna un boolean.
     * true si esta empty o conté menys bytes del tamany especificat.
     * d'altra manera, retorna false.
     * @param file
     * @param byteThreshold
     * @return 
     */
    public static boolean isFileEmpty(File file, int byteThreshold){
        return (file.getTotalSpace()<byteThreshold);
    }
    
    /**
     * Descomprimeix un fitxer zip
     * @param compress
     * @param file 
     * @param main 
     * @throws java.io.FileNotFoundException 
     */
    public void unCompressFile(CompressType compress,File file, Update main) throws FileNotFoundException, IOException{
    switch (compress) {
            case ZIP -> {
		String destDirectory = file.getParentFile().getAbsolutePath();
		File destDirectoryFolder = new File(destDirectory);
		if (!destDirectoryFolder.exists()) {
			destDirectoryFolder.mkdir();
		}
                try (ZipInputStream zis = new ZipInputStream(new FileInputStream(file))) {
                    ZipEntry zipEntry = zis.getNextEntry();
                    while(zipEntry !=null) {
                        String filePath = destDirectory + File.separator + zipEntry.getName();
                        main.logLabel.setText("Descomprimint "+zipEntry.getName()); main.revalidate();
                        if(!zipEntry.isDirectory()) {
                            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                                byte[] buffer = new byte[1024];
                                int len;
                                main.jProgressBar1.setMaximum((int)zipEntry.getSize());
                                main.revalidate();
                                int progress=0;
                                while ((len = zis.read(buffer)) >0){
                                    fos.write(buffer,0,len);
                                    main.jProgressBar1.setValue(progress+=1024);
                                    main.revalidate();
                                }
                            }
                        }
                        else {
                            File dir = new File(filePath);
                            if (!dir.exists()) {
                                dir.mkdir();
                            }
                        }
                        zis.closeEntry();
                        zipEntry = zis.getNextEntry();
                    }
                    zis.closeEntry();
                }
		main.exceptionLabel.setText("Descompressió completada per: " +file.getName()); main.revalidate();
            }

            case _7ZIP -> {
               throw new  UnsupportedOperationException("Not supported yet.");
            }
            case RAR -> {
               throw new  UnsupportedOperationException("Not supported yet.");
            }
        }
    
    }
    
}
