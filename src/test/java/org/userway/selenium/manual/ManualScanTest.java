package org.userway.selenium.manual;

import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.userway.selenium.AccessibilityAuditor;
import org.userway.selenium.model.config.AnalysisConfig;
import org.userway.selenium.model.config.AuditConfig;
import org.userway.selenium.model.report.AnalysisLevel;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

public class ManualScanTest {

    private static WebDriver driver;

    private static final String REPORTS_PATH = "." + File.separator + "ca11y" + File.separator + "uw-a11y-reports";

    @BeforeAll
    public static void setup() {
        var options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @AfterAll
    @SneakyThrows
    public static void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Should scan page and save report")
    void shouldScanPageAndSaveReport() {
        // Open page
        driver.get("https://google.com");

        // Prepare analysis configuration
        var analysisConfig = AnalysisConfig.builder()
                .level(AnalysisLevel.AAA)
                .includeBestPractices(true)
                .includeExperimental(true)
                .build();

        var auditConfig = AuditConfig.builder()
                .driver(driver)
                .analysisConfiguration(analysisConfig)
                .saveReport(true)
                .reportPath(REPORTS_PATH)
                .build();

        // Execute analysis on the page
        var result = AccessibilityAuditor.userwayAnalysis(auditConfig);

        // Do assertions
        assertThat(result.getCountA()).isNotZero();
        assertThat(result.getCountAA()).isNotZero();
        assertThat(result.getCountAAA()).isNotZero();

        var jsonDir = new File(REPORTS_PATH + File.separator +"reports");
        assertThat(jsonDir).exists();
        assertThat(jsonDir).isDirectory();
        assertThat(jsonDir.list()).isNotEmpty();

        var htmlDir = new File(REPORTS_PATH + File.separator + "pages");
        assertThat(htmlDir).exists();
        assertThat(htmlDir).isDirectory();
        assertThat(htmlDir.list()).isNotEmpty();

        var screenshotsDir = new File(REPORTS_PATH + File.separator + "pages");
        assertThat(screenshotsDir).exists();
        assertThat(screenshotsDir).isDirectory();
        assertThat(screenshotsDir.list()).isNotEmpty();
    }

}
