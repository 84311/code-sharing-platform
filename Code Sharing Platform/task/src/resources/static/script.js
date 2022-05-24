function send() {
    const editor = ace.edit("editor");
    const timeInput = document.getElementById("time_restriction");
    const viewsInput = document.getElementById("views_restriction");

    const object = {
        "code": editor.getValue(),
        "time": timeInput.value,
        "views": viewsInput.value
    };

    let json = JSON.stringify(object);

    let xhr = new XMLHttpRequest();
    xhr.open("POST", '/api/code/new', false)
    xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
    xhr.send(json);

    if (xhr.status == 200) {
        alert("Success!");
    }
}