package chatting.chatting.chatMessage;

import chatting.chatting.kafka.KafkaProducerService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    private final KafkaProducerService kafkaProducerService;

    //메세지 송신 및 수신
    @MessageMapping("/message")
    public Mono<ResponseEntity<Void>> receiveMessage(@RequestBody RequestMessageDto chat) {
        return chatService.saveChatMessage(chat).flatMap(message -> {
            // 메시지를 해당 채팅방 구독자들에게 전송
            kafkaProducerService.send(ResponseMessageDto.of(message));
            return Mono.just(ResponseEntity.ok().build());
        });
    }

    // 이전 채팅 내용 조회
    @GetMapping("/find/chat/list/{id}")
    public Mono<ResponseEntity<List<ResponseMessageDto>>> find(@PathVariable("id") Long id) {
        Flux<ResponseMessageDto> response = chatService.findChatMessages(id);
        return response.collectList().map(ResponseEntity::ok);
    }
}