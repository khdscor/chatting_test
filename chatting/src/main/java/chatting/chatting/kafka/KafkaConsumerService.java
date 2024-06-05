package chatting.chatting.kafka;

import chatting.chatting.chatMessage.ResponseMessageDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaConsumerService {

    private static final String TOPIC_NAME = "chatting";

    private final SimpMessageSendingOperations template;

    private final KafkaListenerEndpointRegistry endpointRegistry;

    ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(id = "dynamicListener", topics = TOPIC_NAME, autoStartup = "false")
    public void listenMessage(String jsonMessage) {
        try {
            ResponseMessageDto message = objectMapper.readValue(jsonMessage,
                ResponseMessageDto.class);
            template.convertAndSend("/sub/chatroom/" + message.getRoomId(), message);
        } catch (Exception e) {
            throw new RuntimeException("예외 발생 : " + e.getMessage());
        }
    }

    public void startListening() {
        endpointRegistry.getListenerContainer("dynamicListener").start();
    }

    public void stopListening() {
        endpointRegistry.getListenerContainer("dynamicListener").stop();
    }
}