package chatting.chatting.chatMessage;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatMessageRepository chatMessageRepository;

    @Transactional
    public Flux<ResponseMessageDto> findChatMessages(Long id) {
        Flux<ChatMessage> chatMessages = chatMessageRepository.findAllByRoomId(id);
        return chatMessages.map(
            chatMessage -> new ResponseMessageDto(chatMessage.getId(), chatMessage.getRoomId(),
                chatMessage.getContent(), chatMessage.getWriterId(), chatMessage.getCreatedDate()));
    }
}