/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.prcetrencada.assemblycat.presenter.fetch;

import cat.prcetrencada.assemblycat.model.entitieslayer.Game;
import cat.prcetrencada.assemblycat.model.entitieslayer.Platform;
import cat.prcetrencada.assemblycat.model.enums.PersistanceTech;
import cat.prcetrencada.assemblycat.model.filter.DirectoryFilter;
import cat.prcetrencada.assemblycat.presenter.service.http.impl.HttpService;
import cat.prcetrencada.assemblycat.presenter.Presenter;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.ExecutionException;
import static cat.prcetrencada.assemblycat.presenter.io.IOPresenter.*;
import java.text.ParseException;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Presenter del Panell de Start
 * @author @Narx221 Projecte 'Ce' Trencada
 */
public class DataFetcherPresenter extends Presenter{
    // Singleton
    private static DataFetcherPresenter dfp;
    private DataFetcherPresenter(){}
    public static DataFetcherPresenter getInstance(){
        if(dfp==null){
            dfp=new DataFetcherPresenter();
        }
        return dfp;
    }
    /**
     * <pre>
     * Retorna un Hashmap amb 2 keys.
     * gamesInstalledList -> Array de Game de jocs instal·lats.
     * platformsList -> Array de Platform de jocs instal·lats.
     * </pre>
     * @param tech
     * @return HashMap
     * @throws IOException
     * @throws InterruptedException 
     * @throws ExecutionException 
     */
    public HashMap fetchMatchingData(PersistanceTech tech) throws IOException, InterruptedException, ExecutionException{
        HashMap<String, List> platformInfo = new HashMap<>();
        //getFile & getFileReader
        StringBuilder pathBuilder= new StringBuilder(System.getProperty("user.dir"));
        pathBuilder.append("/cownloader.conf");
        File file = new File(pathBuilder.toString());
        int readFirstChar;
        boolean isReady;
        try (FileReader reader = getFileReader(pathBuilder)) {
            readFirstChar = reader.read();
            isReady = reader.ready();
        }
        //file isEmpty?
        if (isFileEmpty(file, 2) || readFirstChar==-1 || readFirstChar==32 || isReady==false){
                return platformInfo;
        }
        
        //Definir Arrays com a valors (V) del HashMap
        ArrayList<Platform> platformsList = new ArrayList<>();   
        ArrayList<Game> gamesList = new ArrayList<>();   
        platformInfo.put("gamesInstalledList", gamesList);
        platformInfo.put("platformsList", platformsList);
        switch (tech) {
        case JSON -> {
            HttpService service = HttpService.getInstance();
            String httpResult = service.asyncStringHttpRequest("https://cetrencada.cat/json/jocs.json", 1).get();

            JSONArray platformArray = new JSONArray(Files.readString(Paths.get(pathBuilder.toString())));
            JSONArray supportedGamesArray = new JSONArray(httpResult);

            //Iterar plataforma
            for (Object data : platformArray) {
                JSONObject platformData = new JSONObject(data.toString());
                String platformPath = platformData.getString("directory");
                if(platformPath==null || platformPath.isEmpty()){
                    continue;
                }
                Platform platform = new Platform(platformData.getString("name"), platformData.getString("directory"));
                platformsList.add(platform);
                File platformFile = new File(platformPath);
                File[] sortedDirs = platformFile.listFiles(new DirectoryFilter());

                if (sortedDirs==null){
                    continue;
                }
                //Iterar cada directori de la plataforma
                for (File sortedDir : sortedDirs) {
                    //Iterar array de jocs i comprovar si el directori coincideix amb algun joc traduït
                    //Si coincideix, afegir-lo.
                    for (Iterator it = supportedGamesArray.iterator(); it.hasNext();) {
                        JSONObject jsonGame =(JSONObject) it.next();
                        if (sortedDir.getName().equals(jsonGame.get("gameFolder"))){
                            Game game = new Game(jsonGame.get("gameFolder").toString(), platformPath,jsonGame.get("link").toString());
                            gamesList.add(game);
                        }
                    }
                }   
            }
                return platformInfo;
            }
            default -> {
                return platformInfo;
            }
        }
    }
    
    /**
     * <pre>
     * Retorna les dades del fitxer de configuració en un ArrayList de Platforms
     * platformsList -> Llista de plataformes.
     * </pre>
     * @param tech
     * @return HashMap
     * @throws IOException 
     * @throws java.text.ParseException 
     */
    public ArrayList<Platform> fetchConfigFileData(PersistanceTech tech) throws IOException, ParseException{            
        ArrayList<Platform> platformsList = new ArrayList<>();
        //getFile & getFileReader
        StringBuilder pathBuilder= new StringBuilder(System.getProperty("user.dir"));
        pathBuilder.append("/cownloader.conf");
        File file = new File(pathBuilder.toString());
        int readFirstChar;
        boolean isReady;
        try (FileReader reader = getFileReader(pathBuilder)) {
            readFirstChar = reader.read();
            isReady = reader.ready();
        }
        if (isFileEmpty(file, 2) || readFirstChar==-1 || readFirstChar==32 || isReady==false){
                return null;
        }
        switch (tech) {
            case JSON ->{
                String readString = Files.readString(Paths.get(pathBuilder.toString()));
                JSONArray platformArray = new JSONArray(readString);
                //Construïr Array de Platform.
                for (Iterator it = platformArray.iterator(); it.hasNext();) {
                    JSONObject jsonData = new JSONObject(it.next().toString());
                    Platform platform = new Platform(jsonData.get("name").toString(), jsonData.get("directory").toString());
                    platformsList.add(platform);
                }
            } 
            default->{
                return null;   
            }
        }
        return platformsList;
    }

}