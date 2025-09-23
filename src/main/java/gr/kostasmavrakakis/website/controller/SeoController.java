package gr.kostasmavrakakis.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.lang.StringBuilder;

@Controller
public class SeoController {

    @Value("${site.base.url}")
    private String baseUrl; 

    @Value("${site.lastmod}")
    private String lastmod; 

    public SeoController() {}

    @GetMapping(value = "/robots.txt", produces = "text/plain")
    public ResponseEntity<String> getRobotsTxt() {

        String content = """
               User-agent: *
               Disallow:

               Sitemap: %s/sitemap.xml
               """.formatted(baseUrl);

        return ResponseEntity
                .ok()
                .header("Cache-Control", "max-age=86400")
                .body(content);
    }

    @GetMapping(value = "/sitemap.xml", produces = "application/xml")
    public ResponseEntity<String> getSitemapXml() {
        List<String> paths = List.of(
                "/",
                "/en",
                "/cv",
                "/en/cv",
                "/approach",
                "/en/approach",
                "/trauma_therapy",
                "/en/trauma_therapy",
                "/contact",
                "/en/contact"
        );

        StringBuilder sb = new StringBuilder();
        sb.append("""
                <?xml version="1.0" encoding="UTF-8"?>
                <urlset xmlns="http://www.sitemaps.org/schemas/sitemap/0.9">
                """);

        for (String path : paths) {
            sb.append("<url>")
              .append("<loc>").append(baseUrl).append(path).append("</loc>")
              .append("<priority>").append("1.0").append("</priority>")
              .append("<lastmod>").append(lastmod).append("</lastmod>")
              .append("<changefreq>").append("yearly").append("</changefreq>")
              .append("</url>");
        }

        sb.append("</urlset>");

        String content = sb.toString();

        return ResponseEntity
                .ok()
                .header("Cache-Control", "max-age=86400")
                .body(content);
    }
}

// submit sitemap.xml to google search console?