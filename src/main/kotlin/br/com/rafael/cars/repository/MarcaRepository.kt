package br.com.rafael.cars.repository

import br.com.rafael.cars.model.Marca
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MarcaRepository : JpaRepository<Marca, Long?>{
}