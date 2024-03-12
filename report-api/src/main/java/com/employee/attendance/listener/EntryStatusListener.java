package com.employee.attendance.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EntryStatusListener {
    @KafkaListener(topics = "attendance_report", groupId = "report-app")
    public void updateStatus(ConsumerRecord<Integer, String> record) {
        log.info(record.key() + " : " + record.value());
    }
}
