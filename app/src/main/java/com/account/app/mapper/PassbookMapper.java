package com.account.app.mapper;

import com.account.app.dto.PassbookDto;
import com.account.app.entity.PassbookFile;

public class PassbookMapper {
    public static PassbookDto mapToPassbookDto(PassbookFile passbookFile){
        return new PassbookDto(
                passbookFile.getId(),
                passbookFile.getAccountId(),
                passbookFile.getFileName(),
                passbookFile.getContentType(),
                passbookFile.getData()
        );
    }
    public static PassbookFile mapToPassbookFile(PassbookDto passbookDto){
        return new PassbookFile(
                passbookDto.getId(),
                passbookDto.getAccountId(),
                passbookDto.getFileName(),
                passbookDto.getContentType(),
                passbookDto.getData()
        );
    }
}
