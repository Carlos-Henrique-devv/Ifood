package br.com.carlos.api.token;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Token {
    public String token;

    public Token(String token) {
        this.token = token;
    }
}
