package com.ikshvaku.ProjectHive.Services;

import com.ikshvaku.ProjectHive.modal.Chat;
import com.ikshvaku.ProjectHive.modal.Message;
import com.ikshvaku.ProjectHive.modal.User;
import com.ikshvaku.ProjectHive.repository.MessageRepository;
import com.ikshvaku.ProjectHive.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProjectService projectService;

    @Override
    public Message sendMessage(Long senderId, Long projectId, String content) throws Exception {
        User sender = userRepository.findById(senderId).orElseThrow(()-> new Exception("Sender Not Found"));
        Chat chat = projectService.getProjectById(projectId).getChat();
        Message message = new Message();
        message.setContent(content);
        message.setSender(sender);
        message.setCreatedAt(LocalDateTime.now());
        message.setChat(chat);
        Message savedMessage = messageRepository.save(message);
        chat.getMessages().add(savedMessage);
        return savedMessage;
    }

    @Override
    public List<Message> getMessagesByProjectId(Long ProjectId) throws Exception {
        Chat chat = projectService.getChatByProjectId(ProjectId);
        List<Message> findByChatIdOrderByCreatedAtAsc = messageRepository.findByChatIdOrderByCreatedAtAsc(chat.getId());
        return findByChatIdOrderByCreatedAtAsc;
    }
}
