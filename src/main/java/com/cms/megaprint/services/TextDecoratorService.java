package com.cms.megaprint.services;

import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;
import java.lang.String;

@Service
public class TextDecoratorService {

    private class PairOperatorReducer implements BinaryOperator<String> {

        private final String openTag;
        private final String closeTag;

        private boolean flag = false;

        private PairOperatorReducer(String openTag, String closeTag) {
            this.openTag = openTag;
            this.closeTag = closeTag;
        }

        @Override
        public String apply(String s, String s2) {
            flag = !flag;
            return s + (flag ? openTag : closeTag) + s2;
        }
    };

    public boolean startsWith(String str, String start) {
        if (start.length() <= str.length()) {
            for(int i = 0; i < start.length(); i++) {
                if (start.charAt(i) != str.charAt(i)) {
                    return false;
                }
            }
        }
        return true;
    }

    public String decorate(String text) {
        Optional<String> res;
        // list
        res = Stream.of(text.split("\n")).reduce(new BinaryOperator<String>() {
            private boolean inList = false;
            private boolean firstLine = true;
            @Override
            public String apply(String s, String s2) {
                String result = "";
                if (firstLine) {
                    if(startsWith(s, "* ")) {
                        inList = true;
                        result += "<ul><li>" + s.replace("* ", "") + "</li>";
                    } else {
                        result += s;
                    }
                } else {
                    result += s;
                }
                if (startsWith(s2, "* ")) {
                    if (inList) {
                        result += "<li>" + s2.replace("* ", "") +  "</li>";
                    } else {
                        inList = true;
                        result += "<ul><li>" + s2.replace("* ", "") + "</li>";
                    }
                } else {
                    if (inList) {
                        result += "</ul>";
                        inList = false;
                        result += s2;
                    } else {
                        result += "\n" + s2;
                    }
                }
                firstLine = false;
                return result;
            }
        });
        if (res.isPresent()) {
            text = res.get();
        }
        // split by newline
        res = Stream.of(text.split("\n")).reduce((s, s2) -> s += "<br>" + s2);
        if (res.isPresent()) {
            text = res.get();
        }
        // italic
        res = Stream.of(text.split("__")).reduce(new PairOperatorReducer("<i>", "</i>"));
        if (res.isPresent()) {
            text = res.get();
        }
        // bold
        res = Stream.of(text.split("\\*\\*")).reduce(new PairOperatorReducer("<b>", "</b>"));
        if (res.isPresent()) {
            text = res.get();
        }
        // underline
        res = Stream.of(text.split("##")).reduce(new PairOperatorReducer("<u>", "</u>"));
        if (res.isPresent()) {
            text = res.get();
        }
        // orange :)
        res = Stream.of(text.split("@@")).reduce(new PairOperatorReducer("<span class='txt_orange'>", "</span>"));
        if (res.isPresent()) {
            text = res.get();
        }
        return text;
    }

}
