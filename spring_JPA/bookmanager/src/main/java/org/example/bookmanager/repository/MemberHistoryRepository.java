package org.example.bookmanager.repository;

import org.example.bookmanager.domain.MemberHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberHistoryRepository extends JpaRepository<MemberHistory,Long> {
}
