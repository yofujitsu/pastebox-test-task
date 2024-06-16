package com.yofujitsu.pastebox.domain.repository;

import org.springframework.stereotype.Repository;

import java.util.List;


public interface PasteboxRepository{
    PasteboxEntity getByHash(String hash);
    List<PasteboxEntity> getListOfPublicAndAlive(int amount);
    void add(PasteboxEntity pasteboxEntity);
}
