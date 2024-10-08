var checkMatch = function () {
    let password = document.getElementById('password').value
    let passwordConfirmation = document.getElementById('passwordConfirmation').value
    if (password === passwordConfirmation) {
        document.getElementById('message').style.color = 'green';
        document.getElementById('message').innerHTML = 'Password match';
    } else {
        document.getElementById('message').style.color = 'red';
        document.getElementById('message').innerHTML = 'Password not match';
    }
}
addEventListener("change", checkMatch)

var checkLength = function () {
    let password = document.getElementById('password').value
    if (password.length < 8) {
        document.getElementById('validate').style.color = 'red';
        document.getElementById('validate').innerHTML = 'Min. length 8 characters'
    } else {
        document.getElementById('validate').style.color = 'green';
        document.getElementById('validate').innerHTML = 'Min. length 8 characters'
    }
}
addEventListener("change", checkLength)

var submitCheck = function () {
    let password = document.getElementById('password').value
    let passwordConfirmation = document.getElementById('passwordConfirmation').value
    let validateUsername = document.getElementById('username-status').textContent
    let button = document.getElementById('submitButton')
    if (password === passwordConfirmation && password.length >= 8 && validateUsername !== 'Username already taken.') {
        button.disabled = false
    } else {
        button.disabled = true
    }
}
addEventListener("pointerover", submitCheck)


$(document).ready(function () {
    $("#username").on("input", function () {
        var username = $(this).val();
        if (username.length > 0) {
            $.ajax({
                url: 'http://localhost:8080/api/check-username',
                type: 'GET',
                data: {username: username},
                success: function (response) {
                    $("#username-status").text(response.message);
                    if ($("#username-status").text() === "Username already taken.") {
                        $("#username-status").css("color", "red");
                        $("#username-status").removeClass("available").addClass("taken");
                        $("#submitButton").prop("disabled", true);
                    } else {
                        $("#username-status").css("color", "green");
                        $("#username-status").removeClass("taken").addClass("available");
                        $("#submitButton").prop("disabled", false);
                    }
                },
                error: function () {
                    $("#username-status").text("Error checking username.");
                }
            });
        }
    });
});