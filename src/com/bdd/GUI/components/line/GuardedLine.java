package com.bdd.GUI.components.line;

import com.bdd.GUI.components.Component;
import com.bdd.GUI.components.line.guard.Guard;
import com.bdd.GUI.Diagram;

import java.util.ArrayList;
import java.util.List;

public class GuardedLine extends Line {

    private Guard guard;

     GuardedLine(Init<?> init) {
        super(init);
        this.guard = init.guard;
        this.guard.setLine(this);
    }

    /* -------------------------------------------------------------------------------------------------------------- */
    /*                                               Overridden Methods                                               */
    /* -------------------------------------------------------------------------------------------------------------- */

    @Override
    public List<Component> getComponentsForRemoval() {
        List<Component> out = new ArrayList<>(guard.getComponentsForRemoval());
        out.add(guard);
        return out;
    }

    /* -------------------------------------------------------------------------------------------------------------- */
    /*                                                     Builder                                                    */
    /* -------------------------------------------------------------------------------------------------------------- */

    protected static abstract class Init<T extends Init<T>> extends Line.Init<T> {

        // Required parameter.
        private final Guard guard;

        public Init(Diagram diagram, Component firstComponent, Component secondComponent, Guard guard) {
            super(diagram, firstComponent, secondComponent);
            this.guard = guard;
        }

        public GuardedLine build() {
            return new GuardedLine(this);
        }
    }

    public static class Builder extends Init<Builder> {

        public Builder(Diagram diagram, Component firstComponent, Component secondComponent, Guard guard) {
            super(diagram, firstComponent, secondComponent, guard);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    @Override
    public String toString() {
        return this.guard.toString();
    }

    @Override
    public String getText() { return this.guard.getText(); }

    @Override
    public void cleanPresence() {
        this.guard = null;
        super.cleanPresence();
    }
}
