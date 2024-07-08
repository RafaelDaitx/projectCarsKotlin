package br.com.rafael.cars.service

import api_rest_kotlin.mapper.DozerMapper
import br.com.rafael.cars.exceptions.ResourceNotFoundException
import br.com.rafael.cars.model.Marca
import br.com.rafael.cars.model.Modelo
import br.com.rafael.cars.repository.CarroRepository
import br.com.rafael.cars.repository.MarcaRepository
import br.com.rafael.cars.repository.ModeloRepository
import br.com.rafael.cars.vo.MarcaVO
import br.com.rafael.cars.vo.ModeloVO
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

    fun save(modelo: ModeloVO): ModeloVO {
        logger.info("Saving model with id: " + modelo.key);

        //Converto para entidade para salvar no banco
        val entity: Modelo = DozerMapper.parseObject(modelo, Modelo::class.java)

        //converto novamente para Vo para devolver a requisição
        return DozerMapper.parseObject( modeloRepository.save(entity), ModeloVO::class.java)
    }

    fun update(modelo: ModeloVO): ModeloVO{
        logger.info("Updating model with id: " + modelo.key);

        val entity = modeloRepository.findById(modelo.key)
            .orElseThrow(){ResourceNotFoundException("No records found for this ID!")}

        entity.nome = modelo.nome
        entity.valorFipe = modelo.valorFipe
        entity.marca!!.id = modelo.marcaId

        return DozerMapper.parseObject(modeloRepository.save(entity), ModeloVO::class.java)
    }

    fun findById(id: Long): ModeloVO{
        logger.info("finding model with id: " + id);

        val modelo = modeloRepository.findById(id)
            .orElseThrow(){ResourceNotFoundException("No records found for this ID!")}

        return DozerMapper.parseObject(modelo, ModeloVO::class.java)
    }

    fun findAll(): List<ModeloVO>{
        logger.info("finding all models");
        val modelos = modeloRepository.findAll()

        return modelos.map { m ->
            ModeloVO(
                key = m.id,
                nome = m.nome,
                valorFipe = m.valorFipe,
                combustivel = m.combustivel,
                marcaId = m.marca?.id ?: 0L
            )
        }
    }

    fun delete(id: Long){
        logger.info("Deleting a model with ID ${id}");

        val entity = modeloRepository.findById(id)
            .orElseThrow(){ResourceNotFoundException("No records found for this ID!")}

        modeloRepository.delete(entity)
    }

}