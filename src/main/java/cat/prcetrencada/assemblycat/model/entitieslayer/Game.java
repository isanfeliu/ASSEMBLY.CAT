/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.prcetrencada.assemblycat.model.entitieslayer;

/**
 * Joc
 * @author @Narx221 Projecte 'Ce' Trencada
 */
public class Game extends EntityBase{
    private String name;
    private String directory;
    private String downloadLink;

    public Game (String name){
        this.name=name;
    }
    
    public String getDownloadLink() {
        return downloadLink;
    }
    
    public Game (String name, String directory, String downloadLink){
        this.name=name;
        this.directory=directory;
        this.downloadLink=downloadLink;
    }
    
    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String getName() {
        return name;
    }
    
}
