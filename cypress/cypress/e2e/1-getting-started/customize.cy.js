describe('Checking registration and login', () => {

  beforeEach(()=>
  {
      cy.visit("https://migropa.com/client-area/")

  })

  it("verifies if singin works", () => {
      cy.get(".text-primary").click()
      cy.get("form > div.mb-4.flex.gap-4 > div:nth-child(1) > div > input")
      .type("Rose")

      cy.get("form > div.mb-4.flex.gap-4 > div:nth-child(2) > div > input")
      .type("Rose")
      
      cy.get("form > div:nth-child(2) > div > input")
      .type("Rose9@gmail.com")

      cy.get("form > div:nth-child(3) > div > input")
      .type("123456")

      cy.get("form > div:nth-child(4) > div > input")
      .type("123456")

      cy.get("form > div:nth-child(5) > div > input")
      .type("1995-02-02")

      cy.get("#rfs-btn").click()
      cy.get("#rfs-DZ").click()
      cy.get("form > div.mb-5 > input").click()
      cy.get("div > h2").should("have.text","Sign Up to Migropa")
    
      
  })

  it("verifies if Login works for email", () => {
    cy.get('.mb-4 > .relative > .w-full').type("Rose10@gmail.com")
    .wait(1000)  // Adjust wait time as needed, or wait for a specific element to appear
    .should('have.value', 'Rose10@gmail.com');  // Ensure text remains in the input
    cy.get('.mb-6 > .relative > .w-full').type("michelle9089s")
    .wait(1000)
    .should('have.value', 'michelle9089s');
    cy.get("form > div.mb-6 > div > input").invoke("attr","type").then((type)=>
      {
        expect(type).to.equal('password'); 
      })
    cy.get('.bg-primary').click()
  })

 
  })

 







