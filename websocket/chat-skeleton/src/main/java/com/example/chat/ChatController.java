package com.example.chat;

import com.example.chat.dto.ChatMessage;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequestMapping("chat")
@RequiredArgsConstructor
public class ChatController {
    private final SimpleChatHandler simpleChatHandler;
    private final Gson gson;

    @GetMapping("rooms")
    public String rooms() {
        return "rooms";
    }

    @GetMapping("enter")
    public String enter(@RequestParam("username") String username) {
        return "chat";
    }

    @GetMapping("test")
    public @ResponseBody String test() throws IOException {
        simpleChatHandler.broadcast(gson.toJson(new ChatMessage("admin", "아아 마이크테스트")));
        return "done";
    }
}
