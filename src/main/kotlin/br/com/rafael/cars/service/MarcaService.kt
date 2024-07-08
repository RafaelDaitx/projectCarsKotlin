package br.com.rafael.cars.service

import br.com.rafael.cars.exceptions.ResourceNotFoundException
import br.com.rafael.cars.model.Marca
import br.com.rafael.cars.repository.MarcaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class MarcaService {

    @Autowired
    private lateinit var repository: MarcaRepository

    private val logger = Logger.getLogger(MarcaService::class.java.name)

    fun save(marca: Marca): Marca{
        logger.info("Saving brand with id: " + marca.id);
        return repository.save(marca)
    }

    fun update(marca: Marca): Marca{
        logger.info("Updating brand with id: " + marca.id);
        val entity = repository.findById(marca.id)
            .orElseThrow { ResourceNotFoundException("No records found for this ID!") }

        entity.nome_marca = marca.nome_marca

        return repository.save(entity)
    }

    fun findAll(): List<Marca>{
        logger.info("Findig all brands")
        return repository.findAll()
    }

    fun findById(id: Long): Marca{
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