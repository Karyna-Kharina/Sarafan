package org.homeproject.demo.sarafan.controller;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.homeproject.demo.sarafan.domain.User;
import org.homeproject.demo.sarafan.domain.Views;
import org.homeproject.demo.sarafan.repository.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;


@Controller
@RequestMapping("/")
public class MainController {

    private final MessageRepo messageRepo;
    private final ObjectWriter writer;

    @Value("${spring.profiles.active}")
    private String profile;

    @Autowired
    public MainController(MessageRepo messageRepo, ObjectMapper mapper) {
        this.messageRepo = messageRepo;
        this.writer = mapper
                .setConfig(mapper.getSerializationConfig())
                .writerWithView(Views.FullMessage.class);
    }

    @GetMapping
    public String main(
            Model model,
            @AuthenticationPrincipal User user
    ) throws JsonProcessingException {

        HashMap<Object, Object> data = new HashMap<>();

        if (user != null) {

            data.put("profile", user);
            String messages = writer.writeValueAsString(messageRepo.findAll());
            model.addAttribute("messages", messages);
        } else {
            model.addAttribute("messages", "[]");
        }

        model.addAttribute("frontendData", data);
        model.addAttribute("isDevMode", "dev".equals(profile));

        return "index";
    }
}