package chatting.chatting.chatRoom;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    @Transactional
    public ResponseChatRoomDto createChatRoom(RequestChatRoomDto requestChatRoomDto) {
        ChatRoom chatRoom = new ChatRoom(requestChatRoomDto.getTitle(), new Date());
        return ResponseChatRoomDto.of(chatRoomRepository.save(chatRoom));
    }

    @Transactional
    public List<ResponseChatRoomDto> findChatRoomList() {
        List<ChatRoom> chatRooms = chatRoomRepository.findAll();
        return chatRooms.stream().map(ResponseChatRoomDto::of).collect(Collectors.toList());
    }
}