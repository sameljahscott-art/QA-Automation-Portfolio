describe('Verify Homepage Loads Correctly', () => {
    beforeEach(() => {
      
      cy.visit('https://formstack.com')
    })
    it('Check that the page title contains "Formstack', () => {
        cy.get('#navLogoBlack')
      .invoke('text').then((text)=>{
       cy.log(text)
      })
    })
})