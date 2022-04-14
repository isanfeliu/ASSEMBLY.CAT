/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.prcetrencada.assemblycat.presenter.service.http.impl;

import cat.prcetrencada.assemblycat.presenter.service.http.api.IHttp;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;

/**
 * Implementació de la interface cat.prcetrencada.cownloader2.model.http.api.IHttp
 * @author @Narx221 Projecte 'Ce' Trencada
 */
public class HttpService implements IHttp {
    // Singleton
    private static HttpService service;
    private HttpService(){}
    public static HttpService getInstance(){
        if(service==null){
            service=new HttpService();
        }
        return service;
    }
    /**
     * Fer una petició asíncrona http i retornar un String del resultat
     * @param uri
     * @param timeoutMinutes
     * @return 
     */
    @Override
    public CompletableFuture<String> asyncStringHttpRequest(String uri, int timeoutMinutes) {
        try{
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(uri))
                    .timeout(Duration.ofMinutes(2))
                    .build();
                CompletableFuture<String> result = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);
                return result;
        }
        catch(UnsupportedOperationException ex){
            throw new UnsupportedOperationException(ex); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

    }
    
}