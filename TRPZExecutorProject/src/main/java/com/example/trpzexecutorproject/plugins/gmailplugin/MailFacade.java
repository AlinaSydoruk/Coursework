package com.example.trpzexecutorproject.plugins.gmailplugin;

public interface MailFacade {
    void sendMessage(String toEmail, String subject, String body);
}
