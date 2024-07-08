package br.com.rafael.cars.model

import jakarta.persistence.*
import java.util.*


@Entity
@Table(name = "carro")
data class Carro (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(name = "timestamp_cadastro")
    var timestamp_cadastro: Date = Date(),

    @Column(name = "ano")
    var ano: Int = 2024,

    @Column(name = "combustivel")
    var combustivel: String = "",

    @Column(name = "num_portas")
    var num_portas: Int = 0,

    @Column(name = "cor")
    var cor: String = "",

    @ManyToOne
    @JoinColumn(name = "modelo_id")
    var modelo: Modelo? = null

)