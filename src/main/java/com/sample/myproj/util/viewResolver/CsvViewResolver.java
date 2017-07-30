package com.sample.myproj.util.viewResolver;

import java.util.Locale;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import com.sample.myproj.util.views.CsvView;

public class CsvViewResolver implements ViewResolver {
	@Override
    public View resolveViewName(String s, Locale locale) throws Exception {
        CsvView view = new CsvView();
        return view;
    }
}
