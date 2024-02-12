package com.jjeanjacques.rinhabackend.repository

import com.jjeanjacques.rinhabackend.entity.ClientEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ClientRepository : JpaRepository<ClientEntity, Long> {
}