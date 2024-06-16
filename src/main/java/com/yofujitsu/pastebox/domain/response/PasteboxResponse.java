package com.yofujitsu.pastebox.domain.response;

import com.yofujitsu.pastebox.domain.request.PublicStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PasteboxResponse {
    private final String data;
    private final boolean isPublic;
}
