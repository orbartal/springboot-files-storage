package orbartal.demo.springboot.files.storage.api;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v1/hello")
public class HelloController {

	@GetMapping("")
	@Operation(summary = "A simple hello world")
	public ResponseEntity<?> getHello() throws IOException {
		return ResponseEntity.ok("hello");

	}

}