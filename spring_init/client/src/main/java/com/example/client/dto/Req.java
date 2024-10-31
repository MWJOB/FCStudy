package com.example.client.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Req<T> {

    private Header header;

    private T resBody;

    @Override
    public String toString() {
        return "Req{" +
                "header=" + header +
                ", body=" + resBody +
                '}';
    }

    @Getter
    @Setter
    public static class Header{
        private String responseCode;

        @Override
        public String toString() {
            return "Header{" +
                    "responseCode='" + responseCode + '\'' +
                    '}';
        }
    }
}
