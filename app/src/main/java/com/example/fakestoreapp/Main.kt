package com.example.fakestoreapp

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun main(){
    //hilo espacio donde se puede correr un programa
    println("Inicio en hilo: ${Thread.currentThread().name}")

    //println("ABI")

    //runBlocking {
//        println("Hola desde run")
//        delay(2000)
//        println("Termino de saludar")
        //SaludoAsincrono()
    //}
    //println("Guler")
    //Corrutinas

    //cAsync()
    cWithContext()
    println("fin en hilo: ${Thread.currentThread().name}")
}

fun MyGlobal(){
    GlobalScope.launch {
        SaludoAsincrono()
    }
}
//suspend fun
suspend fun SaludoAsincrono(){
    println("Hola")
    delay(2000)
    println("Ya termine de saludarte")
}

//dispatchers
//edificio 1. APP - UI
//edificios 2.
fun cAsync(){
    runBlocking {
        val result = async(Dispatchers.Main) {
            println("Consultando datos de una API")
            delay(5000)
            println("Resultados traidos")
            "Datos"
        }
        println("El resultado es: ${result.await()}")
    }
}

fun cWithContext(){
    runBlocking {
        val result = withContext(Dispatchers.IO){
            println("WithContext en hilo: ${Thread.currentThread().name}")
            println("Consultando info de API")
            delay(2000)
            println("Datos obtenidos")
            "{age : 17}"
        }
        println("El resultado es: $result")
    }
}