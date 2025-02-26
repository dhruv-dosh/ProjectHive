package com.ikshvaku.ProjectHive.Services;

import com.ikshvaku.ProjectHive.modal.Message;
import com.ikshvaku.ProjectHive.repository.MessageRepository;
import com.ikshvaku.ProjectHive.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Message sendMessage(Long senderId, Long chatId, String content) throws Exception {
        return null;
    }

    @Override
    public List<Message> getMessagesByProjectId(Long ProjectId) throws Exception {
        return null;
    }
}
