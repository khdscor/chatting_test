package chatting.chatting.chatMessage;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    // 이전 채팅 내용 조회
    @GetMapping("/find/chat/list/{id}")
    public Mono<ResponseEntity<List<ResponseMessageDto>>> find(@PathVariable("id") Long id) {
        Flux<ResponseMessageDto> response = chatService.findChatMessages(id);
        return response.collectList().map(ResponseEntity::ok);
    }
}