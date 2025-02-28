package com.ikshvaku.ProjectHive.Controller;

import com.ikshvaku.ProjectHive.Services.MessageService;
import com.ikshvaku.ProjectHive.Services.ProjectService;
import com.ikshvaku.ProjectHive.Services.UserService;
import com.ikshvaku.ProjectHive.modal.Chat;
import com.ikshvaku.ProjectHive.modal.Message;
import com.ikshvaku.ProjectHive.modal.User;
import com.ikshvaku.ProjectHive.request.CreateMessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProjectService projectService;

    @PostMapping("/send")
    public ResponseEntity<Message> sendMessage(@RequestBody CreateMessageRequest request)throws Exception{
        User user = userService.findUserById(request.getProjectId());

        Chat chats = projectService.getProjectById(request.getProjectId()).getChat();
        if (chats==null) throw new Exception("Chat Not Found");
        Message sentMessage = messageService.sendMessage(request.getSenderId(), request.getProjectId(), request.getContent());
        return ResponseEntity.ok(sentMessage);
    }

    @GetMapping("/chat/{projectId}")
    public ResponseEntity<List<Message>> getMessageByChatId(@PathVariable Long projectId)throws Exception{
        List<Message> messages = messageService.getMessagesByProjectId(projectId);
        return ResponseEntity.ok(messages);
    }

}
