package diplom.controllers;

import diplom.entity.Mail;
import diplom.service.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    MailSender mailSender;
    @PostMapping()
    @ResponseBody
    private Mail MailSender(@RequestBody Mail mail){
        Mail mail1 = mail;
        mailSender.send(mail1.email,mail1.header,mail1.message);
        return mail;
    }
}