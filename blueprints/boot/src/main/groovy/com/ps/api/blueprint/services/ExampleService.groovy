package com.ps.api.blueprint.services

import com.ps.api.blueprint.repositories.ExampleRepository
import groovy.transform.CompileStatic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
@CompileStatic
class ExampleService {

    private final ExampleRepository repository

    @Autowired
    ExampleService(ExampleRepository repository) {
        this.repository = repository
    }

    public String get(final String name) {
        "Entity-${repository.findByName(name)?.id ?: '?'}"
    }
}
