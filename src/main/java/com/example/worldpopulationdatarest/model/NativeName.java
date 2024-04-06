package com.example.worldpopulationdatarest.model;

import java.util.Map;

public class NativeName {
    private Map<String, Name> names;

    public Map<String, Name> getNames() {
        return names;
    }

    public void setNames(Map<String, Name> names) {
        this.names = names;
    }

    public String getCommon(String languageCode) {
        if (names != null && names.containsKey(languageCode)) {
            return names.get(languageCode).getCommon();
        }
        return null;
    }
}
