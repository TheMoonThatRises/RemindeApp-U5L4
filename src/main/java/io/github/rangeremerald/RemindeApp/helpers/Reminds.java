package io.github.rangeremerald.RemindeApp.helpers;

import io.github.rangeremerald.RemindeApp.screens.Interface;

import java.awt.*;

public class Reminds {

    public static Font remindFont = new Font("san-serif", Font.PLAIN, 10);
    public static Font popupHeaderFont = new Font("san-serif", Font.PLAIN, 20);
    public static Font popupTextFont = new Font("san-serif", Font.PLAIN, 15);
    public String remindText;
    public long remindTime;
    public Rectangle remindRect, popupRect, intersectRect;

    public Reminds(String remindText, long remindTime, int remindPos) {
        this.remindText = remindText;
        this.remindTime = remindTime;

        int remindRectHeight = 20;
        this.remindRect = new Rectangle(Interface.remindersX, Interface.remindersY + remindPos * remindRectHeight, Interface.remindersWidth, remindRectHeight);
        this.popupRect = new Rectangle(remindRect.x + 5, remindRect.y + remindRect.height + 5, remindRect.width - 10, 200);
        this.intersectRect = new Rectangle(0, 0, 0, 0);


        // Creates the rectangle that allows the popup to remain while the mouse hovors over it
        this.intersectRect.x = this.popupRect.x;
        this.intersectRect.y = this.remindRect.y + this.remindRect.height;
        this.intersectRect.width = this.popupRect.width;
        this.intersectRect.height = (this.popupRect.y + this.popupRect.height) - this.intersectRect.y;
    }

    public void drawRemind(Graphics g) {
        // Draws the rect that depicts the reminder
        g.setColor(Color.gray);
        g.fillRoundRect(remindRect.x, remindRect.y, remindRect.width, remindRect.height, 20, 20);

        // Draws what the reminder will remind you of
        int stringWidth = g.getFontMetrics(remindFont).stringWidth(remindText);
        String drawString = remindText;

        if (stringWidth > remindRect.width - 50) drawString = remindText.substring(0, 40) + "..."; // Checks if the string is too long; if it is, then make it shorter

        g.setColor(Color.white);
        g.setFont(remindFont);
        g.drawString(drawString, remindRect.x + 10, remindRect.y + remindFont.getSize() / 2 + remindRect.height / 2);
    }

    public void popupRemind(Graphics g) {
        // Draw the popup window over the other reminds, showing all the information
        g.setColor(Color.gray);
        g.fillRoundRect(popupRect.x, popupRect.y, popupRect.width, popupRect.height, 20, 20);

        g.setColor(Color.white);
        g.setFont(popupHeaderFont);
        g.drawString("Remind Message: ", popupRect.x + 5, popupRect.y + popupHeaderFont.getSize());

        g.setFont(popupTextFont);
        WordWrap.drawWordWrap(g, remindText, popupRect.x + 5, popupRect.y + popupHeaderFont.getSize() + popupTextFont.getSize(), popupRect.width);
    }

    public void remindAction() {

    }

}