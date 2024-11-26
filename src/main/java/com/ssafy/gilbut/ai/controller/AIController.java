package com.ssafy.gilbut.ai.controller;

import com.ssafy.gilbut.advice.ApiResponse;
import com.ssafy.gilbut.util.PromptTemplateLoader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@CrossOrigin("*")
public class AIController {

	private final ChatModel chatModel;
	private final PromptTemplateLoader promptLoader;

	public AIController(ChatModel chatModel, PromptTemplateLoader promptLoader) {
		super();
		this.chatModel = chatModel;
		this.promptLoader = promptLoader;
	}

    @PostMapping("/chatai")
    public ResponseEntity<?> getAttractions(@RequestBody String userInput) {
        try {
            // 사용자 입력 처리

            // 유저 프롬프트 템플릿 렌더링
            PromptTemplate userTemplate = new PromptTemplate("<instruction>${userInput}</instruction>");
            userTemplate.add("userInput", userInput);
            String userCommand = userTemplate.render();
            log.info(userCommand);

            // 시스템 프롬프트 템플릿 렌더링
            String systemPromptTemplate = promptLoader.loadSystemPrompt();
            PromptTemplate systemTemplate = new PromptTemplate(systemPromptTemplate);
            String systemCommand = systemTemplate.render();
            // 메시지 생성
            Message userMessage = new UserMessage(userCommand);
            Message systemMessage = new SystemMessage(systemCommand);

            // API 호출
            String response = chatModel.call(userMessage, systemMessage);
            log.info(response);

            return ResponseEntity.ok(ApiResponse.onSuccess(response));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid input: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing request: " + e.getMessage());
        }
    }
	
}
