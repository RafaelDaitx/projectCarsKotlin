package br.com.rafael.cars.model

import jakarta.persistence.*

@Entity
@Table(name = "modelo")
data class Modelo(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(name = "combustivel")
    var nome: String = "",

    @Column(name = "valor_fipe")
    var valor_fipe: Double = 0.00,

    @ManyToOne
    @JoinColumn(name = "marca_id")
    var marca: Marca? = null

)