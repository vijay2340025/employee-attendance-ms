package com.domain.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NotificationListener {

    @KafkaListener(topics = "absentee_notification_topic", groupId = "notify-app")
    public void notifyAbsentee(ConsumerRecord<Integer, String> record) {
        Integer employeeId = record.key();
        String status = record.value();
        log.info(employeeId + " is " + status + " today");
    }
}
