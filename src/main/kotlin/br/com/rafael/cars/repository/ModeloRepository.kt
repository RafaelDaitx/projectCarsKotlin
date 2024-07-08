package br.com.rafael.cars.repository

import br.com.rafael.cars.model.Modelo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ModeloRepository : JpaRepository<Modelo, Long?>{
}