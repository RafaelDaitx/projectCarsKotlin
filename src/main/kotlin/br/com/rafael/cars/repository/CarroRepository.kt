package br.com.rafael.cars.repository

import br.com.rafael.cars.model.Carro
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface CarroRepository : JpaRepository<Carro, Long?>{
    @Query("SELECT c FROM Carro c WHERE c.modelo.id = :id")
    fun findModeloInCarro(@Param("id") id: Long?): List<Carro?>?
}