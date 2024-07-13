package pl.loma.core;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Endpoint {

    PEOPLE("people/");

    @NonNull
    private final String resource;

}
