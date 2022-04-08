package com.epam.hotelbooking;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class CustomTag extends SimpleTagSupport {

    private String lang;

    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();
        try {
            switch (lang) {
            case "ru":
                out.print("С возвращением!");
                break;
            case "en":
                out.print("Welcome back!");
                break;
            case "fr":
                out.print("Content de te revoir!");
                break;
            default:
                out.print("Welcome back!");
                break;
            }
        } catch (IOException exception) {
            throw new JspException(exception);
        }
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}