/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.prcetrencada.assemblycat.presenter.io;

import cat.prcetrencada.assemblycat.model.entitieslayer.Game;
import cat.prcetrencada.assemblycat.model.enums.CompressType;
import cat.prcetrencada.assemblycat.view.Update;
import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
* Presenter del Panell d'Actualització
* @author @Narx221 Projecte 'Ce' Trencada
*/
public class UpdatePresenter extends IOPresenter{
    //SINGLETON PATTERN
    private static UpdatePresenter up;
        private UpdatePresenter(){}
    public static UpdatePresenter getInstance(){
        if(up==null){
            up= new UpdatePresenter();
        }
        return up;
    }
    
    private ArrayList<Game> selectedGames2Download;
    private Update main;
    private final ArrayList<File> downloadResultFiles= new ArrayList<>();
    private static final String API_KEY= System.getenv("ASSEMBLY_CAT_GD");
    /**
     * <pre>
     * Mètode void que actualitza els jocs seleccionats per actualitzar.
     * -Primer recorre la llista seleccionada i descarrega cada joc
     * -Després comprova si es pot continuar amb la instal·lació sempre que
     *  la mida del array dels fitxers resultants sigui igual al
     *  nº de jocs de la llista - nº de MalformedURLExceptions.
     * -Instal·la els fitxers. Per maximitzar la compatibilitat tots haurien de ser .zip
     *  ja que qualsevol SO natiu pot desencriptar aquests arxius sense programes externs.
     * </pre>
     * @param selectedGames2Download
     * @param main 
     */
    public void updateGames(ArrayList<Game> selectedGames2Download, Update main){
        //Descarregar Fitxers
        this.selectedGames2Download=selectedGames2Download;
        this.main=main;
        int malformedURLExceptionNo= downloadGames(selectedGames2Download,downloadResultFiles);
        
        //Comprovar que tots els fitxers estiguin descarregats.
        if(downloadResultFiles.size() < (selectedGames2Download.size() - malformedURLExceptionNo)){
            main.retryButton.setVisible(false);
            main.startButton.setVisible(true);
            return;
        }
        //Instal·lar jocs descarregats.
        for (File file : downloadResultFiles) {
            try{
                installGame(file);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(main.getParent(), "<html>Hi ha hagut un error durant la instal·lació dels fitxers.<br>Si us plau, torna-ho a intentar", "Error", 1);
                main.retryButton.setVisible(false);
                main.startButton.setVisible(true);
            }
        }
        main.logLabel.setText("Totes les actualitzacions han sigut instal·lades i aplicades");
        main.startButton.setVisible(true);
    }
    
    /**
       * Prepara la ruta i el nom del fitxer, que és el UUID del objecte Game en memòria per la descàrrega
       * @param url
       * @param game
       * @return 
       */
    private String buildPath(URL url, Game game){
        StringBuilder pathBuilder= new StringBuilder(game.getDirectory());
        pathBuilder.append("/").append(game.getUuid());
        if(url.toString().contains(".rar")){
            pathBuilder.append(".rar");
        } else if(url.toString().contains(".7z")){
            pathBuilder.append(".7z");
        } else{
            pathBuilder.append(".zip");
        }
        return pathBuilder.toString();
    }
    
    /**
     * <pre>
     * Descarrega els jocs i retorna un Integer amb
     * el nombre de malformedURLExceptions que hi ha hagut.
     * </pre>
     * @param selectedGames2Download
     * @return 
     */
    private int downloadGames(ArrayList<Game> selectedGames2Download, ArrayList<File> gameFetchedFiles) {
        int malformedURLExceptionCounter=0;
        
        //Iterar Videojocs
        for (Game game : selectedGames2Download) {
            main.gameLabel.setText(game.getName());
            try{
                downloadgame(game,gameFetchedFiles);
            } catch (MalformedURLException ex) {
                JOptionPane.showMessageDialog(main.getParent(), "Error de Link, reporteu-lo a cetrencada", "Error", 0);
                main.exceptionLabel.setText("<html>Error de Link, reportar a cetrencada:<br>"+ex.getMessage());
                main.exceptionLabel.setForeground(Color.RED);
                malformedURLExceptionCounter++;
            }catch(Exception ex){
                System.out.println("Error Runtime");
                main.exceptionLabel.setText("<html>Error inesperat:<br>"+ex.getMessage());
                main.exceptionLabel.setForeground(Color.RED);
            }
        }
        return malformedURLExceptionCounter;
    }
    
    /**
     * Descarrega el joc designat
     * @param game 
     */
    private void downloadgame(Game game, ArrayList<File> gameFetchedFiles) throws IOException {
                //convertir l'enllaç a un de descàrrega directa
                main.logLabel.setText("Connectant al repositori (pot tardar una estona)...");
                URL url;
                String directDownloadLink= game.getDownloadLink();
                if(directDownloadLink.contains("drive.google")){
                    //Google Drive Api
                    StringBuilder driveApiRequestBuilder= new StringBuilder("https://www.googleapis.com/drive/v3/files/");
                    if(!directDownloadLink.contains("export=download")){
                        driveApiRequestBuilder.append(directDownloadLink.substring(32,65));
                        driveApiRequestBuilder.append("?alt=media&key=").append(API_KEY);
                    }
                    else if (directDownloadLink.contains("export=download")){
                        driveApiRequestBuilder.append(directDownloadLink.substring(47,80));
                        driveApiRequestBuilder.append("?alt=media&key=").append(API_KEY);
                    }
                    url= new URL(driveApiRequestBuilder.toString());
                }
                else{
                    //Petició Directa
                    url = new URL(directDownloadLink);
                }
                //Descarregar Joc
                File outFile = new File(buildPath(url, game));
                HttpURLConnection con=(HttpURLConnection)url.openConnection();
               // con.setConnectTimeout(3 * 60000);
                //con.setReadTimeout(3 * 60000);
                FileOutputStream fos;

                if(outFile.exists() && Files.size(outFile.toPath()) < con.getContentLength()){
                  con.setRequestProperty("Range", "bytes="+outFile.length()+"-");
                  fos= new FileOutputStream(outFile,true);
                }else if(!outFile.exists()) {
                    outFile.createNewFile();
                    fos= new FileOutputStream(outFile);
                }else{
                    return;
                }
                int downloadLength= con.getContentLength();
                main.jProgressBar1.setMaximum(downloadLength);
                BufferedInputStream in = new BufferedInputStream(con.getInputStream());
                BufferedOutputStream bout = new BufferedOutputStream(fos,downloadLength);
                byte[] fileData = new byte[downloadLength];
                int downloadedProgress=0;
                int read;
                //Escriure dades
                main.logLabel.setText("Descarregant "+game.getName()+".");
                while ((read=in.read(fileData,0, downloadLength))>=0 ) {
                    bout.write(fileData,0,read);
                    downloadedProgress+=read;
                    main.jProgressBar1.setValue(downloadedProgress);
                    main.revalidate();
                }
                in.close();
                bout.close();
                gameFetchedFiles.add(outFile);
                main.logLabel.setText(game.getName()+" Descarregat");
    }

    private void installGame(File file) throws IOException {
        main.logLabel.setText("Instal·lant arxius...");
        if (file.getName().contains(".zip")){
            unCompressFile(CompressType.ZIP, file, main);
        } else if (file.getName().contains(".7z")){
            unCompressFile(CompressType._7ZIP, file, main);
        } else if (file.getName().contains(".rar")){
            unCompressFile(CompressType.RAR, file, main);
        }
    }
    
}
