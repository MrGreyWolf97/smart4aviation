package com.example.smart4aviation.repository

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.commons.io.IOUtils
import org.springframework.core.io.ClassPathResource
import java.nio.charset.StandardCharsets


open class JsonRepository<T>(
    private val mockPath: String,
    private val typeReference: TypeReference<List<T>>,
    private val objectMapper: ObjectMapper
) {

    fun findAll(): List<T> {
        return findAllByPathAndType(mockPath, typeReference)
    }

    private fun findAllByPathAndType(path: String, typeReference: TypeReference<List<T>>): List<T> {
        val classPathResource = ClassPathResource(path)
        val staticDataString: String = IOUtils.toString(classPathResource.inputStream, StandardCharsets.UTF_8)
        return objectMapper.readValue(staticDataString, typeReference)
    }

}