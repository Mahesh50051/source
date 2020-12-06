package com.kafka.microservice.businessobjects;


import java.io.InputStream;
import java.util.TimeZone;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Populate your test data with a Generic file.
 * 
 * 
 */
@Component
public class TestDataLoader
{

    public TestDataLoader()
    {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    private ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
    
  
    /**
     * Create an object from a type caller resource file.
     * 
     * @param resource
     *            the absolute path of the resource
     * @param clazz
     *            the class of the object to create
     * @return the object from the type caller
     */
    public Object createObjectFromResource(String resource, Class<?> clazz)
    {
        return createObjectFromResource(resource, clazz, this.getClass());
    }

    /**
     * Create an object from a type caller resource file.
     * 
     * @param resource
     *            the absolute or relative path of the resource
     * @param clazz
     *            the class of the object to create
     * @param contextClass
     *            the context for relative path
     * @return the object from the type caller
     */
    public Object createObjectFromResource(String resource, Class<?> clazz, Class<?> contextClass)
    {
        try
        {
            InputStream in = contextClass.getClassLoader().getResourceAsStream(resource);
            return objectMapper.readValue(in, clazz);
        }
        catch (Exception e)
        {
          throw new RuntimeException(e);
        }
    }

    /**
     * A shorthand to {@link #createObjectFromResource(String, Class)} without having to instantiate this class.
     * 
     * @param resource
     *            the path of the resource
     * @param clazz
     *            the class of the object to create
     * @return the object from the type caller
     */
    public static Object staticCreateObjectFromResource(String resource, Class<?> clazz)
    {
        return new TestDataLoader().createObjectFromResource(resource, clazz);
    }

    /**
     * A shorthand to {@link #createObjectFromResource(String, Class, Class)} without having to instantiate this class.
     * 
     * @param resource
     *            the absolute or relative path of the resource
     * @param clazz
     *            the class of the object to create
     * @param contextClass
     *            the context for relative path
     * @return the object from the type caller
     */
    public static Object staticCreateObjectFromResource(String resource, Class<?> clazz, Class<?> contextClass)
    {
        return new TestDataLoader().createObjectFromResource(resource, clazz, contextClass);
    }

    /**
     * Create an object from a type caller string.
     * 
     * @param jsonString
     *            the json string
     * @param clazz
     *            the class of the object to create
     * @return the object from the type caller
     */
    public static Object staticCreateObjectFromString(String jsonString, Class<?> clazz)
    {
        return new TestDataLoader().createObjectFromString(jsonString, clazz);
    }

    /**
     * A shorthand to {@link #exportObjectTo(Object)} without having to instantiate this class.
     * 
     * @param object
     *            the object
     * @param exportNulls
     *            true to show nulls values as well
     * @return the type caller String
     */
    public static String staticExportObjectTo(Object object, boolean exportNulls)
    {
        return new TestDataLoader().exportObjectTo(object, exportNulls);
    }

    /**
     * Create an object from a type caller string.
     * 
     * @param jsonString
     *            the type caller string
     * @param clazz
     *            the class of the object to create
     * @return the object from the type caller
     */
    public Object createObjectFromString(String jsonString, Class<?> clazz)
    {
        try
        {
            return objectMapper.readValue(jsonString, clazz);
        }
        catch (Exception e)
        {
          throw new RuntimeException(e);
        }
    }

    /**
     * Export the object to its type caller representation.
     * 
     * @param object
     *            the object
     * @param exportNulls
     *            true to show nulls values as well
     * @return the type caller String
     */
    public String exportObjectTo(Object object, boolean exportNulls)
    {
        try
        {
            ObjectMapper exportObjectMapper = new ObjectMapper();
            if (!exportNulls)
            {
                exportObjectMapper.setSerializationInclusion(Include.NON_NULL);
            }
            // exportObjectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ"));
            return exportObjectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        }
        catch (Exception e)
        {
          throw new RuntimeException(e);
        }
    }

    /**
     * @return the objectMapper
     */
    public ObjectMapper getObjectMapper()
    {
        return objectMapper;
    }

    /**
     * @param objectMapper
     *            the objectMapper to set
     */
    public void setObjectMapper(ObjectMapper objectMapper)
    {
        this.objectMapper = objectMapper;
    }
}
