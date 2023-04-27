package com.ankhang.config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

import io.micrometer.observation.Observation;

import com.ankhang.model.InfoModelTopicKafka;

import io.micrometer.tracing.Tracer;
import io.micrometer.observation.ObservationRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
@RequiredArgsConstructor
public class ProjectMicroserviceNotificationServerApplication {
	
    private final ObservationRegistry observationRegistry;
    private final Tracer tracer;
    
	public static void main(String[] args) {
		SpringApplication.run(ProjectMicroserviceNotificationServerApplication.class, args);
	}
	
	@KafkaListener(topics = "notificationTopic")
	 public void handleNotification(InfoModelTopicKafka infoModel_Topic_Kafka) {
		   Observation.createNotStarted("on-message", this.observationRegistry).observe(() -> {
	            log.info("TraceId {}, Received Notification for Number {}", this.tracer.currentSpan().context().traceId(), infoModel_Topic_Kafka.getInfoId());
	            log.info("Got message <{}>", infoModel_Topic_Kafka);
	        });
	}
	 

}
