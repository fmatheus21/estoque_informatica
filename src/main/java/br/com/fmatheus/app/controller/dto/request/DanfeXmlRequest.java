package br.com.fmatheus.app.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.*;

import java.util.List;


@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DanfeXmlRequest {

    @JacksonXmlProperty(localName = "versao")
    private String versao;

    @JacksonXmlProperty(localName = "NFe")
    private NFe nFe;

    @JacksonXmlProperty(localName = "protNFe")
    private ProtNFe protNFe;


    @ToString
    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class NFe {

        @JacksonXmlProperty(localName = "infNFe")
        private InfNFe infNFe;

        @JacksonXmlProperty(localName = "Signature")
        private Signature signature;

        @ToString
        @Getter
        @Setter
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class InfNFe {

            @JacksonXmlProperty(localName = "Id")
            private String id;

            @JacksonXmlProperty(localName = "versao")
            private String versao;

            @JacksonXmlProperty(localName = "ide")
            private Ide ide;

            @JacksonXmlProperty(localName = "emit")
            private Emit emit;

            @JacksonXmlProperty(localName = "dest")
            private Dest dest;

            @JacksonXmlElementWrapper(useWrapping = false)
            @JacksonXmlProperty(localName = "det")
            private List<Det> det;

            @JacksonXmlElementWrapper(useWrapping = false)
            @JacksonXmlProperty(localName = "autXML")
            private List<AutXML> autXML;

            @JacksonXmlProperty(localName = "total")
            private Total total;

            @JacksonXmlProperty(localName = "transp")
            private Transp transp;

            @JacksonXmlProperty(localName = "cobr")
            private Cobr cobr;

            @JacksonXmlProperty(localName = "pag")
            private Pag pag;

            @JacksonXmlProperty(localName = "infAdic")
            private InfAdic infAdic;

            @JacksonXmlProperty(localName = "infRespTec")
            private InfRespTec infRespTec;

            @ToString
            @Getter
            @Setter
            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class Ide {

                @JacksonXmlProperty(localName = "cUF")
                private String cUF;

                @JacksonXmlProperty(localName = "cNF")
                private String cNF;

                @JacksonXmlProperty(localName = "natOp")
                private String natOp;

                @JacksonXmlProperty(localName = "mod")
                private String mod;

                @JacksonXmlProperty(localName = "serie")
                private String serie;

                @JacksonXmlProperty(localName = "nNF")
                private String nNF;

                @JacksonXmlProperty(localName = "dhEmi")
                private String dhEmi;

                @JacksonXmlProperty(localName = "dhSaiEnt")
                private String dhSaiEnt;

                @JacksonXmlProperty(localName = "tpNF")
                private String tpNF;

                @JacksonXmlProperty(localName = "idDest")
                private String idDest;

                @JacksonXmlProperty(localName = "cMunFG")
                private String cMunFG;

                @JacksonXmlProperty(localName = "tpImp")
                private String tpImp;

                @JacksonXmlProperty(localName = "tpEmis")
                private String tpEmis;

                @JacksonXmlProperty(localName = "cDV")
                private String cDV;

                @JacksonXmlProperty(localName = "tpAmb")
                private String tpAmb;

                @JacksonXmlProperty(localName = "finNFe")
                private String finNFe;

                @JacksonXmlProperty(localName = "indFinal")
                private String indFinal;

                @JacksonXmlProperty(localName = "indPres")
                private String indPres;

                @JacksonXmlProperty(localName = "indIntermed")
                private String indIntermed;

                @JacksonXmlProperty(localName = "procEmi")
                private String procEmi;

                @JacksonXmlProperty(localName = "verProc")
                private String verProc;
            }

            @ToString
            @Getter
            @Setter
            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class Emit {

                @JacksonXmlProperty(localName = "CNPJ")
                private String cnpj;

                @JacksonXmlProperty(localName = "xNome")
                private String xNome;

                @JacksonXmlProperty(localName = "xFant")
                private String xFant;

                @JacksonXmlProperty(localName = "enderEmit")
                private EnderEmit enderEmit;

                @JacksonXmlProperty(localName = "IE")
                private String ie;

                @JacksonXmlProperty(localName = "IEST")
                private String iest;

                @JacksonXmlProperty(localName = "IM")
                private String im;

                @JacksonXmlProperty(localName = "CRT")
                private String crt;

                @ToString
                @Getter
                @Setter
                @JsonIgnoreProperties(ignoreUnknown = true)
                public static class EnderEmit {

                    @JacksonXmlProperty(localName = "xLgr")
                    private String xLgr;

                    @JacksonXmlProperty(localName = "nro")
                    private String nro;

                    @JacksonXmlProperty(localName = "xCpl")
                    private String xCpl;

                    @JacksonXmlProperty(localName = "xBairro")
                    private String xBairro;

                    @JacksonXmlProperty(localName = "cMun")
                    private String cMun;

                    @JacksonXmlProperty(localName = "xMun")
                    private String xMun;

                    @JacksonXmlProperty(localName = "UF")
                    private String uf;

                    @JacksonXmlProperty(localName = "CEP")
                    private String cep;

                    @JacksonXmlProperty(localName = "cPais")
                    private String cPais;

                    @JacksonXmlProperty(localName = "xPais")
                    private String xPais;

                    @JacksonXmlProperty(localName = "fone")
                    private String fone;
                }
            }

            @ToString
            @Getter
            @Setter
            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class Dest {
                @JacksonXmlProperty(localName = "CPF")
                private String cpf;

                @JacksonXmlProperty(localName = "xNome")
                private String xNome;

                @JacksonXmlProperty(localName = "enderDest")
                private EnderDest enderDest;

                @JacksonXmlProperty(localName = "indIEDest")
                private String indIEDest;

                @JacksonXmlProperty(localName = "email")
                private String email;

                @ToString
                @Getter
                @Setter
                @JsonIgnoreProperties(ignoreUnknown = true)
                public static class EnderDest {

                    @JacksonXmlProperty(localName = "xLgr")
                    private String xLgr;

                    @JacksonXmlProperty(localName = "nro")
                    private String nro;

                    @JacksonXmlProperty(localName = "xCpl")
                    private String xCpl;

                    @JacksonXmlProperty(localName = "xBairro")
                    private String xBairro;

                    @JacksonXmlProperty(localName = "cMun")
                    private String cMun;

                    @JacksonXmlProperty(localName = "xMun")
                    private String xMun;

                    @JacksonXmlProperty(localName = "UF")
                    private String uf;

                    @JacksonXmlProperty(localName = "CEP")
                    private String cep;

                    @JacksonXmlProperty(localName = "cPais")
                    private String cPais;

                    @JacksonXmlProperty(localName = "xPais")
                    private String xPais;

                    @JacksonXmlProperty(localName = "fone")
                    private String fone;
                }
            }

            @ToString
            @Getter
            @Setter
            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class AutXML {

                @JacksonXmlProperty(localName = "CNPJ")
                private String cnpj;

                @JacksonXmlProperty(localName = "CPF")
                private String cpf;
            }

            @ToString
            @Getter
            @Setter
            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class Det {

                @JacksonXmlProperty(localName = "nItem")
                private String nItem;

                @JacksonXmlProperty(localName = "prod")
                private Prod prod;

                @JacksonXmlProperty(localName = "imposto")
                private Imposto imposto;

                @JacksonXmlProperty(localName = "infAdProd")
                private String infAdProd;

                @ToString
                @Getter
                @Setter
                @JsonIgnoreProperties(ignoreUnknown = true)
                public static class Prod {

                    @JacksonXmlProperty(localName = "cProd")
                    private String cProd;

                    @JacksonXmlProperty(localName = "cEAN")
                    private String cEan;

                    @JacksonXmlProperty(localName = "xProd")
                    private String xProd;

                    @JacksonXmlProperty(localName = "NCM")
                    private String ncm;

                    @JacksonXmlProperty(localName = "cBenef")
                    private String cBenef;

                    @JacksonXmlProperty(localName = "CFOP")
                    private String cfop;

                    @JacksonXmlProperty(localName = "uCom")
                    private String uCom;

                    @JacksonXmlProperty(localName = "qCom")
                    private String qCom;

                    @JacksonXmlProperty(localName = "vUnCom")
                    private String vUnCom;

                    @JacksonXmlProperty(localName = "vProd")
                    private String vProd;

                    @JacksonXmlProperty(localName = "cEANTrib")
                    private String cEanTrib;

                    @JacksonXmlProperty(localName = "uTrib")
                    private String uTrib;

                    @JacksonXmlProperty(localName = "qTrib")
                    private String qTrib;

                    @JacksonXmlProperty(localName = "vUnTrib")
                    private String vUnTrib;

                    @JacksonXmlProperty(localName = "indTot")
                    private String indTot;

                    @JacksonXmlProperty(localName = "xPed")
                    private String xPed;

                    @JacksonXmlProperty(localName = "nItemPed")
                    private String nItemPed;
                }

                @ToString
                @Getter
                @Setter
                @JsonIgnoreProperties(ignoreUnknown = true)
                public static class Imposto {

                    @JacksonXmlProperty(localName = "ICMS")
                    private ICMS icms;

                    @JacksonXmlProperty(localName = "PIS")
                    private PIS pis;

                    @JacksonXmlProperty(localName = "COFINS")
                    private COFINS cofins;

                    @JacksonXmlProperty(localName = "ICMSUFDest")
                    private ICMSUFDest icmsUfDest;

                    @ToString
                    @Getter
                    @Setter
                    @JsonIgnoreProperties(ignoreUnknown = true)
                    public static class ICMS {

                        @JacksonXmlProperty(localName = "ICMS00")
                        private ICMS00 icms00;

                        @ToString
                        @Getter
                        @Setter
                        @JsonIgnoreProperties(ignoreUnknown = true)
                        public static class ICMS00 {

                            @JacksonXmlProperty(localName = "orig")
                            private String orig;

                            @JacksonXmlProperty(localName = "CST")
                            private String cst;

                            @JacksonXmlProperty(localName = "modBC")
                            private String modBC;

                            @JacksonXmlProperty(localName = "vBC")
                            private String vBC;

                            @JacksonXmlProperty(localName = "pICMS")
                            private String pICMS;

                            @JacksonXmlProperty(localName = "vICMS")
                            private String vICMS;
                        }
                    }

                    @ToString
                    @Getter
                    @Setter
                    @JsonIgnoreProperties(ignoreUnknown = true)
                    public static class PIS {

                        @JacksonXmlProperty(localName = "PISAliq")
                        private PISAliq pisAliq;

                        @ToString
                        @Getter
                        @Setter
                        @JsonIgnoreProperties(ignoreUnknown = true)
                        public static class PISAliq {

                            @JacksonXmlProperty(localName = "CST")
                            private String cst;

                            @JacksonXmlProperty(localName = "vBC")
                            private String vBC;

                            @JacksonXmlProperty(localName = "pPIS")
                            private String pPIS;

                            @JacksonXmlProperty(localName = "vPIS")
                            private String vPIS;
                        }
                    }

                    @ToString
                    @Getter
                    @Setter
                    @JsonIgnoreProperties(ignoreUnknown = true)
                    public static class COFINS {

                        @JacksonXmlProperty(localName = "COFINSAliq")
                        private COFINSAliq cofinsAliq;

                        @ToString
                        @Getter
                        @Setter
                        @JsonIgnoreProperties(ignoreUnknown = true)
                        public static class COFINSAliq {

                            @JacksonXmlProperty(localName = "CST")
                            private String cst;

                            @JacksonXmlProperty(localName = "vBC")
                            private String vBC;

                            @JacksonXmlProperty(localName = "pCOFINS")
                            private String pCOFINS;

                            @JacksonXmlProperty(localName = "vCOFINS")
                            private String vCOFINS;
                        }
                    }

                    @ToString
                    @Getter
                    @Setter
                    @JsonIgnoreProperties(ignoreUnknown = true)
                    public static class ICMSUFDest {

                        @JacksonXmlProperty(localName = "vBCUFDest")
                        private String vBCUFDest;

                        @JacksonXmlProperty(localName = "vBCFCPUFDest")
                        private String vBCFCPUFDest;

                        @JacksonXmlProperty(localName = "pFCPUFDest")
                        private String pFCPUFDest;

                        @JacksonXmlProperty(localName = "pICMSUFDest")
                        private String pICMSUFDest;

                        @JacksonXmlProperty(localName = "pICMSInter")
                        private String pICMSInter;

                        @JacksonXmlProperty(localName = "pICMSInterPart")
                        private String pICMSInterPart;

                        @JacksonXmlProperty(localName = "vFCPUFDest")
                        private String vFCPUFDest;

                        @JacksonXmlProperty(localName = "vICMSUFDest")
                        private String vICMSUFDest;

                        @JacksonXmlProperty(localName = "vICMSUFRemet")
                        private String vICMSUFRemet;
                    }
                }
            }

            @ToString
            @Getter
            @Setter
            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class Total {

                @JacksonXmlProperty(localName = "ICMSTot")
                private ICMSTot icmsTot;

                @ToString
                @Getter
                @Setter
                @JsonIgnoreProperties(ignoreUnknown = true)
                public static class ICMSTot {

                    @JacksonXmlProperty(localName = "vBC")
                    private String vBC;

                    @JacksonXmlProperty(localName = "vICMS")
                    private String vICMS;

                    @JacksonXmlProperty(localName = "vICMSDeson")
                    private String vICMSDeson;

                    @JacksonXmlProperty(localName = "vFCPUFDest")
                    private String vFCPUFDest;

                    @JacksonXmlProperty(localName = "vICMSUFDest")
                    private String vICMSUFDest;

                    @JacksonXmlProperty(localName = "vFCP")
                    private String vFCP;

                    @JacksonXmlProperty(localName = "vBCST")
                    private String vBCST;

                    @JacksonXmlProperty(localName = "vST")
                    private String vST;

                    @JacksonXmlProperty(localName = "vFCPST")
                    private String vFCPST;

                    @JacksonXmlProperty(localName = "vFCPSTRet")
                    private String vFCPSTRet;

                    @JacksonXmlProperty(localName = "vProd")
                    private String vProd;

                    @JacksonXmlProperty(localName = "vFrete")
                    private String vFrete;

                    @JacksonXmlProperty(localName = "vSeg")
                    private String vSeg;

                    @JacksonXmlProperty(localName = "vDesc")
                    private String vDesc;

                    @JacksonXmlProperty(localName = "vII")
                    private String vII;

                    @JacksonXmlProperty(localName = "vIPI")
                    private String vIPI;

                    @JacksonXmlProperty(localName = "vIPIDevol")
                    private String vIPIDevol;

                    @JacksonXmlProperty(localName = "vPIS")
                    private String vPIS;

                    @JacksonXmlProperty(localName = "vCOFINS")
                    private String vCOFINS;

                    @JacksonXmlProperty(localName = "vOutro")
                    private String vOutro;

                    @JacksonXmlProperty(localName = "vNF")
                    private String vNF;
                }
            }

            @ToString
            @Getter
            @Setter
            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class Transp {

                @JacksonXmlProperty(localName = "modFrete")
                private String modFrete;

                @JacksonXmlProperty(localName = "transporta")
                private Transporta transporta;

                @JacksonXmlProperty(localName = "vol")
                private Vol vol;

                @ToString
                @Getter
                @Setter
                @JsonIgnoreProperties(ignoreUnknown = true)
                public static class Transporta {

                    @JacksonXmlProperty(localName = "CNPJ")
                    private String cnpj;

                    @JacksonXmlProperty(localName = "xNome")
                    private String xNome;

                    @JacksonXmlProperty(localName = "IE")
                    private String ie;

                    @JacksonXmlProperty(localName = "xEnder")
                    private String xEnder;

                    @JacksonXmlProperty(localName = "xMun")
                    private String xMun;

                    @JacksonXmlProperty(localName = "UF")
                    private String uf;
                }

                @ToString
                @Getter
                @Setter
                @JsonIgnoreProperties(ignoreUnknown = true)
                public static class Vol {

                    @JacksonXmlProperty(localName = "qVol")
                    private String qVol;

                    @JacksonXmlProperty(localName = "esp")
                    private String esp;

                    @JacksonXmlProperty(localName = "nVol")
                    private String nVol;

                    @JacksonXmlProperty(localName = "pesoL")
                    private String pesoL;

                    @JacksonXmlProperty(localName = "pesoB")
                    private String pesoB;

                }
            }

            @ToString
            @Getter
            @Setter
            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class Cobr {

                @JacksonXmlProperty(localName = "fat")
                private Fat fat;

                @JacksonXmlElementWrapper(useWrapping = false)
                @JacksonXmlProperty(localName = "dup")
                private List<Dup> dup;

                @ToString
                @Getter
                @Setter
                @JsonIgnoreProperties(ignoreUnknown = true)
                public static class Fat {

                    @JacksonXmlProperty(localName = "nFat")
                    private String nFat;

                    @JacksonXmlProperty(localName = "vOrig")
                    private String vOrig;

                    @JacksonXmlProperty(localName = "vLiq")
                    private String vLiq;
                }

                @ToString
                @Getter
                @Setter
                @JsonIgnoreProperties(ignoreUnknown = true)
                public static class Dup {

                    @JacksonXmlProperty(localName = "nDup")
                    private String nDup;

                    @JacksonXmlProperty(localName = "dVenc")
                    private String dVenc;

                    @JacksonXmlProperty(localName = "vDup")
                    private String vDup;
                }
            }

            @ToString
            @Getter
            @Setter
            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class Pag {

                @JacksonXmlElementWrapper(useWrapping = false)
                @JacksonXmlProperty(localName = "detPag")
                private List<DetPag> detPag;

                @ToString
                @Getter
                @Setter
                @JsonIgnoreProperties(ignoreUnknown = true)
                public static class DetPag {

                    @JacksonXmlProperty(localName = "indPag")
                    private String indPag;

                    @JacksonXmlProperty(localName = "tPag")
                    private String tPag;

                    @JacksonXmlProperty(localName = "vPag")
                    private String vPag;
                }
            }

            @ToString
            @Getter
            @Setter
            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class InfAdic {

                @JacksonXmlProperty(localName = "infCpl")
                private String infCpl;
            }

            @ToString
            @Getter
            @Setter
            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class InfRespTec {

                @JacksonXmlProperty(localName = "CNPJ")
                private String cnpj;

                @JacksonXmlProperty(localName = "xContato")
                private String xContato;

                @JacksonXmlProperty(localName = "email")
                private String email;

                @JacksonXmlProperty(localName = "fone")
                private String fone;
            }
        }

        @ToString
        @Getter
        @Setter
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Signature {
            @JacksonXmlProperty(localName = "SignedInfo")
            private SignedInfo signedInfo;

            @JacksonXmlProperty(localName = "SignatureValue")
            private String signatureValue;

            @JacksonXmlProperty(localName = "KeyInfo")
            private KeyInfo keyInfo;

            @ToString
            @Getter
            @Setter
            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class SignedInfo {

                @JacksonXmlProperty(localName = "CanonicalizationMethod")
                private CanonicalizationMethod canonicalizationMethod;

                @JacksonXmlProperty(localName = "SignatureMethod")
                private SignatureMethod signatureMethod;

                @JacksonXmlElementWrapper(useWrapping = false)
                @JacksonXmlProperty(localName = "Reference")
                private List<Reference> references;

                @ToString
                @Getter
                @Setter
                @JsonIgnoreProperties(ignoreUnknown = true)
                public static class CanonicalizationMethod {

                    @JacksonXmlProperty(localName = "Algorithm")
                    private String algorithm;

                }

                @ToString
                @Getter
                @Setter
                @JsonIgnoreProperties(ignoreUnknown = true)
                public static class SignatureMethod {

                    @JacksonXmlProperty(localName = "Algorithm")
                    private String algorithm;
                }

                @ToString
                @Getter
                @Setter
                @JsonIgnoreProperties(ignoreUnknown = true)
                public static class Reference {

                    @JacksonXmlProperty(localName = "URI")
                    private String uri;

                    @JacksonXmlElementWrapper(useWrapping = false)
                    @JacksonXmlProperty(localName = "Transforms")
                    private List<Transform> transforms;

                    @JacksonXmlProperty(localName = "DigestMethod")
                    private DigestMethod digestMethod;

                    @JacksonXmlProperty(localName = "DigestValue")
                    private String digestValue;

                    @ToString
                    @Getter
                    @Setter
                    @JsonIgnoreProperties(ignoreUnknown = true)
                    public static class Transform {

                        @JacksonXmlProperty(localName = "Algorithm")
                        private String algorithm;
                    }

                    @ToString
                    @Getter
                    @Setter
                    @JsonIgnoreProperties(ignoreUnknown = true)
                    public static class DigestMethod {

                        @JacksonXmlProperty(localName = "Algorithm")
                        private String algorithm;

                    }
                }
            }

            @ToString
            @Getter
            @Setter
            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class KeyInfo {

                @JacksonXmlProperty(localName = "X509Data")
                private X509Data x509Data;

                @ToString
                @Getter
                @Setter
                @JsonIgnoreProperties(ignoreUnknown = true)
                public static class X509Data {
                    @JacksonXmlProperty(localName = "X509Certificate")
                    private String x509Certificate;
                }
            }
        }


    }

    @ToString
    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ProtNFe {

        @JacksonXmlProperty(localName = "infProt")
        private InfProt infProt;

        @ToString
        @Getter
        @Setter
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class InfProt {

            @JacksonXmlProperty(localName = "tpAmb")
            private String tpAmb;

            @JacksonXmlProperty(localName = "verAplic")
            private String verAplic;

            @JacksonXmlProperty(localName = "chNFe")
            private String chNFe;

            @JacksonXmlProperty(localName = "dhRecbto")
            private String dhRecbto;

            @JacksonXmlProperty(localName = "nProt")
            private String nProt;

            @JacksonXmlProperty(localName = "digVal")
            private String digVal;

            @JacksonXmlProperty(localName = "cStat")
            private String cStat;

            @JacksonXmlProperty(localName = "xMotivo")
            private String xMotivo;
        }
    }
}
