package com.example.movieFinal.common

interface ApiMapper<Domain, Entity> {

    fun mapToDomain(apiDto:Entity): Domain

}