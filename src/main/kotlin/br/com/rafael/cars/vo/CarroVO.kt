package br.com.rafael.cars.vo

import br.com.rafael.cars.model.Modelo
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.github.dozermapper.core.Mapping
import java.util.*

@JsonPropertyOrder(
    "id",
    "timestamp_cadastro",
    "modelo_id",
    "ano",
    "combustivel",
    "num_portas",
    "cor",
    "nome_modelo",
    "valor"
)
data class CarroVO (
    @Mapping("id")
    @field:JsonProperty("id")
    var key: Long = 0,

    @field:JsonProperty("timestamp_cadastro")
    var timestampCadastro: Date = Date(),

    @field:JsonProperty("modelo_id")
    var modeloId: Long = 0,
    var ano: Int = 2024,
    var combustivel: String = "",

    @field:JsonProperty("num_portas")
    var numPortas: Int = 0,
    var cor: String = "",
    @field:JsonProperty("nome_modelo")
    var nomeModelo:String = "",
    var valor: Double = 0.00
)