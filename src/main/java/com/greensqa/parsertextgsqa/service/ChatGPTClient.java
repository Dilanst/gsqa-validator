package com.greensqa.parsertextgsqa.service;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class ChatGPTClient {
	private static final String OPENAI_API_KEY = "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";
	private static final String OPENAI_API_URL = "https://api.openai.com/v1/chat/completions";
	private static final String REQUEST = """
			{
			"model": "gpt-3.5-turbo",
			"messages": [
			{
			"role": "system",
			"content": "You are a helpful assistant."
			},
			{
			"role": "user",
			"content": "%s"
			}
			],
			"temperature":0.1
			}

			""";

	private final static String PROMPT = "Ayudame a verificar que la siguiente historia de usuario cumpla con los siguientes requerimientos y en el caso se incumpla alguna de las siguientes reglas debe informar cuales son las reglas que no se cumple y en donde. 1) Estructura: La historia de usuario presenta una adecuada estructura? Como (Rol) Quiero (Qué) De modo que (porque,Beneficio) Criterios de aceptación 2)Completa: La informacion es suficiente para entender el funcionamiento del objetivo/funcionalidad de la historia de usuario. Las deficiones se encuentran completas. 3)Clara: La reacción permite el entendimiento de la historia de usuario. 4)No ambigua: La histoia de usuario no da pie a diferentes interpretaciones. 5)Consistente: No existe contradiccion entre historia de usuario. 6)Independiente: No depende de la historia de usuario para su entendimiento o entender de una manera completa una funcionalidad especifica. Historia de usuario: ";


	public static String askQuestion(String userStory) {
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(OPENAI_API_KEY);
		headers.setContentType(MediaType.APPLICATION_JSON);

		String requestBody = String.format(REQUEST,PROMPT+replaceNewLinesAndCarriageReturns(userStory));


		HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

		ResponseEntity<String> responseEntity = restTemplate.exchange(
				OPENAI_API_URL,
				HttpMethod.POST,
				requestEntity,
				String.class
				);

		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			String jsonResponse = responseEntity.getBody();
			String content = ContentParser.extractContent(jsonResponse);
			return content;
		} else {
			throw new RuntimeException("Error conectando las APIs GSQA");
		}
	}
	
	    public static String replaceNewLinesAndCarriageReturns(String text) {
	        return text.replaceAll("[\n\r]", " ");
	    }
}
