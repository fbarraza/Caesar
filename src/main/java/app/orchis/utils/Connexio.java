/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.orchis.utils;

import java.util.Map;
import java.util.StringTokenizer;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Bobob
 */
public class Connexio {
    public static String obteIPConnexio(EntityManagerFactory emf) {
        Map<String, Object> map = emf.getProperties();
        String str = (String) map.get("javax.persistence.jdbc.url");
        System.out.println(str);
        
        StringTokenizer stoken = new StringTokenizer(str,":");
        boolean salta = false;
        String tmp = null;
        while (stoken.hasMoreElements() && !salta) {
            tmp = (String) stoken.nextElement();
            if (tmp.contains("//")) {
                salta = true;
            }
        }                
        return tmp.replaceFirst("//", "");
    
    }
    
}
