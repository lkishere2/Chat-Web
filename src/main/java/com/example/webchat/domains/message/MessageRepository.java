package com.example.webchat.domains.message;

import com.example.webchat.domains.message.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findTop50ByOrderByTimestampDesc();

}
