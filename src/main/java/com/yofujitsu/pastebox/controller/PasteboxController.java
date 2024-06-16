package com.yofujitsu.pastebox.controller;

import com.yofujitsu.pastebox.domain.request.PasteboxRequest;
import com.yofujitsu.pastebox.domain.response.PasteboxResponse;
import com.yofujitsu.pastebox.domain.response.PasteboxUrlResponse;
import com.yofujitsu.pastebox.service.PasteboxService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;

@RestController
@RequiredArgsConstructor
public class PasteboxController {
    private final PasteboxService pasteboxService;

    @GetMapping("/")
    public Collection<PasteboxResponse> getPublicPasteList() {
        return pasteboxService.getFirstPublicPasteboxes();
    }

    @GetMapping("/{hash}")
    public PasteboxResponse getByHash(@PathVariable("hash") String hash) {
        return pasteboxService.getByHash(hash);
    }

    @PostMapping("/")
    public PasteboxUrlResponse add(@RequestBody PasteboxRequest pasteboxRequest) {
        return pasteboxService.create(pasteboxRequest);
    }
}
