package bizi.bizi.websocket
import org.springframework.web.socket.handler.TextWebSocketHandler
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.WebSocketSession
import java.util.concurrent.ConcurrentHashMap
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class CustomWebSocketHandler : TextWebSocketHandler() {
    private val logger = LoggerFactory.getLogger(CustomWebSocketHandler::class.java)
    
    private val sessions = ConcurrentHashMap<String, WebSocketSession>()

    override fun afterConnectionEstablished(session: WebSocketSession) {
        val remoteAddress = session.remoteAddress?.address?.hostAddress ?: "unknown"
        val remoteName= session.remoteAddress?.address?.hostName ?: "unknown"
        val remotePort= session.remoteAddress?.port ?: "unknown"
        sessions[remoteAddress] = session
        logger.info("Client $remoteAddress $remoteName port $remotePort connected")
    }

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        val remoteAddress = session.remoteAddress?.address?.hostAddress ?: "unknown"
        logger.info("Received message from $remoteAddress: ${message.payload}")
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        val remoteAddress = session.remoteAddress?.address?.hostAddress ?: "unknown"
        sessions.remove(remoteAddress)
        logger.info("Client $remoteAddress disconnected")
    }

    fun sendMessageToClient(clientAddress: String, message: String) {
        sessions[clientAddress]?.sendMessage(TextMessage(message))
    }
    fun getRandomClient(): String? {
        return if (sessions.isNotEmpty()) {
            sessions.keys.random()
        } else {
            null
        }
    }
}