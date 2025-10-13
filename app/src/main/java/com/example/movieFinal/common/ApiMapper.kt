package com.example.cinemana.common

interface ApiMapper<Domain, Entity> {

    fun mapToDomain(apiDto:Entity): Domain

}