## Conversion from Kotlin Object to JSON with Jackson

1. Install the feature
2. Configure the jackson  ObjectMapper
```kotlin
install(ContentNegotiation) {  
  jackson {  
  //Enable support for java.time.*  
  registerModule(JavaTimeModule())  
  
  //Serialization Features  
  enable(SerializationFeature.INDENT_OUTPUT)  
  //enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS)  
 //enable(SerializationFeature.WRAP_ROOT_VALUE)
   enable(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED)  
   disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)  
    }  
}
```
