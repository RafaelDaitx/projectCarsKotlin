package br.com.rafael.cars.service

import api_rest_kotlin.mapper.DozerMapper
import br.com.rafael.cars.exceptions.ResourceNotFoundException
import br.com.rafael.cars.model.Marca
import br.com.rafael.cars.repository.MarcaRepository
import br.com.rafael.cars.vo.MarcaVO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class MarcaService {

    @Autowired
    private lateinit var repository: MarcaRepository

    private val logger = Logger.getLogger(MarcaService::class.java.name)

    fun save(marca: MarcaVO): MarcaVO{
        logger.info("Saving brand with id: " + marca.key)

        //Converto para entidade para salvar no banco
        val entity: Marca = DozerMapper.parseObject(marca, Marca::class.java)

        //converto novamente para Vo para devolver a requisição
        return DozerMapper.parseObject( repository.save(entity), MarcaVO::class.java)
    }

    fun update(marca: MarcaVO): MarcaVO{
        logger.info("Updating brand with id: " + marca.key);
        val entity = repository.findById(marca.key)
            .orElseThrow { ResourceNotFoundException("No records found for this ID!") }

        entity.nomeMarca = marca.nomeMarca

        return DozerMapper.parseObject(repository.save(entity), MarcaVO::class.java)
    }

    fun findAll(): List<MarcaVO>{
        logger.info("Findig all brands")

        val marcas =  repository.findAll()
        return marcas.map { m -> DozerMapper.parseObject(m, MarcaVO::class.java) }
    }

    fun findById(id: Long): MarcaVO{
        logger.info("Finding brand with id: $id")
        val marca =  repository.findById(id)
            .orElseThrow { ResourceNotFoundException("No records found for this ID!") }
        return DozerMapper.parseObject(marca, MarcaVO::class.java)

    }

    fun delete(id: Long) {
        logger.info("Deleting a brand with id ${id}!")

        val entity = repository.findById(id)
            .orElseThrow { ResourceNotFoundException("No records found for this ID!") }

        repository.delete(entity)
    }
}