/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.vynnyk.translations;

import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.UIManager;

/**
 *
 * @author vynnyk
 */
public class TH {
    private static final String FILE_NAME = "ua.vynnyk.translations.translate";
    private static final Locale [] locales = {new Locale("uk", "UA")};  
    
    private static Locale currentLocale = new Locale("uk", "UA");    
    private static ResourceBundle resources;
    
    static {
        setResources(currentLocale);
    }
    
    public static String getString(String resource) {
        try { 
            return resources.getString(resource);
        } catch (Exception e) {
            return "No translation";
        }            
    }
    
    public static void setResources(Locale locale) {                 
        resources = ResourceBundle.getBundle(FILE_NAME, locale);
        currentLocale = locale;
        
        UIManager.put("OptionPane.cancelButtonText", getString("option.cancel"));
        UIManager.put("OptionPane.noButtonText", getString("option.no"));
        UIManager.put("OptionPane.okButtonText", getString("option.ok"));
        UIManager.put("OptionPane.yesButtonText", getString("option.yes"));
    }
    
    //not implemented yet. get list localization file in package
    public static Locale[] getLocalizations() {
        return locales;
    }
    
    public static Locale getLocale() {
        return currentLocale;
    }
    
    //to avoid create instance
    private TH() {
    }   
}
