fun main(args: Array<String>){

    var opc = 0

    while (opc != 3)
     {
        println(":: Bienvenido a Recipe Maker ::\n")
    
        val menu = """
         Selecciona la opción deseada
    
          1. Hacer una receta
          2. Ver mis recetas
          3. Salir
        """.trimIndent()

        println(menu)

        val response:String? = readLine()

        when(response)
         {
           "1","2","3" -> {println("\n Elegiste la opcion: $response\n\n")
                     opc = response.toInt()}

           else ->println("\nRespuesta erronea, vuelve a intentarlo\n\n")
         }

     }
       println("...Saliendo de RecipesMaker")
}
