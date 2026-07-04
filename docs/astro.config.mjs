// @ts-check
import { defineConfig } from 'astro/config';
import starlight from '@astrojs/starlight';
import starlightLlmsTxt from 'starlight-llms-txt';

// https://astro.build/config
export default defineConfig({
	site: 'https://rocketbase-io.github.io',
	base: '/email-template-builder',
	integrations: [
		starlight({
			title: 'email-template-builder',
			description:
				'Fluent Java builder for beautiful, responsive HTML + text emails — based on the battle-tested Postmark templates.',
			logo: {
				light: './src/assets/logomark.svg',
				dark: './src/assets/logomark-dark.svg',
			},
			social: [
				{
					icon: 'github',
					label: 'GitHub',
					href: 'https://github.com/rocketbase-io/email-template-builder',
				},
			],
			editLink: {
				baseUrl: 'https://github.com/rocketbase-io/email-template-builder/edit/master/docs/',
			},
			tableOfContents: false,
			plugins: [
				starlightLlmsTxt({
					description:
						'Fluent Java builder for responsive HTML + text emails, based on the Postmark templates. Maven: io.rocketbase.mail:email-template-builder',
				}),
			],
			customCss: ['./src/styles/custom.css'],
			sidebar: [
				{
					label: 'Start Here',
					items: ['getting-started'],
				},
				{
					label: 'Guides',
					items: ['builder-api', 'configuration', 'custom-table', 'markdown'],
				},
				{
					label: 'Examples',
					items: ['examples'],
				},
				{ label: 'Releases', link: '/releases/' },
			],
		}),
	],
});
