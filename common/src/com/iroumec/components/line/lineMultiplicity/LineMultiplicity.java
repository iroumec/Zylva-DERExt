package com.iroumec.components.line.lineMultiplicity;

import com.iroumec.components.line.lineShape.LineShape;

import java.awt.*;
import java.io.Serializable;

public interface LineMultiplicity extends Serializable {

    void draw(Graphics2D g2, LineShape lineShape, int x1, int y1, int x2, int y2);
}
