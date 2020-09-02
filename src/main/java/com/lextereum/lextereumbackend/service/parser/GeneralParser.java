package com.lextereum.lextereumbackend.service.parser;

import com.google.common.primitives.Ints;
import com.lextereum.lextereumbackend.model.DocumentKeywords;
import com.lextereum.lextereumbackend.model.SellAgreement;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class GeneralParser {

    private final NameRepository nameRepository;

    public SellAgreement getParsedDocument(String document, DocumentKeywords keywords) {
        String sellerFullName = getName(document, keywords.getSellerKeywords());
        String buyFullName = getName(document, keywords.getBuyerKeywords());
        String sellerId = getClientId(document) == null ? "" : getClientId(document).stream()
                                                                                    .filter(Objects::nonNull)
                                                                                    .filter(pair -> pair.getKey().equals("Seller"))
                                                                                    .findFirst()
                                                                                    .get()
                                                                                    .getValue();
        String buyerId = getClientId(document) == null ? "" : getClientId(document).stream()
                                                                                    .filter(Objects::nonNull)
                                                                                    .filter(pair -> pair.getKey().equals("Buyer"))
                                                                                    .findFirst()
                                                                                    .get()
                                                                                    .getValue();
        String date = getWordAfterKeyword(document, keywords.getDateKeywords(), Optional.empty()); // TODO: use Instant class
        int squareMeters = Optional.of(getWordAfterKeyword(document, keywords.getSquareMetersKeywords(), Optional.empty()))
                                   .map(Ints::tryParse)
                                   .orElse(0);
        String city = getWordAfterKeyword(document, keywords.getCityKeywords(), Optional.empty());
        String mortgageRegister = getWordAfterKeyword(document, keywords.getMortgageRegisterKeywords(), keywords.getMortgageRegex());
        int price = Optional.of(getWordAfterKeyword(document, keywords.getPriceKeywords(), keywords.getPriceRegex()))
                               .map(Ints::tryParse)
                               .orElse(0);
        int downpayment = Optional.of(getWordByRegex(document, keywords.getDownpaymentKeywords(), keywords.getPriceRegex()))
                                  .map(Ints::tryParse)
                                  .orElse(0);
        return SellAgreement.builder()
                            .seller(sellerFullName)
                            .buyer(buyFullName)
                            .sellerID(sellerId)
                            .buyerID(buyerId)
                            .date(date)
                            .squareMeters(squareMeters)
                            .city(city)
                            .mortgageRegister(mortgageRegister)
                            .price(price)
                            .downpayment(downpayment)
                            .build();
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

    private List<Map.Entry<String, String>> getClientId(String document) {
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


    private String getWordAfterKeyword(String document, String[] keywords, Optional<String> regex) {
        String lineWithKeyword = getLineWithKeyword(document, keywords, regex);
        Iterator<String> wordsIterator;
        for (String keyword : keywords){
            wordsIterator =  Arrays.stream(lineWithKeyword.split(" ")).iterator();
            while (wordsIterator.hasNext())
                if (wordsIterator.next().equals(keyword.replaceAll("\\s", "")))
                    return wordsIterator.next().replaceAll(",", "");
        }
        return "";
    }

    private String getWordByRegex(String document, String[] keywords, Optional<String> regex) {
        String lineWithKeyword = getLineWithKeyword(document, keywords, regex);
        Matcher matcher = Pattern.compile(regex.orElse(".*")).matcher(lineWithKeyword);
        if (matcher.find()) {
            return matcher.group();
        }
        return "";
    }

    @NotNull
    private String getLineWithKeyword(String document, String[] keywords, Optional<String> regex) {
        return Stream.of(document.split(","))
                     .map(String::new)
                     .collect(Collectors.toList())
                     .stream()
                     .map(line -> line.replaceAll("\n", " "))
                     .filter(line -> Arrays.stream(keywords).anyMatch(line::contains) && Pattern.compile(regex.orElse(".*")).matcher(line).find())
                     .findAny()
                     .map(String::new)
                     .orElse("");
    }
}
