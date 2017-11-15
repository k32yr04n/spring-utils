package amock.io.utils.security;

import org.apache.commons.dbcp.BasicDataSource;

import amock.io.utils.crypto.Encrypter;

public class EncryptedBasicDatasource extends BasicDataSource {

	public EncryptedBasicDatasource(){super();}
	
	@Override
	public synchronized void setPassword(String encryptedPassword)
	{
		Encrypter en = new Encrypter();
		super.setPassword(en.decrypt(en.decrypt(encryptedPassword)));		
    }
	
}
