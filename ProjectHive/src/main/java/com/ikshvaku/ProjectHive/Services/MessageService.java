package com.ikshvaku.ProjectHive.Services;

import com.ikshvaku.ProjectHive.modal.Message;

import java.util.List;

public interface MessageService {
    Message sendMessage(Long senderId,Long chatId,String content)throws Exception;
    List<Message> getMessagesByProjectId(Long ProjectId) throws Exception;

}
