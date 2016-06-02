package com.ps.api.blueprint.commons

import com.jayway.restassured.RestAssured
import com.jayway.restassured.specification.RequestSpecification

class RestAssuretUtil {

    public static RequestSpecification withRestAssured(final Integer port) {
        RestAssured.port = port
        RestAssured.baseURI = "http://localhost:${port}"
        return RestAssured.given()
    }
}
