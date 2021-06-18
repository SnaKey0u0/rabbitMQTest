package rabbit.test.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import rabbit.test.config.MessageConfig;
import rabbit.test.dto.OrderStatus;

@Component
public class OrderConsumer {

	@RabbitListener(queues = MessageConfig.QUEUE)
	public void consumeMessageFromQueue(OrderStatus orderStatus) {
		System.out.println("Message received from queue: " + orderStatus);
	}

}
