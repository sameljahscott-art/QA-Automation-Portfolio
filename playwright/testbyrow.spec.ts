import { test, expect } from '@playwright/test';

test('Navigate to Docs and verify Introduction page', async ({ page }) => {
  await page.goto('https://playwright.dev/');
  await page.getByRole('link', { name: 'Docs' }).click();

  const heading = page.getByRole('heading', { name: 'Introduction' });
  await expect(heading).toBeVisible();
});


