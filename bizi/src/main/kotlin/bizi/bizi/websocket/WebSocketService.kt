package bizi.bizi.websocket

import org.springframework.web.socket.handler.TextWebSocketHandler
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.WebSocketSession
import java.util.concurrent.ConcurrentHashMap
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Component
class CustomWebSocketHandler : TextWebSocketHandler() {
    private val logger = LoggerFactory.getLogger(CustomWebSocketHandler::class.java)

    val sessions = ConcurrentHashMap<String, WebSocketSession>()
    init {
        logger.info("CustomWebSocketHandler instance created")
    }

    override fun afterConnectionEstablished(session: WebSocketSession) {
        val sessionId = session.id
        sessions[sessionId] = session
        logger.info("Client connected with session ID: $sessionId")
        logActiveSessions()
    }

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        val sessionId = session.id
        logger.info("Received message from session ID $sessionId: ${message.payload}")
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        val sessionId = session.id
        sessions.remove(sessionId)
        logger.info("Client disconnected with session ID: $sessionId")
        logActiveSessions()
    }
    fun sendMessageToRandomClient(message: String) {
        TextMessage(message)
        val randomSession = getRandomClient()
        randomSession?.let {
            sendMessageToClient(randomSession, message)
        }
    }

    fun getRandomClient(): WebSocketSession? {
        if (sessions.isEmpty()) {
            logger.warn("No active WebSocket sessions available.")
            return null
        }
        val randomSessionId = sessions.keys.random()
        return sessions[randomSessionId]
    }

    fun sendMessageToClient(session: WebSocketSession, message: String) {
        if (session.isOpen) {
            session.sendMessage(TextMessage(message))
            logger.info("Message sent to session ID: ${session.id}")
        } else {
            logger.warn("Attempted to send message to non-existent or closed session ID: ${session.id}")
        }
    }

    private fun logActiveSessions() {
        logger.info("Active sessions: ${sessions.keys.joinToString(", ")}")
    }
}
