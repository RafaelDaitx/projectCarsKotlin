package br.com.rafael.cars.service

import br.com.rafael.cars.exceptions.ResourceNotFoundException
import br.com.rafael.cars.model.Carro
import br.com.rafael.cars.repository.CarroRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class CarroService {


    @Autowired
    private lateinit var repository: CarroRepository

    private val logger = Logger.getLogger(CarroService::class.java.name)

    fun save(carro: Carro): Carro {
        logger.info("Saving brand with id: " + carro.id);
        return repository.save(carro)
    }

    fun update(carro: Carro): Carro {
        logger.info("Updating brand with id: " + carro.id);
        val entity = repository.findById(carro.id)
            .orElseThrow { ResourceNotFoundException("No records found for this ID!") }

        entity.ano = carro.ano
        entity.combustivel = carro.combustivel
        entity.num_portas = carro.num_portas
        entity.cor = carro.cor
        entity.modelo = carro.modelo

        return repository.save(entity)
    }

    fun findAll(): List<Carro>{
        logger.info("Findig all brands")
        return repository.findAll()
    }

    fun findById(id: Long): Carro {
        logger.info("Finding brand with id: $id")
        return repository.findById(id)
            .orElseThrow { ResourceNotFoundException("No records found for this ID!") }
    }

    fun delete(id: Long) {
        logger.info("Deleting a brand with id ${id}!")

        val entity = repository.findById(id)
            .orElseThrow { ResourceNotFoundException("No records found for this ID!") }

        repository.delete(entity)
    }
}