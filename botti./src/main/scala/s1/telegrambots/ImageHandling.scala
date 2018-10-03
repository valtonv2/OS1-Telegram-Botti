package s1.telegrambots

import java.awt.image.BufferedImage
import java.net.URL
import java.nio.file.Paths
import javax.imageio.ImageIO

import info.mukel.telegrambot4s.methods.SendPhoto
import info.mukel.telegrambot4s.models.InputFile
import org.json4s.jackson.JsonMethods._




trait ImageHandling {
  this: BasicBot =>

  private val imagePath = "output.png"
  private val botURL = s"https://api.telegram.org/bot${token}/"
  private val fileURL = s"https://api.telegram.org/file/bot${token}/"

  private def getFileURL(filePath: String) = fileURL + filePath
  private def getFileCommand(fileID: String) = botURL + s"getFile?file_id=$fileID"

  /**
    * Reacts to a photo being sent
    *
    * @param action A method that does something to the image and returns the edited image
    * @return
    */
  def onPhoto(action: BufferedImage => BufferedImage) = {
    onMessage { implicit msg =>
      val image = loadImage(msg)
      val file = Paths.get("output.png").toFile
      val editedImage = ImageIO.write(action(image), "png", file)

      request(SendPhoto(msg.chat.id, InputFile(Paths.get(imagePath))))

    }
  }


  private def loadImage(message: Message) = {
    val fileID = message.photo.get(0).fileId
    val getFileURL = getFileCommand(fileID)
    val jsonString = scala.io.Source.fromURL(new URL(getFileURL)).mkString
    //println(s"GetFile URL: $getFileURL")
    val json = parse(jsonString)
    val path = compact(json \\ ("result") \\ ("file_path")).filter(_ != '"')

    val imageURL = this.getFileURL(path)
    ImageIO.read(new URL(imageURL))

  }



}
