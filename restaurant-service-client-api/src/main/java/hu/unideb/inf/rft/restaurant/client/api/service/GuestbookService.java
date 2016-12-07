package hu.unideb.inf.rft.restaurant.client.api.service;

import hu.unideb.inf.rft.restaurant.client.api.vo.GuestbookVo;

import java.time.LocalDateTime;
import java.util.List;

public interface GuestbookService {

    GuestbookVo saveMessage(GuestbookVo guestbookVo);

    void addMessage(GuestbookVo guestbookVo);

    GuestbookVo getMessageById(Long id);

    GuestbookVo getMessageByName(String name);

    GuestbookVo getMessageByTime(LocalDateTime time);

    List<GuestbookVo> getMessages();

}
