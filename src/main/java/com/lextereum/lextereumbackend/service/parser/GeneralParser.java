package com.lextereum.lextereumbackend.service.parser;

import com.lextereum.lextereumbackend.model.DocumentKeywords;
import com.lextereum.lextereumbackend.model.SellAgreement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Component
@RequiredArgsConstructor
public class GeneralParser {

    private final NameRepository nameRepository;

    public SellAgreement getParsedDocument(String document, DocumentKeywords keywords) {
        String buyFullName = getName(document, keywords.getBuyerKeywords());
        return null;
    }

    private String getName(String document, String[] keywords){ //TODO make it looks less stupid
        AtomicReference<String> fullName = null;
        new BufferedReader(new StringReader(document)).lines().forEach(
                (line) -> {
                    if (Arrays.stream(keywords).anyMatch(line::contains)) {
                        String[] words = line.split(" ");
                        Optional<String> firstName = Arrays.stream(words)
                                                    .filter(word -> nameRepository.existsByName(word.replaceAll("[^A-Za-z0-9]", "").toLowerCase()))
                                                    .findFirst();
                        if (firstName.isPresent()) {
                            fullName.set(firstName.get() + " " + words[new String(Arrays.toString(words)).indexOf(firstName.get())].replaceAll("[^A-Za-z0-9]", ""));
                            int x =1;
                        }
                    }
                });
        return fullName.get();
    }
}
