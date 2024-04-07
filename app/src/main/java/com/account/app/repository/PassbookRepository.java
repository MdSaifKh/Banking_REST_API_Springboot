package com.account.app.repository;

import com.account.app.entity.PassbookFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassbookRepository extends JpaRepository<PassbookFile,Long> {
    PassbookFile findByAccountId(Long accountId);
}
