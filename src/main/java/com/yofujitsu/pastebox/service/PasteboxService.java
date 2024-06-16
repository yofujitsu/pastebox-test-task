package com.yofujitsu.pastebox.service;

import com.yofujitsu.pastebox.domain.request.PasteboxRequest;
import com.yofujitsu.pastebox.domain.response.PasteboxResponse;
import com.yofujitsu.pastebox.domain.response.PasteboxUrlResponse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PasteboxService {
    PasteboxResponse getByHash(String hash);
    List<PasteboxResponse> getFirstPublicPasteboxes();
    PasteboxUrlResponse create(PasteboxRequest pasteboxRequest);
}
