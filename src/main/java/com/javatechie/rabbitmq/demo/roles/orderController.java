package com.javatechie.rabbitmq.demo.roles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.javatechie.rabbitmq.demo.dto.Order;
import com.javatechie.rabbitmq.demo.repository.H2Repository;
@RestController
@RequestMapping("/api/orders")
public class orderController {

	@Autowired
	private H2Repository H2Repository;

//	@ApiOperation(value = "取得書本", notes = "列出所有書本")
//	@GetMapping
//	public Iterable findAll() {
//		return H2Repository.findAll();
//	}

//	@ApiOperation(value = "搜尋書本", notes = "依書名找書")
//	@GetMapping("/title/{bookTitle}")
//	public List findByTitle(@PathVariable String bookTitle) {
//		return H2Repository.findByCustomerName(bookTitle);
//	}

//	@ApiOperation(value = "取得特定書本", notes = "依id找書")
	@GetMapping("/{customerName}")
	public List<Order> findOne(@PathVariable String customerName) {
		return H2Repository.findByCustomerName(customerName);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Order create(@RequestBody Order order) {
		return H2Repository.save(order);
	}

//	@DeleteMapping("/{id}")
//	public void delete(@PathVariable Long id) {
//		bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException());
//		bookRepository.deleteById(id);
//	}
//
//	@PutMapping("/{id}")
//	public Book updateBook(@RequestBody Book book, @PathVariable Long id) {
//		if (book.getId() != id) {
//			throw new BookIdMismatchException();
//		}
//		bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException());
//		return bookRepository.save(book);
//	}
}