package br.com.fmatheus.app.controller.report.service.impl;

import br.com.fmatheus.app.config.properties.CustomProperties;
import br.com.fmatheus.app.controller.dto.request.DanfeXmlRequest;
import br.com.fmatheus.app.controller.dto.response.DanfeReportResponse;
import br.com.fmatheus.app.controller.report.service.DanfeReportService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Map;


@Slf4j
@RequiredArgsConstructor
@Service
public class DanfeReportServiceImpl implements DanfeReportService {

    private static final String SEPARATOR = File.separator;
    private static final String JRXML = ".jrxml";
    private static final String JASPER = ".jasper";
    private String pathReport;
    private String fileName;
    private final CustomProperties properties;

    @Override
    public void createPdf(Collection<DanfeReportResponse> reports, HttpServletResponse response) throws JRException, IOException {
        this.initPath();
        this.jasperCompile();
        this.jasperPrint(reports, response);
    }

    @Override
    public void initPath() {
        var pathRoot = System.getProperty(this.properties.getUpload().getPathRoot());
        this.pathReport = pathRoot.concat(this.properties.getUpload().getFiles().getJasper().getInvoice().getPath());
        this.fileName = this.properties.getUpload().getFiles().getJasper().getInvoice().getFileName();
    }

    @Override
    public void jasperCompile() throws JRException {
        var reportJrxml = this.pathReport.concat(SEPARATOR) + this.fileName.concat(JRXML);
        var reportJasper = this.pathReport.concat(SEPARATOR) + this.fileName.concat(JASPER);
        log.info("Compilando o arquivo {} para {}", reportJrxml, reportJasper);
        JasperCompileManager.compileReportToFile(reportJrxml, reportJasper);
    }

    @Override
    public Map<String, Object> parametersReport() {
        return Map.of();
    }

    @Override
    public void jasperPrint(Collection<DanfeReportResponse> reports, HttpServletResponse response) throws JRException, IOException {
        JRDataSource datasource = new JRBeanCollectionDataSource(reports);
        var reportJasper = this.pathReport.concat(SEPARATOR) + this.fileName.concat(JASPER);

        // Verifica se o arquivo .jasper existe
        File jasperFile = new File(reportJasper);
        if (!jasperFile.exists()) {
            throw new JRException("O arquivo JASPER não foi encontrado no caminho: " + reportJasper);
        }

        // Log para rastreamento
        log.info("Iniciando o preenchimento do relatório com o arquivo: {}", reportJasper);

        //Map<String, Object> reportParam = this.parametersReport();
        //Thread.currentThread().getContextClassLoader();

        JasperPrint printOut = JasperFillManager.fillReport(reportJasper, null, datasource);
        byte[] bytes = JasperExportManager.exportReportToPdf(printOut);

        response.setContentType("application/pdf");
        //response.setHeader("Content-Disposition", "inline");
        response.setHeader("Content-Disposition", "inline; filename=\"relatorio.pdf\"");
        response.setContentLength(bytes.length);

        OutputStream output = response.getOutputStream();
        output.write(bytes, 0, bytes.length);
        output.flush();
        output.close();

    }


}
