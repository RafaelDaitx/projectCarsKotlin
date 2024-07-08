package br.com.rafael.cars.controller

import br.com.rafael.cars.model.Modelo
import br.com.rafael.cars.service.ModeloService
import br.com.rafael.cars.vo.ModeloVO
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import jakarta.websocket.server.PathParam
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/carros/modelo")
class ModeloController {


    @Autowired
    private lateinit var service: ModeloService

    @GetMapping
    @Operation(
        summary = "Finds all models",
        description = "Finds all models",
        tags = ["Brands"],
        responses = [ApiResponse(
            description = "Success",
            responseCode = "200",
            content = [Content(array = ArraySchema(schema = Schema(implementation = Modelo::class)))]
        ), ApiResponse(
            description = "No Content",
            responseCode = "204",
            content = [Content(schema = Schema(implementation = Void::class))]
        ), ApiResponse(
            description = "Bad Request",
            responseCode = "400",
            content = [Content(schema = Schema(implementation = Void::class))]
        ), ApiResponse(
            description = "Not found",
            responseCode = "404",
            content = [Content(schema = Schema(implementation = Void::class))]
        ), ApiResponse(
            description = "Internal Error",
            responseCode = "500",
            content = [Content(schema = Schema(implementation = Void::class))]
        )]
    )
    private fun findAll(): List<ModeloVO> {
        return service.findAll()
    }

    @RequestMapping(value = ["/{id}"], method = [RequestMethod.GET])
    @Operation(
        summary = "Finds a model",
        description = "Finds a model",
        tags = ["Brands"],
        responses = [ApiResponse(
            description = "Success",
            responseCode = "200",
            content = [Content(schema = Schema(implementation = Modelo::class))]
        ), ApiResponse(
            description = "No Content",
            responseCode = "204",
            content = [Content(schema = Schema(implementation = Void::class))]
        ), ApiResponse(
            description = "Bad Request",
            responseCode = "400",
            content = [Content(schema = Schema(implementation = Void::class))]
        ), ApiResponse(
            description = "Not found",
            responseCode = "404",
            content = [Content(schema = Schema(implementation = Void::class))]
        ), ApiResponse(
            description = "Internal Error",
            responseCode = "500",
            content = [Content(schema = Schema(implementation = Void::class))]
        )]
    )
    fun findbyId(@PathVariable(value = "id") id: Long): ModeloVO{
        return service.findById(id)
    }

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    @Operation(
        summary = "Create a new model",
        description = "Create a new model",
        tags = ["Brands"],
        responses = [ApiResponse(
            description = "Success",
            responseCode = "200",
            content = [Content(schema = Schema(implementation = Modelo::class))]
        ), ApiResponse(
            description = "Bad Request",
            responseCode = "400",
            content = [Content(schema = Schema(implementation = Void::class))]
        ), ApiResponse(
            description = "Not found",
            responseCode = "404",
            content = [Content(schema = Schema(implementation = Void::class))]
        ), ApiResponse(
            description = "Internal Error",
            responseCode = "500",
            content = [Content(schema = Schema(implementation = Void::class))]
        )]
    )
    fun create(@RequestBody modelo: ModeloVO): ModeloVO{
        return service.save(modelo)
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Updates a model",
        description = "Updates a model",
        tags = ["Brands"],
        responses = [ApiResponse(
            description = "Success",
            responseCode = "200",
            content = [Content(schema = Schema(implementation = Modelo::class))]
        ), ApiResponse(
            description = "No Content",
            responseCode = "204",
            content = [Content(schema = Schema(implementation = Void::class))]
        ), ApiResponse(
            description = "Bad Request",
            responseCode = "400",
            content = [Content(schema = Schema(implementation = Void::class))]
        ), ApiResponse(
            description = "Not found",
            responseCode = "404",
            content = [Content(schema = Schema(implementation = Void::class))]
        ), ApiResponse(
            description = "Internal Error",
            responseCode = "500",
            content = [Content(schema = Schema(implementation = Void::class))]
        )]
    )
    fun update(@RequestBody modelo: ModeloVO, @PathVariable("id") id: Long): ModeloVO{
        return service.update(modelo, id)
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Deletes a model",
        description = "Deletes a model",
        tags = ["Brands"],
        responses = [ApiResponse(
            description = "Bad Request",
            responseCode = "400",
            content = [Content(schema = Schema(implementation = Void::class))]
        ), ApiResponse(
            description = "Not found",
            responseCode = "404",
            content = [Content(schema = Schema(implementation = Void::class))]
        ), ApiResponse(
            description = "Internal Error",
            responseCode = "500",
            content = [Content(schema = Schema(implementation = Void::class))]
        )]
    )
    fun delete(@PathVariable(value = "id") id: Long){
        return service.delete(id)
    }
}