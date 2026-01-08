import { test, expect, chromium } from '@playwright/test';

test('Close cookies banner and navigate to pricing', async ({ page }) => {
  await page.goto('https://www.udemy.com/');
  
  // Example interaction
  await page.getByTestId('plans-and-pricing-page-link').click();
});

test('Verify cookies banner presence', async ({ page }) => {
  await page.goto('https://www.udemy.com/');
  
  // Pause for debugging during test development
  await page.pause();
});

test('Use browser fixture to create context', async ({ browser }) => {
  const context = await browser.newContext();
  const page = await context.newPage();
  
  await page.goto('https://www.udemy.com/');
});

test('Manually launch Chromium browser', async () => {
  const browser = await chromium.launch();
  const context = await browser.newContext();
  const page = await context.newPage();
  
  await page.goto('https://www.udemy.com/');
  await browser.close();
});
