package config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


import java.util.HashMap;
import java.util.Map;


public class Config {

    {
        Map<String, String> map = new HashMap<>();
        map.put("app-id", "665b46dc787b736d55ba80c8");
        map.put("Content-Type", "application/json");//za post i put request
        map.put("Accept", "application/json");

        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .setRelaxedHTTPSValidation()
                .setBaseUri("https://dummyapi.io/data")
                .setBasePath("/v1/")
                .addHeaders(map)
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();

//    ResponseSpecification responseSpecification= new ResponseSpecBuilder()
//            .//proveri da li ima nesto zajednicko

        RestAssured.requestSpecification = requestSpecification;


    }
}
