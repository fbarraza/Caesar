/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.orchis.utils;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author m15
 */
public class JavaEmail {

    //Enviar missatge a l'admin. TODO:Encriptar la contrassenya
    public static void enviarMissatge(String username) throws AddressException, MessagingException{
        Properties mailServerProperties;
        Session getMailSession;
        MimeMessage generateMailMessage;
        //String passwd = "d60e194a918f147d77ddaa1321f72e092a221cb994c45ae0f4a189b7aecc7c7a";
        
        //Configuració servidor Gmail
        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");

        //Configuració missatge
        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("m15orchisserver@gmail.com"));
        generateMailMessage.setSubject("Intent de login");
        String emailBody = "Han intentat iniciar sessió amb l'usuari: " +username + " i ha quedat bloquejat. <br><br> Localhost <br>";
        generateMailMessage.setContent(emailBody, "text/html");
        Transport transport = getMailSession.getTransport("smtp");
        
        //Enviar missatge amb el compte de gmail.
        transport.connect("smtp.gmail.com", "m15orchisserver@gmail.com", "5t34mWindows");
        transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        transport.close();
	
    }    
}
