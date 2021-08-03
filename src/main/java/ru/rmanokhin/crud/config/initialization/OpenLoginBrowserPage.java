package ru.rmanokhin.crud.config.initialization;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class OpenLoginBrowserPage {

    @EventListener(ApplicationReadyEvent.class)
    public void EventListener() {

        String url = "http://localhost:8080/";
        String os = System.getProperty("os.name").toLowerCase(); // получаем имя операционной системы
        Runtime rt = Runtime.getRuntime();
        try {
            if (os.contains("win")) {
                // не поддерживаются ссылки формата "leodev.html#someTag"
                rt.exec("rundll32 url.dll,FileProtocolHandler " + url); // если windows, открываем урлу через командную строку
            } else if (os.contains("mac")) {
                rt.exec("open " + url); // аналогично в MAC
            } else if (os.contains("nix") || os.contains("nux")) {
                // c nix системами все несколько проблемотичнее
                String[] browsers = {"epiphany", "firefox", "mozilla", "konqueror", "netscape", "opera", "links", "lynx"};
                // Формируем строку с вызовом всем браузеров через логическое ИЛИ в shell консоли
                // "browser0 "URI" || browser1 "URI" ||..."
                StringBuilder cmd = new StringBuilder();
                for (int i = 0; i < browsers.length; i++)
                    cmd.append(i == 0 ? "" : " || ").append(browsers[i]).append(" \"").append(url).append("\" ");
                rt.exec(new String[]{"sh", "-c", cmd.toString()});
            } else {
                return;
            }
        } catch (Exception ignored) {
        }



    }

}
