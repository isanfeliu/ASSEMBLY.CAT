/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cat.prcetrencada.assemblycat.presenter.service.http.api;

import java.util.concurrent.CompletableFuture;

/**
 * Interface / Api per fer una petició http
 * @author @Narx221 Projecte 'Ce' Trencada
 */
public abstract interface IHttp {

    /**
     * Fer petició asíncrona i retornar el resultat com a String
     */
    public CompletableFuture<String> asyncStringHttpRequest(String uri, int timeoutMinutes);
}
