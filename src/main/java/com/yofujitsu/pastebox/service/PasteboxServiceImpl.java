package com.yofujitsu.pastebox.service;

import com.yofujitsu.pastebox.domain.repository.PasteboxEntity;
import com.yofujitsu.pastebox.domain.repository.PasteboxRepository;
import com.yofujitsu.pastebox.domain.repository.PasteboxRepositoryMapper;
import com.yofujitsu.pastebox.domain.request.PasteboxRequest;
import com.yofujitsu.pastebox.domain.request.PublicStatus;
import com.yofujitsu.pastebox.domain.response.PasteboxResponse;
import com.yofujitsu.pastebox.domain.response.PasteboxUrlResponse;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PasteboxServiceImpl implements PasteboxService {

    private final String host = "http://localhost:8081";
    private int publicListSize = 10;

    private final PasteboxRepository pasteboxRepository;
    private final AtomicInteger idGenerator = new AtomicInteger(0);

    @Override
    public PasteboxResponse getByHash(String hash) {
        PasteboxEntity pasteboxEntity = pasteboxRepository.getByHash(hash);
        return new PasteboxResponse(pasteboxEntity.getData(), pasteboxEntity.isPublic());
    }

    @Override
    public List<PasteboxResponse> getFirstPublicPasteboxes() {
        List<PasteboxEntity> list = pasteboxRepository.getListOfPublicAndAlive(publicListSize);
        return list.stream().map(PasteboxEntity ->
            new PasteboxResponse(PasteboxEntity.getData(), PasteboxEntity.isPublic()))
                .collect(Collectors.toList());
    }

    @Override
    public PasteboxUrlResponse create(PasteboxRequest pasteboxRequest) {
        int hash = generateId();

        PasteboxEntity pasteboxEntity = new PasteboxEntity();
        pasteboxEntity.setData(pasteboxRequest.getData());
        pasteboxEntity.setId(hash);
        pasteboxEntity.setLifetime(LocalDateTime.now().plusSeconds(pasteboxRequest.getExplorationTimeSeconds()));
        pasteboxEntity.setHash(Integer.toHexString(hash));
        pasteboxEntity.setPublic(pasteboxRequest.getPublicStatus() == PublicStatus.PUBLIC);
        pasteboxRepository.add(pasteboxEntity);
        return new PasteboxUrlResponse(host + "/" + pasteboxEntity.getHash());
    }

    private int generateId() {
        return idGenerator.getAndIncrement();
    };
}
