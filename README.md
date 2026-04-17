# Finding Hospitals - Practo Automation (v1.0.1)

## Why your run failed earlier
Practo moved the **Corporate Wellness** page under the `plus.practo.com` domain. This version opens:
- `https://plus.practo.com/plus/corporate`

It also adds more resilient locators and scrolling for dynamic sections.

## Run
```bash
mvn test
```

## Reports
- Cucumber: `target/cucumber-reports.html`
- Extent: `target/extent-report.html`
- Screenshots: `target/screenshots/`
