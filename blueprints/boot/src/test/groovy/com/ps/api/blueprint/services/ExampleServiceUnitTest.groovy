package com.ps.api.blueprint.services

import com.ps.api.blueprint.domains.ExampleDomain
import com.ps.api.blueprint.repositories.ExampleRepository
import spock.lang.Specification

class ExampleServiceUnitTest extends Specification {

    void "Find by name and doesn't exist"() {
        setup:
        ExampleRepository repository = Mock(ExampleRepository) {
            findByName('DOG') >> { null }
        }

        ExampleService service = new ExampleService(repository)

        when:
        def response = service.get('DOG')

        then:
        response == 'Entity-?'
    }

    void "Find by name and name exists"() {
        setup:
        ExampleRepository repository = Mock(ExampleRepository) {
            findByName('DOG') >> { new ExampleDomain(id: 666, name: 'DOG') }
        }

        ExampleService service = new ExampleService(repository)

        when:
        def response = service.get('DOG')

        then:
        response == 'Entity-666'
    }
}
