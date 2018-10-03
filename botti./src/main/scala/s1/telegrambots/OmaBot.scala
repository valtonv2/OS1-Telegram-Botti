package s1.telegrambots

import info.mukel.telegrambot4s._
import api._
import methods.{SendMessage, _}
import models.{InlineKeyboardButton, InlineKeyboardMarkup, _}
import declarative._
import scala.util.Random

/**
 * Ärsyttävä Botti joka kääntää sanat nurinpäin ja muistelee menneitä
 *
 * Botti saamaan reagoimaan kanavan tapahtumiin luomalla funktio/metodi joka käsittelee
 * halutuntyyppistä dataa ja joko palauttaa merkkijonon tai tekee jotain muuta saamallaan
 * datalla.
 *
 * Alla on yksinkertainen esimerkki - metodi joka ottaa merkkijonon ja kääntää sen nurin.
 * Luokassa BasicBot on joukko metodeja, joilla voit asettaa botin suorittamaan oman metodisi
 * ja tekemään tiedolla jotain. replyToString rekisteröi bottiin funktion joka saa
 * syötteekseen jokaisen kanavalle kirjoitetun merkkijonon. Se, mitä funktio
 * palauttaa lähetetään kanavalle.
 */
object startOwnBot extends App {

 

   val bot =  new BasicBot() {


    def catify(userString:String) = {
        
        
        userString + " Meow."
   }

   
        }
def coinFlip:String = {

    val randomFinder = scala.util.Random
    val chooser = randomFinder.nextInt(2)

        if (chooser == 1){

          "Heads"
        } else {

          "Tails"
      }


     /**
      * Kääntää sanan toisin päin
      */
     def nurinpain(s: String) = s.reverse

     /**
      * rekisteröi botille uuden toiminnon joka ajetaan aina kun
      * kanavalle kirjoitetaan jotain.
      */
     this.replyToString(nurinpain)


     /**
      * Luo käyttäjän nimestä tervehdysviestin
      */
     def tervehdi(kayttaja: User) = "Moikka "+kayttaja.firstName

     /**
      * rekisteröi botille uuden toiminnon joka ajetaan aina kun
      * kanavalle tulee uusi käyttäjä.
      */
     this.joinMessage(tervehdi)


     /**
      * Melkein kaikki järkevä toiminta vaatii, että botin tulee
      * pystyä säilyttämään tilaa. Onneksi Botti on viime kädessä
      * ihan tavallinen olio, jolla saa olla instanssimuuttujia.
      */
     var edellinen = "ei vielä mitään"

     /**
      * Meidän bottimme pystyy muistamaan yhden sanan kerrallaan.
      * Se painaa sanan muistiin tällä metodilla
      */
     def muista(msg: Message) = {
        edellinen = getString(msg)
        "Pistettiin muistiin!"
     }

     /**
      * Metodilla command voidaan oma metodimme asettaa ajettavaksi vain tietyn komennon yhteydessä.
      */
     this.command("remember", muista)

     // Kolikon heiton kutsuminen

     this.command("flip", this.coinFlip)


     def kerro(msg: Message) = {
       "Moi " + getUserFirstName(msg) + " sehän oli " + edellinen
     }

     this.command("recall", kerro)

     // Lopuksi Botti pitää vielä saada käyntiin
     this.run()

     println("Started")
   }




   //synchronized{wait()}
}
