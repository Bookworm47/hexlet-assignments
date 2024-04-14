@import exercise.dto.users.UserPage
@param UserPage userPage

<!-- Для наглядности мы не стали включать сюда основную структуру HTML -->
<main>
    <table>
        <thead>
        <tr>
            <th>id</th>
            <th>first_name</th>
            <th>last_name</th>
            <th>email</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${userPage.getUser().getId()}</td>
            <td>${userPage.getUser().getFirstName()}</td>
            <td>${userPage.getUser().getLastName()}</td>
            <td>${userPage.getUser().getEmail()}</td>
        </tr>
        </tbody>
    </table>
</main>