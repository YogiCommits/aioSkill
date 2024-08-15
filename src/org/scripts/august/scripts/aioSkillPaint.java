package org.scripts.august.scripts;

import simple.robot.api.ClientContext;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GradientPaint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class aioSkillPaint {

    private final aioSkill script;
    private final ClientContext ctx;
    private final String scriptName;
    private final String scriptVersion;
    private final int MAX_PAINT_WIDTH = 300;
    private final int MAX_PAINT_HEIGHT = 200;
    private final List<Callable<String>> lines = new ArrayList<>();
    private final Color PAINT_TEXT_COLOR = Color.WHITE;
    private final Color PAINT_OUTLINE_COLOR = new Color(255, 165, 0);
    private final Color PAINT_BACKGROUND_COLOR = new Color(0, 0, 0, 180);
    private final Rectangle PAINT_BOUNDS = new Rectangle(5, 2, MAX_PAINT_WIDTH, MAX_PAINT_HEIGHT);
    private int versionTitleXPos = -1;
    private boolean drawingPaint = true;
    private final Rectangle GUI_BUTTON_BOUNDS = new Rectangle(5, PAINT_BOUNDS.height + 10, 60, 20);
    private String status;

    private final Font HEADER_FONT = new Font("Arial", Font.BOLD, 16);
    private final Font TEXT_FONT = new Font("Arial", Font.PLAIN, 12);

    public aioSkillPaint(final aioSkill script) {
        if (script == null) {
            throw new IllegalArgumentException("Script cannot be null");
        }

        this.ctx = script.ctx;
        this.script = script;

        if (this.ctx == null) {
            throw new IllegalStateException("Context (ctx) cannot be null");
        }

        this.scriptName = script.getName();

        if (script.getManifest() == null || script.getManifest().version() == null) {
            throw new IllegalStateException("Script manifest or version cannot be null");
        }
        this.scriptVersion = script.getManifest().version();

        this.status = script.getScriptStatus();
        if (this.status == null) {
            this.status = "Unknown Status";
        }

        addLine(() -> "Runtime: " + ctx.paint.formatTime(System.currentTimeMillis() - script.getStartTime()));
        addLine(() -> "Status: " + (script.status != null ? script.status : "Select Settings in the GUI"));
        addLine(() -> "Skill: " + (script.skill != null ? script.skill : ""));
        addLine(() -> "Option: " + (script.target != null ? script.target : ""));
    }

    public void drawPaint(Graphics2D g) {
        if (!drawingPaint) {
            return;
        }

        // Enable anti-aliasing for smoother graphics
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw a gradient background
        GradientPaint gradient = new GradientPaint(0, 0, Color.DARK_GRAY, 0, MAX_PAINT_HEIGHT, Color.BLACK, true);
        g.setPaint(gradient);
        g.fillRoundRect(PAINT_BOUNDS.x, PAINT_BOUNDS.y, PAINT_BOUNDS.width, PAINT_BOUNDS.height, 20, 20);

        // Draw the outline
        g.setColor(PAINT_OUTLINE_COLOR);
        g.drawRoundRect(PAINT_BOUNDS.x, PAINT_BOUNDS.y, PAINT_BOUNDS.width, PAINT_BOUNDS.height, 20, 20);
        g.drawLine(PAINT_BOUNDS.x, 30, PAINT_BOUNDS.x + PAINT_BOUNDS.width, 30);

        // Draw the header
        g.setFont(HEADER_FONT);
        g.setColor(PAINT_TEXT_COLOR);
        drawTitleText(g);

        // Draw the lines of text
        g.setFont(TEXT_FONT);
        int y = 50;
        for (Callable<String> line : lines) {
            try {
                drawLine(g, line.call(), 15, y);
            } catch (Exception e) {
                e.printStackTrace();
            }
            y += 18;
        }

        // Draw GUI button
        g.setColor(new Color(50, 50, 50));
        g.fillRoundRect(GUI_BUTTON_BOUNDS.x, GUI_BUTTON_BOUNDS.y, GUI_BUTTON_BOUNDS.width, GUI_BUTTON_BOUNDS.height, 10,
                10);
        g.setColor(Color.LIGHT_GRAY);
        g.drawRoundRect(GUI_BUTTON_BOUNDS.x, GUI_BUTTON_BOUNDS.y, GUI_BUTTON_BOUNDS.width, GUI_BUTTON_BOUNDS.height, 10,
                10);
        g.drawString("GUI", GUI_BUTTON_BOUNDS.x + 15, GUI_BUTTON_BOUNDS.y + 15);
    }

    public void addLine(Callable<String> line) {
        this.lines.add(line);
        reCalculatePaintBounds();
    }

    private void reCalculatePaintBounds() {
        final int height = 40 + (lines.size() * 18);
        PAINT_BOUNDS.setSize(MAX_PAINT_WIDTH, height);
        GUI_BUTTON_BOUNDS.setLocation(5, height + 10);
    }

    private void drawLine(Graphics2D g, String text, int x, int y) {
        g.drawString(text, x, y);
    }

    private void drawTitleText(Graphics2D g) {
        if (this.versionTitleXPos == -1) {
            final int textWidthRight = g.getFontMetrics().stringWidth(this.scriptVersion);
            this.versionTitleXPos = PAINT_BOUNDS.getBounds().width - textWidthRight - 10;
        }
        drawLine(g, this.scriptName, 15, 25);
        drawLine(g, this.scriptVersion, this.versionTitleXPos, 25);
    }

    public void handleMouseClick(MouseEvent e) {
        if (GUI_BUTTON_BOUNDS.contains(e.getPoint())) {
            script.setupGUI();
        } else if (PAINT_BOUNDS.contains(e.getPoint())) {
            this.drawingPaint = !this.drawingPaint;
        }
    }
}
