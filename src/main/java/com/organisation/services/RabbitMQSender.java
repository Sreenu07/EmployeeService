package com.organisation.services;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.organisation.beans.EmployeeEvent;

@Service
public class RabbitMQSender {
	
	@Autowired
	private AmqpTemplate rabbitAmqpTemplate;
	
	@Value("${employeeevent.rabbitmq.exchange}")
	private String exchange;
	
	@Value("${employeeevent.rabbitmq.routingkey}")
	private String routingkey;	
	
	public void send(EmployeeEvent employeeEvent) {
		rabbitAmqpTemplate.convertAndSend(exchange, routingkey, employeeEvent);	    
	}
}
