
function sendMessage() {
    let formSend = document.getElementById("submitform");
    let fieldText = document.getElementById("message")
    let fieldPost = document.getElementById("productid");

    formSend.addEventListener('submit', function(event) {
        event.preventDefault();

        let listUsers = document.getElementsByClassName("list-users");
        let activeItem = listUsers.item(0);
        for (let i = 0; i < listUsers.length; i++) {
            if (listUsers.item(i).classList.contains("active")) {
                console.log("Item clicked: " + i);
                activeItem = listUsers.item(i);
            }
        }
        let fieldUser = activeItem.getAttribute("userid");

        var request = new XMLHttpRequest();
        var url = "/rest/message/send";
        var data = JSON.stringify({"text": fieldText.value, "productId": fieldPost.value, "userId": fieldUser});

        request.open("POST", url, true);
        request.setRequestHeader("Content-type", "application/json");
        request.send(data);

        request.addEventListener("readystatechange", () => {
            if (request.readyState === 4 && request.status === 200) {
                loadChatByUser(fieldUser, fieldPost.value);
                fieldText.value = "";
            }
        })
    })
}

function showMessages(messages) {
    let messageField = document.getElementById("message-field");
    let currentUserId = messageField.getAttribute("useridcurrent");
    messageField.textContent = "";

    messages.forEach(e => {
        let dateString = e.date.replace("T"," ");

        let messageItem = document.createElement("div");
        messageItem.classList.add("message-item");
        if (e.user.id != currentUserId) {
            messageItem.classList.add("message-item-green");
        } else {
            messageItem.classList.add("message-item-blue");
        }

        let messageUser = document.createElement("p");
        messageUser.className = "message-user";
        messageUser.innerHTML = e.user.name;
        let messageDate = document.createElement("p");
        messageDate.className = "message-date";
        messageDate.innerHTML = dateString;
        let messageText = document.createElement("span")
        messageText.className = "message-text";
        messageText.innerHTML = e.text;

        messageItem.appendChild(messageUser);
        messageItem.appendChild(messageDate);
        messageItem.appendChild(messageText);

        messageField.appendChild(messageItem);
    })

    messageField.scrollTop = messageField.scrollHeight;
}

function showSelectedChat() {
    // console.log("Show message");
    let listUsers = document.getElementsByClassName("list-users");
    for (let i = 0; i < listUsers.length; i++) {
        listUsers.item(i).addEventListener("click", function() {
            // console.log("click event")
            let userId = listUsers.item(i).getAttribute("userid");
            let productId = document.getElementById("productid").value;
            let currentUser = listUsers.item(i);

            showSelectedItem(currentUser);
            loadChatByUser(userId, productId);
        })
    }
}

function showSelectedItem(currentItem) {
    let listUsers = document.getElementsByClassName("list-users");
    for (let i = 0; i < listUsers.length; i++) {
        listUsers.item(i).classList.remove("list-group-item-primary");
        listUsers.item(i).classList.remove("active");
    }

    currentItem.classList.add("list-group-item-primary");
    currentItem.classList.add("active");
}

function loadChatByUser(userId, productId) {
    var request = new XMLHttpRequest();
    var url = "/rest/message/chattwo";
    var data = JSON.stringify({
        "post": productId,
        "user": userId
    });

    request.open("POST", url, true);
    request.setRequestHeader("Content-type", "application/json");
    request.send(data);

    request.addEventListener("readystatechange", () => {
        if (request.readyState === 4 && request.status === 200) {
            let messages = JSON.parse(request.responseText);
            showMessages(messages)
        } else if (request.readyState == 4 && request.status === 404) {
            let field = document.getElementById("message-field");
            field.textContent = "";
        }
    })
}

function showChatOnOpen() {
    let listUsers = document.getElementsByClassName("list-users");
    let activeItem = listUsers.item(0);
    let userId = activeItem.getAttribute("userid");
    let productId = document.getElementById("productid").value;

    loadChatByUser(userId, productId);
}

showSelectedChat();
sendMessage();
showChatOnOpen();