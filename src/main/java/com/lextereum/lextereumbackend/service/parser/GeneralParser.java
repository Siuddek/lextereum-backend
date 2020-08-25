package com.lextereum.lextereumbackend.service.parser;

import com.lextereum.lextereumbackend.model.DocumentKeywords;
import com.lextereum.lextereumbackend.model.SellAgreement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class GeneralParser {

    private final NameRepository nameRepository;

    public SellAgreement getParsedDocument(String document, DocumentKeywords keywords) {
        String buyFullName = getName(document, keywords.getBuyerKeywords());
        String sellerId = getSellerId(document) == null ? "" : getSellerId(document).stream()
                                                                                    .filter(Objects::nonNull)
                                                                                    .filter(pair -> pair.getKey().equals("Seller"))
                                                                                    .findFirst()
                                                                                    .get()
                                                                                    .getValue();
        // SellAgreement sellAgreement = SellAgreement.builder()
        //                                            .seller(getName(document, keywords.getSellerKeywords()))
        //                                            .buyer(getName(document, keywords.getBuyerKeywords())).sellerID()
        return null;
    }

    private String getName(String document, String[] keywords){ //TODO make it looks less stupid
        AtomicReference<String> fullName = new AtomicReference<>();
        new BufferedReader(new StringReader(document)).lines().forEach(
                (line) -> {
                    if (Arrays.stream(keywords).anyMatch(line::contains)) {
                        String[] words = line.split(" ");
                        Optional<String> firstName = Arrays.stream(words)
                                                    .filter(word -> nameRepository.existsByName(word.replaceAll("[^A-Za-z0-9]", "").toLowerCase()))
                                                    .findFirst();
                        firstName.ifPresent(s -> fullName.set(s + " " + words[new String(Arrays.toString(words)).indexOf(s)].replaceAll("[^A-Za-z0-9]", "")));
                    }
                });
        return fullName.get();
    }

    private List<Map.Entry<String, String>> getSellerId(String document) {
        Pattern idPattern = Pattern.compile("[0-9]{11}");
        Matcher matcher = idPattern.matcher(document);
        if(matcher.find()) {
            List<Map.Entry<String, String>> ids = new ArrayList<>();
            ids.add(new AbstractMap.SimpleEntry<>("Seller", matcher.group()));
            matcher.find();
            ids.add(new AbstractMap.SimpleEntry<>("Buyer", matcher.group()));
            return ids;
        }
        return null;
    }

}
