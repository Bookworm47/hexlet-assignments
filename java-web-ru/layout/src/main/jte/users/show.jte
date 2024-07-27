@import exercise.dto.users.UserPage
@param UserPage page
@template.layout.page(
content = @`
    <h2>${page.getUser().getFirstName()} ${page.getUser().getLastName()}</h2>
    <h3>Email: ${page.getUser().getEmail()}</h3>
`
)