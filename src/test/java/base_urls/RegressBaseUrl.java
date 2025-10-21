package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;

public class RegressBaseUrl {

    protected RequestSpecification spec;

    @BeforeMethod
    public void setSpec(){
        spec = new RequestSpecBuilder()
                .setBaseUri("https://reqres.in/api")
                .setContentType(ContentType.JSON)
                .addHeader("x-api-key","reqres-free-v1")
                .build();
    }

}