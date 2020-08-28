package com.apicatalog.jsonld.issue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonValue;
import javax.json.stream.JsonParser;

import org.junit.Test;

import com.apicatalog.jsonld.JsonLd;
import com.apicatalog.jsonld.api.JsonLdError;
import com.apicatalog.jsonld.document.RdfDocument;

public class NativeTypesIssue64Test {

    
    @Test
    public void testFromRdfNativeTypes() throws JsonLdError, IOException {

        final JsonArray result;
        
        try (final InputStream is = getClass().getResourceAsStream("issue64-in.nq")) {
            assertNotNull(is);
            
            result = JsonLd.fromRdf(RdfDocument.of(is)).nativeTypes().get();
            
            assertNotNull(result);
        }
        
        final JsonValue expected;
        
        try (final InputStream is = getClass().getResourceAsStream("issue64-out.json")) {
            assertNotNull(is);

            final JsonParser parser = Json.createParser(is);
            
            assertTrue(parser.hasNext());
            
            parser.next();
            
            expected = parser.getValue();
            
            assertNotNull(expected);
        }

        assertEquals(expected, result);
    }
    
}
