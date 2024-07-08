package br.com.rafael.cars.model

import jakarta.persistence.*

@Entity
@Table(name = "marca")
data class Marca (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(name = "nome_marca")
    var nome_marca: String = ""
)