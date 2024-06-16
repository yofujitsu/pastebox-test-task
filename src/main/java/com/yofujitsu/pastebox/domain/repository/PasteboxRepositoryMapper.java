package com.yofujitsu.pastebox.domain.repository;

import com.yofujitsu.pastebox.domain.exception.NotFoundEntityException;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class PasteboxRepositoryMapper implements PasteboxRepository {

    private final Map<String, PasteboxEntity> map = new ConcurrentHashMap<>();

    @Override
    public PasteboxEntity getByHash(String hash) {
        if (map.get(hash) == null) {
            throw new NotFoundEntityException("Pastebox not found with hash = " + hash);
        }
        return map.get(hash);
    }

    @Override
    public List<PasteboxEntity> getListOfPublicAndAlive(int amount) {
        LocalDateTime now = LocalDateTime.now();
        return map.values().stream()
                .filter(PasteboxEntity::isPublic)
                .filter(PasteboxEntity -> PasteboxEntity.getLifetime().isAfter(now))
                .sorted(Comparator.comparing(PasteboxEntity::getId).reversed())
                .limit(amount)
                .collect(Collectors.toList());
    }

    @Override
    public void add(PasteboxEntity pasteboxEntity) {
        map.put(pasteboxEntity.getHash(), pasteboxEntity);
    }
}
