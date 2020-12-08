const MENSAJE_ERROR = function(title, text) {
    swal.fire({
        "title": title,
        "text": text,
        "type": "error",
        "closeOnConfirm": false
    });
}

const MENSAJE_SUCCESS = function(title, text, textButton, action) {
    swal.fire({
        "title": title,
        "text": text,
        "type": "success",
        "confirmButtonClass": "btn btn-success",
        "confirmButtonText": textButton,
        "closeOnConfirm": false

    }).then((result) => {
        location.href = action;
    });
}