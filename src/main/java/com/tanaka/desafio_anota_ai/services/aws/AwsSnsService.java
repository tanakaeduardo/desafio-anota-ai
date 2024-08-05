package com.tanaka.desafio_anota_ai.services.aws;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.Topic;

@Service
public class AwsSnsService {
	
	AmazonSNS snsCliente;
	
	Topic catalogTopic;
	
	public AwsSnsService(AmazonSNS snsCliente, @Qualifier("catalogEventsTopic") Topic catalogTopic) {
		this.snsCliente = snsCliente;
		this.catalogTopic = catalogTopic;
	}
	
	public void publish(MessageDTO message) {
		this.snsCliente.publish(catalogTopic.getTopicArn(), message.message());
		
	}

}
