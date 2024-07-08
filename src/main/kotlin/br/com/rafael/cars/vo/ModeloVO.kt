package br.com.rafael.cars.vo

import br.com.rafael.cars.model.Marca
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.github.dozermapper.core.Mapping
import jakarta.persistence.*

@JsonPropertyOrder("id", "nome", "valorFipe","combustivel", "marcaId")
data class ModeloVO(

    @Mapping("id")
    @field:JsonProperty("id")
    var key: Long = 0,
    var nome: String = "",
    var valorFipe: Double = 0.00,
    var combustivel: String = "",
    var marcaId: Long = 0
)