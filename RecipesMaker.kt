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
           "1" -> {makeRecipe(); opc = response.toInt() }
	   "2" -> {viewRecipe();  opc = response.toInt() }
           "3" ->  opc = response.toInt()

           else ->println("\nRespuesta erronea, vuelve a intentarlo\n\n")
         }

     }
       println("...Saliendo de RecipesMaker")
}

fun makeRecipe(){
  val menu = """
       :Hacer receta:

Selecciona por categoría el ingrediente que buscas
   1. Agua
   2. Leche
   3. Carne
   4. Verduras
   5. Frutas
   6. Cereal
   7. Huevos
   8. Aceites
""".trimIndent()

 println(menu)

 val response:String? = readLine()

}

fun viewRecipe(){
  val menu = """
       :Ver mis recetas:

   Pollo a la bbq
   Pescado a las finas hierbas
   Filete de res con espinacas
""".trimIndent()

 println(menu)

 val response:String? = readLine()
}
