import { CatalogNgPage } from './app.po';

describe('catalog-ng App', function() {
  let page: CatalogNgPage;

  beforeEach(() => {
    page = new CatalogNgPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
