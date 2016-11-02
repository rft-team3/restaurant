package hu.unideb.inf.rft.restaurant.client.api.service;

import hu.unideb.inf.rft.restaurant.client.api.exception.EmailSendingException;

public interface MailService {

    void sendMail(String mailFrom, String mailTo, String subject, String mailText) throws EmailSendingException;

}
