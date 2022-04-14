/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.prcetrencada.assemblycat.model.entitieslayer;

/**
 * Plataforma
 * @author @Narx221 Projecte 'Ce' Trencada
 */
public class Platform extends EntityBase{
    private String name;
    private String directory;
    
    public Platform(){}
    
    public Platform(String name){
        this.name=name;
    }
    
    public Platform(String name, String directory){
        this.name=name;
        this.directory=directory;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDirectory() {
        return directory;
    }
    
    public void setDirectory(String directory) {
        this.directory = directory;
    }
    
}

