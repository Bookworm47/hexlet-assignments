@import exercise.dto.users.UsersPage
@param UsersPage page

@template.layout.page(
content = @`
    @if (page.getUsers().isEmpty())
        <p>Пока не добавлено ни одного пользователя</p>
    @else
        @for(var user : page.getUsers())
            <div class="div class="mx-auto p-4 py-md-5">
            <h2><a href="/users/${user.getId()}">${user.getFirstName()} ${user.getLastName()}</a></h2>
            </div>
        @endfor
    @endif
`)