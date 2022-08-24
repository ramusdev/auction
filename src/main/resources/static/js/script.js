
var buttonObj = document.getElementsByClassName("btnsubmit");
var button = buttonObj[0];

var fieldText = document.getElementById("message")
var fieldPost = document.getElementById("productid");

// var postId = button.dataset.postid;
// var data = "message=" + fieldObj[0].value;

/*
request.addEventListener("readystatechange", () => {
    if (request.readyState === 4 && request.status === 200) {
        container.textContent = request.responseText;
    }
})
*/

button.addEventListener('click', function(event) {
    event.preventDefault();

    var request = new XMLHttpRequest();
    var url = "/rest/message/send";
    var data = JSON.stringify({"text": fieldText.value, "product": fieldPost.value});

    request.open("POST", url, true);
    request.setRequestHeader("Content-type", "application/json");
    request.send(data);

    request.addEventListener("readystatechange", () => {
        if (request.readyState === 4 && request.status === 200) {
            getMessage();
        }
    })

})

function getMessage() {
    console.log("Inside getMessage js");

    var request = new XMLHttpRequest();
    var url = "/rest/message/get";

    request.open("GET", url, true);
    request.setRequestHeader("Content-type", "application/json");
    request.send();

    request.addEventListener("readystatechange", () => {
        if (request.readyState === 4 && request.status === 200) {
            console.log("Request with text: " + request.responseText)
            let messages = JSON.parse(request.responseText);
            showMessages(messages);

            let messageField = document.getElementById("message-field");
            messageField.scrollTop = messageField.scrollHeight;

            fieldText.value = "";
        }
    })
}

function showMessages(messages) {
    let messageField = document.getElementById("message-field");
    messageField.textContent = "";

    messages.forEach(e => {
        let messageItem = document.createElement("div");
        messageItem.className = "message-item";
        let messageDate = document.createElement("p");
        messageDate.className = "message-date";
        messageDate.innerHTML = e.user.name + " " + e.date;
        let messageText = document.createElement("span")
        messageText.className = "message-text badge bg-success";
        messageText.innerHTML = e.text;

        messageItem.appendChild(messageDate);
        messageItem.appendChild(messageText);

        messageField.appendChild(messageItem);
    })
}

// getMessage();