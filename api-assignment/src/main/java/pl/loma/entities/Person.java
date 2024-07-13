package pl.loma.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Person(String name, String birth_year, List<String> films, List<String> starships) {

}
