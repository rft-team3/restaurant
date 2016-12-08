package hu.unideb.inf.rft.restaurant.client.api.service;

import hu.unideb.inf.rft.restaurant.client.api.vo.GuestbookVo;

import java.util.Date;
import java.util.List;

public interface GuestbookService {

    GuestbookVo saveMessage(GuestbookVo guestbookVo);

    void addMessage(GuestbookVo guestbookVo);

    GuestbookVo getMessageById(Long id);

    GuestbookVo getMessageByName(String name);

    GuestbookVo getMessageByTime(Date time);

    List<GuestbookVo> getMessages();

}
