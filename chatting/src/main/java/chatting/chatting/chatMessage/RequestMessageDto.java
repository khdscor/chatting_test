package chatting.chatting.chatMessage;

import lombok.Getter;

@Getter
public class RequestMessageDto {
    private Long roomId;
    private String content;
    private Long writerId;

    public RequestMessageDto(Long roomId, String content, Long writerId) {
        this.roomId = roomId;
        this.content = content;
        this.writerId = writerId;
    }
}
