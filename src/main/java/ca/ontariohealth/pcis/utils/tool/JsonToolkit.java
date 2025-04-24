package ca.ontariohealth.pcis.utils.tool;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class JsonToolkit {

	private static ObjectMapper mapper = new ObjectMapper();
	
	private static ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
	
	public static String toJsonContent(Object obj) {
	
		try {
			return writer.writeValueAsString(obj);
		} 
		catch (JsonProcessingException e) {			
			e.printStackTrace();
			throw new RuntimeException(e);
		}		
		
	}	
	
}
