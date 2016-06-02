package com.ps.api.blueprint.handlers

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.ResponseStatus

@Controller
class BaseExceptionHandlingController {

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public Map<String, ?> notFound() {
        [:]
    }
}
