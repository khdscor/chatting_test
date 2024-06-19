package chatting.chatting.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@RequiredArgsConstructor
public class WebSocketEventListener {

    private final KafkaConsumerService kafkaConsumerService;
    private final WebSocketTracker webSocketTracker;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectEvent event) {
        // 웹소켓 연결 시 사용자가 아무도 없을 경우
        if (!webSocketTracker.hasActiveConnections()) {
            // 카프카 컨슈머로서 구독 시작
            kafkaConsumerService.startListening();
        }
        // 사용자 추가
        webSocketTracker.userConnected();
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        // 웹소켓 종료 시 사용자 제거
        webSocketTracker.userDisconnected();
        // 웹소켓 연결한 사용자가 아무도 없을 경우
        if (!webSocketTracker.hasActiveConnections()) {
            // 카프카 구독 종료
            kafkaConsumerService.stopListening();
        }
    }
}