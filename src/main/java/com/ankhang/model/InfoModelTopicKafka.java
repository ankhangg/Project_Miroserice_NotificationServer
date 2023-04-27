package com.ankhang.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfoModelTopicKafka {
	private Long infoId;
	private String infoPhone;
	private String messageSend;
}
