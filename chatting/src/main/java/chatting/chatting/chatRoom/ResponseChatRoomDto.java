package chatting.chatting.chatRoom;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseChatRoomDto {

    private Long id;
    private String title;
    private Date createDate;

    public static ResponseChatRoomDto of(ChatRoom chatRoom) {
        return new ResponseChatRoomDto(chatRoom.getId(), chatRoom.getTitle(),
            chatRoom.getNewDate());
    }
}
