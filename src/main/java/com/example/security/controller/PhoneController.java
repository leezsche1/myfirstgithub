package com.example.security.controller;

import com.example.security.dto.PhoneNumberDTO;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/sms")
public class PhoneController {

    @Value("${cool.api.key}")
    private String apiKey;
    @Value("${cool.api.secret}")
    private String apiSecretKey;

    final DefaultMessageService messageService;

    public PhoneController() {
        // 반드시 계정 내 등록된 유효한 API 키, API Secret Key를 입력해주셔야 합니다!
        this.messageService = NurigoApp.INSTANCE.initialize("NCSAQ3IZZJHCQVGR", "D4YJWW7PV3O0QY4NK2S5B2PXN8G7AXFR", "https://api.coolsms.co.kr");
    }

    @PostMapping("/phone")
    public SingleMessageSentResponse phoneP(@RequestBody PhoneNumberDTO number) {

        Random rand = new Random();
        String numStr = "";
        for (int i = 0; i < 4; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            numStr += ran;
        }

        String phoneNumber = number.getPhoneNumber();
        int numbering = Integer.parseInt(phoneNumber);


        Message message = new Message();
        // 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다.
        message.setFrom("01065512471");
        message.setTo(numbering + "");
        message.setText("다음 인증번호를 입력해주세요" + numStr);

        SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(message));
        System.out.println(response);

        return response;
    }


    @PostMapping("/hi1")
    public String hiP() {
        return "hi boy";
    }
}
