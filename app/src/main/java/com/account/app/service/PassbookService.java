package com.account.app.service;


import com.account.app.dto.PassbookDto;

public interface PassbookService {
    String uploadPassbookByAccountId(PassbookDto passbookDto);

    PassbookDto downloadFileByAccountId(Long accountId);
}
