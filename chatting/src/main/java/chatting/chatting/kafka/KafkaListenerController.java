package chatting.chatting.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaListenerController {

    @Autowired
    private KafkaConsumerService kafkaConsumerService;

    @GetMapping("/start")
    public String start() {
        kafkaConsumerService.startListening();
        return "start";
    }

    @GetMapping("/stop")
    public String stop() {
        kafkaConsumerService.stopListening();
        return "stop";
    }
}
