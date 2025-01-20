package br.com.fmatheus.app.controller.report.service;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

public interface GenericReportService<T> {

    void createPdf(Collection<T> reports, HttpServletResponse response) throws JRException, IOException;

    void jasperCompile() throws JRException;

    Map<String, Object> parametersReport();

    void jasperPrint(Collection<T> reports, HttpServletResponse response) throws JRException, IOException;

    void initPath();
}
