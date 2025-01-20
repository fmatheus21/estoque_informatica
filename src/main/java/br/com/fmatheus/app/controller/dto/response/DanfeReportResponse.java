package br.com.fmatheus.app.controller.dto.response;


import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DanfeReportResponse {
    private String accessKey;
    private String serie;
    private String natureOperation;
    private String invoiceNumber;
    private String emitterName;
    private String emitterDocument;
    private String emitterAddress;
    private String emitterStreet;
    private String emitterStateRegistration;
    private String recipientName;
    private String recipientDocument;
    private String recipientAddress;
    private String recipientDistrict;
    private String recipientZipCode;
    private String recipientCity;
    private String recipientFu;
    private String authorizationProtocol;

}
