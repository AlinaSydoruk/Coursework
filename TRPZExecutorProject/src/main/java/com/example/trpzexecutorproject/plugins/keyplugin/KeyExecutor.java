package com.example.trpzexecutorproject.plugins.keyplugin;

import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Component;


import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Component
@AllArgsConstructor
//Receiver
public class KeyExecutor {

    public void execute(JSONObject jsonObject) throws AWTException, InterruptedException {
        System.setProperty("java.awt.headless", "false"); // це гарантує, що графічний інтерфейс користувача доступний, оскільки деякі операції з Robot не можуть бути виконані в режимі без голови (headless).
        Robot robot = new Robot(); //- цей об'єкт використовується для генерування введення з клавіатури та миші, яке імітує дії користувача.
        List<Integer> keys = Arrays.stream(jsonObject.getString("keys").split("\\s"))
                .map(KeyUtil::getCodeByName)//Клавіші, які потрібно натиснути, зчитуються з JSON об'єкта. Вони перетворюються з текстових назв у відповідні коди клавіш
                .filter(Optional::isPresent)//, після чого відбувається фільтрація, щоб вибрати лише ті клавіші, які були успішно перетворені.
                .map(Optional::get).toList();

//Якщо JSON містить ключ "simultaneously" зі значенням true, всі клавіші будуть натиснуті одночасно (executeAllSimultaneouslyPressed), інакше вони будуть натиснуті по черзі (executeAllAlternatelyPressed).
        if (jsonObject.has("simultaneously")) {
            boolean sim = jsonObject.getBoolean("simultaneously");
            if (sim) {
                executeAllSimultaneouslyPressed(keys, robot);
                return;
            }
        }
        executeAllAlternatelyPressed(keys, robot);

    }

   // всі клавіші спочатку натискаються одна за одною без звільнення. Після короткої паузи (10 мс), всі клавіші відпускаються.
    private void executeAllSimultaneouslyPressed(List<Integer> keys, Robot robot) throws InterruptedException {

        for (int key : keys) {
            robot.keyPress(key);
            Thread.sleep(10);
        }
        for (int key : keys) {
            robot.keyRelease(key);
            Thread.sleep(10);
        }
    }
//кожна клавіша натискається та відпускається по черзі з короткою паузою між натисканням та відпусканням.
    private void executeAllAlternatelyPressed(List<Integer> keys, Robot robot) throws InterruptedException {
        for (int key : keys) {
            robot.keyPress(key);
            Thread.sleep(10);
            robot.keyRelease(key);
            Thread.sleep(10);
        }
    }

}
