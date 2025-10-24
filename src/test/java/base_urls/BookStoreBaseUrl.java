package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;

public class BookStoreBaseUrl {

    protected RequestSpecification spec;
    private static String baseUrl ="https://bookstore.demoqa.com";

    @BeforeMethod
    public void setSpec() {
        spec = new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .addHeader("authorization","Basic c2RhX3phaDpBczEyMzQ1Ng==")
                .setContentType(ContentType.JSON)
                .build();
    }

}