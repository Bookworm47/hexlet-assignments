@import exercise.dto.users.UsersPage
@param UsersPage page

@template.layout.page(
    content = @`
<form action="/users" method="get">
    <input type="search" name="term" value="${page.getTerm()}"/>
    <input type="submit" value="Искать">
</form>
    @if (page.getUsers().isEmpty())
        <p>Не найдено ни одного пользователя</p>
    @else
        @for (var user : page.getUsers())
            <div>
                <ul>
                    <li>${user.getFirstName()} ${user.getLastName()}</li>
                </ul>
            </div>
            @endfor
        @endif
    `
)
