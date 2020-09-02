package com.lextereum.lextereumbackend.service.parser;

import com.google.common.base.Optional;
import com.lextereum.lextereumbackend.model.SellAgreement;
import com.lextereum.lextereumbackend.service.parser.exception.LanguageNotFoundException;
import com.optimaize.langdetect.LanguageDetector;
import com.optimaize.langdetect.LanguageDetectorBuilder;
import com.optimaize.langdetect.i18n.LdLocale;
import com.optimaize.langdetect.ngram.NgramExtractors;
import com.optimaize.langdetect.profiles.LanguageProfile;
import com.optimaize.langdetect.profiles.LanguageProfileReader;
import com.optimaize.langdetect.text.CommonTextObjectFactories;
import com.optimaize.langdetect.text.TextObject;
import com.optimaize.langdetect.text.TextObjectFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentParserService {

    private final DocumentParserFactory documentParserFactory;

    public SellAgreement parseDocument(String document) throws IOException {
        var language = recognizeLanguage(document);
        if (language.isPresent()) {
            var parser = documentParserFactory.getParser(language.get().getLanguage());
            return parser.parseDocument(document);
        } else {
            throw new LanguageNotFoundException();
        }
    }

    private Optional<LdLocale> recognizeLanguage(String document) throws IOException {
        List<LanguageProfile> languageProfiles = new LanguageProfileReader().readAllBuiltIn();
        LanguageDetector languageDetector = LanguageDetectorBuilder.create(NgramExtractors.standard())
                                                                   .withProfiles(languageProfiles)
                                                                   .build();
        TextObjectFactory textObjectFactory = CommonTextObjectFactories.forDetectingOnLargeText();
        TextObject text = textObjectFactory.forText(document);
        com.google.common.base.Optional<LdLocale> lang = languageDetector.detect(text);
        return lang;
    }

}
