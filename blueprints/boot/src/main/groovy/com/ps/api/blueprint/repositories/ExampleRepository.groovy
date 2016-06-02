package com.ps.api.blueprint.repositories

import com.ps.api.blueprint.domains.ExampleDomain
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ExampleRepository extends JpaRepository<ExampleDomain, Long> {

    public ExampleDomain findByName(String name)
}
