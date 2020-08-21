package com.lextereum.lextereumbackend.service.parser;

import com.lextereum.lextereumbackend.service.parser.languageParser.PolishParser;
import com.lextereum.lextereumbackend.service.parser.exception.ParserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DocumentParserFactory {

    private final GeneralParser parser;

    public IDocumentParser getParser(String language) {

        if (language.equals("pl")) {
            return new PolishParser(parser);
        }

        throw new ParserNotFoundException();
    }
}
