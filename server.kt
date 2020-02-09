package server

import lab1.RomeToArabic
import java.io.InputStream
import java.io.OutputStream
import java.net.ServerSocket
import java.net.Socket
import java.nio.charset.Charset
import java.util.*

const val PORT = 12345

fun main() {
    var server = ServerSocket(PORT)
    println("Server is running on port ${server.localPort}")
    val client = server.accept()
    println ("Client connected: ${client.inetAddress.hostAddress}")
    ClientHandler(client).run()

}
class ClientHandler (client: Socket) {
    private val client: Socket = client
    private val reader: Scanner = Scanner(client.getInputStream())
    private val writer: OutputStream = client.getOutputStream()
    private var running = false


    fun run() {
        running = true
        while (running) {
            write("Hello! Enter your Rome number below:\n")
            val text = reader.nextLine()
            if (text == "exit") {
                shutdown()
                continue
            }
            val roman = RomeToArabic(text)
            println("Client inputted $text")
            write("Your arabic number is: ${roman.getResult()}")
            println(roman.arabic)



        }
    }

    private fun write(message: String) {
        writer.write((message + '\n').toByteArray())
        writer.flush()
    }
    private fun shutdown() {
        running = false
        client.close()
    }
}