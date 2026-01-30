
import { test, expect } from '@playwright/test';

test('validate first 100 articles are sorted from newest to oldest', async ({ page }) => {
  // 1️ Go to your target site
  await page.goto('https://news.ycombinator.com/newest');

  // 2️ Extract the first 100 post timestamps
  const timestamps = await page.$$eval('.age', els =>
    els.slice(0, 100).map(el => new Date(el.getAttribute('title')).getTime())
  );

  // 3️ Sort timestamps from newest → oldest
  const sorted = [...timestamps].sort((a, b) => b - a);

  // 4️ Assert that timestamps are already sorted
  expect(timestamps).toEqual(sorted);

  console.log('Timestamps are sorted from newest to oldest.');
});
