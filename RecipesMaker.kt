fun main(args: Array<String>){

   var recipes:MutableList<Recipe> = mutableListOf()

 menu@ while (true) //label break loop menu@
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
  var name: String = readLine() ?: "Receta sin nombre"  //valida nulo
    if (name.isEmpty())                                 //valida vacio
      name = "Receta sin nombre"

  val ingredients = selectIngredients() //devuelve una lista de ingredientes seleccionados

  println("Ingresa el modo de preparación: ")

  var instructions: String = readLine() ?: "Receta sin modo de preparación"  
   if (instructions.isEmpty())
     instructions = "Receta sin modo de preparación"

  println("\n\n")

  return Recipe(name, ingredients, instructions) //agrega a la receta que devuelve esta funcion el nombre y los ingredientes, sin modo de prep (editable despues)
}

fun selectIngredients(): List<Ingredient>{

    var selectedIngredients: MutableList<Ingredient> = mutableListOf()

menu@ while(true)
    {
     val categorias = """
       Ingresa ingredientes para tu receta:

       Selecciona una categoría
       1. Agua
       2. Lácteos
       3. Carnes, Legumbres y huevos
       4. Verduras
       5. Frutas
       6. Granos
       7. Aceites
      
         ...pulsa enter para salir
     """.trimIndent()                                                             

     println(categorias)

    val response: String = readLine() ?: ""
       if(response.isEmpty())
          {break@menu}

    try{
     val category = response.toInt()
       when(category)                                                                               
        {   
  	  in 1..7 ->  selectedIngredients.add( getIngredient(category) )
	  else -> println("\nRespuesta erronea, vuelve a intentarlo\n\n")
        }
      } catch(e: Exception){ println("\nRespuesta erronea, vuelve a intentarlo\n\n") }
    } //end while

    return selectedIngredients
}

fun getIngredient(category: Int): Ingredient
{
    var ingrediente: String = openList(getCategory(category))
    
    println("Escribe la unidad de medida")
     val unidad: String = readLine() ?: ""

 while(true)
   {
    println("Escribe la cantidad con número")
     val cantidad: String = readLine() ?: ""

     try{
       val cant = cantidad.toDouble()
        return Ingredient(ingrediente,cant,unidad) 
     }
     catch(e: Exception){println("Cantidad errónea")}
   }

}

fun getCategory(category: Int): List<String>
{
   	when(category)
    {
      1 -> return listOf ("Agua Hirviendo","Agua Natural","Agua Hervida")  //Agua
      2 -> return listOf ("Queso Panela","Leche","Yogurth","Queso Parmesano", "Queso Gouda")//Lacteos
      3 -> return listOf ("Res","Pollo","Pescado","Jamón","Salchicha","Chorizo")  //Carnes 
      4 -> return listOf ("Lechuga","Tomate","Pepino","Limón","Zanahoria","Pimiento") //Verduras  
      5 -> return listOf ("Fresa","Plátano","Uvas","Manzana","Naranja","Pera","Cereza") // Frutas  
      6 -> return listOf ("Avena","Trigo","Arroz","Maiz")  //Granos
      7 -> return listOf ("Aceite de Oliva","Aceite de Cocina","Chimichurri") //Aceites
      else -> return listOf("")
    }
}

fun openList(list: List<String>): String
{
   while(true)
   {
    println("Elige un ingrediente") 

   for ((index,item) in list.withIndex())
   {
     println("${index+1}. $item")  
   }
    val ingrediente: String = readLine() ?: ""  

    try{
       val opc = ingrediente.toInt()
       //si esta entre 1 y el tamaño devuelve item, si no repite
       if (opc in 1..list.size ){
       return list.elementAt(opc-1)
       }
       else{
          println("Respuesta erronea, vuelve a intentarlo\n")  
       }
    }
    catch(e: Exception){
     println("Respuesta erronea, vuelve a intentarlo\n")	    
    }
 }

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

class Ingredient(val name: String, val quantity: Double = 1.0, val measurement_unit: String = " " ){

   override fun toString(): String {
     return "${this.quantity} ${this.measurement_unit} de ${this.name}"
   }
}
