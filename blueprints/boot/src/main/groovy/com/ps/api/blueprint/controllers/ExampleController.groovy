package com.ps.api.blueprint.controllers

import com.ps.api.blueprint.services.ExampleService
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@Slf4j
@RestController
class ExampleController {

    private final ExampleService service

    @Autowired
    ExampleController(ExampleService service) {
        this.service = service
    }

    /**
     * @api{GET} /example/{exampleId}
     * @apiVersion 1.0.0
     * @apiPermission auth
     * @apiName Get Example
     * @apiGroup Example
     *
     * @apiDescription This is an example resource
     *
     * @apiParam {Number} exampleId Identifier of example
     *
     * @apiSuccessTitle ( 200 ) OK
     * @apiSuccess{Object} Example Object
     *
     * @apiSuccessExample Success-Response:
     * HTTP/1.1 200 OK
     * {
     *    "name": ...
     * }
     */
    @RequestMapping(value = "/example/{name}", method = RequestMethod.GET, headers = 'Accept-Version=1.0.0')
    Map<String, String> getIdentity(@PathVariable String name) {
        log.info('Handling test request')
        return [name: service.get(name)]
    }
}
