fun main(args: Array<String>){

   var recipes:MutableList<Recipe> = mutableListOf()

 menu@ while (true) //labe break loop menu@
     {
        println(":: Bienvenido a Recipe Maker ::\n")
    
        val menu = """
         Selecciona la opción deseada
    
          1. Hacer una receta
          2. Ver mis recetas
          3. Salir
        """.trimIndent()

        println(menu)

       // val response:String? = readLine()
       val response: String = readLine() ?: "" // Elvis operator

        when(response)
         {
           "1" -> recipes.add(makeRecipe()) // Agrega una receta a la lista recipes cada que se ejecuta makeRecipe (esta devuelve una receta)
	   "2" -> readRecipes(recipes)
           "3" -> break@menu

           else ->println("\nRespuesta erronea, vuelve a intentarlo\n\n")
         }

     }
       println("...Saliendo de RecipesMaker")
}


fun makeRecipe(): Recipe{
 
  println("\n\nIntroduce el nombre de tu receta.")
  val name: String = readLine() ?: "Receta sin nombre"

  val ingredients = selectIngredients() //devuelve una lista de ingredientes seleccionados

  println("Ingresa el modo de preparación: ")
  val instructions: String = readLine() ?: "Receta sin modo de preparación"  
  println("\n\n")

  return Recipe(name, ingredients, instructions) //agrega a la receta que devuelve esta funcion el nombre y los ingredientes, sin modo de prep (editable despues)
}

fun selectIngredients(): List<Ingredient>{

    var selectedIngredients: MutableList<Ingredient> = mutableListOf()

     selectedIngredients.add(Ingredient("Fresa",5.5,"tazas") ) // falta seleccionar que ingredientes y por categorias

    return selectedIngredients
}


fun readRecipes(recipes: List<Recipe>){
 
  menu@ while (true)
  {
     val title = "       :Mis recetas:"
     println(title)

    if(recipes.isNotEmpty())
    {
      for ((index,recipe) in recipes.withIndex())
      {
        println("${index+1}. ${recipe.getRecipeName()}")
      }

      println("\nIngresa el numero de receta que quieres ver, o enter para salir")
      val recipeNum: String = readLine() ?: ""    
   
      if(recipeNum.equals("")){
        break@menu
      }
      else{
	    try{
             recipes.elementAt(recipeNum.toInt()-1).viewRecipe()

             println("..Pulsa enter para regresar a las recetas")
             readLine()
            }
	  catch(e: Exception){//out of bounds or NumberFormatException
	    println("\nRespuesta erronea, vuelve a intentarlo\n")
	  }
      }
    }//end if empty

  else{
     println("... No hay recetas para mostrar\n\n\n")
     break@menu
  }

  }//end while

}


////////////////////////////////////Clases que deberian ir en la carpeta Models si usaramos un ide como Intelij Idea////////////////

class Recipe(val name: String, val ingredients: List<Ingredient>, val instructions: String) {

  fun viewRecipe(){
     println("\n ::Receta: ${this.name} ::\n")
     println("Ingredientes:")
	 for (ingredient in this.ingredients){
        	println("   ${ingredient.toString()}")
	      }
     println("\nModo de preparación:")
     println("${this.instructions}\n")
	}

   fun getRecipeName(): String {
     return "${this.name}"
   }	

}

class Ingredient(val name: String, val quantity: Double = 0.0, val measurement_unit: String = " " ){

   override fun toString(): String {
     return "${this.quantity} ${this.measurement_unit} de ${this.name}"
   }
}
