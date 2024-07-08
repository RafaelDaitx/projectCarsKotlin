package br.com.rafael.cars.service

import br.com.rafael.cars.exceptions.ResourceNotFoundException
import br.com.rafael.cars.model.Modelo
import br.com.rafael.cars.repository.CarroRepository
import br.com.rafael.cars.repository.MarcaRepository
import br.com.rafael.cars.repository.ModeloRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.Banner.Mode
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class ModeloService {

    @Autowired
    private lateinit var marcaRepository: MarcaRepository

    @Autowired
    private lateinit var modeloRepository: ModeloRepository

    @Autowired
    private lateinit var carroRepository: CarroRepository

    private val logger = Logger.getLogger(ModeloService::class.java.name)

    fun save(modelo: Modelo): Modelo{
        logger.info("Saving model with id: " + modelo.id);
        return modeloRepository.save(modelo)
    }

    fun update(modelo: Modelo): Modelo{
        logger.info("Updating model with id: " + modelo.id);

        val entity = modeloRepository.findById(modelo.id)
            .orElseThrow(){ResourceNotFoundException("No records found for this ID!")}

        entity.nome = modelo.nome
        entity.valor_fipe = modelo.valor_fipe
        entity.marca = modelo.marca

        return modeloRepository.save(entity)
    }

    fun findById(id: Long): Modelo{
        logger.info("finding model with id: " + id);

        return modeloRepository.findById(id)
            .orElseThrow(){ResourceNotFoundException("No records found for this ID!")}
    }

    fun findAll(): List<Modelo>{
        logger.info("finding all models");
        return modeloRepository.findAll()
    }

    fun delete(id: Long){
        logger.info("Deleting a model with ID ${id}");

        val entity = modeloRepository.findById(id)
            .orElseThrow(){ResourceNotFoundException("No records found for this ID!")}

        modeloRepository.delete(entity)
    }

}