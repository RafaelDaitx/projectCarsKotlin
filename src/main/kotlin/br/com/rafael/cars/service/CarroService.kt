package br.com.rafael.cars.service

import api_rest_kotlin.mapper.DozerMapper
import br.com.rafael.cars.exceptions.ResourceNotFoundException
import br.com.rafael.cars.model.Carro
import br.com.rafael.cars.repository.CarroRepository
import br.com.rafael.cars.repository.ModeloRepository
import br.com.rafael.cars.vo.CarroVO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class CarroService {


    @Autowired
    private lateinit var repository: CarroRepository

    @Autowired
    private lateinit var modeloRepository: ModeloRepository

    private val logger = Logger.getLogger(CarroService::class.java.name)

    fun save(carro: CarroVO): CarroVO {
        logger.info("Saving brand with id: " + carro.key);
        val entity: Carro = DozerMapper.parseObject(carro, Carro::class.java)

        val modelo = modeloRepository.findById(carro.modeloId)
            .orElseThrow(){ResourceNotFoundException("Model not found with ID ${carro.modeloId}")}

        val vo =  DozerMapper.parseObject(repository.save(entity), CarroVO::class.java)
        vo.modeloId = modelo.id
        vo.valor  = modelo.valorFipe
        vo.nomeModelo = modelo.nome

        return vo
    }

    fun update(carro: CarroVO): CarroVO {
        logger.info("Updating brand with id: " + carro.key);
        val entity = repository.findById(carro.key)
            .orElseThrow { ResourceNotFoundException("No records found for this ID!") }

        entity.ano = carro.ano
        entity.combustivel = carro.combustivel
        entity.num_portas = carro.numPortas
        entity.cor = carro.cor
        entity.modelo!!.id = carro.modeloId

        return DozerMapper.parseObject(repository.save(entity), CarroVO::class.java)
    }

    fun findAll(): List<CarroVO>{
        logger.info("Findig all brands")
        val carros = repository.findAll()

        val vo = carros.map { m -> DozerMapper.parseObject(m, CarroVO::class.java) }

        vo.forEachIndexed { index, carroVO ->
            val carro = carros[index]
            carroVO.modeloId = carro.modelo?.id ?: 0L
            carroVO.valor = carro.modelo?.valorFipe ?: 0.0
        }
        return vo


    }

    fun findById(id: Long): CarroVO {
        logger.info("Finding brand with id: $id")
        val carro = repository.findById(id)
            .orElseThrow { ResourceNotFoundException("No records found for this ID!") }

        return DozerMapper.parseObject(carro, CarroVO::class.java)
    }

    fun delete(id: Long) {
        logger.info("Deleting a brand with id ${id}!")

        val entity = repository.findById(id)
            .orElseThrow { ResourceNotFoundException("No records found for this ID!") }

        repository.delete(entity)
    }
}