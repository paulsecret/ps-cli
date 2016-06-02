package com.ps.api.blueprint.commons

import com.ps.api.blueprint.Application
import groovy.transform.AnnotationCollector
import org.junit.runner.RunWith
import org.springframework.boot.test.IntegrationTest
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration

@RunWith(SpringJUnit4ClassRunner)
@SpringApplicationConfiguration(Application)
@WebAppConfiguration
@ActiveProfiles('functionaltest')
@AnnotationCollector
@IntegrationTest('server.port:0')
@interface FunctionalTest {}