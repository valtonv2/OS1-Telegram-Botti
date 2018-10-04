import scala.util.Random
import scala.io.Source
import scala.collection.mutable.Buffer

class Cat {
  
  private val nameChooser = Random.nextInt(3)
  private val typeChooser = Random.nextInt(4)
  
  
  
  private val names = Buffer[String]("Misu", "Sir Reginald The 3rd", "koira")
  
  private val types = Buffer[String]("Norwegian Forest Cat", "Maine Coon", "Sfinx", "common")
  
  val nimi = names(nameChooser)
  
  val race = types(typeChooser)
  
  
   
  
  
  
}