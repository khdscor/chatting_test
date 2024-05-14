package chatting.chatting.chatRoom;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService ChatRoomService;

    @PostMapping("/create")
    public ResponseEntity<Void> createChatRoom(@RequestBody RequestChatRoomDto requestChatRoomDto) {
        ChatRoomService.createChatRoom(requestChatRoomDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/chatList")
    public ResponseEntity<List<ResponseChatRoomDto>> getChatRoomList() {
        List<ResponseChatRoomDto> responses = ChatRoomService.findChatRoomList();
        return ResponseEntity.ok().body(responses);
    }
}