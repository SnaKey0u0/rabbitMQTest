package rabbit.test.publisher;

import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rabbit.test.config.MessageConfig;
import rabbit.test.dto.Order;
import rabbit.test.dto.OrderStatus;

@RestController
@RequestMapping("/order")
public class OrderPublisher {
	@Autowired
	private RabbitTemplate template;
	
	@PostMapping("/{restaurantName}")
	public String bookOrder(@RequestBody Order order,@PathVariable String restaurantName) {
		order.setOrderId(UUID.randomUUID().toString());
		OrderStatus orderStatus = new OrderStatus(order,"PROCESS","order place successfully in "+restaurantName);
		
		//EXCHANGE透過ROUTING_KEY找到綁定的QUEUE，將訊息傳送過去
		template.convertAndSend(MessageConfig.EXCHANGE,MessageConfig.ROUTING_KEY,orderStatus);
		return "Success !!";
	}
}
