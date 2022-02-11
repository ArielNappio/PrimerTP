
import java.awt.AWTEventMulticaster.add

import java.awt.font.NumericShaper
import java.lang.Exception
import java.security.Principal
import java.util.*
import java.util.stream.DoubleStream.concat
import kotlin.jvm.internal.PropertyReference0Impl
import kotlin.time.measureTime
fun main(){

   val menuPrincipal = Menu(null,"Menu Principal",null)

    val subMenuA = Menu(menuPrincipal,"subMenu A",menuPrincipal)
    val subMenuB  = Menu(menuPrincipal,"subMenu B",menuPrincipal)
    val submenuC =Menu(menuPrincipal,"SubMenu C",menuPrincipal)


    val opcionA = Opcion(subMenuA,"Opcion A " ,  { it->it.lowercase() } )
    val opcionB = Opcion(subMenuA,"Opcion B " ,  { it->it.uppercase() } )

     val opcionC = Menu(submenuC,"opcion de subMenus",menuPrincipal)

    val subOpcion = Opcion(opcionC,"Opcion final" ,{it->it.lowercase()})

    subMenuA.agregar(opcionA)
    subMenuB.agregar(opcionB)
    opcionC.agregar(subOpcion)
    submenuC.agregar(opcionC)

    menuPrincipal.agregar(subMenuA)
    menuPrincipal.agregar(subMenuB)
    menuPrincipal.agregar(submenuC)


    menuPrincipal.mostrar()
}

public interface herramientas{
   fun mostrar()
   fun atras()
   fun mostrarNombre():String
}

public class Menu(val menuAnterior:Menu?,val nombreMenu:String, val padrePrincipal: Menu?):herramientas {
    val opciones  = mutableListOf<herramientas>()


    fun volverAlMenuPrincpal(){
        if(padrePrincipal == null) {
            mostrar()
        }else{
            padrePrincipal.mostrar()
        }
    }

    fun chekeo(I: Int){
        try {
            opciones[I].mostrar()
        }catch (e:Exception){
            println("Opcion Incorrecta")
            mostrar()
        }

    }

    override fun mostrar() {

        println( "${ mostrarNombre() } : ")

        opciones.forEach { opcion -> println( "    ${opciones.indexOf(opcion)} --  ${opcion.mostrarNombre()} "  )
        }


        println("    ${opciones.size}  --   Retroceder" )
        println("    ${opciones.size+1}  --  volver al menu principal " )


        println("SELECCIONE PORFAVOR..")
            seleccionarOpcion()

    }
    override fun atras() {

    if(menuAnterior!=null){
            menuAnterior.mostrar()
    }else{
        mostrar()
    }

    }

    override fun mostrarNombre():String {
    return nombreMenu
    }


    fun agregar(agregado:herramientas){
       if(!opciones.contains(agregado)){
           opciones.add(agregado)
       }
    }

    private fun seleccionarOpcion(){
           val numero  = readLine()?.toInt()

           if(numero!=null){
               when(numero){
                   opciones.size -> atras()
                   opciones.size+1 -> volverAlMenuPrincpal()
                   else -> chekeo(numero)
               }
           } else{
               println("OPCION INCORRECTA")
               mostrar()

           }
       }
    }
    class Opcion(val menuAnterior: Menu,val texto:String , val accion:(String) -> String) :herramientas {

        override fun mostrar() {
            println(accion(texto))
        }

        override fun atras() {
            menuAnterior.mostrar()
        }

        override fun mostrarNombre(): String{
            return texto
        }

    }
