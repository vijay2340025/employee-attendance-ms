package com.employee.attendance.listener;

import com.employee.attendance.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EntryStatusListener {

    @Autowired
    private ReportService reportService;

    @KafkaListener(topics = "attendance_report_topic", groupId = "report-app")
    public void updateStatus(ConsumerRecord<Integer, String> record) {
        Integer employeeId = record.key();
        String status = record.value();
        reportService.updateReportData(Long.valueOf(employeeId), status);
        log.info(employeeId + " : " + status);
    }
}
