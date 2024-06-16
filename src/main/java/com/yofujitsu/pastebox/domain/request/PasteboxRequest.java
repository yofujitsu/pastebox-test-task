package com.yofujitsu.pastebox.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PasteboxRequest {
    private String data;
    private long explorationTimeSeconds;
    private PublicStatus publicStatus;
}
