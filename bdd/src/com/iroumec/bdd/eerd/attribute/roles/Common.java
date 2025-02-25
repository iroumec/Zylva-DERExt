package com.iroumec.bdd.eerd.attribute.roles;

import com.iroumec.bdd.derivation.Derivation;
import com.iroumec.bdd.derivation.elements.Element;
import com.iroumec.bdd.derivation.elements.SingleElement;
import com.iroumec.bdd.derivation.elements.containers.Holder;
import com.iroumec.bdd.eerd.attribute.Attribute;
import com.iroumec.bdd.eerd.attribute.DescriptiveAttributable;
import com.iroumec.bdd.eerd.attribute.cardinalities.Cardinality;
import com.iroumec.bdd.eerd.attribute.presences.Presence;
import com.iroumec.userPreferences.LanguageManager;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public final class Common implements Rol {

    private final static Common instance = new Common();

    private Common() {}

    public static Common getInstance() {

        return instance;
    }

    @Override
    public JPopupMenu getPopupMenu(Attribute attribute) {

        JPopupMenu popupMenu = new JPopupMenu();

        JMenuItem item = new JMenuItem(LanguageManager.getMessage("action.addAttribute"));
        item.addActionListener(_ -> attribute.addAttribute());
        popupMenu.add(item);

        item = new JMenuItem(LanguageManager.getMessage("action.swapPresence"));
        item.addActionListener(_ -> attribute.swapPresence());
        popupMenu.add(item);

        item = new JMenuItem(LanguageManager.getMessage("action.swapCardinality"));
        item.addActionListener(_ -> attribute.swapCardinality());
        popupMenu.add(item);

        return popupMenu;
    }

    @Override
    public List<Derivation> getDerivations(@NotNull DescriptiveAttributable owner,
                                           @NotNull Attribute attribute,
                                           @NotNull Presence presence,
                                           @NotNull Cardinality cardinality) {



        if (cardinality.generatesDerivation()) {
            return cardinality.getDerivations(owner, attribute);
        }

        Derivation derivation = new Derivation(owner.getText());

        Holder holder = this.getHolder(attribute, presence);

        Element element = new SingleElement(attribute.getIdentifier(), holder);
        presence.addDecoration(element);
        derivation.addCommonElement(element);

        return List.of(derivation);
    }

    @Override
    public void draw(Graphics2D g2, int x, int y, int radius) {

        int doubleRadius = radius * 2;

        g2.drawOval(x, y, doubleRadius, doubleRadius);
    }
}
