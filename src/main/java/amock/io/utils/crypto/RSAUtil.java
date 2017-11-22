package amock.io.utils.crypto;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;

import sun.misc.BASE64Encoder;

@SuppressWarnings("restriction")
public class RSAUtil {

    public static String decryptRsa(String payload, String keyPath) throws Exception {
        String decrypted = decrypt(Base64.decodeBase64(payload), keyPath);
        return decrypted;
    }

    public static String decrypt(byte[] text, String keyPath) throws Exception {
        byte[] dectyptedText = null;
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            getPrivateKeyFromString(keyPath);
            PrivateKey key = (PrivateKey) getPrivateKeyFromString(keyPath);
            System.out.println("After getting private key");

            cipher.init(2, key);
            dectyptedText = cipher.doFinal(text);

            System.out.println("Decrypted text:" + dectyptedText);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }

        return new String(dectyptedText);
    }

    public static byte[] encrypt(String text, PublicKey key) {
        byte[] cipherText = null;
        try {
            Cipher cipher = Cipher.getInstance("RSA");

            cipher.init(Cipher.ENCRYPT_MODE, key);
            cipherText = cipher.doFinal(text.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cipherText;
    }

    public static String decrypt(byte[] text, String keyPath, String privateKeyName) {
        byte[] dectyptedText = null;
        
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            ObjectInputStream inputStream = null;
            inputStream = new ObjectInputStream(new FileInputStream(keyPath + "/" + privateKeyName));
            PrivateKey key = (PrivateKey) inputStream.readObject();
            System.out.println("After getting private key");

            cipher.init(2, key);
            dectyptedText = cipher.doFinal(text);

            System.out.println("Decrypted text:" + dectyptedText);
            inputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return new String(dectyptedText);
    }


    private static String getKeyAcct(String filename) throws IOException {
        // Read key from file
        String strKeyPEM = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
        //BufferedReader br = new BufferedReader(new FileReader(RSA2.class.getClassLoader().getResource(filename).toString()));
        String line;
        while ((line = br.readLine()) != null) {
            strKeyPEM += line;
        }
        br.close();
        return strKeyPEM;
    }

    public static RSAPrivateKey getPrivateKeyFromString(String key) throws IOException, GeneralSecurityException {
        String privateKeyPEM = key;
        privateKeyPEM = getKeyAcct(privateKeyPEM);
        // Remove the first and last lines
        privateKeyPEM = privateKeyPEM.replace("-----BEGIN PRIVATE KEY-----", "");
        privateKeyPEM = privateKeyPEM.replace("-----END PRIVATE KEY-----", "");

        //logger.info(privateKeyPEM);
        // Base64 decode data
        byte[] encoded = Base64.decodeBase64(privateKeyPEM);
        //byte[] encoded = Base64.getDecoder().decode(privateKeyPEM);

        KeyFactory kf = KeyFactory.getInstance("RSA");
        RSAPrivateKey privKey = (RSAPrivateKey) kf.generatePrivate(new PKCS8EncodedKeySpec(encoded));
        return privKey;
    }

    public static PublicKey getKey(String filename) throws IOException, GeneralSecurityException {

        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        X509Certificate cert = null;

        try {
            InputStream str = new FileInputStream(filename);
            cert = (X509Certificate) cf.generateCertificate(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        PublicKey pubKey = cert.getPublicKey();
        return pubKey;
    }
    
    public static void main(String[] argz)
    {
    	PublicKey key;
		try 
		{
			key = RSAUtil.getKey("uba.key");
			byte[] encrypt = RSAUtil.encrypt("uba_", key);
	        String encryptedToken = new BASE64Encoder().encode(encrypt);
	        System.out.println(encryptedToken);
		}
		catch (IOException | GeneralSecurityException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }

}
