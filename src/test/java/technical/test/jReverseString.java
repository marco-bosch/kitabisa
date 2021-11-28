package technical.test;

import java.util.Scanner;


import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMultipart;

public class jReverseString {
    Matcher m = null;

    public static void main(String[] args) {

        jReverseString gmail = new jReverseString();
        String OTP = gmail.read();
        System.out.println("OTPnya adalah : " + OTP);

    }

    public String read() {

        Properties props = new Properties();
        String OTP = "";

        try {
            props.load(new FileInputStream(new File("/Users/marcobosch/Automation Test/kitabisa/smtp.properties")));
            Session session = Session.getDefaultInstance(props, null);

            Store store = session.getStore("imaps");
            store.connect("smtp.gmail.com", "javamailchecking@gmail.com", "_qwerty123_");
            Folder inbox = store.getFolder("inbox");
            inbox.open(Folder.READ_ONLY);
            int messageCount = inbox.getMessageCount();

            System.out.println("Total Messages:- " + messageCount);
            Message[] messages = inbox.getMessages();
            System.out.println("------------------------------");
            String kodeVerifikasi="";
            for (int i = 0; i < messageCount; i++) {
                String ada =  ( (MimeMultipart) messages[i].getContent() ).getBodyPart( 0 ).getContent().toString();
                if(ada.contains("Kode verifikasi akun Kitabisa")) {
                    kodeVerifikasi = ada;
                }
            }
//            System.out.println(kodeVerifikasi);
//            System.out.println("==================================================================================");
            Pattern p = Pattern.compile("\\d{6}");
             m = p.matcher(kodeVerifikasi);
            while(m.find()) {
//                System.out.println("OTPNYA : " + m.group());
                 OTP = m.group();
            }
//            System.out.println("==================================================================================");

            inbox.close(true);
            store.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

        return OTP;
    }

}