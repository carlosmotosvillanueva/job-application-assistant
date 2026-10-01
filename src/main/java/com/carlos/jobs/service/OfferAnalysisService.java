package com.carlos.jobs.service;

import com.carlos.jobs.model.OfferAnalysis;
import com.carlos.jobs.model.OfferRequest;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.Locale;

@Service
public class OfferAnalysisService {
    public OfferAnalysis analyze(OfferRequest offer) {
        String text = normalize(offer.title() + " " + offer.description());
        String language = chooseLanguage(offer.language(), text);
        String cv = chooseCv(text);
        String focus = cv.equals("JAVA")
                ? "Java, Spring Boot, SQL, ERP, bases de datos, resolución de incidencias y Git"
                : "PHP, WordPress, Drupal, JavaScript, Node.js, APIs, CMS y bases de datos";
        return new OfferAnalysis(language, cv,
                "Prioriza " + focus + ". Revisa también los requisitos obligatorios de la oferta.",
                buildLetter(offer, language, cv),
                "Borrador preparado: revisa la oferta, el CV y la carta antes de enviarlos. La aplicación no envía candidaturas automáticamente.");
    }

    private String chooseLanguage(String requested, String text) {
        if (requested != null && (requested.equalsIgnoreCase("es") || requested.equalsIgnoreCase("en"))) {
            return requested.toLowerCase(Locale.ROOT);
        }
        String[] markers = {" el ", " la ", " para ", " experiencia ", " desarrollo ", " empresa ", " requisitos ", " ofrecemos "};
        int spanish = 0;
        String padded = " " + text + " ";
        for (String marker : markers) if (padded.contains(marker)) spanish++;
        return spanish >= 2 ? "es" : "en";
    }

    private String chooseCv(String text) {
        String[] webMarkers = {"wordpress", "drupal", "php", "magento", "prestashop", "cms", "frontend", "front end"};
        for (String marker : webMarkers) if (text.contains(marker)) return "WEB";
        return "JAVA";
    }

    private String buildLetter(OfferRequest offer, String language, String cv) {
        if (language.equals("es")) {
            return "Asunto: Candidatura para " + offer.title() + "\n\n" +
                    "Estimado equipo de selección:\n\n" +
                    "Me gustaría presentar mi candidatura para el puesto de " + offer.title() + " en " + offer.company() + ". " +
                    "Soy desarrollador de software con experiencia en aplicaciones empresariales, backend, bases de datos y resolución de incidencias.\n\n" +
                    "Mi experiencia más relevante incluye el desarrollo y mantenimiento de software ERP con Java y SQL, integración de bases de datos SQL y NoSQL y colaboración con equipos de desarrollo y soporte. También cuento con experiencia en " + (cv.equals("JAVA") ? "Spring Boot, APIs y Git" : "PHP, WordPress, Drupal, JavaScript y soluciones CMS") + ".\n\n" +
                    "He completado el curso Spring Framework Essentials de Broadcom Spring Academy y estoy preparado para seguir creciendo en proyectos modernos y mantenibles. Me gustaría conocer mejor las necesidades de su equipo y explicar cómo puedo contribuir.\n\n" +
                    "Un saludo,\n\nCarlos Motos Villanueva";
        }
        return "Subject: Application for " + offer.title() + "\n\n" +
                "Dear Hiring Team,\n\n" +
                "I am writing to apply for the " + offer.title() + " position at " + offer.company() + ". I am a software developer with experience in enterprise applications, backend development, databases and incident resolution.\n\n" +
                "My most relevant experience includes developing and maintaining ERP software with Java and SQL, integrating SQL and NoSQL databases, and collaborating with development and technical support teams. I also have experience with " + (cv.equals("JAVA") ? "Spring Boot, APIs and Git" : "PHP, WordPress, Drupal, JavaScript and CMS solutions") + ".\n\n" +
                "I have completed the Spring Framework Essentials course at Broadcom Spring Academy and I am motivated to keep growing through modern, maintainable software projects. I would welcome the opportunity to discuss how I could contribute to your team.\n\n" +
                "Kind regards,\n\nCarlos Motos Villanueva";
    }

    private String normalize(String value) {
        return Normalizer.normalize(value == null ? "" : value, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "").toLowerCase(Locale.ROOT);
    }
}
