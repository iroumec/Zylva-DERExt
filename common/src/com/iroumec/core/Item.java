package com.iroumec.core;

import com.iroumec.userPreferences.LanguageManager;
import com.iroumec.userPreferences.Multilingual;

import javax.swing.*;

public final class Item extends JMenuItem implements Multilingual {

    private final String key;

    public Item(String languageKey) {
        super(LanguageManager.getMessage(languageKey));
        LanguageManager.suscribeToLanguageResetList(this);
        this.key = languageKey;
    }

    @Override
    public void resetLanguage() {
        this.setText(LanguageManager.getMessage(key));
    }
}
