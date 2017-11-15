package amock.io.utils.crypto;

public class Tester {
	
    private static final String PASSPHRASE = "okezie";

//    private static final String DE_INPUT = "C:\\tmp\\encrypted\\workbook_1507736667841.pgp";
    private static final String DE_INPUT = "C:\\keys\\new.pgp";
    private static final String DE_OUTPUT = "c:\\keys\\file.xls";
//    private static final String DE_OUTPUT = "C:\\tmp\\encrypted\\workbook_1507736667841.pgp.xls";
    private static final String DE_KEY_FILE = "C:\\keys\\keyfile.skr";

//    private static final String E_INPUT = "src/test/resources/visa2.xlsx";
    private static final String E_INPUT = "C:\\keys\\testfile.txt";
//    private static final String E_OUTPUT = "src/test/resources/visa2_enc.pgp";
    private static final String E_OUTPUT = "C:\\keys\\testfile.txt.pgp";
//    private static final String E_KEY_FILE = "C:\\keys\\uba_pub_key.asc";
    private static final String E_KEY_FILE = "C:\\keys\\new\\keyfile.asc";
    
    public static void testDecrypt() throws Exception {
        PGPFileProcessor p = new PGPFileProcessor();
        p.setInputFileName(DE_INPUT);
        p.setOutputFileName(DE_OUTPUT);
        p.setPassphrase(PASSPHRASE);
        p.setSecretKeyFileName(DE_KEY_FILE);
        System.out.println(p.decrypt());
    }

    public static void testEncrypt() throws Exception {
        PGPFileProcessor p = new PGPFileProcessor();
        p.setInputFileName(E_INPUT);
        p.setOutputFileName(E_OUTPUT);
        p.setPassphrase(PASSPHRASE);
        p.setPublicKeyFileName(E_KEY_FILE);
        System.out.println(p.encrypt());
    }

    public static void testDecryptAndVerifySigned() throws Exception {
    	PGPFileProcessor p = new PGPFileProcessor();
        p.setInputFileName(DE_INPUT);
        p.setOutputFileName(DE_OUTPUT);
        p.setPassphrase(PASSPHRASE);
        p.setSecretKeyFileName(DE_KEY_FILE);
        p.setPublicKeyFileName(E_KEY_FILE);
        System.out.println(p.decryptAndVerifySignedFile());
    }
    
    public static void main(String[] args)
    {
    	try {
//			RSAGen.Generate("1", PASSPHRASE, KEYFILE);
//    		testEncrypt();
//    		testDecrypt();
    		testDecryptAndVerifySigned();
		}
    	catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
}
