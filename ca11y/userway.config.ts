import { Config } from '@userway/cicd-core';

export default {
    token: process.env.USERWAY_TOKEN,
    organization: 'volodymyr-kulyk-userway-org-igpgx',
    project: 'a11y-selenium-java-sample',
    reportPaths: ['./ca11y/uw-a11y-reports'],
    // reportPaths: ['./uw-a11y-reports'], - for managed scans
    server: "https://api.qa.userway.dev",
    override: {
        'br1': {
            targetBranch: 'main',
            retention: 'long',
            scope: 'overall',
        },
    }
} satisfies Config;
