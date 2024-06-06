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

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectEvent event) {
        // 카프카 컨슈머 시작
        kafkaConsumerService.startListening();
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        // 카프카 컨슈머 종료
        kafkaConsumerService.stopListening();
    }
}