package com.greensqa.parsertextgsqa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/parser")
public class ChatGPTController {

	@PostMapping
	public ResponseEntity<String> parser(@RequestBody String us) {

		try {
			String respuesta = ChatGPTClient.askQuestion(us);
			return ResponseEntity.ok(respuesta);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al hacer la pregunta");
		}
	}

}
