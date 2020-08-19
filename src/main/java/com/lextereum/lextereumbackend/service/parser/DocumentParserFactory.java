package com.lextereum.lextereumbackend.service.parser;

import com.lextereum.lextereumbackend.service.parser.languageParser.PolishParser;
import com.lextereum.lextereumbackend.service.parser.exception.ParserNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DocumentParserFactory {

    public IDocumentParser getParser(String language) {

        if (language.equals("pol")) {
            return new PolishParser();
        }

        throw new ParserNotFoundException();
    }
}
