package com.ps.api.blueprint.controllers

import com.ps.api.blueprint.commons.IntegtrationTest
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

import static org.junit.Assert.assertEquals

/**
 * EXAMPLE OF THE TESTS WITH DATA FROM src/test/resources/db/migration/*
 */
@IntegtrationTest
class ExampleControllerIntegrationTest {

    @Autowired
    ExampleController controller

    @Test
    public void getIdentity_notExisting_shouldReturnDefaultValue_flyway() {
        assertEquals([name: 'Entity-?'], controller.getIdentity('NOT-EXISTING'))
    }

    @Test
    public void getIdentity_existing_shouldReturnDefaultValue_flyway() {
        assertEquals([name: "Entity-1"], controller.getIdentity('DOG'))
    }

}
