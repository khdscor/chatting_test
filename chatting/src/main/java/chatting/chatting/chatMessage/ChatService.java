package chatting.chatting.chatMessage;

import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatMessageRepository chatMessageRepository;

    @Transactional
    public Flux<ResponseMessageDto> findChatMessages(Long id) {
        Flux<ChatMessage> chatMessages = chatMessageRepository.findAllByRoomId(id);
        return chatMessages.map(ResponseMessageDto::of);
    }

    @Transactional
    public Mono<ChatMessage> saveChatMessage(RequestMessageDto chat) {
        return chatMessageRepository.save(
            new ChatMessage(chat.getRoomId(), chat.getContent(), chat.getWriterId(), new Date()));
    }
}