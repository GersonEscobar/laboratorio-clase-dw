package com.laboratorio_clase.controladores;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api")
public class EmailController {

    @PostMapping("/upload")
    public List<String> handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        List<String> emails = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            Pattern emailPattern = Pattern.compile("[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}");

            // Leer línea por línea y buscar correos electrónicos
            while ((line = reader.readLine()) != null) {
                Matcher matcher = emailPattern.matcher(line);
                while (matcher.find()) {
                    emails.add(matcher.group());
                }
            }
        }

        // Devolver la lista de correos electrónicos en formato JSON
        return emails;
    }
}
