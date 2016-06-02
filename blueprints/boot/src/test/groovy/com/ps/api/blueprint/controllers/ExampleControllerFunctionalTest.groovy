package com.ps.api.blueprint.controllers

import com.ps.api.blueprint.commons.FunctionalTest
import org.junit.Test
import org.springframework.beans.factory.annotation.Value

import static com.ps.api.blueprint.commons.RestAssuretUtil.withRestAssured
import static org.hamcrest.core.StringContains.containsString

/**
 * EXAMPLE OF THE TESTS WITH DATA FROM src/test/resources/db/migration/*
 */
@FunctionalTest
class ExampleControllerFunctionalTest {

    @Value('${local.server.port}')
    int port;

    @Test
    public void getIdentity_notExisting_shouldReturnDefaultValue_flyway() {
        withRestAssured(port).
                log().all().
                header('Content-Type', 'application/json').
                header('Accept-Version', '1.0.0').
                when().
                get("example/NOT-EXISTING").
                then().
                body(containsString("Entity-?"));

    }

    @Test
    public void getIdentity_existing_shouldReturnDefaultValue_flyway() {
        withRestAssured(port).
                log().all().
                header('Content-Type', 'application/json').
                header('Accept-Version', '1.0.0').
                when().
                get("example/DOG").
                then().
                body(containsString("Entity-1"));
    }

}
