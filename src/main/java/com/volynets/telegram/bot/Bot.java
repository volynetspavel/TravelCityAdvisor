package com.volynets.telegram.bot;

import com.volynets.telegram.dto.CityAdvisorDto;
import com.volynets.telegram.exception.ResourceNotFoundException;
import com.volynets.telegram.service.CityAdvisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.stream.Collectors;

/**
 * Class for handling requests from telegram-bot.
 */
@Component
public class Bot extends TelegramLongPollingBot {

    private static final String INPUT_TEXT_CITIES = "/cities";

    private final CityAdvisorService cityAdvisorService;

    @Value("${bot.name}")
    private String botUsername;

    @Value("${bot.token}")
    private String botToken;

    @Autowired
    public Bot(CityAdvisorService cityAdvisorService) {
        this.cityAdvisorService = cityAdvisorService;
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.getMessage() != null && update.getMessage().hasText()) {
            Long chatId = update.getMessage().getChatId();
            String inputText = update.getMessage().getText();

            String response;
            switch (inputText) {
                case INPUT_TEXT_CITIES:
                    response = cityAdvisorService.findAll()
                            .stream()
                            .map(this::fromCityAdvisorToCityName)
                            .collect(Collectors.joining());
                    break;
                default:
                    try {
                        response = cityAdvisorService.findCityByName(inputText).getAdvice();
                    } catch (ResourceNotFoundException e) {
                        response = e.getMessage();
                    }
            }

            try {
                SendMessage message = new SendMessage();
                message.setChatId(String.valueOf(chatId));
                message.setText(response);

                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    private String fromCityAdvisorToCityName(CityAdvisorDto cityAdvisorDto) {
        return cityAdvisorDto.getName() + "\n";
    }
}
