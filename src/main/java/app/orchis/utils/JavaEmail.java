/*
 * What if we do not want to change the license header?
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
import static app.orchis.utils.CryptoHelper.desencriptarPublic;

/**
 *
 * @author m15
 */
public class JavaEmail {

    //Enviar missatge a l'admin. TODO:Encriptar la contrassenya
    public static void enviarMissatge(String username) throws AddressException, MessagingException, Exception{
        //Vars
        Properties mailServerProperties;
        Session getMailSession;
        MimeMessage generateMailMessage;
        String correu = "m15orchisserver@gmail.com";
        byte[] passwd = {91, -9, -118, -92, 101, 58, -62, 124, 98, 124, 41, -122, 58, -119, 62, -4};
        
        //Configuració servidor Gmail
        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");

        //Configuració missatge
        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(correu));
        generateMailMessage.setSubject("Intent de login");
        String emailBody = "Han intentat iniciar sessió amb l'usuari: " +username + " i ha quedat bloquejat. <br><br> Localhost <br>";
        generateMailMessage.setContent(emailBody, "text/html");
        Transport transport = getMailSession.getTransport("smtp");
        
        //Enviar missatge amb el compte de gmail.
        transport.connect("smtp.gmail.com", correu, desencriptarPublic(passwd));
        transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        transport.close();
	
    }    
}
