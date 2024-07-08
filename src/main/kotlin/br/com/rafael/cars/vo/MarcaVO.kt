package br.com.rafael.cars.vo

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.github.dozermapper.core.Mapping


@JsonPropertyOrder("id", "nome_marca")
data class MarcaVO (

    @Mapping("id")
    @field:JsonProperty("id")
    var key: Long = 0,

    var nomeMarca: String = ""
)