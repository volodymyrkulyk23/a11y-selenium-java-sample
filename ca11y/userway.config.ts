import { Config } from '@userway/cicd-core';

export default {
    token: process.env.USERWAY_TOKEN,
    organization: '<ORGANIZATION_SLUG>',
    project: '<PROJECT_SLUG>',
    reportPaths: ['./ca11y/uw-a11y-reports'],
    server: "https://userway.org",
    override: {
        'main': {
            retention: 'long',
            scope: 'overall',
        },
    }
} satisfies Config;