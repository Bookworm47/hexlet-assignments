@import exercise.dto.users.UsersPage
@param UsersPage usersPage

<html>
<head>
    <title>Хекслет</title>
</head>
<body>
<h1>Все зарегистрированные пользователи</h1>
@if(usersPage.getUsers().isEmpty())
    <p>Пока нет ни одного пользователя</p>
@else
    @for(var user : usersPage.getUsers())
        <div>
            <h2><a href="/users/${user.getId()}">${user.getId()}</a></h2>
            <p>${user.getFirstName()}</p>
            <p>${user.getLastName()}</p>
            <p>${user.getEmail()}</p>
        </div>
    @endfor
@endif
</body>
</html>