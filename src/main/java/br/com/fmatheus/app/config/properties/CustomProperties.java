package br.com.fmatheus.app.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "custom")
public class CustomProperties {

    private Upload upload;

    @Getter
    @Setter
    public static class Upload {
        private String pathRoot;
        private Files files;

        @Getter
        @Setter
        public static class Files {
            private Danfe danfe;
            private Jasper jasper;

            @Getter
            @Setter
            public static class Danfe {
                private String path;
                private String extension;
            }

            @Getter
            @Setter
            public static class Jasper {
                private Invoice invoice;

                @Getter
                @Setter
                public static class Invoice {
                    private String path;
                    private String fileName;
                }
            }
        }
    }
}
