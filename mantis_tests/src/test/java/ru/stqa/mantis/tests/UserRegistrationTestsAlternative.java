package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunctions;
import ru.stqa.mantis.model.UserData;

import java.time.Duration;

public class UserRegistrationTestsAlternative extends TestBase{

    @Test
    void CanRegisterUserAlternative() {

        //создать пользователя(адрес) на почтовом сервере (JamesHelper)
        var username = CommonFunctions.randomString(8);
        var email = String.format("%s@localhost", username);
        var password = "password";
        app.jamesApi().addUser(email, password);

        // заполняем форму создания и отправляем(браузер)
        app.rest().logInToCreatedAccount(new UserData()
                .withUsername(username)
                .withPassword(password)
                .withEmail(email));

        //ждем почту (MailHelper)
        var messages = app.mail().receive(email, password, Duration.ofSeconds(60));
        Assertions.assertEquals(1, messages.size());
        System.out.println(messages);

        //извлекаем ссылку из письма
        var url = app.mail().extractUrl(messages.toString());

        //проходим по ссылке и завершаем регистрацию пользователя (браузер)
        app.session().openPage(url.toString());
        app.session().endOfRegistration(username, password);

        //проверяем что пользователь может залогиниться (HttpSessionHelper)
        app.http().login(username, password);
        Assertions.assertTrue(app.http().isLoggedIn());
    }
}
