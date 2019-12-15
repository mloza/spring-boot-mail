package pl.mloza.controllers;

import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mloza.services.MailService;

@RestController
public class MailController {

    private MailService mailService;

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @GetMapping("/sendemail")
    public String sendEmail() {
        mailService.sendSimpleEmail("Michal <odbiorca@wiadomosci.com>", "Test e-mail", "Testing email functionality");

        return "E-mail sent!";
    }


    @GetMapping("/sendhtmlemail")
    public String sendHtmlEmail() {
        JtwigTemplate emailTemplate = JtwigTemplate.classpathTemplate("email/htmlEmail.twig");
        JtwigModel model = JtwigModel.newModel()
                .with("username", "Michał");

        String emailMessage = emailTemplate.render(model);

        mailService.sendHtmlEmail("Michal <michal@mloza.pl>", "Test e-mail", emailMessage);

        return "E-mail sent!";
    }
}
