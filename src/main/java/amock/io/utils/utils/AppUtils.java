package amock.io.utils.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class AppUtils {

	public static boolean isEmptyString(String str)
	{
		return str == null || str == "null" || str.equalsIgnoreCase("") || str.isEmpty();
	}
	
	public static boolean getBoolean(String boolStr) 
	{
		if(boolStr != null && boolStr.equalsIgnoreCase("true"))
			return true;
		return false;
	}

	public static String getSystemEnvVar(String varName)
	{
		String var = System.getenv(varName);
		return var;
	}
	
	public static String getSystemEnvVarOrDefault(String varName, String defaultVar)
	{
		String var = getSystemEnvVar(varName);
		if(var != null) return var;
		return defaultVar;
	}

	public static Object unMarshal(String str, Class<?> objClass) throws Exception
	{
		Object unMarshalledObject = null;
		try
		{
			JAXBContext jaxbContext = JAXBContext.newInstance(objClass);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			InputStream is = new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8.name()));
			unMarshalledObject = jaxbUnmarshaller.unmarshal(is);
		}
		catch(Exception ex)
		{
			throw new Exception("Invalid XML file content. XML file content is malformed.", ex);
		}
		
		return unMarshalledObject;
	}
	
	public static String marshal(Object obj) throws Exception
	{
		String str = null;
		try
		{
			JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			StringWriter sw = new StringWriter();
			jaxbMarshaller.marshal(obj, sw);
		    str = sw.toString();
		}
		catch(Exception ex)
		{
			throw new Exception("Invalid XML file content. XML file content is malformed.", ex);
		}
		
		return str;
	}
	
}
