package com.ps.api.blueprint.domains

import groovy.transform.ToString
import javax.persistence.*

@ToString
@Entity
class ExampleDomain {

    @Id
    @GeneratedValue
    Long id

    String name

}
