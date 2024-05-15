package chatting.chatting.chatMessage;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bson.types.ObjectId;

@Getter
@AllArgsConstructor
public class ResponseMessageDto {

    private ObjectId id;
    private Long roomId;
    private String content;
    private Long writerId;
    private Date createdDate;

    static public ResponseMessageDto of(ChatMessage chatMessage) {
        return new ResponseMessageDto(chatMessage.getId(), chatMessage.getRoomId(),
            chatMessage.getContent(), chatMessage.getWriterId(), chatMessage.getCreatedDate());
    }
}
