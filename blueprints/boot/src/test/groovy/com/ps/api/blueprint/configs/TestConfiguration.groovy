package com.ps.api.blueprint.configs

import groovy.transform.CompileStatic
import org.flywaydb.core.Flyway
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@CompileStatic
class TestConfiguration {

    @Bean
    FlywayMigrationStrategy flywayMigrationStrategy() {
        { Flyway it ->
            it.clean()
            it.migrate()
        } as FlywayMigrationStrategy
    }
}
