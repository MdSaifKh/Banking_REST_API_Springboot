package com.account.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassbookDto {
    private Long id;
    private Long accountId;
    private String fileName;
    private String contentType;
    private byte[] data;
}
