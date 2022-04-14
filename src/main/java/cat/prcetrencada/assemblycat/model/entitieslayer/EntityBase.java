/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.prcetrencada.assemblycat.model.entitieslayer;

import java.util.UUID;

/**
 * Entitat Base
 * @author @Narx221 Projecte 'Ce' Trencada
 */
public class EntityBase {
    
    private final UUID uuid;
    
    public EntityBase(){
        this.uuid= UUID.randomUUID();
    }
    
    public UUID getUuid() {
        return uuid;
    }
}
