/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.prcetrencada.assemblycat.presenter.os;
import cat.prcetrencada.assemblycat.model.enums.Os;

/**
 *
 * @author @Ignasi Sanfeliu
 */
public class OsPresenter {
    public static Os identifyOS(){
        if(System.getProperty("os.name").contains("Win")){
            return Os.WINDOWS;
        }else if(System.getProperty("os.name").contains("Linux")) {
            return Os.LINUX;
        }else{
            return Os.MACOS;
        }
    }
}
