package br.com.rafael.cars.repository

import br.com.rafael.cars.model.Carro
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CarroRepository : JpaRepository<Carro, Long?>{
}