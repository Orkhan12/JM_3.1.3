/**
 *
 * */
$(document).ready(function () {
    $('.eBtn').on('click', function (event) {
        event.preventDefault();
        let href = $(this).attr('href');

        $.get(href, function (user, status) {
            $('.myForm #id').val(user.id)
            $('.myForm #name').val(user.name)
            $('.myForm #lastName').val(user.lastName)
            $('.myForm #age').val(user.age)
            $('.myForm #email').val(user.email)

        });
        $('.myForm #exampleModal').modal();
    });
});