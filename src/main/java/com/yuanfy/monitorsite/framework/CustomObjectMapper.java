package com.yuanfy.monitorsite.framework;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.ser.CustomSerializerFactory;

public class CustomObjectMapper extends ObjectMapper {  
    
    public CustomObjectMapper(){  
        CustomSerializerFactory factory = new CustomSerializerFactory();  
        factory.addGenericMapping(Date.class, new JsonSerializer<Date>(){  
            @Override
            public void serialize(Date arg0, JsonGenerator arg1,
                    SerializerProvider arg2) throws IOException,
                    JsonProcessingException {
                arg1.writeString(DateFormatUtils.format(arg0,"yyyy-MM-dd HH:mm:ss")); 
                
            }  
        });  
        this.setSerializerFactory(factory);  
    }
}
