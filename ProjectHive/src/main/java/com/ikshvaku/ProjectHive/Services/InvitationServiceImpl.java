package com.ikshvaku.ProjectHive.Services;

import com.ikshvaku.ProjectHive.modal.Invitation;
import com.ikshvaku.ProjectHive.repository.InvitationRepository;
import jakarta.mail.MessagingException;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class InvitationServiceImpl implements InvitationService{

    @Autowired
    private InvitationRepository invitationRepository;
    @Autowired
    private EmailService emailService;
    @Override
    public void sendInvitation(String email, Long projectId) throws MessagingException {
          String invitationToken = UUID.randomUUID().toString();

          Invitation invitation = new Invitation();
          invitation.setEmail(email);
          invitation.setProjectId(projectId);
          invitation.setToken(invitationToken);

          invitationRepository.save(invitation);

          String invitationLink = "http://localhost:5173/accept_invitation?token="+invitationToken;
          emailService.sendEmailWithToken(email,invitationLink);
    }

    @Override
    public Invitation acceptInvitation(String token, Long userId) throws Exception {
        Invitation invitation = invitationRepository.findByToken(token);
        if (invitation==null){
            throw new Exception("Invalid Invitation Token");
        }
        return invitation;
    }

    @Override
    public String getTokenByUserEmail(String userEmail) throws Exception {
        Invitation invitation = invitationRepository.findByEmail(userEmail);
        if(invitation==null){
            throw new Exception("Invalid Email");
        }
        return invitation.getToken();
    }

    @Override
    public void deleteToken(String token) {
        Invitation invitation = invitationRepository.findByToken(token);
        invitationRepository.delete(invitation);
    }
}
